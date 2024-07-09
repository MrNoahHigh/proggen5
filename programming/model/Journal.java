package edu.kit.informatik.programming.model;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private final String name;
    private final String publisher;
    private final List<Publication> publications;
    private final List<String> keywords;

    public Journal (String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
        this.publications = new ArrayList<>();
        this.keywords = new ArrayList<>();
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
