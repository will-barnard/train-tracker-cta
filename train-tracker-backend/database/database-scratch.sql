select train_run from arrivals
group by train_run;


select * from arrivals 
where prediction_time between '2024-05-01 00:00:00' and '2024-05-01 23:59:59' 
and train_run = 101
order by prediction_time asc;


select * from train_run
order by arrival_time asc;

select * from train_run 
where run = 129
order by arrival_time desc;

select * from arrivals 
where arrival_id = 700 OR arrival_id = 100001;


select run, count(CASE WHEN was_late THEN 1 END) as count_late from train_run 
where was_late = true
group by run
order by count_late desc;

select run, avg(abs(difference_actual_average)) as sum_diff 
from train_run 
group by run
order by sum_diff desc;

select run, count(*) from train_run
group by run;

select run, count(CASE WHEN was_late THEN 1 END) as count_late, 
count(CASE WHEN was_faulty THEN 1 END) as count_faulty, 
avg(abs(difference_actual_average)) as avg_dev, 
count(*) as data_size from train_run 
group by run
order by data_size asc;

select (select count(*) from arrivals) as arrival_count, (select count(*) from train_run) as run_count;


begin transaction;

ALTER TABLE arrivals DROP CONSTRAINT arrivals_train_run_id_fkey;
ALTER TABLE arrivals DROP COLUMN train_run_id;
DELETE FROM train_run;
ALTER TABLE arrivals ADD train_run_id int;
ALTER TABLE arrivals ADD CONSTRAINT arrivals_train_run_id_fkey FOREIGN KEY (train_run_id) REFERENCES train_run(run_id);

ABORT

select * from train_run
select * from arrivals