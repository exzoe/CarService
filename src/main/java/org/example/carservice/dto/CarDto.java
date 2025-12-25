package org.example.carservice.dto;

/**
 * Data Transfer Object для сущности Car.
 * Содержит информацию об автомобиле, его водителе и типе.
 */
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private int year;
    private Long driverId;
    private String driverName;
    private Long carTypeId;
    private String carTypeName;

    public CarDto() {}

    /**
     * Создает CarDto со всеми параметрами.
     *
     * @param id идентификатор автомобиля
     * @param brand марка автомобиля
     * @param model модель автомобиля
     * @param year год выпуска
     * @param driverId идентификатор водителя
     * @param driverName имя водителя
     * @param carTypeId идентификатор типа автомобиля
     * @param carTypeName название типа автомобиля
     */
    public CarDto(Long id, String brand, String model, int year,
                  Long driverId, String driverName, Long carTypeId, String carTypeName) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.driverId = driverId;
        this.driverName = driverName;
        this.carTypeId = carTypeId;
        this.carTypeName = carTypeName;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public Long getCarTypeId() {
        return carTypeId;
    }
    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }
}