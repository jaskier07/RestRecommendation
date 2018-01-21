/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexp
 */
@Service
public class ProductCommentService implements IProductCommentService {

        @Autowired
        private IProductCommentDAO prodComDAO;

        @Override
        public List<ProductComment> getAllProductComments() {
                return prodComDAO.getAllProductComments();
        }
        
         @Override
        public List<ProductComment> getProductsCommentsByCustomerId(int id) {
                return prodComDAO.getProductsCommentsByCustomerId(id);
        }

        @Override
        public List<CountedUserRatings> countUserRatings() {
                return prodComDAO.countUserRatings();
        }

}
