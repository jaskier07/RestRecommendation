/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author alexp
 */ // This tells Hibernate to make a table out of this class
public class CountedUserRatings {
        Integer id_customer;
        Integer ratings;
}
