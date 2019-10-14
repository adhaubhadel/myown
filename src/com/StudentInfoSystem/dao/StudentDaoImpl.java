package com.StudentInfoSystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.StudentInfoSystem.model.Student;


public class StudentDaoImpl implements StudentDao{

	private static final String INSERT_SQL = "insert into student_tbl (first_name, last_name, gender, hobby, dob)values(?, ?,?,?,?)";
	private static final String SELECT_SQL = "select * from student_tbl";
	private static final String DELETE_SQL = "delete from student_tbl where id = ?";
	private static final String UPDATE_SQL = "update student_tbl set first_name = ?, last_name = ?, gender = ?, hobby = ?, dob = ? where id = ?";
	
	class DbConnection {
		
		private static final String DRIVER = "com.mysql.jdbc.Driver";
		private static final String URL = "jdbc:mysql://localhost:3306/student_db";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "root";
		
		private Connection getConnection() throws ClassNotFoundException, SQLException {
			Class.forName(DRIVER);		
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		}
	}
	
	@Override
	public int saveStudent(Student student) {
		int saved = 0;
		try(Connection conn = new DbConnection().getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL);){
			
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getGender());
			ps.setString(4, student.getHobby());
			ps.setDate(5, new Date(student.getDob().getTime()));
			saved = ps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saved;
		
	}

	@Override
	public void deleteStudent(int id) {
		try(Connection conn = new DbConnection().getConnection();
				PreparedStatement ps = conn.prepareStatement(DELETE_SQL)){
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateStudent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> getStudentInfo() {
		List<Student> studentList = new ArrayList();
		try(Connection conn = new DbConnection().getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_SQL)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Student student = new Student();
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setGender(rs.getString("gender"));
				student.setHobby(rs.getString("hobby"));
				student.setDob(rs.getDate("dob"));
				student.setId(rs.getInt("id"));
				studentList.add(student);
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentList;
	}

}
