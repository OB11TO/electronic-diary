INSERT INTO student (name, address, phone, email, gradebook_number)
VALUES ('Ivan', 'Moscow', '+79782106200', 'ivan@gmail.com', 10001),
       ('Petr', 'London', '+79782106201', 'petr@gmail.com', 10002),
       ('Artem', 'Moscow', '+79782106202', 'artem@gmail.com', 10003),
       ('Obito', 'Konoha', '+79782106203', 'obito@gmail.com', 10004),
       ('Guts', 'World', '+79782106204', 'guts@gmail.com', 10005);

INSERT INTO teacher (name, address, phone, payment)
VALUES ('Leonardo da Vinci', 'Anchiano', '+79782106210', 15000), -- матан + алгоритм
       ('Petr Elder', 'SpB', '+79782106211', 10000),             -- спринг
       ('Ivan Elder', 'SpB', '+79782106212', 11000),             -- без курса
       ('William Hodges', 'Moscow', '+79782106213', 1000),
       ('Henry Walton', 'London', '+79782106214', 1400),
       ('Joseph Barney', 'London', '+79782106215', 1000),
       ('Charles Lewis', 'Konoha', '+79782106216', 5000),
       ('Maria Bell', 'Moscow', '+79782106217', 2500);

INSERT INTO course (title, number, cost, teacher_id)
VALUES ('Math Analysis', 111, 2500, (SELECT id FROM teacher WHERE phone = '+79782106210')),
       ('Sambo', 222, 550, (SELECT id FROM teacher WHERE phone = '+79782106213')),
       ('Sambo', 999, 660, (SELECT id FROM teacher WHERE phone = '+79782106214')),
       ('Java Core', 333, 3500, null), --без препода
       ('Http Servlets', 444, 1500, (SELECT id FROM teacher WHERE phone = '+79782106215')),
       ('Spring Starter', 555, 5500, (SELECT id FROM teacher WHERE phone = '+79782106211')),
       ('Maven', 666, 1500, (SELECT id FROM teacher WHERE phone = '+79782106216')),
       ('Gradle', 777, 2500, (SELECT id FROM teacher WHERE phone = '+79782106217')),
       ('Algorithm', 888, 8500, (SELECT id FROM teacher WHERE phone = '+79782106210'));

-- INSERT INTO students_course(student_id, course_id, grade)
-- VALUES ((SELECT id FROM student WHERE phone = '+79782106200'), (SELECT id FROM course WHERE id = 1), 4),--матан
--        ((SELECT id FROM student WHERE phone = '+79782106200'), (SELECT id FROM course WHERE id = 9), 3), --алгоритмы
--        ((SELECT id FROM student WHERE phone = '+79782106201'), (SELECT id FROM course WHERE id = 9), 3),
--        ((SELECT id FROM student WHERE phone = '+79782106201'), (SELECT id FROM course WHERE id = 6), 4), --спринг
--        ((SELECT id FROM student WHERE phone = '+79782106202'), (SELECT id FROM course WHERE id = 6), 5), --спринг
--        ((SELECT id FROM student WHERE phone = '+79782106202'), (SELECT id FROM course WHERE id = 4), 5),--джава
--        ((SELECT id FROM student WHERE phone = '+79782106203'), (SELECT id FROM course WHERE id = 2), 5),--самбо1
--        ((SELECT id FROM student WHERE phone = '+79782106203'), (SELECT id FROM course WHERE id = 3), 5),--самбо2
--        ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT id FROM course WHERE id = 5), 4),--сервлеты
--        ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT id FROM course WHERE id = 7), 5), --мавен
--        ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT id FROM course WHERE id = 8), 4); --грейдл

INSERT INTO students_course(student_id, course_id)
VALUES ((SELECT id FROM student WHERE phone = '+79782106200'), (SELECT id FROM course WHERE id = 1)),--матан
       ((SELECT id FROM student WHERE phone = '+79782106200'), (SELECT id FROM course WHERE id = 9)), --алгоритмы
       ((SELECT id FROM student WHERE phone = '+79782106201'), (SELECT id FROM course WHERE id = 9)),
       ((SELECT id FROM student WHERE phone = '+79782106201'), (SELECT id FROM course WHERE id = 6)), --спринг
       ((SELECT id FROM student WHERE phone = '+79782106202'), (SELECT id FROM course WHERE id = 6)), --спринг
       ((SELECT id FROM student WHERE phone = '+79782106202'), (SELECT id FROM course WHERE id = 4)),--джава
       ((SELECT id FROM student WHERE phone = '+79782106203'), (SELECT id FROM course WHERE id = 2)),--самбо1
       ((SELECT id FROM student WHERE phone = '+79782106203'), (SELECT id FROM course WHERE id = 3)),--самбо2
       ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT id FROM course WHERE id = 5)),--сервлеты
       ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT id FROM course WHERE id = 7)), --мавен
       ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT id FROM course WHERE id = 8));  --грейдл

INSERT INTO grades(students_course_id, grade)
VALUES (1,5),
       (1,4),
       (1,3),
       (2,4),
       (2,5),
       (3,4),
       (3,5),
       (4,4),
       (5,4),
       (6,5),
       (6,4),
       (7,3),
       (7,4),
       (8,4),
       (8,4),
       (9,4),
       (9,3),
       (10,2),
       (10,3),
       (10,4),
       (11,5);