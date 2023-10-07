package com.example.damian_jozwiak_99794.domain.book.dto;

public class BookDto {
    private Long id;
    private String name;
    private String price;
    private String stock;
    private int stars;

    private String genre;

    public BookDto(){}
    public BookDto(Long id, String name, String price, String stock, int stars, String genre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.stars = stars;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
