/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksCatalog {
    private final static Logger LOGGER = Logger.getLogger(BooksCatalog.class.getName());

    private final static Map<String, List<Book>> catalog = new HashMap<>();

    static {
        LOGGER.setLevel(Level.INFO);

        catalog.put("C++", Arrays.asList(
                new Book(":The C++ Programming Language", "11111"),
                new Book("C++ Primer", "22222"),
                new Book("A Tour of C++", "33333")
        ));

        catalog.put("GO", Arrays.asList(
                new Book(":The Go Programming Language", "44444"),
                new Book("Go in Practice", "55555"),
                new Book("Black Hat Go", "66666")
        ));

        catalog.put("JAVA", Arrays.asList(
                new Book(":Effective Java", "77777"),
                new Book("Modern Java in Action", "88888"),
                new Book("Java: The Complete Reference", "99999")
        ));

        catalog.put("PYTHON", Arrays.asList(
                new Book(":Python Crash Course", "12121"),
                new Book("Learning Python", "34343"),
                new Book("Effective Python", "56565")
        ));
    }

    private BooksCatalog() {
    }

    public static List<Book> getBooks(String topic) {
        String key = topic.toUpperCase();

        LOGGER.info(String.format("Books request for topic: %s", key));

        if (!catalog.containsKey(key)) {
            LOGGER.severe(String.format("No books for topic: %s", key));
            throw new RuntimeException(String.format("No books for topic: %s", key));
        }

        List<Book> books = catalog.get(key);

        LOGGER.info(String.format("Books for key: %s = %s", key, books));

        return books;
    }
}
