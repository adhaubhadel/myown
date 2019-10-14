package com.StudentInfoSystem.dao;

import java.util.List;

import com.StudentInfoSystem.model.Student;

public interface StudentDao {
	
	public int saveStudent(Student student);
	
	public void deleteStudent(int id);
	
	public void updateStudent();
	
	public List<Student> getStudentInfo();
}
