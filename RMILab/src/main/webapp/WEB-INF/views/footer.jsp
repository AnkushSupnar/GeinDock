<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    #footerSection {
        background: #222429;
        padding-top: 160px; 
        color: #9ca3a9; /* Default text color for better contrast */
        font-family: 'Rubik', sans-serif;
        position: relative;
    }
    .footer-box .logo {
        font-size: 38px;
        font-weight: bold;
        color: #fff;
        text-decoration: none;
    }
    .footer-box .about,
    .footer-box .title,
    .box2 ul li a,
    .box3 .icon-box .txt span,
    .box4 span {
        font-size: 16px;
    }
    .footer-box .title,
    .box4 form .form-group .form-control {
        margin-bottom: 30px;
    }
    .footer-box .social-links a {
        background: #000;
        display: inline-block;
        height: 40px;
        width: 40px;
        border-radius: 100px;
        text-align: center;
        margin-right: 10px;
        transition: .4s;
        color: #9ca3a9; /* Icon color */
    }
    .footer-box .social-links a:hover {
        background: #fb383b;
        color: #000;
    }
    .box2 ul li {
        list-style: none;
        margin-bottom: 10px;
    }
    .box3 .icon-box {
        display: flex;
        align-items: center;
        margin-bottom: 20px;
    }
    .box3 .icon-box .icon {
        width: 30px;
        color: #fb383b; /* Icon color */
    }
    .box4 form .form-group {
        max-width: 250px;
        position: relative;
    }
    .box4 form .form-group .form-control {
        background: #000;
        border: 1px solid #000;
        padding: 20px;
        border-radius: 8px;
    }
    .box4 form .btn {
        background: #fb383b;
        position: absolute;
        top: 8px;
        right: 8px;
        height: 45px;
        width: 45px;
        border-radius: 100px;
        transition: .4s;
        color: #000;
    }
    .box4 form .btn:hover {
        background: #fff;
        color: #fb383b;
    }
    .copy-right {
        margin-top: 80px;
        border-top: 1px solid #ffffff1a;
        padding: 30px 0;
        text-align: center;
    }

    #footerSection:before {
        content: "";
        position: absolute;
        height: 100%;
        width: 100%;
        top: 0;
        right: 0;
        background: url('${pageContext.request.contextPath}/images/footer.png');
        background-repeat: no-repeat;
        background-position: center;
    }
</style>
<footer class="site-footer">
    <section id="footerSection">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 mb-5 mb-lg-0">
                    <div class="footer-box">
                        <a href="#" class="logo">GEINFORCE</a>
                        <p class="about">Welcome to our web design agency. Lorem ipsum simply free text dolor sit amet cons cing elit.</p>
                        <div class="social-links">
                            <a href="#"><i class="fab fa-facebook-f"></i></a>
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                            <a href="#"><i class="fab fa-github"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 mb-5 mb-lg-0">
                    <div class="footer-box box2">
                        <p class="title">EXPLORE</p>
                        <ul>
                            <li><a href="#">Support</a></li>
                            <li><a href="#">Privacy</a></li>
                            <li><a href="#">Policy</a></li>
                            <li><a href="#">Terms Of Use</a></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 mb-5 mb-md-0">
                    <div class="footer-box box3">
                        <p class="title">CONTACT</p>
                        <div class="icon-box">
                            <div class="icon"><i class="fal fa-map-marker-alt"></i></div>
                            <div class="txt"><span>66 Broklyn Street, New York. USA</span></div>
                        </div>
                        <div class="icon-box">
                            <div class="icon"><i class="fal fa-envelope"></i></div>
                            <div class="txt"><span>softoweb1@gmail.com</span></div>
                        </div>
                        <div class="icon-box mb-0">
                            <div class="icon"><i class="fal fa-phone-alt"></i></div>
                            <div class="txt"><span>01934565397</span></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="footer-box box4">
                        <p class="title">NEWSLETTER</p>
                        <form>
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="Email Address">
                                <button type="submit" class="btn"><i class="fal fa-envelope"></i></button>
                            </div>
                        </form>
                        <span>Sign up for our latest news & articles. We won’t give you spam mails.</span>
                    </div>
                </div>
            </div>
            <div class="copy-right">
                <span>Copyright © 2024 GeinForce. All rights reserved.</span>
            </div>
        </div>
    </section>
</footer>
