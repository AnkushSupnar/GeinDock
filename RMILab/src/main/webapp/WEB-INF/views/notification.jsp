<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
      .notification {
            position: fixed;
            top: 32px;
            right: 32px;
            align-items: center;
            border-radius: 6px;
            color: white;
            max-width: 320px;
            min-width: 200px;
            width: auto;
            opacity: 0;
            visibility: hidden;
            animation: fade-in 3s linear;
            z-index: 9999;
            padding: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            display: flex;
            flex-direction: row;
            gap: 10px;
        }
     .notification.success {
            background-color: #5cb85c; /* Green for success */
        }

        .notification.error {
            background-color: #d9534f; /* Red for error */
        }

    .notification__description {
        display: flex;
        align-items: center;
        font-size: 1rem;
        flex-grow: 2;
    }

    .notification__button {
        cursor: pointer;
        background: none;
        border: none;
        color: white;
        font-size: 1.1rem;
    }

    .notification__button:hover {
        text-decoration: underline;
    }

    .icon__wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 28px;
        height: 28px;
        border-radius: 50%;
        background-color: #9CE6A8; /* --toast-success */
        padding: 6px;
    }


@keyframes fade-in {
        0% { opacity: 0; transform: translateY(-100%); }
        5% { opacity: 1; visibility: visible; transform: translateY(0); }
        95% { opacity: 1; transform: translateY(0); }
        100% { opacity: 0; visibility: hidden; transform: translateY(-100%); }
    }
     .icon__wrapper svg {
            stroke: white;
            fill: white;
            stroke-width: 2px;
        }
         .icon__wrapper.success {
                background-color: #5cb85c; /* Green background for success */
            }

            .icon__wrapper.error {
                background-color: #d9534f; /* Red background for error */
            }

</style>
<figure class="notification" style="display:none;" id="notificationComponent">
    <div class="notification__body">
        <div class="notification__description">
         <div class="icon__wrapper" id="notificationIcon">
                <!-- Placeholder for SVG icon -->
            </div>
            <span id="notificationMessage">Report is saved!</span>
        </div>
       <!-- <button class="notification__button" onclick="editReport()">Edit report</button>-->
    </div>
    <div class="notification__progress"></div>
</figure>

<script>
   function showNotification(message, type) {
          var notificationComponent = document.getElementById('notificationComponent');
          var messageSpan = document.getElementById('notificationMessage');
          var iconWrapper = document.getElementById('notificationIcon');


          // Remove any existing type classes
          notificationComponent.classList.remove('success', 'error');
           iconWrapper.classList.remove('success', 'error');
          iconWrapper.innerHTML = '';
          // Add the class based on the type
          if(type === 'success') {
              notificationComponent.classList.add('success');
              iconWrapper.classList.add('success');
              iconWrapper.innerHTML =
              `<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                <path d="M20.285,4.707,9,16l-5.285-5.3a1,1,0,0,0-1.414,1.414l6,6a1,1,0,0,0,1.414,0L21.7,6.121A1,1,0,0,0,20.285,4.707Z"/>
               </svg>`;
          } else if(type === 'error') {
              notificationComponent.classList.add('error');
               notificationComponent.classList.add('error');
               iconWrapper.innerHTML = `
                              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                  <path d="M18.3,5.71,13.41,10.6,18.3,15.49a1,1,0,0,1,0,1.42,1,1,0,0,1-1.42,0L12,12.41,7.12,17.3A1,1,0,0,1,5.7,15.88L10.59,11,5.7,6.12A1,1,0,0,1,7.12,4.7L12,9.59l4.88-4.88a1,1,0,0,1,1.42,1.42Z"/>
                              </svg>
                          `;
          }

          messageSpan.innerText = message;
          notificationComponent.style.display = 'flex';
          notificationComponent.style.opacity = 1;
          notificationComponent.style.visibility = 'visible';

          setTimeout(function() {
              notificationComponent.style.opacity = 0;
              notificationComponent.style.visibility = 'hidden';
              notificationComponent.style.display = 'none';
          }, 3000);
      }

      function editReport() {
          // Implement the edit report functionality
      }
</script>
