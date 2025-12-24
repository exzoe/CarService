package org.example.carservice.repository;

import org.example.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Репозиторий для работы с сущностью Car.
 * Предоставляет CRUD операции для автомобилей.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Находит все автомобили, принадлежащие указанному водителю.
     *
     * @param driverId идентификатор водителя
     * @return список автомобилей водителя
     */
    List<Car> findByDriverId(Long driverId);
}