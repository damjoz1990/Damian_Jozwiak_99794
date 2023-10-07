package com.example.damian_jozwiak_99794.domain.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ScraperService {
    public List<Book> scrapeBooks() throws IOException {
        String baseUrl = "https://books.toscrape.com/";
        List<Book> books = new ArrayList<>();

        Document doc = Jsoup
                .connect(baseUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements nextElements = doc.select(".next");

        while (!nextElements.isEmpty()) {
            Element nextElement = nextElements.first();
            String relativeUrl = nextElement.getElementsByTag("a").first().attr("href");

            if (relativeUrl.length() > 12){
                if(relativeUrl != null && relativeUrl.length() > 11) {
                    System.out.println(relativeUrl);
                    relativeUrl = relativeUrl.substring(10);
                }
            }

            String completeUrl = baseUrl + "catalogue/" + relativeUrl;

            doc = Jsoup
                    .connect(completeUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();

            Elements booksElements = doc.select(".product_pod");

            for (Element booksElement : booksElements) {
                Book book = new Book();

                String name = booksElement.select(".product_pod h3").first().text();
                String price = booksElement.select(".price_color").first().text();
                String inStock = booksElement.select(".availability.instock").first().text();
                int stars = parseStars(booksElement.select(".star-rating").first().className());

                book.setName(name);
                book.setPrice(price);
                book.setInStock(inStock);
                book.setStars(stars);

                books.add(book);
            }

            nextElements = doc.select(".next");
        }
        return books;
    }

    private int parseStars(String starClass) {
        switch (starClass) {
            case "star-rating One":
                return 1;
            case "star-rating Two":
                return 2;
            case "star-rating Three":
                return 3;
            case "star-rating Four":
                return 4;
            case "star-rating Five":
                return 5;
            default:
                return 0;
        }
    }

    public void saveBooksToCsv(List<Book> books, OutputStream outputStream) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.write('\ufeff'); // BOM (Byte Order Mark) dla UTF-8

            int id_generator = 0;
            Random rand = new Random();

            for (Book book : books) {
                List<String> row = new ArrayList<>();

                id_generator++;
                int random = rand.nextInt(10) + 1;

                if (id_generator == 1) {
                    row.add("id");
                    row.add("name");
                    row.add("price");
                    row.add("stock");
                    row.add("stars");
                    row.add("genre_id");
                } else {
                    row.add(String.valueOf(id_generator));
                    row.add("\"" + book.getName() + "\"");
                    row.add("\"" + book.getPrice() + "\"");
                    row.add("\"" + book.getInStock() + "\"");
                    row.add(" " + book.getStars() + " ");
                    row.add(" " + random + " ");
                }

                writer.println(String.join(",", row));
            }
        }
    }

    public List<Book> searchBooksByQuery(String query) throws IOException {
        List<Book> allBooks = scrapeBooks(); // Pobierz wszystkie książki (lub wcześniej zapisane)
        List<Book> matchingBooks = new ArrayList<>();

        // Iteruj przez wszystkie książki i sprawdź, czy pasują do zapytania
        for (Book book : allBooks) {
            if (book.getName().toLowerCase().contains(query.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        System.out.println(matchingBooks.size());
        return matchingBooks;
    }

    public List<Book> getTop10Books() throws IOException {
        List<Book> allBooks = scrapeBooks(); // Pobierz wszystkie książki

        // Posortuj książki po ocenie (lub innym kryterium)
        List<Book> sortedBooks = allBooks.stream()
                .sorted(Comparator.comparingInt(Book::getStars).reversed()) // Sortuj malejąco po ocenie
                .collect(Collectors.toList());

        // Zwróć top 10 książek (lub mniej, jeśli jest ich mniej niż 10)
        return sortedBooks.stream().limit(10).collect(Collectors.toList());
    }
}
