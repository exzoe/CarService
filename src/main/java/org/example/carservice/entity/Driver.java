package org.example.carservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность "Водитель" - представляет водителя автомобиля.
 * Хранит информацию о водителе и его автомобилях.
 */
@Entity
@Table(name = "drivers")
public class Driver {

    /**
     * Уникальный идентификатор водителя.
     * Генерируется автоматически базой данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    /**
     * Список автомобилей, принадлежащих водителю.
     * Связь один-ко-многим с сущностью Car.
     */
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Car> cars = new ArrayList<>();

    public Driver() {}

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}