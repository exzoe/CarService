package org.example.carservice.repository;

import org.example.carservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Driver.
 * Предоставляет CRUD операции для водителей.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}