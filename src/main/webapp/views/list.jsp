<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<center><h1>SSM-CRUD</h1></center>	
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			 <div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					新添
				</button>
				<button class="btn btn-danger">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					删除
				</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr class="success">
						<th>#</th>
						<th>员工姓名</th>
						<th>员工性别</th>
						<th>员工邮箱</th>
						<th>所在部门</th>
						<th>操作</th>
					</tr>
						
					<c:forEach var="emps" items="${pageInfo.list }">
						<tr class="active">
							<th>${emps.empId }</th>
							<th>${emps.empName }</th>
							<th>${emps.gender=="M"?"男":"女" }</th>
							<th>${emps.email }</th>
							<th>${emps.department.deptName }</th>
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil " aria-hidden="true"></span>
									修改
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-12">
				
					111
				
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-12">
				
					<nav aria-label="Page navigation">
						<ul class="pagination pagination-lg">
							<li><a href="#">首页</a></li>
							<li>
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li>
								<a href="#" aria-label="Next"> 
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
							<li><a href="#">末页</a></li>
						</ul>
					</nav>
				
			</div>
		</div>
	</div>
</body>
</html>