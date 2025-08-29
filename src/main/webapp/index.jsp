<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeeManagementSystem</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

		<h2>Employee Management System</h2>
		<nav class="navbar navbar-expand-sm bg-light navbar-light">
  			<div class="container-fluid">
    			<ul class="navbar-nav">
     			 <li class="nav-item">
       		  		<a style="text-decoration: none;" href="addEmployeePage.jsp">Add Employee</a> 
     			 </li>
		     	 <li class="nav-item">
		       		&nbsp;&nbsp; <a style="text-decoration: none;" href="viewEmployeePage.jsp?page=1">View Employee</a>
		      	 </li>
		         <li class="nav-item">
		        	<span style="margin-left: 950px;">Current Date : <%=Calendar.getInstance().getTime() %></span>
		         </li>
    		    </ul>
 			 </div>
</nav>
				
		<hr>

</body>
</html>