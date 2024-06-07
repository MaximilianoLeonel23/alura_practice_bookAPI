package com.aluracursos.alura_books.services;

public interface IConversorAPI {
    <T> T getData(String json, Class<T> c);
}
