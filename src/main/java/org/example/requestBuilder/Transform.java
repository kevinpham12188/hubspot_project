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

    /** Convert list of Partner objects into a list of Country objects **/

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

    /** Process the partners for a specific country and determin the best start date with the most attendees **/
    private Country transformOneCountry(String countryName, List<Partner> countryPartners) {

//            Find the start date (with next day) and number of attendees for each start date
        Map<String, Long> dateCountMap = new HashMap<>();
        for(Partner partner : countryPartners) {
            List<String> dates = partner.getAvailableDates();

            for(int i = 0; i < dates.size() - 1; i++) {
                String startDate = dates.get(i);
                String endDate = getNextDay(startDate);
                if(dates.contains(endDate)) {
                    dateCountMap.put(startDate, dateCountMap.getOrDefault(startDate, 0L) + 1);
                }
            }
        }

//              Find the best start date by sorting by maximum number of attendees and by date
        String bestStartDate = dateCountMap.entrySet().stream()
                .sorted((e1, e2) -> {
                    int countComparison = e2.getValue().compareTo(e1.getValue()); // Descending order of count
                    if (countComparison == 0) {
                        return e1.getKey().compareTo(e2.getKey()); // Ascending order of date
                    }
                    return countComparison;
                })
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
//        System.out.println(bestStartDate);

//        Find the attendees that have best start date and meet 2 consecutive date requirement
        List<String> attendees = new ArrayList<>();
        for(Partner partner : countryPartners) {
            List<String> dates = partner.getAvailableDates();
            for(int i = 0; i < dates.size() - 1; i++) {
                String date = dates.get(i);
                if(date.equals(bestStartDate) && dates.contains(getNextDay(bestStartDate))) {
                    attendees.add(partner.getEmail());
                }
            }
        }

//            Create the country object
        Country country = new Country(attendees.size(), attendees, countryName, bestStartDate);
        return country;
    }

    private String getNextDay(String date) {
        java.time.LocalDate localDate = java.time.LocalDate.parse(date);
        return localDate.plusDays(1).toString();
    }
}
