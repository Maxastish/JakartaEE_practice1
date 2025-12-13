# JakartaEE_practice1

Скрипт для создания и заполнения базы данных:

```
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    group_name VARCHAR(10) NOT NULL
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    professor VARCHAR(100) NOT NULL
);

CREATE TABLE student_courses (
    student_id INT REFERENCES students(id),
    course_id INT REFERENCES courses(id),
    mark INT,
    PRIMARY KEY (student_id, course_id)
);

-- Вставка данных
INSERT INTO students (name, birth_date, group_name) VALUES
('Буданов Максим', '2003-12-12', '6131'),
('Демченко Максим', '2003-10-31', '6131');

INSERT INTO courses (title, professor) VALUES
('Архитектура корпоративных систем', 'Гаврилов Андрей Вадимович'),
('Иностранный язык в профессиональной сфере', 'Слобожанина Наталья Александровна'),
('Методы оптимизации в машинном обучении', 'Котов Антон Петрович'),
('Большие данные', 'Попов Сергей Борисович'),
('Искусственный интеллект и машинное обучение', 'Куприянов Александр Викторович');

INSERT INTO student_courses (student_id, course_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5);
```

<img width="1277" height="1392" alt="image" src="https://github.com/user-attachments/assets/b4011340-57b4-4c9c-bf1d-35d338602c45" />

<img width="1277" height="1392" alt="image" src="https://github.com/user-attachments/assets/26aefeb1-e412-4e1d-948e-5926fcecb6cc" />

<img width="1277" height="1392" alt="image" src="https://github.com/user-attachments/assets/6b0953fb-6299-46c5-b95a-4e82c0794764" />
