package com.StudentInfoSystem.basic;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.StudentInfoSystem.dao.StudentDao;
import com.StudentInfoSystem.dao.StudentDaoImpl;
import com.StudentInfoSystem.model.Student;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class StudentTable extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	StudentDao studentDao = new StudentDaoImpl();
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnDelete;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentTable frame = new StudentTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	
	public StudentTable() {
		
		setTitle("User Info Table");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 568);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(406, 100, -372, -62);
		String [] columns = {"Id", "First Name", "Last Name", "Gender", "Hobby", "DOB"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		table.setModel(tableModel);
		
		loadDataIntoTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 739, 413);
		contentPane.add(scrollPane);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StudentForm studentForm = new StudentForm();
				studentForm.setVisible(true);
				dispose();
			}
		});
		btnNew.setBounds(69, 467, 97, 25);
		contentPane.add(btnNew);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(233, 467, 97, 25);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					int deleteConfirm = JOptionPane.showConfirmDialog(new StudentTable(), "Are you sure want to delete?", "Delete!!", JOptionPane.YES_NO_OPTION);
					if(deleteConfirm == 0){
						Object id = tableModel.getValueAt(selectedRow, 0);
						studentDao.deleteStudent(Integer.parseInt(id.toString()));
						loadDataIntoTable();
						
					}
					}else{
						JOptionPane.showMessageDialog(new StudentTable(), "please select any row to delete");
				}
				
			}
		});
		btnDelete.setBounds(396, 467, 97, 25);
		contentPane.add(btnDelete);
		
	}		
	
	public void loadDataIntoTable(){
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		List<Student> studentList = studentDao.getStudentInfo();		
		
		for(Student s:studentList){
			tableModel.addRow(new Object [] {s.getId(), s.getFirstName(), s.getLastName(), s.getGender(), s.getHobby(), s.getDob()});
		}
	}

}
