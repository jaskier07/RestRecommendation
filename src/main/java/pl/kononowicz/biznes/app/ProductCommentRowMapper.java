/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author alexp
 */
public class ProductCommentRowMapper  implements RowMapper<ProductComment>  {
        @Override
        public ProductComment mapRow(ResultSet row, int rowNum) throws SQLException {
                ProductComment comment = new ProductComment();
                comment.id_product_comment =(row.getInt("id_product_comment"));
                comment.id_product =(row.getInt("id_product"));
                comment.id_customer =(row.getInt("id_customer"));
                comment.id_guest =(row.getInt("id_guest"));
                comment.title =(row.getString("title"));
                comment.content =(row.getString("content"));
                comment.customer_name =(row.getString("customer_name"));
                comment.grade =(row.getFloat("grade"));
                comment.validate =(row.getBoolean("validate"));
                comment.deleted =(row.getBoolean("deleted"));
                comment.date_add =(row.getDate("date_add"));
                return comment;
        }
}