document.addEventListener('DOMContentLoaded', (event) => {
    // Retrieve user information from localStorage
    var user = JSON.parse(localStorage.getItem('user'));
      if (user) {
        // If there is user information, display the user icon
        showUserLogin();

    }
});

function login3() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var csrfToken = document.getElementsByName('_csrf')[0].value;

    var formData = new URLSearchParams();
    formData.append('username', email);
    formData.append('password', password);
    formData.append('_csrf', csrfToken);

    fetch('/GeinDock/api/login', {
        method: 'POST',
        body: formData
    }).then(response => {
        return response.json().then(data => ({ status: response.status, body: data }));
    }).then(result => {
        if (result.status === 200) {
            // Login success
            showNotification("Login Success", "success");

            // Handle successful response
            console.log("Email: ", result.body.email);
            console.log("Role: ", result.body.role);
            console.log("Name: ", result.body.name);
            result.body.role.forEach((role, index) => {
                console.log("Role " + (index + 1) + ": ", JSON.stringify(role));
            });
            localStorage.setItem('user', JSON.stringify(result.body));
            localStorage.setItem('jobName', "");
            localStorage.setItem('token', csrfToken);
            localStorage.setItem('email', result.body.email);
            showUserLogin();
        } else {
            // Login error with message from the server
            showNotification(result.body, "error");
        }
    }).catch(error => {
        // Network error
        showNotification("Network error", "error");
        console.error('Network error', error);
    });
}
function login() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var csrfToken = document.getElementsByName('_csrf')[0].value;

    var formData = new URLSearchParams();
    formData.append('username', email);
    formData.append('password', password);
    formData.append('_csrf', csrfToken);

    fetch('/GeinDock/api/login', {
        method: 'POST',
        body: formData
    }).then(response => {
        return response.json().then(data => ({ status: response.status, body: data }));
    }).then(result => {
        if (result.status === 200) {
            // Login success
            showNotification("Login Success", "success");

            // Handle successful response
            console.log("Email: ", result.body.data.email);
            console.log("Role: ", result.body.data.role);
            console.log("Name: ", result.body.data.name);
            result.body.data.role.forEach((role, index) => {
                console.log("Role " + (index + 1) + ": ", JSON.stringify(role));
            });
            localStorage.setItem('user', JSON.stringify(result.body.data));
            localStorage.setItem('jobName', "");
            localStorage.setItem('token', csrfToken);
            localStorage.setItem('email', result.body.data.email);
            showUserLogin();
            document.getElementById('email').value="";
    		document.getElementById('password').value="";
        } else {
            // Login error with message from the server
            showNotification(result.body.message, "error");
        }
    }).catch(error => {
        // Network error
        showNotification("Network error", "error");
        console.error('Network error', error);
    });
}


