package org.example.carservice.controller;

import org.example.carservice.dto.DriverDto;
import org.example.carservice.dto.CarDto;
import org.example.carservice.dto.CarTypeDto;
import org.example.carservice.entity.Driver;
import org.example.carservice.entity.Car;
import org.example.carservice.entity.CarType;
import org.example.carservice.repository.DriverRepository;
import org.example.carservice.repository.CarRepository;
import org.example.carservice.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Основной контроллер REST API для управления водителями, автомобилями и типами автомобилей.
 * Предоставляет полный набор CRUD операций для всех сущностей системы.
 */
@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private DriverRepository driverRepo;

    @Autowired
    private CarRepository carRepo;

    @Autowired
    private CarTypeRepository carTypeRepo;


    /**
     * Возвращает список всех водителей в системе в формате DTO.
     *
     * @return список всех водителей
     */
    @GetMapping("/drivers")
    public List<DriverDto> getAllDrivers() {
        return driverRepo.findAll().stream()
                .map(this::convertToDriverDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает информацию о водителе по указанному идентификатору.
     *
     * @param id идентификатор водителя
     * @return объект DriverDto с данными водителя
     * @throws RuntimeException если водитель не найден
     */
    @GetMapping("/drivers/{id}")
    public DriverDto getDriver(@PathVariable Long id) {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        return convertToDriverDto(driver);
    }

    /**
     * Создает нового водителя в системе.
     *
     * @param driver данные нового водителя
     * @return созданный водитель с присвоенным идентификатором
     */
    @PostMapping("/drivers")
    public DriverDto createDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverRepo.save(driver);
        return convertToDriverDto(savedDriver);
    }

    @PutMapping("/drivers/{id}")
    public DriverDto updateDriver(@PathVariable Long id, @RequestBody Driver driverDetails) {
        Driver driver = driverRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));

        driver.setName(driverDetails.getName());
        driver.setAge(driverDetails.getAge());

        Driver updatedDriver = driverRepo.save(driver);
        return convertToDriverDto(updatedDriver);
    }

    /**
     * Удаляет водителя из системы по идентификатору.
     *
     * @param id идентификатор водителя для удаления
     * @return ответ с кодом 200 OK
     */
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id) {
        driverRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает список всех автомобилей, принадлежащих указанному водителю.
     *
     * @param id идентификатор водителя
     * @return список автомобилей водителя в формате DTO
     */
    @GetMapping("/drivers/{id}/cars")
    public List<CarDto> getDriverCars(@PathVariable Long id) {
        return carRepo.findByDriverId(id).stream()
                .map(this::convertToCarDto)
                .collect(Collectors.toList());
    }


    /**
     * Возвращает список всех автомобилей в системе в формате DTO.
     * Включает информацию о водителе и типе автомобиля.
     *
     * @return список всех автомобилей
     */
    @GetMapping("/cars")
    public List<CarDto> getAllCars() {
        return carRepo.findAll().stream()
                .map(this::convertToCarDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает информацию об автомобиле по указанному идентификатору.
     *
     * @param id идентификатор автомобиля
     * @return объект CarDto с данными автомобиля
     * @throws RuntimeException если автомобиль не найден
     */
    @GetMapping("/cars/{id}")
    public CarDto getCar(@PathVariable Long id) {
        Car car = carRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        return convertToCarDto(car);
    }

    /**
     * Создает новый автомобиль в системе.
     *
     * @param car данные нового автомобиля
     * @return созданный автомобиль с присвоенным идентификатором
     */
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {
        return carRepo.save(car);
    }

    /**
     * Обновляет информацию об автомобиле.
     *
     * @param id идентификатор автомобиля для обновления
     * @param carDetails новые данные автомобиля
     * @return обновленный объект Car
     * @throws RuntimeException если автомобиль не найден
     */
    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car car = carRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setCarType(carDetails.getCarType());

        return carRepo.save(car);
    }

    /**
     * Удаляет автомобиль из системы по идентификатору.
     *
     * @param id идентификатор автомобиля для удаления
     * @return ответ с кодом 200 OK
     */
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Назначает водителя на указанный автомобиль.
     *
     * @param carId идентификатор автомобиля
     * @param driverId идентификатор водителя
     * @return обновленный автомобиль в формате DTO
     * @throws RuntimeException если автомобиль или водитель не найдены
     */
    @PostMapping("/cars/{carId}/driver/{driverId}")
    public CarDto assignDriver(@PathVariable Long carId, @PathVariable Long driverId) {
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));
        Driver driver = driverRepo.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + driverId));

        car.setDriver(driver);
        Car savedCar = carRepo.save(car);

        return convertToCarDto(savedCar);
    }

    /**
     * Отвязывает водителя от указанного автомобиля.
     *
     * @param carId идентификатор автомобиля
     * @return обновленный автомобиль в формате DTO без водителя
     * @throws RuntimeException если автомобиль не найден
     */
    @DeleteMapping("/cars/{carId}/driver")
    public CarDto removeDriver(@PathVariable Long carId) {
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));

        car.setDriver(null);
        Car savedCar = carRepo.save(car);

        return convertToCarDto(savedCar);
    }

    /**
     * Возвращает список всех типов автомобилей в системе.
     *
     * @return список всех типов автомобилей
     */
    @GetMapping("/car-types")
    public List<CarTypeDto> getAllCarTypes() {
        return carTypeRepo.findAll().stream()
                .map(this::convertToCarTypeDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает информацию о типе автомобиля по указанному идентификатору.
     *
     * @param id идентификатор типа автомобиля
     * @return объект CarTypeDto с данными типа автомобиля
     * @throws RuntimeException если тип автомобиля не найден
     */
    @GetMapping("/car-types/{id}")
    public CarTypeDto getCarType(@PathVariable Long id) {
        CarType carType = carTypeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car type not found with id: " + id));
        return convertToCarTypeDto(carType);
    }

    /**
     * Создает новый тип автомобиля в системе.
     *
     * @param carType данные нового типа автомобиля
     * @return созданный тип автомобиля с присвоенным идентификатором
     */
    @PostMapping("/car-types")
    public CarType createCarType(@RequestBody CarType carType) {
        return carTypeRepo.save(carType);
    }

    /**
     * Обновляет информацию о типе автомобиля.
     *
     * @param id идентификатор типа автомобиля для обновления
     * @param carTypeDetails новые данные типа автомобиля
     * @return обновленный объект CarType
     * @throws RuntimeException если тип автомобиля не найден
     */
    @PutMapping("/car-types/{id}")
    public CarType updateCarType(@PathVariable Long id, @RequestBody CarType carTypeDetails) {
        CarType carType = carTypeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car type not found with id: " + id));

        carType.setName(carTypeDetails.getName());
        carType.setDescription(carTypeDetails.getDescription());

        return carTypeRepo.save(carType);
    }

    /**
     * Удаляет тип автомобиля из системы по идентификатору.
     *
     * @param id идентификатор типа автомобиля для удаления
     * @return ответ с кодом 200 OK
     */
    @DeleteMapping("/car-types/{id}")
    public ResponseEntity<?> deleteCarType(@PathVariable Long id) {
        carTypeRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


    /**
     * Преобразует объект Driver в DriverDto.
     *
     * @param driver объект Driver
     * @return объект DriverDto
     */
    private DriverDto convertToDriverDto(Driver driver) {
        return new DriverDto(driver.getId(), driver.getName(), driver.getAge());
    }

    /**
     * Преобразует объект Car в CarDto.
     * Включает информацию о водителе и типе автомобиля, если они назначены.
     *
     * @param car объект Car
     * @return объект CarDto
     */
    private CarDto convertToCarDto(Car car) {
        Long driverId = car.getDriver() != null ? car.getDriver().getId() : null;
        String driverName = car.getDriver() != null ? car.getDriver().getName() : null;
        Long carTypeId = car.getCarType() != null ? car.getCarType().getId() : null;
        String carTypeName = car.getCarType() != null ? car.getCarType().getName() : null;

        return new CarDto(car.getId(), car.getBrand(), car.getModel(),
                car.getYear(), driverId, driverName, carTypeId, carTypeName);
    }

    /**
     * Преобразует объект CarType в CarTypeDto.
     *
     * @param carType объект CarType
     * @return объект CarTypeDto
     */
    private CarTypeDto convertToCarTypeDto(CarType carType) {
        return new CarTypeDto(carType.getId(), carType.getName(), carType.getDescription());
    }


}