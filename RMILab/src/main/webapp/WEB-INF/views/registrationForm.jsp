<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <head>
        <meta charset="UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrationForm.css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script defer src="${pageContext.request.contextPath}/js/register.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta charset="ISO-8859-1">
    </head>

    <body>
        <div class="container">
            <ul class="nav nav-tabs">
                <li class="active" id="indi-link"><a href="#" onclick="showIndiDiv()">Individual Registration</a></li>
                <li id="inst-link"><a href="#" onclick="showInstituteDiv()">Institute Registration</a></li>
            </ul>
            <div id="indi-div">
                <div class="title">Registration</div>
                <div class="content" id="indi">
                    <form id="submitForm" action="/GeinDock/api/saveRegisterUser" method="post">
                        <div class="user-details" id="inst-name-div">
                            <div class="input-box full-input">
                                <span class="details">Institute Name</span>
                                <input class="full-input" type="text" placeholder="Enter Institute Name"
                                    id="instituteName" name="institute_name" required>
                            </div>
                        </div>
                        <div class="input-box" id="inst-department-div">
                            <span class="details">Department</span>
                            <select class="form-control" id="institute_department" name="institute_department" required>
                                <option value="">Please select Department</option>
                                <option value="Pharmaceutical Chemistry">Pharmaceutical Chemistry</option>
                                <option value="Medicinal Chemistry">Medicinal Chemistry</option>
                                <option value="Pharmaceutics">Pharmaceutics</option>
                                <option value="Pharmacology">Pharmacology</option>
                                <option value="Bioinformatics">Bioinformatics</option>
                            </select>
                        </div>
                        <div class="user-details">
                            <div class="input-box" id="firstName-div">
                                <span class="details">First Name</span>
                                <input type="text" placeholder="Enter First Name" id="firstName" name="first_name"
                                    required>
                            </div>
                            <div class="input-box" id="lastName-div">
                                <span class="details">Last Name</span>
                                <input type="text" placeholder="Enter Last Name" id="lastName" name="last_name"
                                    required>
                            </div>
                            <div class="input-box">
                                <span class="details">Email</span>
                                <input type="text" placeholder="Enter Your Email" id="email" name="email" required>
                            </div>
                            <div class="mobile-div">
                                <span class="details">Mobile</span>
                                <div class="row">
                                    <select class="custom-select" id="country_code" name="country_code">
                                        <option selected value="+91">+91</option>
                                        <option value="+44">+44</option>
                                        <option value="+81">+81</option>
                                    </select>
                                    <input type="text" placeholder="Enter Your Mobile" id="mobile" name="mobile"
                                        maxlength="10" required>
                                </div>
                            </div>
                            <div class="form-check" id="isInstitute-div">
                                <input type="checkbox" class="form-check-input" id="isInstitute" required>
                                <label class="form-check-label" for="isInstitute">Is Your Institute Registered?</label>
                            </div>
                            <div class="input-box" id="select_inst-div">
                            </div>

                            <div class="input-box" id="enter-inst-div">
                                <span class="details">Institute Name</span>
                                <input type="text" placeholder="Enter Institute Name" id="institute" name="institute"
                                    required>
                            </div>
                            <div class="input-box" id="select-inst-div">
                                <span class="details">Select Institute Name</span>
                                <select class="form-control" id="select_institute" name="select_institute" required>
                                </select>
                            </div>
                            <div class="input-box" id="purpose-div">
                                <span class="details">Purpose Of Use</span>
                                <select class="form-control" id="purpose" name="purpose" required>
                                    <option value="">Please select an purpose of use</option>
                                    <option value="Research">Academic</option>
                                    <option value="Academic">Laboratory</option>
                                    <option value="Industrial">Drug Discovery</option>
                                    <option value="Other">Other</option>
                                </select>
                            </div>
                            <div class="input-box" id="department-div">
                                <span class="details">Department</span>
                                <input type="text" placeholder="Enter Department Name" id="department" name="department"
                                    required>
                            </div>
                            <div class="input-box">
                                <span class="details">Country</span>
                                <input type="text" placeholder="Enter Your Country Name" id="country" name="country"
                                    required>
                            </div>
                            <div class="input-box">
                                <span class="details">State</span>
                                <input type="text" placeholder="Enter Your State Name" id="state" name="state" required>
                            </div>
                            <div class="input-box">
                                <span class="details">City</span>
                                <input type="text" placeholder="Enter Your City Name" id="city" name="city" required>
                            </div>

                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="agreement" required>
                            <label class="form-check-label" for="agreement">I agree to the terms and conditions</label>
                            <div class="invalid-feedback">Please agree to the terms and conditions.</div>
                        </div>
                        <div class="form-group" id="otp-div">
                            <div class="input-box">
                                <label for="otp">Enter OTP</label>
                                <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP"
                                    required minlength="6">
                                <!-- <small class="form-text text-muted">Please enter OTP received on email.</small> -->
                            </div>
                            <div class="input-box">
                                <span class="details">Password</span>
                                <input type="password" placeholder="Enter Password" id="password" name="password"
                                    required>
                            </div>
                            <div class="input-box">
                                <span class="details">Confirm Password</span>
                                <input type="password" placeholder="Confirm Password" id="repassword" name="repassword"
                                    required>
                            </div>
                        </div>
                        <div class="button" id="button-getotp-div">
                            <input type="button" value="Get OTP" id="getOtp-btn" onclick="submitForm()">
                        </div>
                        <div class="button-reset" id="button-reset-div">
                            <input type="reset" value="Reset" id="reset-btn"">
                    </div>
                    <div class=" button" id="button-submit-div">
                            <input type="button" value="Submit" id="submit-btn" onclick="verifyUser()">
                        </div>
                    </form>
                    <input type=" hidden" id="email-hidden" name=id="email-hidden" style="display: none;">
                </div>
            </div>
    </body>