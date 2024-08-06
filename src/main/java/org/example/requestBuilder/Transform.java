package org.example.requestBuilder;

import org.example.schemaClass.Country;
import org.example.schemaClass.Partner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Transform {

//    public Country transform(List<Partner> partners) {
//        if(partners.isEmpty()) {
//            return null;
//        }
//
////        Calculate total partners for each country
//        Map<String, List<Partner>> countryMap = partners.stream().collect(Collectors.groupingBy(Partner::getCountry));
//
//        for(Map.Entry<String, List<Partner>> entry : countryMap.entrySet()) {
//            String countryName = entry.getKey();
//            List<Partner> countryPartners = entry.getValue();
//
////            Find the best start date with the maximum number of attendees
//            Map<String, Long> dateCountMap = new HashMap<>();
//            for(Partner partner : countryPartners) {
//                List<String> dates = partner.getAvailableDates();
//
//                for(int i = 0; i < dates.size() - 1; i++) {
//                    String startDate = dates.get(i);
//                    String endDate = getNextDay(startDate);
//                    if(dates.contains(endDate)) {
//                        String dateRange = startDate + " / " + endDate;
//                        dateCountMap.put(dateRange, dateCountMap.getOrDefault(dateRange, 0L) + 1);
//                    }
//                }
//            }
//
////            Find the date range with the maximum count
//            String bestDateRange = dateCountMap.entrySet().stream()
//                    .max(Map.Entry.comparingByValue())
//                    .map(Map.Entry::getKey)
//                    .orElse("");
//
////            Extracting start date from best date range
//            String startDate = bestDateRange != null ? bestDateRange.split("/")[0] : null;
//
////            Collect the attendees for the chosen date range
////            List<String> attendees = countryPartners.stream()
////                    .filter(partner -> partner.getAvailableDates().contains(startDate) &&
////                            partner.getAvailableDates().contains(getNextDay(startDate)))
////                    .map(Partner::getEmail)
////                    .collect(Collectors.toList());
//
//            Map<String, List<String>> dateToAttendees = new HashMap<>();
//            for(Partner partner : countryPartners) {
//                List<String> dates = partner.getAvailableDates();
//                for(int i = 0; i < dates.size() - 1; i++) {
//                    String date = dates.get(i);
//                    if(date == startDate) {
//
//                    }
//                }
//            }
//
//            List<String> attendees = new ArrayList<>();
//            for (Partner partner : countryPartners) {
//                List<String> availableDates = partner.getAvailableDates();
//                if (availableDates.contains(startDate) && availableDates.contains(getNextDay(startDate))) {
//                    attendees.add(partner.getEmail());
//                }
//            }
//
////            System.out.println("Attendees for country " + countryName + ": " + attendees);
//
////            Create the country object
//            Country country = new Country(attendees.size(), attendees, countryName, startDate);
//        }
//        return country;
//    }

    private String getNextDay(String date) {
        java.time.LocalDate localDate = java.time.LocalDate.parse(date);
        return localDate.plusDays(1).toString();
    }

    public List<Country> transformPartnerToCountry(List<Partner> partners) {
        Map<String, List<Partner>> countries = new HashMap<>();
        // group partner into different group based on each country
        for(Partner partner : partners) {
            List<Partner> partnerInfo = countries.getOrDefault(partner.getCountry(), new ArrayList<>());
            partnerInfo.add(partner);
            countries.put(partner.getCountry(), partnerInfo);
        }
        List<Country> countriesList = new ArrayList<>();
        for(Map.Entry<String, List<Partner>> entry : countries.entrySet()) {
            Country countryInfo = this.transformOneCountry(entry.getKey(), entry.getValue());
            countriesList.add(countryInfo);
        }
        return countriesList;
    }

    private Country transformOneCountry(String countryName, List<Partner> countryPartners) {

//            Find the best start date with the maximum number of attendees
        Map<String, Long> dateCountMap = new HashMap<>();
        for(Partner partner : countryPartners) {
            List<String> dates = partner.getAvailableDates();

            for(int i = 0; i < dates.size() - 1; i++) {
                String startDate = dates.get(i);
                String endDate = getNextDay(startDate);
                if(dates.contains(endDate)) {
                    String dateRange = startDate + "/ " + endDate;
                    dateCountMap.put(dateRange, dateCountMap.getOrDefault(dateRange, 0L) + 1);
                }
            }
        }

//            Find the date range with the maximum count
        String bestDateRange = dateCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

//            Extracting start date from best date range
        String startDate = bestDateRange != null ? bestDateRange.split("/")[0] : null;

        Map<String, List<String>> dateToAttendees = new HashMap<>();
        List<String> attendees = new ArrayList<>();
        for(Partner partner : countryPartners) {
            List<String> dates = partner.getAvailableDates();
            for(int i = 0; i < dates.size() - 1; i++) {
                String date = dates.get(i);
                if(date.equals(startDate) && dates.contains(startDate + 1)) {
                    attendees.add(partner.getEmail());
                }
            }
        }

//            Create the country object
        Country country = new Country(attendees.size(), attendees, countryName, startDate);
        return country;
    }
}
