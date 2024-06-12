<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<title>Registration Page</title>
		<link rel="stylesheet" href="/GeinDock/css/register.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
			integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
			integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
			crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
			crossorigin="anonymous"></script>
		<script defer src="/GeinDock/js/register.js"></script>
	</head>

	<body>

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-6">
					<!--<form id="submitForm" action="/register" method="post"  >-->
					<form id="submitForm" action="/api/saveRegisterUser" method="post">
						<div class="form-group">
							<label for="firstName">First Name</label>
							<input type="text" class="form-control" id="firstName" name="firstName" required>
						</div>
						<div class="form-group">
							<label for="lastName">Last Name</label>
							<input type="text" class="form-control" id="lastName" name="lastName" required>
						</div>
						<div class="form-group">
							<label for="email">Email ID</label>
							<input type="email" class="form-control" id="email" name="email" required>
						</div>
						<div class="form-group">
							<label for="mobile">Mobile Number</label>
							<div class="input-group">
								<div class="input-group-prepend">
									<select class="custom-select" id="country_code" name="country_code">
										<option selected value="+91">+91</option>
										<option value="+44">+44</option>
										<option value="+81">+81</option>
									</select>
								</div>
								<input type="tel" class="form-control" id="mobile" name="mobile"
									placeholder="Enter mobile number" maxlength="10">
							</div>
						</div>
						<div class="form-group">
							<label for="institute">Institute Name</label>
							<input type="text" class="form-control" id="institute" name="institute" required>
						</div>
						<div class="form-group">
							<label for="purpose">Purpose of Use</label>
							<select class="form-control" id="purpose" name="purpose" required>
								<option value="">Please select an purpose of use</option>
								<option value="Research">Academic</option>
								<option value="Academic">Laboratory</option>
								<option value="Industrial">Drug Discovery</option>
								<option value="Other">Other</option>
							</select>
						</div>
						<div class="form-group">
							<label for="department">Department</label>
							<input type="text" class="form-control" id="department" name="department" required>
						</div>
						<div class="form-group">
							<label for="country">Country</label>
							<input type="text" class="form-control" id="country" name="country" required>
						</div>
						<div class="form-group">
							<label for="state">State</label>
							<input type="text" class="form-control" id="state" name="state" required>
						</div>
						<div class="form-group">
							<label for="city">City</label>
							<input type="text" class="form-control" id="city" name="city" required>
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" class="form-control" id="password" name="password"
								placeholder="Enter password" required minlength="8">
							<small class="form-text text-muted">Password must be at least 8 characters long.</small>
						</div>
						<div class="form-group">
							<label for="repassword">Re-enter Password</label>
							<input type="password" class="form-control" id="repassword" placeholder="Re-enter password"
								required minlength="8">
							<small class="form-text text-muted">Please re-enter your password.</small>
						</div>
						<div class="form-group form-check">
							<input type="checkbox" class="form-check-input" id="agreement" required>
							<label class="form-check-label" for="agreement">I agree to the terms and conditions</label>
							<div class="invalid-feedback">Please agree to the terms and conditions.</div>
						</div>
						<div class="form-group" id="otp-div">
							<label for="otp">Enter OTP</label>
							<input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" required
								minlength="6">
							<small class="form-text text-muted">Please enter OTP received on email.</small>
						</div>
						<button type="button" class="btn btn-primary mr-3" id="getOtp-btn" onclick="submitForm()">Get
							OTP</button>
						<button type="button" class="btn btn-primary mr-3" id="submit-btn"
							onclick="verifyUser()">Submit</button>
						<button type="reset" class="btn btn-danger mr-3" id="reset-btn">Reset</button>
					</form>
					<input type="hidden" id="email-hidden" name=id="email-hidden">


				</div>
	</body>

	</html>