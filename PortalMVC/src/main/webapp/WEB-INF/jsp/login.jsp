<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
*, body {
	-webkit-font-smoothing: antialiased;
	text-rendering: optimizeLegibility;
	-moz-osx-font-smoothing: grayscale;
}

html, body {
	height: 100%;
	background-color: #04AA6D;
}

.form-holder {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: center;
	min-height: 100vh;
}

.form-holder .form-content {
	position: relative;
	text-align: center;
	display: -webkit-box;
	display: -moz-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-align-items: center;
	align-items: center;
	padding: 60px;
}

.form-content .form-items {
	border: 3px double #fff;
	padding: 30px;
	display: inline-block;
	width: 100%;
	min-width: 540px;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 0px;
	text-align: left;
	-webkit-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.form-content h3 {
	color: #fff;
	text-align: left;
	font-size: 28px;
	font-weight: 600;
	margin-bottom: 15px;
}

.form-body {
	margin-top: -80px;
}

.inputs {
	width: 100%;
	padding: 9px 20px;
	text-align: left;
	border: 0;
	outline: 0;
	border-radius: 10px;
	background-color: #fff;
	font-size: 15px;
	font-weight: 300;
	color: #8D8D8D;
	-webkit-transition: all 0.3s ease;
	transition: all 0.3s ease;
	margin-top: 16px;
}

.labels {
	color: #fff;
	text-align: left;
	font-size: 17px;
	font-weight: 300;
	line-height: 20px;
	margin-bottom: 30px;
}

.btn-primary {
	outline: none;
	border-radius: 8px;
	border: 0px;
	box-shadow: none;
	margin-top: 20px;
	margin-left: auto;
}

.btn-primary:hover, .btn-primary:focus, .btn-primary:active {
	background-color: #495056;
	outline: none !important;
	border: none !important;
	box-shadow: none;
}

h1 {
	color: #fff;
	text-align: center;
	font-size: 50px;
	margin-top: 20px;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	console.log("inside the javacritp")
	$(document)
			.ready(
					function() {
						$("button")
								.click(
										function() {
											console.log('inside the click')
											var username = $("#username").val();
											var password = $("#password").val();
											console.log({
												'username' : username,
												'password' : password
											});
											$
													.post(
															"http://localhost:1111/authenticate",
															{
																'username' : username,
																'password' : password
															},
															function(data,
																	result) {

																alert('Log in successful');
																localStorage
																		.setItem(
																				"token",
																				data)

																$
																		.ajax({
																			url : "http://localhost:8080/authenticate",
																			type : "GET",
																			headers : {
																				"Authorization" : 'Bearer '
																						+ localStorage
																								.getItem("token")
																			},
																			success : function(
																					data) {

																			}
																		});
																window.location.href = "http://localhost:8999/offer/offers";
															});
										});
					});
</script>



</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/log/login">Corporate Classifieds</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

		</div>
	</nav>
	<div class="container text-center">
		<h1>Corporate Classifieds</h1>
		<br>
		<div class="form-body">
			<div class="row">
				<div class="form-holder">
					<div class="form-content">
						<div class="form-items">
							<h3>Login Page</h3>
							<br>
							<c:url value="/authenticate" var="loginUrl" />
							<form method="post"
								action="<c:url value='j_spring_security_check' />">
								<c:if test="${param.error != null}">
									<p>Invalid username and password.</p>
								</c:if>
								<c:if test="${param.logout != null}">
									<p>You have been logged out.</p>
								</c:if>
								<p>
									<label class="labels" for="username">Username</label> <input
										class="inputs" type="text" id="username" name="username" />
								</p>
								<p>
									<label class="labels" for="password">Password</label> <input
										class="inputs" type="password" id="password" name="password" />
								</p>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />


								<button class="btn btn-primary" type="button" class="btn">Log
									in</button>
							</form>
</body>
</html>