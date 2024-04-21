BEGIN TRANSACTION;

DROP TABLE IF EXISTS arrivals;

CREATE TABLE arrivals (
    arrival_id SERIAL PRIMARY KEY,
    station_id INT,
    stop_id INT,
    station_name VARCHAR(50),
    stop_description VARCHAR(100),
    train_run INT,
    train_route VARCHAR(15),
    destination_stop INT,
    destination_name VARCHAR(50),
    train_direction INT,
    prediction_time TIMESTAMP,
    arrival_prediction TIMESTAMP,
    is_app INT,
    is_sch INT,
    is_delay INT,
    is_faulty INT,
    lat DECIMAL,
    lon DECIMAL
)

COMMIT TRANSACTION;