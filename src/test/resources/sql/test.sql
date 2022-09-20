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

--2.Получить текущий средний бал студента по всем курсам (аналог диплома)
select avg(gg)
from (select s.id student, c.id course, avg(g.grade) gg
      from student s
               join students_course sc on s.id = sc.student_id
               join course c on c.id = sc.course_id
               join grades g on sc.id = g.students_course_id
      group by c.id, s.id
      order by s.id) ss
where ss.student = 1;


--3.Получить финальную оценку по курсу
select avg(g.grade)
from student s
         join students_course sc on s.id = sc.student_id
         join course c on c.id = sc.course_id
         join grades g on sc.id = g.students_course_id
where s.id = 1
  and c.id = 9;

--3.1.Получить всех студентов на всех курсов и их средний бал по предмету
select s.id, c.id, avg(g.grade)
from student s
         join students_course sc on s.id = sc.student_id
         join course c on c.id = sc.course_id
         join grades g on sc.id = g.students_course_id
group by c.id, s.id
order by s.id;


--4.Получает |ИМЯ Teacher /
-- Суммарное количество студентов по всем курсам /
-- Средняя успеваемость студентов по всем курсам |
select t.name, count(distinct (s.id)), round(avg(g.grade), 2)
from teacher t
         join course c on t.id = c.teacher_id
         join students_course sc on c.id = sc.course_id
         join student s on s.id = sc.student_id
         join grades g on sc.id = g.students_course_id
group by t.name;





