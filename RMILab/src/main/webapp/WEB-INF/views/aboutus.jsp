<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>About Us - GeinDock Suite</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeinDock.css">
    <style>
        body {
            /* Using the primary color (light blue) for the background */
            background: #E0EFFF;
            font-family: 'Arial', sans-serif; /* Consistent font family */
        }

        .title-container .title-text, .subtitle {
            color: #37474F;
        }

        .about-section {
            padding: 50px 0;
            text-align: center;
        }

        .about-section h2, .about-section h3 {
            color: #37474F;
            margin-bottom: 30px;
        }

        .about-content {
            margin: 0 auto;
            max-width: 800px;
            text-align: left;
        }

        .about-content p {
            color: #37474F;
            line-height: 1.6;
        }

        .team-section {
            padding: 50px 0;
            text-align: center;
        }

        .team-section h2 {
            color: #37474F;
            margin-bottom: 30px;
        }

        .team-member {
            margin-bottom: 30px;
        }

        .team-member img {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
        }

        .team-member h5 {
            color: #37474F;
            margin-top: 10px;
            margin-bottom: 5px;
        }

        .team-member p {
            color: #777;
        }
    </style>
</head>

<body>
    <div class="title">
        <img src="${pageContext.request.contextPath}/images/GeinDock_Suite_logo-Transperent.png" alt="GeinDock Suite Logo" />
        <div class="title-container">
            <span class="title-text">GeinDock Suite</span>
            <span class="subtitle">A Molecular Docking Tool</span>
        </div>
    </div>

    <div class="container">
        <div class="about-section">
            <h2>About Us</h2>
            <div class="about-content">
                <p>Geinforce Technology Private Limited is a pioneering software development and consultancy firm with a specialized focus on fostering advancement in drug discovery and scientific research. Based on our comprehensive understanding of the challenges faced by researchers, scientists, and corporations in the field of science, we have dedicated ourselves to provide the best software, web tools, and customized digital solutions.</p>
                <p>At Geinforce, we embrace technology as a potent enabler, bridging the gap between complex scientific knowledge and its practical application. Our software solutions are designed to streamline the research process, eliminate obstacles, and hasten the pace of discovery, helping our clients to deliver remarkable outcomes in their respective fields.</p>
                <p>Our team is made up of proficient software developers, experienced consultants, and subject matter experts who possess a deep understanding of both technology and the scientific realm. Our interdisciplinary approach enables us to grasp the intricacies of our clients' needs and to tailor our services, accordingly, ensuring the delivery of high-impact solutions.</p>
                <p>In our consulting division, we offer personalized advisory services to clients, helping them optimize their operations and make informed decisions. We work closely with our clients, aligning our strategies with their objectives, to co-create value and facilitate their success.</p>
                <p>Our commitment to technology, innovation, and customer service drives us to push the boundaries and set new standards in the scientific software industry. At Geinforce Technology, we aren't just delivering software; we're shaping the future of scientific exploration and discovery.</p>
                <p>Join us in our mission to revolutionize the world of science through advanced technology.</p>
            </div>
        </div>

        <div class="about-section">
            <h3>Vision & Mission</h3>
            <div class="about-content">
                <h4>Vision:</h4>
                <p>Empowering global scientific innovation through cutting-edge software, revolutionizing drug discovery and research.</p>
                <h4>Mission:</h4>
                <p>Delivering bespoke digital solutions and consultancy services, accelerating scientific breakthroughs, and fostering client success in their scientific endeavors.</p>
            </div>
        </div>

        <div class="team-section">
            <h2>Meet Our Team</h2>
            <div class="row">
                <div class="col-md-4 team-member">
                    <img src="${pageContext.request.contextPath}/images/team_member_1.jpg" alt="Team Member 1">
                    <h5>John Doe</h5>
                    <p>Chief Scientist</p>
                </div>
                <div class="col-md-4 team-member">
                    <img src="${pageContext.request.contextPath}/images/team_member_2.jpg" alt="Team Member 2">
                    <h5>Jane Smith</h5>
                    <p>Lead Developer</p>
                </div>
                <div class="col-md-4 team-member">
                    <img src="${pageContext.request.contextPath}/images/team_member_3.jpg" alt="Team Member 3">
                    <h5>Emily Johnson</h5>
                    <p>Project Manager</p>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1Ktv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>

</html>
