//var stage = new NGL.Stage("viewport");
var stage = new NGL.Stage("view3d_rec", { backgroundColor: "white", cameraType: "orthographic" });
const viewerContainer = document.getElementById('viewerContainer');
var uploadProtein = document.getElementById('upload-protein');
var uploadLigand = document.getElementById('upload-ligand');
stage.setParameters({ backgroundColor: "white" });
var component;
let box;
var surfaceRepresentation = null;
var fileInput = document.getElementById('file');
var surfaceButton = document.getElementById('surfaceButton');
var surfaceValues = document.getElementById("surfaceDropdown");
var opacityRange = document.getElementById('opacityRange');
var representationDropdown = document.getElementById('representationDropdown');
var uploadbtn = document.getElementById('upload-btn');

let debounceTimeout = null;
//showUploadProtein();

var x_center = 0;
var y_center = 0;
var z_center = 0;
var x_size = 0;
var y_size = 0;
var z_size = 0;
var color_list;
var shape;
var shapeComp;
var opacity;
document.getElementById("surfaceOpacity").addEventListener("input", function () {
  changeSurfaceOpacity();
});
document.getElementById('box-coor').style.display = 'none';


//file change START
fileInput.addEventListener('change', function () {
alert("File changed");
  var file = this.files[0];
  if (file) {
    if (!stage) {
      console.log("stage creating....");
      //stage = new NGL.Stage("viewer");
      stage.removeAllComponents();
      //stage = new NGL.Stage("view3d_rec",{backgroundColor: "white",cameraType: "orthographic"} );
    }
    stage.removeAllComponents();
    component = undefined;
    stage.loadFile(file).then(function (comp) {
      component = comp;
      component.addRepresentation("cartoon");
      component.autoView();
      var pa = component.structure.getPrincipalAxes();
      stage.animationControls.rotate(pa.getRotationQuaternion(), 1500);
      stage.autoView();
      representationDropdown.disabled = false;
      document.getElementById("surfaceOpacity").disabled = false;
      document.getElementById("surfaceDropdown").disabled = false;

      console.log("showBoxCoordinates called");
      // showBoxCoordinates();
    });
    //stage.viewer.render();
    color = hexToRgb(document.getElementById("color").value);
    color_list = [color.r / 255, color.g / 255, color.b / 255]
    console.log(color_list);
    shape = new NGL.Shape("shape", { lineWidth: 10, aspectRatio: 10 });
    var pos = [x_center, y_center, z_center];
    var size = [x_size, y_size, z_size];
    shape.addBox(pos,               // position
      color_list,        // color
      size[0],           // size
      [0, size[1], 0], // height
      [0, 0, size[2]]); // depth
    shapeComp = stage.addComponentFromObject(shape);
    repr = shapeComp.addRepresentation("buffer", { opacity: 0.5 });
    // shapeComp.autoView();
    stage.viewer.render();
    alert("stage loaded")
  }
}
);
//File Change END
representationDropdown.addEventListener('change', function () {
  if (component) {
    if (this.value === 'surface') {
      component.addRepresentation(this.value);
      if (surfaceValues.value !== 'none') {
        component.addRepresentation('surface', { color: surfaceValues.value });
      }
      //component.addRepresentation('surface', {color: 'hydrophobicity'});
    } else {
      component.removeAllRepresentations();
      component.addRepresentation(this.value);
      if (surfaceValues.value !== 'none') {
        component.addRepresentation('surface', { color: surfaceValues.value });
      }
    }
  }
});
function changeSurface(selectedElement) {
  var selectedValue = selectedElement.value;
  console.log("Selected value: " + selectedValue);
  if (selectedValue === 'none') {
    component.removeRepresentation(surfaceRepresentation);
    surfaceRepresentation = null;
  } else {
    surfaceRepresentation = component.addRepresentation("surface", { opacity: document.getElementById("surfaceOpacity").value, colorScheme: selectedValue });
  }
}
function loadNGL() {
  stage = new NGL.Stage("viewport");
  stage.setParameters({ backgroundColor: "white" });
  // stage =new NGL.Stage("view3d_rec",{backgroundColor: "black",cameraType: "orthographic"} );
}
function showUploadProtein() {
  viewerContainer.style.display = 'block';
  uploadProtein.classList.add("show-div");
  uploadProtein.classList.remove("hide-div");
  //hide ligand uploaf div
  uploadLigand.classList.add("hide-div");
  uploadLigand.classList.remove("show-div");
  if (window.NGL) {
    loadNGL();
    stage.handleResize();
    move();
  } else {
    const script = document.createElement("script");
    script.src = "/js/ngl.js";
    script.onload = loadNGL;
    move();
    stage.handleResize();
    document.head.appendChild(script);
  }
}
// Surface opacity change function
function changeSurfaceOpacity() {
  clearTimeout(debounceTimeout);
  debounceTimeout = setTimeout(() => {
    if (component && surfaceRepresentation) {
      component.removeRepresentation(surfaceRepresentation);
      if (surfaceValues.value !== 'none') {
        //component.addRepresentation('surface', {color: surfaceValues.value});
        surfaceRepresentation = component.addRepresentation("surface", {
          opacity: document.getElementById("surfaceOpacity").value,
          //colorScheme: 'hydrophobicity'
          colorScheme: surfaceValues.value
        });
      }

    }
  }, 200);  // 200 ms debounce time
}
function uploadFile() {
    console.log("called get cooke");
    //get grid co-ordinates
   let data = {
       center: {
           x: x_center,
           y: y_center,
           z: z_center
       },
       size: {
           x: x_size,
           y: y_size,
           z: z_size
       }
   }
  var fileInput = document.getElementById('file');
  var file = fileInput.files[0];
 // var csrfToken = document.getElementsByName('_csrf')[0].value;
  var csrfToken = localStorage.getItem("token");
  let user = JSON.parse(localStorage.getItem('user'));
  let jobName = localStorage.getItem("jobName") || new Date().getTime();
  localStorage.setItem('jobName', jobName);
  console.log("token",csrfToken);
  if (!file) return;

  //const loadingIcon = uploadbtn.querySelector('#loadingIcon');
 // loadingIcon.style.display = 'inline-block';
 // uploadbtn.disabled = true;
  var formData = new FormData();
  formData.append('file', file);
  formData.append('_csrf', csrfToken);
  formData.append('email',user.email );
  formData.append('jobName',jobName);
  formData.append('coordinates', JSON.stringify(data));

  var xhr = new XMLHttpRequest();
  xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
  xhr.open('POST', '/GeinDock/dock/serverDockUpload', true);
  //xhr.setRequestHeader(csrfHeaderName, csrfToken);
  xhr.upload.onprogress = function (e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;

      // document.getElementById('progress-bar').value = percentComplete;
    }
  };
  xhr.onload = function () {
    if (this.status == 200) {

      alert("upload success");
      document.getElementById('first-step').classList.add('bg-success');
    //  loadingIcon.style.display = 'none';
      // document.getElementById('progress-bar').value = 100;
      // Load the uploaded file into the NGL viewer

    }else if (this.status == 401 || this.status == 403) {
            // If unauthorized or forbidden, redirect to login page
          //  window.location.href = "/path-to-your-login-page";
          alert("YOur token is expired need to login");
        }
    
     else {
      // handle error here
      
    }
  };
  xhr.send(formData);
}
function showBoxCoordinates() {
  //$('#myModal').modal('show');
  //document.getElementById('box-coor').classList.remove('hide-div');
  var coorDiv = document.getElementById('box-coor');
  console.log("style==" + coorDiv.style.display);
  if (coorDiv.style.display === 'none') {
    coorDiv.style.display = 'block';
  }
  else {
    coorDiv.style.display = 'none';
    return;
  }


  x_center = document.getElementById("x_center").value;
  y_center = document.getElementById("y_center").value;
  z_center = document.getElementById("z_center").value;

  x_size = parseFloat(document.getElementById("x_size").value);
  y_size = parseFloat(document.getElementById("y_size").value);
  z_size = parseFloat(document.getElementById("z_size").value);
  console.log("x_center=" + x_center);
  console.log("y_center=" + y_center);
  console.log("z_center=" + z_center);
  console.log("x_size=" + x_size);
  console.log("y_size=" + y_size);
  console.log("z_size=" + z_size);

  /*const fileInput = document.getElementById('file');
  if (fileInput.files.length > 0) {
     $('#myModal').modal('show');
    } else {
      alert("Please select Protein file");
    }
  */
}
function move(e) {
  x_center = document.getElementById("x_center").value;
  y_center = document.getElementById("y_center").value;
  z_center = document.getElementById("z_center").value;

  x_size = parseFloat(document.getElementById("x_size").value);
  y_size = parseFloat(document.getElementById("y_size").value);
  z_size = parseFloat(document.getElementById("z_size").value);

  document.getElementById("x_center_label").innerHTML = x_center;
  document.getElementById("y_center_label").innerHTML = y_center;
  document.getElementById("z_center_label").innerHTML = z_center;

  document.getElementById("x_size_label").innerHTML = x_size;
  document.getElementById("y_size_label").innerHTML = y_size;
  document.getElementById("z_size_label").innerHTML = z_size;

  color = hexToRgb(document.getElementById("color").value);
  color_list = [color.r / 255, color.g / 255, color.b / 255];
  opacity = parseFloat(document.getElementById("opacity").value) / 100;
  update_box();

  // shapeComp.autoView();
  //  stage.viewer.render();

}
box_updater = undefined;
function update_box() {
  box_updater = setTimeout(
    function () {
      if (shapeComp) {
        shapeComp.removeAllRepresentations();
        stage.removeComponent(shapeComp);

      }
      shape = new NGL.Shape("shape", { lineWidth: 10, aspectRatio: 10 });
      var pos = [
        parseFloat(x_center),
        parseFloat(y_center),
        parseFloat(z_center)
      ];
      //pos = [14.87, -0.71, 8.5];
      var size = [
        parseFloat(x_size),
        parseFloat(y_size),
        parseFloat(z_size)
      ];
      // var color_list = [0, 1, 1];
      shape.addBox(
        pos,               // position
        color_list,        // color
        size[0],           // size
        [0, size[1], 0],   // height
        [0, 0, size[2]]    // depth
      );
      wireframe_flag = document.getElementById("wire").checked
      shapeComp = stage.addComponentFromObject(shape);
      repr = shapeComp.addRepresentation("buffer", { opacity: opacity, wireframe: wireframe_flag });
    }, 50)
}
function hexToRgb(hex) {
  // Convert Hex color to rgb fomat
  var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
  return result ? {
    r: parseInt(result[1], 16),
    g: parseInt(result[2], 16),
    b: parseInt(result[3], 16)
  } : null;
}

