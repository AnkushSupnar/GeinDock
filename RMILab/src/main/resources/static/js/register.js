var institute_name = document.getElementById('inst-name-div');
var purpose_div = document.getElementById('purpose-div');
var firstName_div = document.getElementById('firstName-div');
var lastName_div = document.getElementById('lastName-div');
var select_inst_div = document.getElementById('select_inst-div');
var indi_link = document.getElementById('indi-link');
var inst_link = document.getElementById('inst-link');
var department_div = document.getElementById('department-div');
var inst_department_div = document.getElementById('inst-department-div');
const passwordInput = document.getElementById('password');
const rePasswordInput = document.getElementById('repassword');
const isInstitute_div = document.getElementById('isInstitute-div');
const enter_institute_div = document.getElementById('enter-inst-div');
institute_name.style.display='none';
inst_department_div.style.display='none';

var isInstitute=false;
var isStudent=false;
window.onload = function () {

  document.getElementById("otp-div").style.display = "none";
  // document.getElementById("repassword").style.border = "2px solid red";
  document.getElementById('button-submit-div').style.display = 'none';
}
const checkbox = document.getElementById("isInstitute");
document.getElementById("select-inst-div").style.display='none';
checkbox.addEventListener("change", function() {
  if (this.checked) {
    // Checkbox is checked
    console.log("Checkbox is checked");
    document.getElementById("enter-inst-div").style.display='none';
    document.getElementById("select-inst-div").style.display='block';
    loadInstituteNames();
    isStudent=true;
    // Perform actions when checkbox is checked
  } else {
    // Checkbox is unchecked
    console.log("Checkbox is unchecked");
    document.getElementById("enter-inst-div").style.display='block';
    document.getElementById("select-inst-div").style.display='none';
    isStudent=false;
    // Perform actions when checkbox is unchecked
  }
});
function showInstituteDiv(){
    institute_name.style.display='block';
    purpose_div.style.display='none';
    firstName_div.style.display='none';
    lastName_div.style.display='none';
    select_inst_div.style.display='none';
    department_div.style.display='none';
    inst_department_div.style.display='block';
    isInstitute_div.style.display='none';
    enter_institute_div.style.display='none';
    indi_link.classList.add('active');
    inst_link.classList.remove('active');

    isInstitute=true;
}
function showIndiDiv(){
     institute_name.style.display='none';
        purpose_div.style.display='block';
        firstName_div.style.display='block';
        lastName_div.style.display='block';
        select_inst_div.style.display='block';
        department_div.style.display='block';
        inst_department_div.style.display='none';
        isInstitute_div.style.display='block';
        enter_institute_div.style.display='block';
        indi_link.classList.remove('active');
        inst_link.classList.add('active');
        isInstitute=false;
}
function verifyUser() {
  var email = document.getElementById("email-hidden").value;
  var otp = document.getElementById("otp").value;
  //alert("Getting verify", email, otp);
  if (passwordInput.value !== rePasswordInput.value) {
         alert("Password not Matched!");
         return;
    }

  var xhr = new XMLHttpRequest();
  var result = false;
  xhr.open("POST", "/GeinDock/api/verifyUser?email=" + email + "&otp=" + otp+"&password="+passwordInput.value, false);
  xhr.onload = function () {
    if (xhr.status === 200) {
      var response = xhr.responseText; // get the response as string
      console.log(response);
      if (response.indexOf("success") !== -1) {
        alert("Success");
        //window.location.href = "/home";
       var ctx = '<c:url value="/" />';  
    console.log("context path=", ctx);
    window.location.replace(ctx + "/home");
      }
      else
        alert("Failed");
    } else {
      // handle error response
    }
  };
  xhr.send();
}
function submitForm() {
  var form = document.getElementById("submitForm");
  var email = document.getElementById("email").value;

  const checkbox = document.getElementById('agreement');
  const isChecked = checkbox.checked;
  if (!isChecked) {
    alert("Please accept terms and conditions!!!")
    return;
  }
  if(isStudent && !checkInstituteSelect() ){
    alert("Please Your Select Institute Name");
   // return;
  }
  if (!checkEmail(email)) {
   alert("This email is already register with us");
   return;
  }


    var formData = new FormData(form);
    //formData.append("is_institute",isInstitute);
    formData.append("is_institute", String(isInstitute));
    formData.append("is_student",String(isStudent));
    //formData.set("institute",)

    console.log(formData);
    for (var pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }
    console.log("data", formData);
    var button = document.getElementById("getOtp-btn");
      button.value = "Sending email...";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, false);
    xhr.onload = function () {
    console.log("Status="+xhr.status);
    console.log("response text ", xhr.responseText);
      if (xhr.status === 200 || xhr.status === 201 ) {
        console.log("Success");
        var response = xhr.responseText; // get the response as string
        console.log("response text ", response);
        if (response.length == 0) {
          alert("Email not sent");
        }
        else {
          alert("OTP send to your email " + email + "\n Please Enter OTP to register");
          // console.log(xhr.responseText);
          document.getElementById("otp-div").style.display = "block";
          document.getElementById("button-getotp-div").style.display = "none";
          document.getElementById("button-reset-div").style.display = "none";
          document.getElementById('button-submit-div').style.display = 'block';
          document.getElementById("email-hidden").value = email;
          disableForm();
        }
      } else {
      alert(xhr.responseText);
        console.log("Failed");
      }
       button.value = "Get OTP";
    };
    xhr.send(formData);

}
function checkEmail(email) {
  var xhr = new XMLHttpRequest();
  var result = false;
  xhr.open("GET", "/GeinDock/util/checkEmail?email=" + email, false);
  xhr.onload = function () {
    if (xhr.status === 200) {
      var response = xhr.responseText; // get the response as string
      console.log(response);
      if (response.indexOf("Not Exist") !== -1)
        result = true;
      else
        result = false;
    } else {
      // handle error response
    }
  };
  xhr.send();
  console.log("check email result=",result)
  return result;
}
function disableForm() {
  var form = document.getElementById("submitForm");
  var elements = form.elements;
  for (var i = 0; i < elements.length; i++) {
    if (elements[i].id !== "otp" && elements[i].id !== "submit-btn" && elements[i].id!=="password" && elements[i].id!=="repassword") {
      elements[i].disabled = true;
    }
  }
}


