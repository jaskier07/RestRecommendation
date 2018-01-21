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
public class CountedUserRatingsRowMapper implements RowMapper<CountedUserRatings>   {
        @Override
        public CountedUserRatings mapRow(ResultSet row, int rowNum) throws SQLException {
                CountedUserRatings comment = new CountedUserRatings();
                comment.id_customer =(row.getInt("id_customer"));
                comment.ratings =(row.getInt("ratings"));
                return comment;
        }
}
