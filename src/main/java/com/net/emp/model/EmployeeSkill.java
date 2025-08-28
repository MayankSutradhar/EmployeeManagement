package com.net.emp.model;

public class EmployeeSkill {
	private int skillMasterId;
	private String name;

	public EmployeeSkill() {
		super();
	}

	public EmployeeSkill(int skillMasterId, String name) {
		super();
		this.skillMasterId = skillMasterId;
		this.name = name;
	}

	public int getSkillMasterId() {
		return skillMasterId;
	}

	public void setSkillMasterId(int skillMasterId) {
		this.skillMasterId = skillMasterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return  name ;
	}

}
