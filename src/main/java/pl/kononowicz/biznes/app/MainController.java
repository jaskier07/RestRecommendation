/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/reccommendation")
public class MainController {

        DataModel model = null;
        @Autowired
        private IProductCommentService prodcomService;


        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @RequestMapping("/geteasybyid/{id}")
        public @ResponseBody
        ArrayList<Integer> getProductRecommendation(@PathVariable("id") int id)  {
                int items = 3;
                double sim = 0.1;
                
                //List<ProductComment> prodComList = prodcomService.getAllProductComments();
                List<CountedUserRatings> userRatings = prodcomService.countUserRatings();
                
                //TUTAJ dodaj tablicę preferencji do tego niżej
                FastByIDMap<PreferenceArray> userData = new FastByIDMap<>();
                int mapCounter = 0;
                for (CountedUserRatings cur : userRatings) {
                        GenericUserPreferenceArray userArray = new GenericUserPreferenceArray(cur.ratings);
                        List<ProductComment> prodComList = prodcomService.getProductsCommentsByCustomerId(cur.id_customer);
                        
                        int productCounter = 0;
                        for (ProductComment prodcom : prodComList) {                            
                                Preference p = new GenericPreference(prodcom.id_customer, prodcom.id_product, prodcom.grade);
                                userArray.set(productCounter++, p);
                         }             
                        
                        userData.put(cur.id_customer, userArray);
                }
                
                
                this.model = new GenericDataModel(userData);
//                
                ArrayList<Integer> recommendedProducts = new ArrayList<>();
                
                UserSimilarity similarity = null;
                try {
                        similarity = new PearsonCorrelationSimilarity(model);
                } catch (TasteException ex) {
                        System.out.println("NIEPOPRAWNE PRZEKAZANIE DANYCH DO MAHOUTA.");
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                UserNeighborhood neighborhood = new ThresholdUserNeighborhood(sim, similarity, model);
                UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

                List recommendations = null;
                try {
                        recommendations = recommender.recommend(id, items);
                } catch (TasteException ex) {
                        System.out.println("NIE MA UŻYTKOWNIKA O ID ." + id);
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Recommendation for " + id);
                for (Object recommendation : recommendations) {
                        saveRecommendation(recommendation, recommendedProducts);
              }

                return recommendedProducts;
        }

        private void saveRecommendation(Object recommendation, ArrayList<Integer> array) {
                
                        System.out.println(recommendation);
                        String recomString = recommendation.toString();
                        recomString = recomString.substring(recomString.indexOf(':') + 1, recomString.lastIndexOf(','));
                        int recommendedIndex = Integer.parseInt(recomString);
                        array.add(recommendedIndex);
        }
}
