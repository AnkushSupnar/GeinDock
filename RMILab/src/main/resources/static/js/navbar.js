// Toggle the display of the nav list when hamburger is clicked
var navbarHamburger = document.querySelector('.navbar-hamburger');
var navList = document.querySelector('.nav-list');
navbarHamburger.addEventListener('click', function() {
  navList.classList.toggle('nav-list-active');
  navbarHamburger.classList.toggle('navbar-hamburger-active');
});
