package fr.epita.services.dto;

import fr.epita.datamodel.Student;

public class StudentDTO {
	
	private Integer id;
	
	private String StudentName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public Student toDataModel() {
		Student student = new Student();
		student.setStudentId(id);
		student.setStudentName(StudentName);
		return student;
	}
	
	public void fromDataModel(Student student) {
		this.StudentName = student.getStudentName();
		this.id = student.getStudentId();
	}
}
