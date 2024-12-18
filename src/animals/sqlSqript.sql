# 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
CREATE SCHEMA `human_friends` ;
use human_friends;
# 8. Создать таблицы с иерархией из диаграммы в БД
create Table animal_classes (
	Id serial Primary key,
    Class_name VARCHAR(20)
    );
insert into animal_classes (Class_name)
VALUES ('вьючные'),('домашние');
select * from animal_classes;

create Table packAnimals (
	Id serial Primary key,
    TypeAnimal VARCHAR(20),
    ClassId BIGINT UNSIGNED,
    FOREIGN KEY (ClassId) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into packAnimals (TypeAnimal, ClassId)
VALUES ('Лошади', 1),('Ослы', 1),('Верблюды', 1);

select * from packAnimals;

create Table pets (
	Id serial Primary key,
    TypeAnimal VARCHAR(20),
    ClassId BIGINT UNSIGNED,
    FOREIGN KEY (ClassId) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into pets (TypeAnimal, ClassId)
VALUES ('Кошки', 2),('Собаки', 2),('Хомяки', 2);

select * from pets;

CREATE TABLE cats 
(       
    Id serial PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    GenusId BIGINT UNSIGNED,
    Foreign KEY (GenusId) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dogs 
(       
    Id serial PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    GenusId BIGINT UNSIGNED,
    Foreign KEY (GenusId) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE humsters 
(       
    Id serial PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    GenusId BIGINT UNSIGNED,
    Foreign KEY (GenusId) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE horses 
(       
    Id serial PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    GenusId BIGINT UNSIGNED,
    Foreign KEY (GenusId) REFERENCES packAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE donkeys 
(       
    Id serial PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    GenusId BIGINT UNSIGNED,
    Foreign KEY (GenusId) REFERENCES packAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels 
(       
    Id serial PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    GenusId BIGINT UNSIGNED,
    Foreign KEY (GenusId) REFERENCES packAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

# 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения

insert into cats (Name, Birthday, Commands, GenusId)
Values ('Люся', '2015-05-15', 'кс-кс', 1),
	('Себастьян', '2021-11-10', 'кудабля', 1),
    ('Тишка', '2010-06-14', 'жрать!', 1);
    
insert into dogs (Name, Birthday, Commands, GenusId)
Values ('Ральф', '2020-04-10', 'фас, аппорт', 2),
	('Жора', '2020-11-10', 'голос, тихо, рядом', 2),
    ('Бармалей', '2012-11-04', 'лежать, сидеть, фу', 2);
    
INSERT INTO humsters (Name, Birthday, Commands, GenusId)
VALUES ('Малой', '2020-10-12', '', 3),
('Медведь', '2021-03-12', "атака сверху", 3),  
('Ниндзя', '2022-07-11', '', 3), 
('Бурый', '2022-05-10', '', 3);
select * from dogs;

INSERT INTO horses (Name, Birthday, Commands, GenusId)
VALUES ('Гром', '2020-01-12', 'бегом, шагом', 1),
('Закат', '2017-03-12', "бегом, шагом, хоп", 1),  
('Байкал', '2016-07-12', "бегом, шагом, хоп, брр", 1), 
('Молния', '2020-11-10', "бегом, шагом, хоп", 1);

INSERT INTO donkeys (Name, Birthday, Commands, GenusId)
VALUES ('Первый', '2019-04-10', NULL, 2),
('Второй', '2020-03-12', "", 2),  
('Третий', '2021-07-12', "", 2), 
('Четвертый', '2022-12-10', NULL, 2);

INSERT INTO camels (Name, Birthday, Commands, GenusId)
VALUES ('Горбатый', '2022-04-10', 'вернись', 3),
('Самец', '2019-03-12', "остановись", 3),  
('Сифон', '2015-07-12', "повернись", 3), 
('Борода', '2022-12-10', "улыбнись", 3);

# 10. Удалить из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;

SELECT Name, Birthday, Commands, GenusId FROM horses
UNION SELECT  Name, Birthday, Commands, GenusId FROM donkeys;

# 11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет
# и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
CREATE TEMPORARY TABLE animals AS 
SELECT *, 'Лошади' as genus FROM horses
UNION SELECT *, 'Ослы' AS genus FROM donkeys
UNION SELECT *, 'Собаки' AS genus FROM dogs
UNION SELECT *, 'Кошки' AS genus FROM cats
UNION SELECT *, 'Хомяки' AS genus FROM humsters;

CREATE TABLE young_animal AS
SELECT Name, Birthday, Commands, genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM animals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM young_animal;

# 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

SELECT h.Name, h.Birthday, h.Commands, pa.TypeAnimal, ya.Age_in_month 
FROM horses h
LEFT JOIN young_animal ya ON ya.Name = h.Name
LEFT JOIN packAnimals pa ON pa.Id = h.GenusId
UNION 
SELECT d.Name, d.Birthday, d.Commands, pa.TypeAnimal, ya.Age_in_month 
FROM donkeys d 
LEFT JOIN young_animal ya ON ya.Name = d.Name
LEFT JOIN packAnimals pa ON pa.Id = d.GenusId
UNION
SELECT c.Name, c.Birthday, c.Commands, ha.TypeAnimal, ya.Age_in_month 
FROM cats c
LEFT JOIN young_animal ya ON ya.Name = c.Name
LEFT JOIN pets ha ON ha.Id = c.GenusId
UNION
SELECT d.Name, d.Birthday, d.Commands, ha.TypeAnimal, ya.Age_in_month 
FROM dogs d
LEFT JOIN young_animal ya ON ya.Name = d.Name
LEFT JOIN pets ha ON ha.Id = d.GenusId
UNION
SELECT hm.Name, hm.Birthday, hm.Commands, ha.TypeAnimal, ya.Age_in_month 
FROM humsters hm
LEFT JOIN young_animal ya ON ya.Name = hm.Name
LEFT JOIN pets ha ON ha.Id = hm.GenusId;