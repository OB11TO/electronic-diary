--ПЛОХА В РЕАЛИЗАЦИИ
-- CREATE TABLE IF NOT EXISTS teachers_course
-- (
--     id BIGSERIAL PRIMARY KEY,
--     teacher_id BIGINT REFERENCES teacher (id) ON DELETE CASCADE,
--     course_id BIGINT NOT NULL REFERENCES course (id) ON DELETE CASCADE,
--     student_id BIGINT NOT NULL REFERENCES student (id) ON DELETE CASCADE,
--     UNIQUE (teacher_id, course_id, student_id)
-- );

--ПЛОХА В РЕАЛИЗАЦИИ
-- INSERT INTO teachers_course(teacher_id, course_id, student_id)
-- VALUES ((SELECT id FROM teacher WHERE phone = '+79782106210'), (SELECT id FROM course WHERE number = 1),
--         (SELECT id FROM student WHERE phone = '+79782106200')), --матан
--        ((SELECT id FROM teacher WHERE phone = '+79782106210'), (SELECT id FROM course WHERE number = 1),
--         (SELECT id FROM student WHERE phone = '+7978210620'));
-- --матан
--        ((SELECT id FROM teacher WHERE phone = '+79782106210'), (SELECT id FROM course WHERE number = 8)), -- алгоритмы
--        ((SELECT id FROM teacher WHERE phone = '+79782106211'), (SELECT id FROM course WHERE number = 5)), --спринг
--        ((SELECT id FROM teacher WHERE phone = '+79782106212'), (SELECT id FROM course WHERE number = 5)), --спринг
--        ((SELECT id FROM teacher WHERE phone = '+79782106213'), (SELECT id FROM course WHERE number = 2)), --самбо1
--        ((SELECT id FROM teacher WHERE phone = '+79782106214'), (SELECT id FROM course WHERE number = 9)), --самбо2
--        ((SELECT id FROM teacher WHERE phone = '+79782106215'), (SELECT id FROM course WHERE number = 4)), --сервлеты
--        ((SELECT id FROM teacher WHERE phone = '+79782106216'), (SELECT id FROM course WHERE number = 6)), --мавен
--        ((SELECT id FROM teacher WHERE phone = '+79782106217'), (SELECT id FROM course WHERE number = 7)), --грейдл
--        (null, (SELECT id FROM course WHERE number = 3)); -- джава