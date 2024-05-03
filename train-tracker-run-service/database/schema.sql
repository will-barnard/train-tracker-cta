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
    lon DECIMAL,
    ADD train_run_id int references train_run(run_id);
);

CREATE TABLE train_run (
    run_id serial primary key,
	run int,
	run_date date,
	average_prediction timestamp,
	arrival_time timestamp,
	difference_actual_average int,
	was_late boolean,
	was_faulty boolean
);

CREATE TABLE run_arrival (
	run_id int REFERENCES train_run(run_id),
	arrival_id int REFERENCES arrivals(arrival_id),
	CONSTRAINT pk_run_arrival PRIMARY KEY (run_id, arrival_id)
);

COMMIT TRANSACTION;