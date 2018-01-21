/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import java.util.List;

/**
 *
 * @author alexp
 */
public interface IProductCommentDAO {
        public List<ProductComment> getAllProductComments();
        
        public List<CountedUserRatings> countUserRatings();
        
        public List<ProductComment>getProductsCommentsByCustomerId(int id);
}
