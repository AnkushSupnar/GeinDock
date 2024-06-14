<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
/* Import Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap');

/* Primary Color: Blue (#E0EFFF) - 60% */
/* Secondary Color: Dark Gray (#37474F) - 30% */
/* Accent Color: Coral (#FF6B6B) - 10% */

.navbar {
    background-color: #E0EFFF; /* Primary - for the background */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-family: 'Roboto', sans-serif;
}

.navbar-brand {
    color: #37474F; /* Secondary - for contrast */
    font-weight: 500;
    transition: color 0.3s;
}

.navbar-brand:hover {
    color: #FF6B6B; /* Accent - for interaction */
}

.navbar-light .navbar-nav .nav-link {
    color: #37474F; /* Secondary - main elements */
    font-size: 1rem;
    transition: color 0.3s;
}

.navbar-light .navbar-nav .nav-link:hover {
    color: #FF6B6B; /* Accent - to draw attention */
}

.dropdown-menu {
    border: none;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15);
}

.dropdown-item:hover {
    background-color: #E0EFFF; /* Primary - subtle interaction */
    color: #FF6B6B; /* Accent - to draw attention */
}

.btn-outline-success {
    border-color: #FF6B6B; /* Accent - stands out */
    color: #FF6B6B; /* Accent */
    transition: background-color 0.3s, color 0.3s;
}

.btn-outline-success:hover {
    background-color: #FF6B6B; /* Accent - interaction */
    color: white;
}

</style>
<nav class="navbar navbar-expand-lg navbar-light navbar-fixed ">
	<a class="navbar-brand" href="#">Geinforce</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-toggle="dropdown" aria-expanded="false"> Products </a>
				<div class="dropdown-menu">
					<!-- <a class="dropdown-item" href="/docking">Server Docking</a>-->
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/geinDock">GeinDock
						Suite</a>
					<!--<a class="dropdown-item" href="#">Another action</a>-->
					<!--<div class="dropdown-divider"></div>-->
					<!--<a class="dropdown-item" href="#">Something else here</a>-->
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-toggle="dropdown" aria-expanded="false"> Services </a> <!--<div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>--></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-toggle="dropdown" aria-expanded="false"> Solution </a> <!-- <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>--></li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/pricing">Pricing
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/contact">Contact
					Us <span class="sr-only">(current)</span>
			</a></li>
		<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/about">About
					Us <span class="sr-only">(current)</span>
			</a></li>
		</ul>
		<!-- 
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" name="search" type="search" placeholder="Search"
                    aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form> 
          -->
		
		
		
		
		
		<span class="navbar-text user-link">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="#">Sign in</a></li>
				<li class="nav-item "><a class="nav-link " href="${pageContext.request.contextPath}/registerForm">Sign
						up</a></li>
			</ul>
		</span>
	</div>
</nav>