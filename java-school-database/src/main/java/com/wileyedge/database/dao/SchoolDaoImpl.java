package com.wileyedge.database.dao;

import com.wileyedge.database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SchoolDaoImpl implements SchoolDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SchoolDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    * Add each SQL statement to the methods below.
    * The SQL statement must be completely inside the quotation marks provided
    * in the existing Java statement:
    *   String sql = "";
    *
    * Special notes:
    *   - Strings must be inside single quotation marks (' ').
    *   - Strings are case-sensitive.
    *   - Semi-colons are optional at the end of the SQL statement.
    *
    * Do not change any code outside of the placeholders provided.
     */

    @Override
    public List<Student> allStudents() {
        // Write a query that returns all students (first name, last name only)
        // sorted by last name.
        // YOUR CODE STARTS HERE

        String sql = "SELECT student.fName, student.lName FROM student ORDER BY lName; ";

        // YOUR CODE ENDS HERE

        return jdbcTemplate.query(sql, new StudentMapper());
    }

    @Override
    public List<Course> csCourses() {
        // Write a query that lists the course code and course name
        // for all courses in the Computer Science department.
        // YOUR CODE STARTS HERE

         String sql = "SELECT\n" +
                 "\tcourse.courseCode,\n" +
                 "\tcourse.courseDesc\n" +
                 "\tFROM\n" +
                 "\tcourse\n" +
                 "\tJOIN teacher ON course.teacherId = teacher.tid\n" +
                 "\tWHERE\n" +
                 "\tteacher.dept = 'Computer Science';";

        // YOUR CODE ENDS HERE
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public List<TeacherCount> teacherCountByDept() {
        //  Write a query that displays the department and the total number of teachers assigned to each department.
        //  Name the aggregate field `teacherCount`.
        // YOUR CODE STARTS HERE

        String sql = "SELECT dept, COUNT(*) As teacherCount\n" +
                "FROM teacher\n" +
                "GROUP BY dept;";

        // YOUR CODE ENDS HERE
        return jdbcTemplate.query(sql, new TeacherCountMapper());
    }

    @Override
    public List<StudentClassCount> studentsPerClass() {
        // Write a query that lists the course code and course description for each course,
        // with the number of students enrolled in each course.
        // Name the aggregate field `numStudents`.
        // YOUR CODE STARTS HERE

        String sql = "SELECT\n" +
                "  course.courseCode,\n" +
                "  course.courseDesc,\n" +
                "  COUNT(course_student.student_id) AS numStudents\n" +
                "FROM\n" +
                "  course\n" +
                "  LEFT JOIN course_student ON course.cid = course_student.course_id\n" +
                "GROUP BY\n" +
                "  course.courseCode, course.courseDesc;";

        // YOUR CODE ENDS HERE
        return jdbcTemplate.query(sql, new StudentCountMapper());
    }

    // This step includes two parts. Both parts must be completed to pass the test.
    // Create a new student and enroll the new student in a course
     @Override
    public void addStudent() {
        // Part 1: Write a query to add the student Robert Dylan to the student table.
        // YOUR CODE STARTS HERE

        String sql = "INSERT INTO student (fName, lName)\n" +
                "VALUES ('Robert', 'Dylan');";

        // YOUR CODE ENDS HERE
         System.out.println(jdbcTemplate.update(sql));

    }

    @Override
    public void addStudentToClass() {
        // Part 2: Write a query to add Robert Dylan to CS148.
        // YOUR CODE STARTS HERE

        String sql = "INSERT INTO course_student (student_id, course_id)\n" +
                "VALUES (\n" +
                "    (SELECT sid FROM student WHERE fName = 'Robert' AND lName = 'Dylan'),\n" +
                "    (SELECT cid FROM course WHERE courseCode = 'CS148')\n" +
                ");";

        // YOUR CODE ENDS HERE
        jdbcTemplate.update(sql);
    }

    @Override
    public void editCourseDescription() {
        // Write a query to change the course description for course CS305 to "Advanced Python with Flask".
        // YOUR CODE STARTS HERE

        String sql = "UPDATE course\n" +
                "SET courseDesc = 'Advanced Python with Flask'\n" +
                "WHERE courseCode = 'CS305';";

        // YOUR CODE ENDS HERE
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteTeacher() {
        // Write a query to remove David Mitchell as a teacher.
        // YOUR CODE STARTS HERE

        String sql = "DELETE FROM teacher\n" +
                "WHERE tid IN (\n" +
                "    SELECT tid\n" +
                "    FROM teacher\n" +
                "    WHERE tFName = 'David' AND tLName = 'Mitchell'\n" +
                ")";

        // YOUR CODE ENDS HERE
        jdbcTemplate.update(sql);
    }

    //***** EXTRA HELPER METHODS
    //***** DO NOT CHANGE THE SQL STRING IN THESE METHODS!!!
    @Override
    public List<Teacher> listAllTeachers() {
        String sql = "Select * from Teacher;";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }

    @Override
    public List<Student> studentsCS148() {
        String sql = "select fname, lname\n" +
                "from student s \n" +
                "join course_student cs on s.sid = cs.student_id\n" +
                "where course_id = 1;";
        return jdbcTemplate.query(sql, new StudentMapper());
    }
}
