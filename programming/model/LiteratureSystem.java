package edu.kit.informatik.programming.model;

import java.util.HashMap;
import java.util.Map;

public class LiteratureSystem {
    private Map<String, Publication> publications;
    private Map<String, Publication> invalidPublications;
    private Map<String, Author> authors;
    private Map<String, Journal> journals;
    private Map<String, Series> series;

    public LiteratureSystem() {
        authors = new HashMap<>();
        journals = new HashMap<>();
        publications = new HashMap();
        invalidPublications = new HashMap<>();
        series = new HashMap<>();
    }

    public void addPublication(String id, Publication publication) {
        this.publications.put(id, publication);
    }

    public void addInvalidPublication(String id, Publication publication) {
        this.invalidPublications.put(id, publication);
    }

    public void addAuthor(String name, Author author) {
        this.authors.put(name, author);
    }

    public void addJournal(String name, Journal journal) {
        this.journals.put(name, journal);
    }

    public void addSeries(String name, Series series) {
        this.series.put(name, series);
    }

    public Map<String, Publication> getPublications() {
        return new HashMap<>(publications);
    }

    public Map<String, Publication> getInvalidPublications() {
        return new HashMap<>(invalidPublications);
    }

    public void removeInvalidPublication(String id) {
        invalidPublications.remove(id);
    }

    public Map<String, Author> getAuthors() {
        return new HashMap<>(authors);
    }

    public Map<String, Journal> getJournals() {
        return new HashMap<>(journals);
    }

    public Map<String, Series> getSeries() {
        return new HashMap<>(series);
    }
}
