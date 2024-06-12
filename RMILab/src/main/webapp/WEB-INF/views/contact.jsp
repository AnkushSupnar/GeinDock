<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">

   <!-- GOOGLE FONTS CDN LINK -->
   <link rel="preconnect" href="https://fonts.gstatic.com">
   <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@300;400;500;600;700;800&family=Teko:wght@400;500;600;700&display=swap" rel="stylesheet">
   <!-- CSS LINK -->
   <link rel="stylesheet" href="css/contact/bootstrap.css">
   <!-- AOS ANIAMATION PLUGIN -->
   <link rel="stylesheet" href="css/contact/animate.css">
   <!-- OWL CAROUSEL PLUGIN -->
   <link rel="stylesheet" href="css/contact/owl.carousel.min.css">
   <link rel="stylesheet" href="css/contact/owl.theme.default.min.css">
   <!-- MAGNIFIC POPUP PLUGIN -->
   <link rel="stylesheet" href="css/contact/magnific-popup.css">
   <link rel="stylesheet" href="css/contact/custom.css">
   <title>GeinForce</title>
   <style>
   #pageHeader {
     /*background: url(../img/home-bg.jpg);*/
     /*background: url(../images/footer.png);*/
     /*background: url(../images/footer.png);*/
     background: url('../images/footer.png') no-repeat center center/cover;
       background-size: cover;
       background-position: center top;
       margin-bottom: 100px;
}
   </style>
</head>
<body>
<%@ include file="navbar.jsp" %>
   <!-- ======================================== PAGE HEADER ======================================== -->
   <section id="pageHeader">
      <div class="overlay">
         <div class="container">
            <div class="row">
               <div class="col">
                  <div class="txt text-center">
                     <div class="sec-header">
                        <h2>CONTACT US<span>.</span></h2>
                        <p><a href="index.html">HOME</a> <span>/</span> CONTACT US</p>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </section>





   <!-- ======================================== CONTACT SECTION ======================================== -->
   <section id="contactSection">
      <div class="container">
         <div class="row">
            <div class="col-lg-6 mb-5 mb-lg-0">
               <div class="part1">
                  <div class="sec-header">
                     <h2>WE ARE ALWAYS HERE TO HELP YOU<span>.</span></h2>
                     <p>There are many variatns of passages the majority have suffered alteration in some foor randomised words believable.</p>
                  </div>
                  <div class="icon-box wow fadeInLeftBig" data-wow-duration="1.5s">
                     <div class="icon">
                        <i class="fal fa-map-marker-alt"></i>
                     </div>
                     <div class="txt">
                        <p>VISIT US</p>
                        <span>66 Broklyn Street, New York. USA</span>
                     </div>
                  </div>
                  <div class="icon-box wow fadeInLeftBig" data-wow-duration="1.5s">
                     <div class="icon">
                        <i class="fal fa-envelope"></i>
                     </div>
                     <div class="txt">
                        <p>EMAIL ADDRESS</p>
                        <span>softoweb1@gmail.com</span>
                     </div>
                  </div>
                  <div class="icon-box mb-0 wow fadeInLeftBig" data-wow-duration="1.5s">
                     <div class="icon">
                        <i class="fal fa-phone-alt"></i>
                     </div>
                     <div class="txt">
                        <p>CALL NOW</p>
                        <span>01934565397</span>
                     </div>
                  </div>
               </div>
            </div>
            <div class="col-lg-6">
               <form class="wow fadeInUpBig" data-wow-duration="1.5s">
                  <div class="form-row">
                     <div class="form-group col-md-6">
                        <input type="text" class="form-control" placeholder="Your Name">
                     </div>
                     <div class="form-group col-md-6">
                        <input type="email" class="form-control" placeholder="Email Address">
                     </div>
                     <div class="form-group col-md-6">
                        <input type="number" class="form-control" placeholder="Phone Number">
                     </div>
                     <div class="form-group col-md-6">
                        <input type="text" class="form-control" placeholder="Subject">
                     </div>
                     <div class="form-group col-12">
                        <textarea class="form-control" rows="8" cols="80" placeholder="Write Message"></textarea>
                     </div>
                  </div>
                  <button type="submit" class="btn layer-btn">SEND MESSAGE</button>
               </form>
            </div>
         </div>
      </div>
   </section>



   <section id="googleMap">
      <div class="container">
         <div class="row">
            <div class="col">
               <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d116834.00977795363!2d90.34928585186965!3d23.78077774443207!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3755b8b087026b81%3A0x8fa563bbdd5904c2!2sDhaka!5e0!3m2!1sen!2sbd!4v1615455043438!5m2!1sen!2sbd" width="100%" height="370" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
            </div>
         </div>
      </div>
   </section>


<%@ include file="footer.jsp" %>



   <!-- ======================================== SCROLL TO TOP ICON ======================================== -->
   <a href="#" class="scroll-up">
      <i class="fal fa-chevron-up"></i>
   </a>


   <script src="js/jquery.min.js"></script>
   <script src="js/bootstrap.min.js"></script>
   <script src="js/popper.min.js"></script>
   <script src="js/font-awesome-pro.js"></script>
   <!-- SCROLL ANIAMATION Plugin -->
   <script src="js/wow.min.js"></script>
   <!-- MIXIT UP Plugin -->
   <script src="js/mixitup.min.js"></script>
   <!-- MAGNIFIC POPUP Plugin -->
   <script src="js/jquery.magnific-popup.min.js"></script>
   <!-- CounterUp Plugin -->
   <script src="js/jquery.counterup.min.js"></script>
   <script src="js/jquery.waypoints.min.js"></script>
   <!-- OWL CAROUSEL Plugin -->
   <script src="js/owl.carousel.min.js"></script>
   <script src="js/custom.js"></script>
   <script>






   </script>
</body>
</html>