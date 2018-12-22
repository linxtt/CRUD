<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
}

body {
	background:
		url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544509339504&di=03a5303acadec0713be5d4159cd72e52&imgtype=0&src=http%3A%2F%2Fbbsfiles.vivo.com.cn%2Fvivobbs%2Fattachment%2Fforum%2F201601%2F06%2F113353lk9zq9m7ghxzkkjk.jpg")
		no-repeat;
	background-size: 100%;
}

.main {
	/*text-align: center;*/
	/*让div内部文字居中*/
	border-radius: 20px;
	width: 300px;
	height: 350px;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
}
</style>
</head>
<script type="text/javascript">
	function check(){
		var userCode = $("#userCode").val();
		var userPassword = $("#userPassword").val();
		if(usercode==""||password==""){
			$("#message").text("账号或密码不能为空！");
			return false;
		}
		return true;
	}
</script>
<body>

	<div class="main">
			<h2><b>BOOT CRUD</b></h2>
			<p>员工管理系统</p>
			<form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post" onsubmit="return check()">
				<div class="form-group">
					<h4><span class="label label-primary">UserCode</span></h4>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="userCode" name="userCode" placeholder="UserCode">
					</div>
				</div>
				<div class="form-group">
					<h4><span class="label label-primary">Password</span></h4>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="userPassword" name=userPassword placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Sign in</button>
					</div>
				</div>
			</form>
			<font color="red">
				<span id = "message">${msg}</span>
			</font>
		</div>


</body>

</html>