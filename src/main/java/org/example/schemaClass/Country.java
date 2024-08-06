package org.example.schemaClass;

import java.util.List;

public class Country {

    private int attendeeCount;
    List<String> attendees;
    private String name;
    private String startDate;

    public Country() {}

    public Country(int attendeeCount, List<String> attendees, String name, String startDate) {
        this.attendeeCount = attendeeCount;
        this.attendees = attendees;
        this.name = name;
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    @Override
    public String toString() {
        return "Country{" +
                "attendeeCount=" + attendeeCount +
                ", attendees=" + attendees +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
