package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.requestBuilder.Transform;
import org.example.schemaClass.Country;
import org.example.schemaClass.Partner;

import java.io.IOException;
import java.util.ArrayList;
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
//        try {
//            // Fetch the list of Country entities
//            List<Country> countries = httpUtil.getCountries();
//
//            // Print details of each country
//            if (countries != null && !countries.isEmpty()) {
//                for (Country country : countries) {
//                    System.out.println(country);
//                }
//            } else {
//                System.out.println("No countries found.");
//            }
//        } catch (IOException e) {
//            System.err.println("Error fetching countries: " + e.getMessage());
//        }

//        Post method
        try{
            List<Country> countries = httpUtil.getCountries();
            httpUtil.postCountries(countries);
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(countries);
//            System.out.println(jsonString);
        } catch(IOException e) {
            e.printStackTrace();
        }

//        List<Partner> partners = new ArrayList<>();
//
//        // Example partners
//        partners.add(new Partner("Darin", "Daignault", "ddaignault@hubspotpartners.com", "United States", List.of("2017-05-03", "2017-05-06")));
//        partners.add(new Partner("Crystal", "Brenna", "cbrenna@hubspotpartners.com", "Ireland", List.of("2017-04-27", "2017-04-29", "2017-04-30")));
//        partners.add(new Partner("Janyce", "Gustison", "jgustison@hubspotpartners.com", "Spain", List.of("2017-04-29", "2017-04-30", "2017-05-01")));
//        partners.add(new Partner("Tifany", "Mozie", "tmozie@hubspotpartners.com", "Spain", List.of("2017-04-28", "2017-04-29", "2017-05-01", "2017-05-04")));
//        partners.add(new Partner("Temple", "Affelt", "taffelt@hubspotpartners.com", "Spain", List.of("2017-04-28", "2017-04-29", "2017-05-02", "2017-05-04")));
//        partners.add(new Partner("Robyn", "Yarwood", "ryarwood@hubspotpartners.com", "Spain", List.of("2017-04-29", "2017-04-30", "2017-05-02", "2017-05-03")));
//        partners.add(new Partner("Shirlene", "Filipponi", "sfilipponi@hubspotpartners.com", "Spain", List.of("2017-04-30", "2017-05-01")));
//        partners.add(new Partner("Oliver", "Majica", "omajica@hubspotpartners.com", "Spain", List.of("2017-04-28", "2017-04-29", "2017-05-01", "2017-05-03")));
//        partners.add(new Partner("Wilber", "Zartman", "wzartman@hubspotpartners.com", "Spain", List.of("2017-04-29", "2017-04-30", "2017-05-02", "2017-05-03")));
//        partners.add(new Partner("Eugena", "Auther", "eauther@hubspotpartners.com", "United States", List.of("2017-05-04", "2017-05-09")));
//
//        // Transform partners into countries
//        Transform transform = new Transform();
//        List<Country> countries = transform.transformPartnerToCountry(partners);
//
//        // Post countries and print response
//        try{
//        httpUtil.postCountries(countries);}
//        catch (IOException e){
//            e.printStackTrace();
//        }
    }
}