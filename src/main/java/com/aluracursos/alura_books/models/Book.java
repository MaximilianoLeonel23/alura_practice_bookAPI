package com.aluracursos.alura_books.models;

import java.util.List;
import java.util.Optional;

public class Book {
    private String title;
    private List<AuthorRecord> authors;
    private List<String> languages;
    private int downloads;

    public Book(BookRecord b) {
        this.title = b.title();
        this.authors = b.authors();
        this.languages = b.languages();
        this.downloads = b.downloads();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorRecord> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorRecord> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloads=" + downloads;
    }
}
