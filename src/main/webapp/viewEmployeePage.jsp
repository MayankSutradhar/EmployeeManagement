<%@page import="com.net.emp.empdao.EmployeeDAO"%>
<%@page import="com.net.emp.model.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Employee List</title>
</head>
<body>
<jsp:include page="/index.jsp"/>
	<div class="container-fluid">
		<%
		String strpageid=request.getParameter("page");
		int pageid=0;
		int total=5;
		
		if(strpageid==null){
			pageid=1;
		}
		
		if(pageid==1){
			
		}else{
			pageid=Integer.parseInt(strpageid);
			pageid=pageid-1;
			pageid=pageid*total+1;
		}
		String massage = (String) request.getAttribute("massage");
		List<Employee> list = EmployeeDAO.getAllEmployee(pageid,total);
		request.setAttribute("list", list);
		%>
		<div align="center" style="margin-top: 50px;">
			<h4 style="color: green;">${massage}</h4>
			<table border="1" style="width: 70%;text-align: center;"  class="table table-bordered table-sm">
				<tr class="table-info"><td colspan="8" align="center"><h2>Employee List</h2></td></tr>
				<tr class="table-success">
					<th>Employee Id</th>
					<th>Name</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Salary</th>
					<th>Birth date</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${list}" var="list">
					<tr>
						<td>${list.employeeId}</td>
						<td>${list.name}</td>
						<td>${list.address}</td>
						<td>${list.gender}</td>
						<td>${list.salary}</td>
						<td>${list.birthDate}</td>
						<td><a href="addEmployeePage.jsp?id=${list.employeeId}">Edit</a></td>
						<td><a onclick="return confirm('Are you sure you want to delete this item')" href="DeleteServlet?id=${list.employeeId}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="viewEmployeePage.jsp?page=1">1</a>
			<a href="viewEmployeePage.jsp?page=2">2</a>
			<a href="viewEmployeePage.jsp?page=3">3</a>
		</div>
	</div>
</body>
</html>