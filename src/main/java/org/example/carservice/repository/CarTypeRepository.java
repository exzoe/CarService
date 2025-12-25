package org.example.carservice.repository;

import org.example.carservice.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью CarType.
 * Предоставляет CRUD операции для типов автомобилей.
 */
@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}