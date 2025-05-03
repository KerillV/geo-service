package ru.netology.entity;

import java.util.Objects;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    // Переопределяем метод hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(city, country, street, builing);
    }

    // Переопределяем метод equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Location other) {                   // если тип Location
            return builing == other.builing &&                 // Сравнение примитивных типов, поэтому используем ==
                    Objects.equals(city, other.city) &&        // Сравнение строковых полей
                    Objects.equals(country, other.country) &&  // Сравнение объектов
                    Objects.equals(street, other.street);      // Сравнение строковых полей
        }

        return false;
    }
}
