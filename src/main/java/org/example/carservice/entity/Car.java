package org.example.carservice.entity;

import jakarta.persistence.*;

/**
 * Сущность "Автомобиль" - представляет автомобиль в системе.
 * Хранит информацию об автомобиле, его водителе и типе.
 */
@Entity
@Table(name = "cars")
public class Car {

    /**
     * Уникальный идентификатор автомобиля.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;

    /**
     * Водитель, назначенный на автомобиль.
     * Связь многие-к-одному с сущностью Driver.
     * Может быть null, если автомобиль не имеет водителя.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    /**
     * Тип автомобиля.
     * Связь многие-к-одному с сущностью CarType.
     * Определяет категорию автомобиля (седан, внедорожник и т.д.).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_type_id")
    private CarType carType;

    public Car() {}

    /**
     * Создает новый автомобиль с указанными параметрами.
     *
     * @param brand марка автомобиля
     * @param model модель автомобиля
     * @param year год выпуска
     */
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    /**
     * Создает новый автомобиль с указанными параметрами и типом.
     *
     * @param brand марка автомобиля
     * @param model модель автомобиля
     * @param year год выпуска
     * @param carType тип автомобиля
     */
    public Car(String brand, String model, int year, CarType carType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.carType = carType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}