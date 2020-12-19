/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.server;

public class Book {
    private final String title;

    private final String ISBN;

    public Book(String title, String isbn) {
        this.title = title;
        this.ISBN = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
