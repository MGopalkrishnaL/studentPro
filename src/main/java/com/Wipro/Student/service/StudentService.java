package com.Wipro.Student.service;

import com.Wipro.Student.DTO.RequestData;
import com.Wipro.Student.model.Address;
import com.Wipro.Student.model.Student;
import com.Wipro.Student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final AddressService addressService;

    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    public ResponseEntity<String> saveStudent(RequestData requestData) {
        Student student = new Student();
        Address address = new Address();
        student.setFirstName(requestData.getFirstName());
        student.setLastName(requestData.getLastName());
        student.setPhoneNumber(requestData.getPhoneNumber());
        address.setCity(requestData.getCity());
        address.setColony(requestData.getColony());
        address.setCountry(requestData.getCountry());
        address.setDoorNumber(requestData.getDoorNumber());
        address.setPinCode(requestData.getPinCode());
        addressService.saveAddress(address);
        student.setAddress(address);
        studentRepository.save(student);
        return new ResponseEntity<>("student saved successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateStudent(Long studentId, RequestData requestData) {
        if (studentRepository.existsByStudentId(studentId)) {
            Student student = studentRepository.findByStudentId(studentId);
            student.setStudentId(studentId);
            student.setFirstName(requestData.getFirstName());
            student.setLastName(requestData.getLastName());
            student.setPhoneNumber(requestData.getPhoneNumber());
            studentRepository.save(student);
            return new ResponseEntity<>("student Updated successfully", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Student with the id has not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteStudent(Long StudentId) {
        if (studentRepository.existsByStudentId(StudentId)) {
            studentRepository.deleteByStudentId(StudentId);
            return new ResponseEntity<>("student deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("student with the following id is not present", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Student> getStudentById(Long studentId) {
        if (studentRepository.existsByStudentId(studentId)) {
            return new ResponseEntity<>(studentRepository.findByStudentId(studentId), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Student>> getStudentByName(String name) {
        System.out.println(studentRepository.getListByName(name));
        return new ResponseEntity<>(studentRepository.getListByName(name), HttpStatus.FOUND);
    }
}
