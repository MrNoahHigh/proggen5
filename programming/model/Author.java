package edu.kit.informatik.programming.model;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private final String firstName;
    private final String lastName;
    private final List<Publication> publications;
    private final List<String> coauthors;


    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.publications = new ArrayList<>();
        this.coauthors = new ArrayList<>();
    }

    public List<Publication> getPublications() {
        return new ArrayList<>(publications);
    }

    public List<String> getCoauthors() {
        return new ArrayList<>(coauthors);
    }

    public void addCoauthor(String coauthor) {
        if (!coauthors.contains(coauthor)) {
            coauthors.add(coauthor);
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
