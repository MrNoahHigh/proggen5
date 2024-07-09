package edu.kit.informatik.programming.model;

import java.util.ArrayList;
import java.util.List;

public class Series {
    private final String name;
    private final List<Publication> publications;
    private final List<Conference> conferences;
    private final List<String> keywords;

    public Series(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
        this.conferences = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }

    public void addConference(Conference conference) {
        this.conferences.add(conference);
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    public void addKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            if (!this.keywords.contains(keyword)) {
                this.keywords.add(keyword);
            }
        }
    }

    public List<String> getKeywords() {
        return new ArrayList<>(keywords);
    }

    public List<Publication> getPublications() {
        return new ArrayList<>(publications);
    }
}
