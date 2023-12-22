-- DROP DATABASE IF EXISTS school;

-- create database school;
-- use school;

-- Table structure for student

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  studentId int AUTO_INCREMENT PRIMARY KEY,
  studentFirstName varchar(255) DEFAULT NULL,
  studentLastName varchar(255) DEFAULT NULL
);

-- Table structure for teacher

DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher (
  teacherId int AUTO_INCREMENT PRIMARY KEY,
  teacherFirstName varchar(255) DEFAULT NULL,
  teacherLastName varchar(255) DEFAULT NULL,
  dept varchar(255) DEFAULT NULL
);


-- Table structure for course

DROP TABLE IF EXISTS course;
CREATE TABLE course (
  courseId int AUTO_INCREMENT PRIMARY KEY,
  courseCode varchar(255) DEFAULT NULL,
  courseDesc varchar(255) DEFAULT NULL,
  teacherId int DEFAULT NULL
);

-- Table structure for course_student

DROP TABLE IF EXISTS course_student;
CREATE TABLE course_student (
  courseId int,
  studentId int
);
