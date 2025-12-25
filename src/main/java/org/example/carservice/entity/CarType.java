package org.example.carservice.entity;

import jakarta.persistence.*;

/**
 * Сущность "Тип автомобиля" - представляет категорию или класс автомобиля.
 * Используется для классификации автомобилей (седан, внедорожник, купе и т.д.).
 */
@Entity
@Table(name = "car_types")
public class CarType {

    /**
     * Уникальный идентификатор типа автомобиля.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public CarType() {}

    /**
     * Создает новый тип автомобиля с указанными параметрами.
     *
     * @param name название типа автомобиля
     * @param description описание типа автомобиля
     */
    public CarType(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}