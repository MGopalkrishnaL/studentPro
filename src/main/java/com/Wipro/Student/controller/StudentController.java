package com.Wipro.Student.controller;

import com.Wipro.Student.DTO.RequestData;
import com.Wipro.Student.model.Student;
import com.Wipro.Student.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudent();
    }
    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody RequestData requestData){
        return studentService.saveStudent(requestData);
    }

    @PutMapping("/updateStudentDetails")
    public ResponseEntity<String> updateStudent(@RequestParam Long studentId,@RequestBody RequestData requestData){
        return studentService.updateStudent(studentId,requestData);
    }
    @Transactional
    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/getStudentBySearchByStudentId")
    public ResponseEntity<Student> getStudentById(@RequestParam Long studentId){
        return studentService.getStudentById(studentId);
    }
    @GetMapping("/getStudentListByName")
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam String name){
        System.out.println(name);
        return studentService.getStudentByName(name);
    }
}
