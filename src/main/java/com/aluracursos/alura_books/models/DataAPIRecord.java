package com.aluracursos.alura_books.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DataAPIRecord(
        List<BookRecord> results
) {
}