function login2() {
    // Fetch values
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var csrfToken = document.getElementsByName('_csrf')[0].value;
    console.log("token",csrfToken);

    // Prepare form data
    var formData = new URLSearchParams();
    formData.append('username', email);
    formData.append('password', password);
    formData.append('_csrf', csrfToken);

    // Send request
    fetch('/GeinDock/api/login', {
        method: 'POST',
        body: formData
    }).then(response => {
        if (response.ok) {
           // showNotification("Login success");
           showNotification("Login Success", "success");

             return response.json();
        } else {
            // Handle login error
            console.error('Login failed');
            //showNotification("Login failed");
            showNotification("Login Failed", "error");
        }
    })
     .then(responseObject => {
             // Print each field
             console.log("Email: ", responseObject.email);
             console.log("Role: ", responseObject.role);
             console.log("Name: ", responseObject.name);
             responseObject.role.forEach((role, index) => {
                 console.log("Role " + (index + 1) + ": ", JSON.stringify(role));
             });
             localStorage.setItem('user', JSON.stringify(responseObject));
             localStorage.setItem('jobName',"");
             localStorage.setItem('token',csrfToken);
             localStorage.setItem('email',responseObject.email);
             showUserLogin();

         })
    .catch(error => {
        // Handle network error
       // console.error('Network error', error);
    });
}
function logout() {
    // Clear user information from localStorage
    localStorage.removeItem('user');

    // TODO: perform actual logout action (e.g., call logout API)

    // Then, refresh the page or redirect user to the login page
    location.reload();
}
function showUserLogin() {
    console.log('Calling showUserLogin...');
    let user = JSON.parse(localStorage.getItem('user'));
    // select 'Sign in' and 'Sign up' links
    var signInLink = document.querySelector('.user-link .nav-item:nth-child(1)');
    var signUpLink = document.querySelector('.user-link .nav-item:nth-child(2)');

    // hide 'Sign in' and 'Sign up' links
    signInLink.style.display = 'none';
    signUpLink.style.display = 'none';

    // only create a user icon if it doesn't exist yet
    var userIconLi = document.querySelector('.user-link .nav-item.user-icon');
    if (!userIconLi) {
        userIconLi = document.createElement('li');
        userIconLi.className = "nav-item user-icon";

        var userIconLink = document.createElement('a');
        userIconLink.className = "nav-link";

       // var userIcon = document.createElement('i');
        //userIcon.className = "fas fa-user"; // Font Awesome user icon
       // userIcon.className = "fas fa-user-circle"; // Font Awesome round user icon
        var userIcon = document.createElement('div');
        userIcon.className="user-icon"
        userIcon.style.width = '32px';
        userIcon.style.height = '32px';
        userIcon.style.borderRadius = '50%';
        userIcon.style.backgroundColor = '#ccc';
        userIcon.style.display = 'flex';
        userIcon.style.justifyContent = 'center';
        userIcon.style.alignItems = 'center';
        //userIcon.innerText = 'U'; // initial of the user
        userIcon.innerText = user.name.charAt(0).toUpperCase();

        userIconLink.appendChild(userIcon);
        userIconLi.appendChild(userIconLink);

        // add the new li element to the navbar
        var navbar = document.querySelector('.user-link .navbar-nav');
        navbar.appendChild(userIconLi);

        console.log('User icon should now be visible');
    }

    // remove old user info card if it exists
    var oldUserInfoCard = document.querySelector('.user-link .user-info-card');
    if (oldUserInfoCard) {
        oldUserInfoCard.remove();
    }

    // create a new user info card

    let roles = user.role.map(r => r.role).join(', ');
    console.log(JSON.stringify(user.role));
    var userInfoCard = document.createElement('div');
    userInfoCard.className = "user-info-card";
    userInfoCard.style.display = 'none'; // hide the card initially
    userInfoCard.innerHTML = `
        <h3>User Info</h3>
        <p>Name: ${user.name}</p>
        <p>Email: ${user.email}</p>
        <p>Role: ${roles}</p>
        <button>Sign Out</button>
    `;

    // append the card to the user icon link
    userIconLi.firstChild.appendChild(userInfoCard);
    userInfoCard.addEventListener('click', function(e) {
            e.stopPropagation(); // prevent the event from bubbling up to the user icon
    });

    // add click event listener to user icon
    userIconLi.firstChild.addEventListener('click', function(e) {
        e.preventDefault(); // prevent the default action
         e.stopPropagation();
        // calculate the position of the card
        var rect = userIconLi.firstChild.getBoundingClientRect();
        userInfoCard.style.top = rect.bottom + 'px'; // position the card below the icon
        userInfoCard.style.right = (window.innerWidth - rect.right) + 'px'; // align the right edges

        // toggle the visibility of the user info card
        if (userInfoCard.style.display === 'none') {
            userInfoCard.style.display = 'block'; // show the card
        } else {
            userInfoCard.style.display = 'none'; // hide the card
        }
    });
}
document.addEventListener('click', function(e) {
    var userInfoCard = document.querySelector('.user-info-card');
    if (userInfoCard && userInfoCard.style.display === 'block') {
        // check if the click was not on the user info card or any of its children
        var isClickInside = userInfoCard.contains(e.target);
        if (!isClickInside) {
            userInfoCard.style.display = 'block'; // hide the card
        }
    }
});

