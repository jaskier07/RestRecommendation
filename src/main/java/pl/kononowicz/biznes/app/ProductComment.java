/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alexp
 */
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "ps_product_comment")
public class ProductComment {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
         Integer id_product_comment;
         Integer id_product;
         Integer id_customer;
         Integer id_guest;
         String title;
         String content;
         String customer_name;
         Float grade;
         Boolean validate;
         Boolean deleted;
         Date date_add;
        
}
