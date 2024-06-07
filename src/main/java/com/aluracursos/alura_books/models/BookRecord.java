package com.aluracursos.alura_books.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record BookRecord(
        String title,
        List<AuthorRecord> authors,
        List<String> languages,
        @JsonAlias("download_count") int downloads
) {
}
