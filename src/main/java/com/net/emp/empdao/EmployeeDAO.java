package com.net.emp.empdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.JdbcConnection;
import com.net.emp.model.Employee;
import com.net.emp.model.EmployeeSkill;
import com.net.emp.model.common.JdbcDriverSetup;

import org.apache.taglibs.standard.extra.spath.Step;

public class EmployeeDAO {
	private static JdbcDriverSetup jdbcConnection;

	public static ArrayList<EmployeeSkill> getEmployeeSkill(EmployeeSkill empSkill) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeSkill> listEmpSkill = null;
		try {
			con = jdbcConnection.getJdbcConnection();

			String sqlSkill = "select skillMasterId,name from skillmaster";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlSkill);
			listEmpSkill = new ArrayList<EmployeeSkill>();
			while (rs.next()) {
				System.out.println(rs.getInt("skillMasterId") + "," + rs.getString("name"));
				empSkill = new EmployeeSkill();
				empSkill.setSkillMasterId(rs.getInt("skillMasterId"));
				empSkill.setName(rs.getString("name"));

				listEmpSkill.add(empSkill);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(listEmpSkill);
		return listEmpSkill;
	}

	public static int saveEmployee(Employee employee) {
		Connection con = null;
		PreparedStatement preStatement = null;
		int saveData = 0;
		try {
			con = jdbcConnection.getJdbcConnection();
			
			String insertEmpQuery = "INSERT INTO employee(name,address,gender,salary,birthDate) VALUES(?,?,?,?,?)";
			String insertEmpSkillQuery = "INSERT INTO employeeskill(employeeId,skillMasterId) VALUES(?,?)";
	
			preStatement = con.prepareStatement(insertEmpQuery);
			preStatement.setString(1, employee.getName());
			preStatement.setString(2, employee.getAddress());
			preStatement.setByte(3, employee.getGender());
			preStatement.setDouble(4, employee.getSalary());
			preStatement.setString(5, employee.getBirthDate());
			saveData = preStatement.executeUpdate();
			System.out.println(saveData + " employee data inserted.");
			preStatement.close();
			
			int empId = 0;
			String getEmpIdQuery="select employeeId from employee";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(getEmpIdQuery);
			while (rs.next()) {
				empId=rs.getInt("employeeId");
			}
			employee.setEmployeeId(empId);
			rs.close();st.close();
			
		
			byte empSkill = 0;
			String skill;
			String[] str=employee.getSkillId();
			for(int i=0;i<str.length;i++) {
				skill=str[i];
				empSkill=Byte.parseByte(skill);
				preStatement = con.prepareStatement(insertEmpSkillQuery);
				preStatement.setInt(1, employee.getEmployeeId());
				preStatement.setByte(2, empSkill);
				saveData=preStatement.executeUpdate();
			}
			
			System.out.println(saveData+" emp skills data inserted");
			preStatement.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return saveData;
	}
	
	public static int updateEmployee(Employee employee) {
		Connection con = null;
		PreparedStatement preStatement = null;
		int saveData = 0;
		try {
			con = jdbcConnection.getJdbcConnection();
			
			String updateEmpQuery = "update employee set name=?,address=?,gender=?,salary=?,birthDate=? where employeeId=?";
			String insertEmpSkillQuery = "INSERT INTO employeeskill(employeeId,skillMasterId) VALUES(?,?)";
	
			preStatement = con.prepareStatement(updateEmpQuery);
			preStatement.setString(1, employee.getName());
			preStatement.setString(2, employee.getAddress());
			preStatement.setByte(3, employee.getGender());
			preStatement.setDouble(4, employee.getSalary());
			preStatement.setString(5, employee.getBirthDate());
			preStatement.setInt(6, employee.getEmployeeId());
			saveData = preStatement.executeUpdate();
			System.out.println(saveData + " employee data updated.");
			preStatement.close();
			
			
			byte empSkill = 0;
			String skill;
			String[] str=employee.getSkillId();
			
			if(str.length>0) {
				String deleteQuery="delete from employeeskill where employeeId=?";
				preStatement=con.prepareStatement(deleteQuery);
				preStatement.setInt(1, employee.getEmployeeId());
				preStatement.executeUpdate();
				preStatement.close();
				
				for(int i=0;i<str.length;i++) {
					skill=str[i];
					empSkill=Byte.parseByte(skill);
					System.out.println("Skills---->>>"+empSkill);
					preStatement = con.prepareStatement(insertEmpSkillQuery);
					preStatement.setInt(1, employee.getEmployeeId());
					preStatement.setByte(2, empSkill);
					saveData=preStatement.executeUpdate();
					System.out.println(saveData+"new emp skills data inserted");
					preStatement.close();
				}
				
			}
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return saveData;
	}
	
	
	public static List<Employee> getAllEmployee(int start,int total) throws Exception {
		List<Employee> empList=new ArrayList<>();
		Connection con=jdbcConnection.getJdbcConnection();
		String getEmpQuery="select * from employee limit "+(start-1)+","+total+"";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(getEmpQuery);
		while(rs.next()) {
			Employee emp=new Employee();
			emp.setEmployeeId(rs.getInt("employeeId"));
			emp.setName(rs.getString("name"));
			emp.setAddress(rs.getString("address"));
			emp.setGender(rs.getByte("gender"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setBirthDate(rs.getString("birthDate"));
			empList.add(emp);
		}
		return empList;
	}
	
	public static int deleteEmployee(int empId) throws Exception {
		int status=0;
		PreparedStatement ps;
		Connection con=jdbcConnection.getJdbcConnection();
		String deleteQuery="delete from employee where employeeId=?";
		ps=con.prepareStatement(deleteQuery);
		ps.setInt(1, empId);
		status=ps.executeUpdate();
		System.out.println(status+"emp deleted");
		ps.close();
		con.close();
		
		String deleteSkillQuery="delete from employeeSkill where employeeId=?";
		ps=con.prepareStatement(deleteSkillQuery);
		ps.setInt(1, empId);
		status=ps.executeUpdate();
		System.out.println(status+"emp skills deleted");
		ps.close();
		con.close();
		
		return status;
	}
	
	public static Employee getEmployeeById(int eid) throws Exception {
		Employee employee = null;
		PreparedStatement ps = null;
		ResultSet rs;
		Connection con=jdbcConnection.getJdbcConnection();
		String getEmpQuery="select name,address,gender,salary,birthDate from employee where employeeId=?";
		ps=con.prepareStatement(getEmpQuery);
		ps.setInt(1, eid);
		rs=ps.executeQuery();
		while(rs.next()) {
			employee=new Employee();
			employee.setName(rs.getString("name"));
			employee.setAddress(rs.getString("address"));
			employee.setGender(rs.getByte("gender"));
			employee.setSalary(rs.getDouble("salary"));
			employee.setBirthDate(rs.getString("birthDate"));
			System.out.println("Employee---->>"+employee);
		}
		rs.close();
		ps.close();
		return employee;
	}
	
	public static List<Employee> getEmployeeSkillById(int eid) throws Exception {
		List<Employee> employeeList = null;
		Employee employee;
		PreparedStatement ps = null;
		ResultSet rs;
		Connection con=jdbcConnection.getJdbcConnection();
		String getEmpSkillQuery="SELECT sm.skillMasterId,sm.name,CASE WHEN es.skillMasterId IS NULL THEN 'false' ELSE 'true' END AS isChecked"
								+ " FROM skillmaster sm LEFT OUTER JOIN employeeskill es ON sm.skillMasterId=es.skillMasterId AND es.employeeId=?";
		ps=con.prepareStatement(getEmpSkillQuery);
		ps.setInt(1, eid);
		rs=ps.executeQuery();
		employeeList=new ArrayList<Employee>();
		while(rs.next()) {
			employee=new Employee();
			employee.setSkillMasterId(rs.getByte("skillMasterId"));
			employee.setSkillName(rs.getString("name"));
			employee.setIsChecked(rs.getString("isChecked"));;
			employeeList.add(employee);
			System.out.println("EmpSkill--->>"+employeeList);
		}
		rs.close();ps.close();
		return employeeList;
	}
}
