package com.aluracursos.alura_books.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorAPI implements IConversorAPI {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> c) {
        try {
            return mapper.readValue(json, c);
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e.getMessage());
        }

    }
}
