package com.StudentInfoSystem.basic;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import com.StudentInfoSystem.dao.StudentDaoImpl;
import com.StudentInfoSystem.model.Student;
import com.toedter.calendar.JDateChooser;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class StudentForm extends JFrame{
	private JTextField firstNameTxtField;
	private JTextField lastNameTextField;
	public JPanel contentPane;
	
	public StudentForm() {
		setAlwaysOnTop(true);
		setLocation(10, -45);
		setTitle("User Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 485, 429);
		setSize(595, 625);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(64, 180, 70, 16);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(64, 235, 70, 16);
		contentPane.add(lblLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(64, 283, 56, 16);
		contentPane.add(lblGender);
		
		JLabel lblHobby = new JLabel("Hobby");
		lblHobby.setBounds(64, 335, 56, 16);
		contentPane.add(lblHobby);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(64, 387, 56, 16);
		contentPane.add(lblDob);
		
		firstNameTxtField = new JTextField();
		firstNameTxtField.setBounds(182, 177, 184, 22);
		contentPane.add(firstNameTxtField);
		firstNameTxtField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(182, 232, 184, 22);
		lastNameTextField.setColumns(10);
		contentPane.add(lastNameTextField);
		
		JRadioButton maleRadioBtn = new JRadioButton("Male");
		maleRadioBtn.setBounds(182, 279, 85, 25);
		contentPane.add(maleRadioBtn);
		
		JRadioButton femaleRadioBtn = new JRadioButton("Female");
		femaleRadioBtn.setBounds(281, 279, 85, 25);
		contentPane.add(femaleRadioBtn);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRadioBtn);
		buttonGroup.add(femaleRadioBtn);
		
		JCheckBox readChkBox = new JCheckBox("Reading");
		readChkBox.setBounds(182, 331, 93, 25);
		contentPane.add(readChkBox);
		
		JCheckBox playChkBox = new JCheckBox("Playing");
		playChkBox.setBounds(281, 331, 93, 25);
		contentPane.add(playChkBox);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(182, 381, 184, 22);
		contentPane.add(dateChooser);
		
		JButton submitBtn = new JButton("Sbmit");
		submitBtn.setBounds(269, 453, 97, 25);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// creating the object of the class that will execute the query
				StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
				
				// creating the student object whose records will be captured and inserted into db
				Student student = new Student();
				student.setFirstName(firstNameTxtField.getText());
				student.setLastName(lastNameTextField.getText());
				if(maleRadioBtn.isSelected()){
					student.setGender("Male");
				}else{
					student.setGender("Female");
				}
				if(readChkBox.isSelected() && playChkBox.isSelected()){
					student.setHobby("Reading, Playing");
				}
				if(readChkBox.isSelected()){
					student.setHobby("Reading");
				}if(playChkBox.isSelected()){
					student.setHobby("Playing");
				}
				student.setDob(dateChooser.getDate());
				
				int saved = studentDaoImpl.saveStudent(student);
				if(saved !=0){
					JOptionPane.showMessageDialog(new StudentForm(), "User Info is saved successfully", "Saved", JOptionPane.PLAIN_MESSAGE);
					dispose();
				}else{ 
					JOptionPane.showMessageDialog(new StudentForm(), "Ooops!!! Error Occured in db", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}
		});
		contentPane.add(submitBtn);
		
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(64, 453, 97, 25);
		contentPane.add(refreshBtn);
	}
}
