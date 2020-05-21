package com.example.ejercicio3cm.modelo;

public class producto {
    int id;
    String thumbnail_url, name, provider;
    double price, delivery;

    public producto(int id, String thumbnail_url, String name, String provider, double price, double delivery) {
        this.id = id;
        this.thumbnail_url = thumbnail_url;
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }
}
