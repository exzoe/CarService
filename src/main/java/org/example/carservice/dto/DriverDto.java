package org.example.carservice.dto;

/**
 * Data Transfer Object для сущности Driver.
 * Используется для передачи данных водителя через REST API
 * без циклических ссылок и лишней информации о связанных автомобилях.
 */

public class DriverDto {

    private Long id;
    private String name;
    private int age;

    public DriverDto() {}

    public DriverDto(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}