package com.aluracursos.alura_books.principal;

import com.aluracursos.alura_books.models.AuthorRecord;
import com.aluracursos.alura_books.models.Book;
import com.aluracursos.alura_books.models.BookRecord;
import com.aluracursos.alura_books.models.DataAPIRecord;
import com.aluracursos.alura_books.services.ConsumeAPI;
import com.aluracursos.alura_books.services.ConversorAPI;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final ConsumeAPI consumeAPI = new ConsumeAPI();
    private static final ConversorAPI conversor = new ConversorAPI();
    private static final Scanner sc = new Scanner(System.in);


    public void getTopTenBooks() {
        var json = consumeAPI.getDataAPI(URL_BASE);
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("Data from API is empty");
        }
        var results = conversor.getData(json, DataAPIRecord.class);

        //Top 10 libros más descargados

        System.out.println("Top 10 books most downloaded");
        results.results().stream()
                .sorted(Comparator.comparing(BookRecord::downloads).reversed())
                .limit(10)
                .forEach(book -> {
                    System.out.println(book.title().toUpperCase() + " - " + book.downloads());
                });
    }

    public void getBookByTitle() {
        System.out.println("Type the book's title");
        String title = sc.nextLine().replace(" ", "+");
        var json = consumeAPI.getDataAPI(URL_BASE + "?search=" + title);
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("Data from API is empty");
        }
        var results = conversor.getData(json, DataAPIRecord.class);

        Optional<BookRecord> bookFound = results.results().stream()
                .filter(book -> book.title().toUpperCase().contains(title.replace("+", " ").toUpperCase()))
                .findFirst();
        bookFound.ifPresentOrElse(bookRecord -> {
            System.out.println("Book found");
            Book book = new Book(bookRecord);
            System.out.println(book.toString());
        }, () -> System.out.println("Book not found"));
    }

    public void getBookByTitleOrAuthor() {
        System.out.println("Write book's title");
        String title = sc.nextLine().replace(" ", "+");
        System.out.println("Write book's author");
        String author = sc.nextLine().replace(" ", "+");
        var json = consumeAPI.getDataAPI(URL_BASE + "?search=" + author + "%20" + title);
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("Data from API is empty");
        }
        var results = conversor.getData(json, DataAPIRecord.class);

        Optional<BookRecord> bookFound = results.results().stream()
                .filter(book -> {
                    boolean titleFound = book.title().toUpperCase().contains(title.replace("+", " ").toUpperCase());
                    Optional<AuthorRecord> authorFound = book.authors().stream().filter(authorRecord -> authorRecord.name().toUpperCase().contains(author.replace("+", " ").toUpperCase())).findFirst();
                    return authorFound.isPresent() && titleFound;
                })
                .findFirst();
        bookFound.ifPresentOrElse(bookRecord -> {
            System.out.println("Book found");
            Book book = new Book(bookRecord);
            System.out.println(book.toString());
        }, () -> System.out.println("Book not found"));
    }

    public void getBooksStatistics() {
        var json = consumeAPI.getDataAPI(URL_BASE);
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("Data from API is empty");
        }
        var results = conversor.getData(json, DataAPIRecord.class);
        IntSummaryStatistics statistics = results.results().stream()
                .filter(book -> book.downloads() > 0)
                .collect(Collectors.summarizingInt(BookRecord::downloads));
        System.out.println("Downloads average: " + statistics.getAverage());
        System.out.println("Downloads max amount: " + statistics.getMax());
        System.out.println("Evaluated book amount: " + statistics.getCount());
    }

    public void getAllBooks() {
        var json = consumeAPI.getDataAPI(URL_BASE);
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("Data from API is empty");
        }
        var results = conversor.getData(json, DataAPIRecord.class);

        for (int i = 0; i < results.results().size() - 1; i++) {
            System.out.println("'" + results.results().get(i).title().toUpperCase() + "'");
        }
    }

    public void showMenu() {
        System.out.println("*** Welcome to Alura Books ***");
        System.out.println("Choose one option");
        System.out.println("""
                1. Get All Books
                2. Get Top 10 Most Downloaded Books
                3. Get a Book By Title
                4. Get a Book By Title and Author
                5. Get Statistics from Books
                0. Exit
                """);
    }


}
