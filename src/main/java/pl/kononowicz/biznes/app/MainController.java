/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import static org.hibernate.jpa.AvailableSettings.PERSISTENCE_UNIT_NAME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/reccommendation")
public class MainController {
        // This means to get the bean called userRepository
        // Which is auto-generated by Spring, we will use it to handle the data
        DataModel model;

        @Autowired
        private UserRepository userRepository;
        //private AuthorRepository authorRepository;

        public MainController() throws IOException {
                String projectPath = System.getProperty("user.dir");
                this.model = new FileDataModel(new File(projectPath + "\\dataset.csv"));
        }

        @GetMapping(path = "/add") // Map ONLY GET Requests
        public @ResponseBody
        String addNewUser(@RequestParam String name) {
                // @ResponseBody means the returned String is the response, not a view name

                User n = new User(name, 10);
                userRepository.save(n);

                return "Saved";
        }

        @GetMapping(path = "/all")
        public @ResponseBody
        Iterable<User> getAllUsers() {
                // This returns a JSON or XML with the users
                return userRepository.findAll();
        }

        @RequestMapping("/user/{id}")
        public @ResponseBody
        User getUserById(@PathVariable("id") int id) {
                // This returns a JSON or XML with the users
                //return authorRepository.findAll();

                User user = userRepository.findOne(id);
                return user;
        }

        @RequestMapping("/getbyid/{id}&{items}&{similarity}")
        public @ResponseBody
        User getRecommendation(@PathVariable("id") int id, @PathVariable("items") int items, @PathVariable("similarity") Double sim) throws TasteException {
                //int howManyItems = 3;

                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                UserNeighborhood neighborhood = new ThresholdUserNeighborhood(sim, similarity, model);
                UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

                List recommendations = recommender.recommend(id, items);
                System.out.println("Recommendation for " + id);
                recommendations.forEach((recommendation) -> {
                        System.out.println(recommendation);
                });

                return userRepository.findOne(id);
        }
}