/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

/**
 *
 * @author alexp
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class ProductCommentDAO implements IProductCommentDAO {
        @Autowired
        private JdbcTemplate jdbcTemplate;
        @Override
        public List<ProductComment> getAllProductComments() {
                String sql = "SELECT * FROM ps_product_comment;";
                RowMapper<ProductComment> rowMapper = new ProductCommentRowMapper();
                return this.jdbcTemplate.query(sql, rowMapper);
        }

        @Override
        public List<CountedUserRatings> countUserRatings() {
                String sql = "select DISTINCT COUNT(id_customer) AS ratings, id_customer from ps_product_comment GROUP BY (id_customer);";
                RowMapper<CountedUserRatings> rowMapper = new CountedUserRatingsRowMapper();
                return this.jdbcTemplate.query(sql, rowMapper);           
        }

        @Override
        public List<ProductComment> getProductsCommentsByCustomerId(int id) {
                String sql = "SELECT * FROM ps_product_comment WHERE (id_customer=" + id + ");";
                RowMapper<ProductComment> rowMapper = new ProductCommentRowMapper();
                return this.jdbcTemplate.query(sql, rowMapper);
        }
        
       
}
