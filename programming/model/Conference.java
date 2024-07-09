package edu.kit.informatik.programming.model;

public class Conference {
    private final String series;
    private final int year;
    private final String location;

    public Conference(String series, int year, String location) {
        this.series = series;
        this.year = year;
        this.location = location;
    }
}
