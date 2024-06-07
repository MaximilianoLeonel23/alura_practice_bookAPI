package com.aluracursos.alura_books;

import com.aluracursos.alura_books.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AluraBooksApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(AluraBooksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        int opc;
        Scanner sc = new Scanner(System.in);

        do {
            principal.showMenu();
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    principal.getAllBooks();
                    break;
                case 2:
                    principal.getTopTenBooks();
                    break;
                case 3:
                    principal.getBookByTitle();
                    break;
                case 4:
                    principal.getBookByTitleOrAuthor();
                    break;
                case 5:
                    principal.getBooksStatistics();
                    break;
                case 0:
                    System.out.println("See you later...");
                    break;
                default:
                    System.out.println("Type a valid option");
            }
        } while (opc != 0);

    }
}
