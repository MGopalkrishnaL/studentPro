package com.Wipro.Student.repository;

import com.Wipro.Student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentId(long studentId);

    Student findByStudentId(long studentId);

    void deleteByStudentId(Long studentId);

    @Query(value = "SELECT * FROM student_tbl WHERE first_name LIKE :name OR last_name LIKE :name", nativeQuery = true)
    List<Student> getListByName(String name);
}
