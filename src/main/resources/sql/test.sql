--ТРЕНИРОВОЧНЫЕ ЗАПРОСЫ

--1.Получить список прослушанных курсов
select c.title,
       c.number,
       sc.course_id
from student s
         join students_course sc
              on s.id = sc.student_id
         join course c
              on c.number = sc.course_id
where sc.student_id = 5;

--2.Получить текущий средний балл
select avg(sc.grade)
from students_course sc
where sc.student_id = 1;

--3.Получить финальную оценку
select grade
from students_course sc
where sc.student_id = 1 and sc.course_id = 111;

--4.Получает |ИМЯ Teacher /
-- Суммарное количество студентов по всем курсам /
-- Средняя успеваемость студентов по всем курсам |
select t.name, count(distinct sc.student_id), avg(sc.grade)
from teacher t
join course c on t.id = c.teacher_id
join students_course sc on c.number = sc.course_id
group by t.name

