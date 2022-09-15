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

INSERT INTO students_course(student_id, course_id, grade)
VALUES ((SELECT id FROM student WHERE phone = '+79782106200'), (SELECT number FROM course WHERE number = 111), 4),--матан
       ((SELECT id FROM student WHERE phone = '+79782106200'), (SELECT number FROM course WHERE number = 888), 3), --алгоритмы
       ((SELECT id FROM student WHERE phone = '+79782106201'), (SELECT number FROM course WHERE number = 888), 3),
       ((SELECT id FROM student WHERE phone = '+79782106201'), (SELECT number FROM course WHERE number = 555), 4), --спринг
       ((SELECT id FROM student WHERE phone = '+79782106202'), (SELECT number FROM course WHERE number = 555), 5), --спринг
       ((SELECT id FROM student WHERE phone = '+79782106202'), (SELECT number FROM course WHERE number = 333), 5),--джава
       ((SELECT id FROM student WHERE phone = '+79782106203'), (SELECT number FROM course WHERE number = 222), 5),--самбо1
       ((SELECT id FROM student WHERE phone = '+79782106203'), (SELECT number FROM course WHERE number = 999), 5),--самбо2
       ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT number FROM course WHERE number = 444), 4),--сервлеты
       ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT number FROM course WHERE number = 666), 5), --мавен
       ((SELECT id FROM student WHERE phone = '+79782106204'), (SELECT number FROM course WHERE number = 777), 4); --грейдл