async function submitJob() {
  alert("submitting job");
  const form = new FormData(document.getElementById('docking-form'));
  const response = await fetch('/GeinDock/uploadpdb', {
    method: 'POST',
    body: form
  });
  if (response.ok) {
    const result = await response.json();
    addSubmittedJob(result);
  } else {
    console.error('Error submitting job:', response.statusText);
  }
}

//make ajax call to submit file

function uploadPDBFile() {
  alert("Uploading File");
  const form = new FormData(document.getElementById('uploadPdb-form'));
  const userFile = document.getElementById('file').files[0];
  alert(userFile);
  const formData = new FormData();
  formData.append("file", userFile, "userfile.pdb");
  const response = fetch('/GeinDock/dock/serverDockUpload', {
    method: 'POST',
    body: form
  });
  alert(response);
  console.log("Success upload");
}

//Docking
/*
function startDocking(){
    //check file is uploaded
    let user = JSON.parse(localStorage.getItem('user'));
    let jobName = localStorage.getItem("jobName") || new Date().getTime();
    let csrfToken = localStorage.getItem("token");
    console.log(user.email,jobName);
    let isError;

    const params = new URLSearchParams({
        jobName: jobName,
        email: user.email,
        csrfToken:csrfToken
    });
   fetch('/dock/checkUpload?' + params.toString(), { method: 'GET' })
       .then(response => response.text())
       .then(text => {
           console.log(text);
           if(text.toLowerCase().includes('failed')){
            alert(text);
            return;
           }

       })
       .catch(error => console.error(error));
establishSseConnection();
    alert("Start docking");
    //call start docking
   var formData = new FormData();
   formData.append('_csrf', csrfToken);
   formData.append('email', user.email);
   formData.append('jobName', jobName);
   console.log("csrfToken", csrfToken);
   console.log("form data", formData);

   fetch('/dock/serverDock', {
       method: 'POST',
       body: formData,
   })
   .then(response => response.text())
   .then(text => {
       console.log(text);
   })
   .catch(error => console.error(error));

    //establishSseConnection();
}
function establishSseConnection() {
    var eventSource = new EventSource("/stream-updates");

    eventSource.onmessage = function(event) {
        var message = event.data;
        console.log("Server Update: ", message);
        updateUiWithServerMessage(message);
    };
    eventSource.onerror = function(err) {
        console.error("EventSource failed:", err);
        eventSource.close();
    };
}
function updateUiWithServerMessage(message) {
    alert("Server Update: " + message);
}
*/
//Docking
function startDocking() {
    // Retrieve user and job details from localStorage
    let user = JSON.parse(localStorage.getItem('user'));
    let jobName = localStorage.getItem("jobName") || new Date().getTime();
    let csrfToken = localStorage.getItem("token");

    console.log(user.email, jobName);

    // Construct parameters for the checkUpload request
    const params = new URLSearchParams({
        jobName: jobName,
        email: user.email,
        csrfToken: csrfToken
    });

    // Check if the file is uploaded
    fetch('/dock/checkUpload?' + params.toString(), { method: 'GET' })
        .then(response => response.text())
        .then(text => {
            console.log(text);
            if (text.toLowerCase().includes('failed')) {
                alert(text);
                return; // Exit the function if the upload check fails
            }

            // Proceed with docking if upload check is successful
            console.log("start connection");
             establishSseConnection(); // Establish SSE connection after initiating docking
            console.log("start docking");
            initiateDocking(user, jobName, csrfToken);

        })
        .catch(error => console.error('Error checking upload:', error));
}

function initiateDocking(user, jobName, csrfToken) {
    alert("Start docking");

    // Prepare formData for the serverDock request
    var formData = new FormData();
    formData.append('_csrf', csrfToken);
    formData.append('email', user.email);
    formData.append('jobName', jobName);

    console.log("csrfToken", csrfToken);
    console.log("form data", formData);

    // Call start docking endpoint
    fetch('/GeinDock/dock/serverDock', {
        method: 'POST',
        body: formData,
    })
    .then(response => response.text())
    .then(text => {
        console.log(text);
        //establishSseConnection(); // Establish SSE connection after initiating docking
    })
    .catch(error => console.error('Error initiating docking:', error));
}

function establishSseConnection() {
    var eventSource = new EventSource("/GeinDock/stream-updates");

    eventSource.onmessage = function(event) {
        var message = event.data;
        console.log("Server Update: ", message);
        updateUiWithServerMessage(message);
    };

    eventSource.onerror = function(err) {
        console.error("EventSource failed:", err);
        eventSource.close();
    };
}

function updateUiWithServerMessage(message) {
    alert("Server Update: " + message);
}

// Call startDocking when the page loads or based on some user action
//startDocking();


