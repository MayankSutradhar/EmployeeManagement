package com.net.emp.model;

import java.util.Arrays;

public class Employee {
	private int employeeId;
	private String name;
	private String birthDate;
	private String Address;
	private byte gender;
	private byte skillMasterId;
	private String skillName;
	private String[] skillId;
	private double salary;
	private String isChecked;

	public Employee(String name, String birthDate, String address, byte gender, String[] skillId, double salary) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		Address = address;
		this.gender = gender;
		this.skillId = skillId;
		this.salary = salary;
	}

	public Employee(int employeeId, String name, String birthDate, String address, byte gender, String[] skillId,
			double salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.birthDate = birthDate;
		this.Address = address;
		this.gender = gender;
		this.skillId = skillId;
		this.salary = salary;
	}

	public Employee(String name, String birthDate, String address, byte gender, byte skillMasterId, double salary) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		Address = address;
		this.gender = gender;
		this.skillMasterId = skillMasterId;
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String[] getSkillId() {
		return skillId;
	}

	public void setSkillId(String[] skillId) {
		this.skillId = skillId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public byte getSkillMasterId() {
		return skillMasterId;
	}

	public void setSkillMasterId(byte skillMasterId) {
		this.skillMasterId = skillMasterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", birthDate=" + birthDate + ", Address="
				+ Address + ", gender=" + gender + ", skillMasterId=" + skillMasterId + ", skillName=" + skillName
				+ ", skillId=" + Arrays.toString(skillId) + ", salary=" + salary + ", isChecked=" + isChecked + "]";
	}



}
