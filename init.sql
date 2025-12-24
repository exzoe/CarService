
CREATE TABLE IF NOT EXISTS car_types (
                                         id BIGSERIAL PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS drivers (
                                       id BIGSERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS cars (
                                    id BIGSERIAL PRIMARY KEY,
                                    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INTEGER NOT NULL,
    driver_id BIGINT REFERENCES drivers(id) ON DELETE SET NULL,
    car_type_id BIGINT REFERENCES car_types(id) ON DELETE SET NULL
    );

TRUNCATE TABLE cars, drivers, car_types RESTART IDENTITY CASCADE;

INSERT INTO car_types (name, description) VALUES
                                              ('Седан', 'Легковой автомобиль с отдельным багажником'),
                                              ('Внедорожник (SUV)', 'Автомобиль повышенной проходимости'),
                                              ('Хэтчбек', 'Компактный автомобиль с дверью в задней части'),
                                              ('Купе', 'Двухдверный спортивный автомобиль'),
                                              ('Минивэн', 'Семейный автомобиль с увеличенным салоном')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO drivers (name, age) VALUES
                                    ('Иван Иванов', 32),
                                    ('Петр Петров', 28),
                                    ('Мария Сидорова', 35),
                                    ('Алексей Смирнов', 41),
                                    ('Елена Кузнецова', 29)
    ON CONFLICT DO NOTHING;

INSERT INTO cars (brand, model, year, driver_id, car_type_id) VALUES
                                                                  ('Toyota', 'Camry', 2020, 1, 1),
                                                                  ('BMW', 'X5', 2021, 2, 2),
                                                                  ('Honda', 'Civic', 2019, 3, 3),
                                                                  ('Audi', 'A5', 2022, 4, 4),
                                                                  ('Ford', 'S-Max', 2021, 5, 5)
    ON CONFLICT DO NOTHING;

DO $$
DECLARE
car_types_count INTEGER;
    drivers_count INTEGER;
    cars_count INTEGER;
BEGIN
SELECT COUNT(*) INTO car_types_count FROM car_types;
SELECT COUNT(*) INTO drivers_count FROM drivers;
SELECT COUNT(*) INTO cars_count FROM cars;

RAISE NOTICE 'База данных инициализирована!';
    RAISE NOTICE '   Типов автомобилей: %', car_types_count;
    RAISE NOTICE '   Водителей: %', drivers_count;
    RAISE NOTICE '   Автомобилей: %', cars_count;
END $$;