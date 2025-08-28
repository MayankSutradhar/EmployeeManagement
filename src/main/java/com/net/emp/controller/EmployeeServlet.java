package com.net.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.emp.empdao.EmployeeDAO;
import com.net.emp.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		//request.getRequestDispatcher("/views/addEmployeePage.jsp").include(request, response);
		PrintWriter out = response.getWriter();
		String massage;
		String txtEmpId = request.getParameter("txtid");
		String txtEmpName = request.getParameter("txtname");
		String dtEmpDob = request.getParameter("dtdob");
		String txtAddress = request.getParameter("txtaddress");
		String rdGender = request.getParameter("rdgender");
		String chkEmpSkills[] = request.getParameterValues("chkname");
		String txtEmpSalary = request.getParameter("txtsalary");
		System.out.println("Name--->"+txtEmpName);
		byte rdGender1 = Byte.parseByte(rdGender);
		String skill = "";
		byte skillId = 0;
		
		for(int i=0;i<chkEmpSkills.length;i++) {
			skill=chkEmpSkills[i];
			skillId= Byte.parseByte(skill);
		}
		System.out.println("SkillID--"+skillId);
		double salary = Double.parseDouble(txtEmpSalary);
		System.out.println("ID--->"+txtEmpId);
		Employee emp;
		if(txtEmpId==null || txtEmpId=="") {
			emp = new Employee(txtEmpName, dtEmpDob, txtAddress, rdGender1, chkEmpSkills, salary);
			int input=EmployeeDAO.saveEmployee(emp);
			System.out.println(input);
			if(input>0) {
				massage="Data inserted successfully";
				request.setAttribute("massage", massage);
				//response.sendRedirect("views/addEmployeePage.jsp");
				request.getRequestDispatcher("addEmployeePage.jsp").include(request, response);
			} else {
				massage="Invalid Input";
				request.setAttribute("massage", massage);
				request.getRequestDispatcher("addEmployeePage.jsp").include(request, response);
			}
		}else {
			int eid=Integer.parseInt(txtEmpId);
			emp = new Employee(eid,txtEmpName, dtEmpDob, txtAddress, rdGender1, chkEmpSkills, salary);
			int input=EmployeeDAO.updateEmployee(emp);
			System.out.println(input);
			if(input>0) {
				massage="Data updated successfully";
				request.setAttribute("massage", massage);
				//response.sendRedirect("views/addEmployeePage.jsp");
				request.getRequestDispatcher("viewEmployeePage.jsp?page=1").include(request, response);
			} else {
				massage="Invalid Input";
				request.setAttribute("massage", massage);
				request.getRequestDispatcher("addEmployeePage.jsp").include(request, response);
			}
		}
	}

}
