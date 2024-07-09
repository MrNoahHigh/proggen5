package edu.kit.informatik.programming.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Publication {
    private final String id;
    private final int year;
    private final String title;

    private final String series;
    private final String journal;
    private final HashMap<String, Author> authors;
    private final List<String> keywords;

    public Publication(String id, int year, String title, String series, String journal) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.series = series;
        this.journal = journal;
        this.authors = new HashMap<>();
        this.keywords = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getSeries() {
        return series;
    }

    public String getJournal() {
        return journal;
    }
    public void addAuthors(List<String> authorList, Map<String, Author> authors) {
        for (String author : authorList) {
            this.authors.put(author, authors.get(author));
        }
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

    public HashMap<String, Author> getAuthors() {
        return new HashMap<>(authors);
    }
}
