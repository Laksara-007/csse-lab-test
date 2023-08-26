package com.csse.models;

/* 
 * this class is used to store the employee details
 */
public class Employee {

	public String id;
	public String fullName;
	public String address;
	public String faculty;
	public String department;
	public String designation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/*
	 * this method is used to display the employee details. this overides the
	 * toString() method
	 */
	@Override
	public String toString() {
		return "Employee ID = " + id + "\n" + "FullName = " + fullName + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + faculty + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}
}