function loadInstituteNames(){
const selectElement = document.getElementById("select_institute");

fetch("/GeinDock/api/verifiedInstituteNames")
  .then(response => {
    if (response.status === 204) {
      // No Content
      const option = document.createElement("option");
      option.text = "No institutes found";
      option.value="noInstitutes";
      selectElement.add(option);
      throw new Error("No institutes found");
    } else if (!response.ok) {
      throw new Error("Error fetching institute names: " + response.status);
    }
    return response.json();
  })
  .then(data => {
    data.forEach(instituteName => {
      const option = document.createElement("option");
      option.text = instituteName;
      option.value=instituteName;
      selectElement.add(option);
    });
  })
  .catch(error => {
    console.log(error);
  });
}
function checkInstituteSelect() {
  const selectElement = document.getElementById("select_institute");
  const selectedValue = selectElement.value;
  console.log(selectedValue);
  if (selectedValue === "noInstitutes") {
      return false;
  }
  else{
  return true;
  }
}
function checkPasswordMatch() {
  if (passwordInput.value !== rePasswordInput.value) {
    rePasswordInput.classList.add('error');
  } else {
    rePasswordInput.classList.remove('error');
  }
}
passwordInput.addEventListener('input', checkPasswordMatch);
rePasswordInput.addEventListener('input', checkPasswordMatch);
function validateForm() {
  var firstName = document.getElementById("firstName").value;
  var lastName = document.getElementById("lastName").value;
  var email = document.getElementById("email").value;
  var country_code = document.getElementById("country-code").value;
  var mobile = document.getElementById("mobile").value;
  var institute = document.getElementById("institute").value;
  var purpose = document.getElementById("purpose").value;
  var department = document.getElementById("department").value;
  var country = document.getElementById("firstName").value;
  var state = document.getElementById("firstName").value;
  var city = document.getElementById("firstName").value;
  var password = document.getElementById("firstName").value;
  var repassword = document.getElementById("firstName").value;
  alert("Js Called", firstName, lastName, email, country_code, mobile, institute, purpose, department, country,
    state, city, password, repassword);
}
