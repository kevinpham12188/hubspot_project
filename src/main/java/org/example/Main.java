package org.example;

import org.example.schemaClass.Country;
import org.example.schemaClass.Partner;

import java.io.IOException;
import java.util.List;

import org.example.httpHandler.HttpUtil;

public class Main {
    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();

//        Get partners
//        try {
//            // Call the getPartners method to fetch the list of partners
//            List<Partner> partners = httpUtil.getPartners();
//
//            // Check if partners list is not null
//            if (partners != null) {
//                // Print details of each partner
//                for (Partner partner : partners) {
//                    System.out.println("Name: " + partner.getFirstName() + " " + partner.getLastName());
//                    System.out.println("Email: " + partner.getEmail());
//                    System.out.println("Country: " + partner.getCountry());
//                    System.out.println("Available Dates: " + partner.getAvailableDates());
//                    System.out.println();
//                }
//            } else {
//                System.out.println("No partners found.");
//            }
//        } catch (IOException e) {
//            // Handle and print the error message if an exception occurs
//            System.err.println("Error fetching partners: " + e.getMessage());
//        }

//        Get countries
        try {
            // Fetch the list of Country entities
            List<Country> countries = httpUtil.getCountries();

            // Print details of each country
            if (countries != null && !countries.isEmpty()) {
                for (Country country : countries) {
                    System.out.println(country);
                }
            } else {
                System.out.println("No countries found.");
            }
        } catch (IOException e) {
            System.err.println("Error fetching countries: " + e.getMessage());
        }

//        Post method
//        try{
//            List<Country> countries = httpUtil.getCountries();
//            httpUtil.postCountries(countries);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
    }
}