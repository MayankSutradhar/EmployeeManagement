<%@page import="com.net.emp.model.Employee"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.net.emp.model.EmployeeSkill"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.net.emp.empdao.EmployeeDAO"%>
<%@page isELIgnored="false"%>
<jsp:useBean id="empbean" class="com.net.emp.model.EmployeeSkill"></jsp:useBean>
<jsp:setProperty property="*" name="empbean" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
function validationName(){
	var getTxtName=document.getElementById("txtName").value;
	if(getTxtName != "[A-Za-z]"){
		document.getElementById("txtname").innerHTML="Name must have value.";
	}
}
function validationBirth(){
	var getTxtBirth=document.getElementById("txtBirth").value;
	if(getTxtBirth != "[0-9/-]"){
		document.getElementById("txtbirth").innerHTML="1-Must have value 2-Age must be > 15 years.";
	}
}
function validationSalary(){
	var getTxtSal=document.getElementById("txtSalary").value;
	if(isNaN(getTxtSal)){
		document.getElementById("txtsal").innerHTML="Only number allowed Max length = 9.";
	}
}
function validationAddress(){
		var getTxtAddress=document.getElementById("txtAddress").value;
		if(getTxtAddress!="[A-Za-z0-9]"){
			document.getElementById("txtaddress").innerHTML="Max limit is 150 characters.";
		}
	}
</script>
</head>
<body>
	<jsp:include page="/index.jsp" />
	<%
	List getEmp = EmployeeDAO.getEmployeeSkill(empbean);
	System.out.println("EmpSkillList---->" + getEmp);
	request.setAttribute("list", getEmp);
	String massage = (String) request.getAttribute("massage");
	System.out.println("Message--->>" + massage);
	String empId = request.getParameter("id");
	int id=0;
	Employee eid = null;
	List<Employee> skill=null;
	if (empId != null) {
		id = Integer.parseInt(empId);
		eid = EmployeeDAO.getEmployeeById(id);
		skill=EmployeeDAO.getEmployeeSkillById(id);
		System.out.println("skill---"+skill);
	}
	request.setAttribute("skill", skill);
	%>
	<div class="container-fluid" align="center" style="margin-top: 50px;">
		<div class="col-sm-8">
		<c:choose>
			<c:when test="<%=id == 0%>">
				<form action="EmployeeServlet" method="post">
					<table  class="table table-borderless">
						<tr class="table-info">
							<td colspan="2" align="center"><h4 style="color: green;">${massage}</h4>
								<h2>Add New Employee</h2></td>
						</tr>
						<tr>
							<td><label>Name</label></td>
							<td><input class="input-group mb-3" type="text" name="txtname" required="required" id="txtName" onkeyup="validationName()">
							<span style="color: red;" id="txtname"></span>
							</td>
						</tr>
						<tr>
							<td><label>Birth Date</label></td>
							<td><input class="input-group mb-3" type="date" name="dtdob" required="required" id="txtBirth"  onkeyup="validationBirth()">
								<span style="color: red;" id="txtbirth"></span>
							</td>
						</tr>
						<tr>
							<td><label>Address</label></td>
							<td><textarea class="input-group mb-3" rows="3" cols="20" name="txtaddress" maxlength="150" required="required" id="txtAddress"  onkeyup="validationAddress()"></textarea>
								<span style="color: red;" id="txtaddress"></span>
							</td>
						</tr>
							<tr>
							<td><label>Gender</label></td>
							<td>
								<input type="radio" name="rdgender" value="1" required="required">Male 
								<input type="radio" name="rdgender" value="2" required="required">Female
							</td>
						</tr>
						<tr>
							<td><label>Skill Id</label></td>
							<td>
								<c:forEach items="${list}" var="list">
									<input type="checkbox" name="chkname" value="${list.getSkillMasterId()}">${list}
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td><label>Salary</label></td>
							<td><input class="input-group mb-3" type="text" name="txtsalary" required="required" maxlength="9" id="txtSalary" onkeyup="validationSalary()">
							<span style="color: red;" id="txtsal"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="Save" class="btn btn-primary">
								<input type="reset" value="Clear" class="btn btn-primary">
							</td>
						</tr>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<form action="EmployeeServlet" method="post">
				<table  class="table table-borderless">
					<tr>
						<td colspan="2" align="center"><h4 style="color: green;">${massage}</h4></td>
					</tr>
					<tr>
						<td><input type="hidden" name="txtid" value="<%=id%>">
						</td>
					</tr>
					<tr>
						<td><label>Name</label></td>
						<td><input class="input-group mb-3" type="text" name="txtname" required="required" id="txtName" onkeyup="validationName()" value="<%=eid.getName()%>">
							<span style="color: red;" id="txtname"></span>
						</td>
					</tr>
					<tr>
						<td><label>Birth Date</label></td>
						<td><input class="input-group mb-3" type="date" name="dtdob" required="required" id="txtBirth" value="<%=eid.getBirthDate()%>" onkeyup="validationBirth()">
							<span style="color: red;" id="txtbirth"></span>
						</td>
					</tr>
					<tr>
						<td><label>Address</label></td>
						<td><textarea class="input-group mb-3" rows="3" cols="20" name="txtaddress"  maxlength="150" onkeyup="validationAddress()" id="txtAddress" required="required"><%=eid.getAddress()%></textarea>
							<span style="color: red;" id="txtaddress"></span>
						</td>
					</tr>
					<tr>
						<td><label>Gender</label></td>
						<td>
							<c:if test="<%=eid.getGender() == 1%>">
								<input type="radio" name="rdgender" value=<%=eid.getGender() %> required="required" checked="checked">Male
								<input type="radio" name="rdgender" value="1" required="required">Female
							</c:if> 
							<c:if test="<%=eid.getGender() == 2%>">
								<input type="radio" name="rdgender" value="2" required="required">Male
								<input type="radio" name="rdgender" value=<%=eid.getGender() %> required="required" checked="checked">Female
							</c:if>
						</td>
					</tr>
					<tr>
						<td><label>Skill Id</label></td>
						<td>
							<c:forEach items="${skill}" var="list">
								<c:if test="${list.getIsChecked() == true}">
									<input type="checkbox" name="chkname" checked="checked" value="${list.getSkillMasterId()}">${list.getSkillName()}
								</c:if>
								<c:if test="${list.getIsChecked() == false}">
									<input type="checkbox" name="chkname" value="${list.getSkillMasterId()}">${list.getSkillName()}
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td><label>Salary</label></td>
						<td><input class="input-group mb-3" type="text" name="txtsalary" required="required" maxlength="9" id="txtSalary" value="<%=eid.getSalary()%>" onkeyup="validationSalary()">
							<span style="color: red;" id="txtsal"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							 <input type="submit" value="Update" class="btn btn-primary">
							 <input type="reset" value="Clear" class="btn btn-primary">
						</td>
					</tr>
				</table>
			</form>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
</body>
</html>