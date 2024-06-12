
//var stage = new NGL.Stage("viewport1");
var proteinComponent;
 var contextPath = "<%=request.getContextPath()%>";
//var stage = new NGL.Stage("viewport1");
var stage = new NGL.Stage("viewport1", { backgroundColor: "white", cameraType: "orthographic" });
var surfaceValues = document.getElementById("surfaceDropdown");
var fileInput = document.getElementById('file');
var surfaceRepresentation = null;
let debounceTimeout = null;
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
window.addEventListener("resize", function (event) {
    stage.handleResize();
    //stage_lig.handleResize();
    //stage_dock.handleResize();
}, false);

function loadProtein(input) {
    alert("load protein");
    if (input.files && input.files[0]) {
        showProteinViewDiv();
        var reader = new FileReader();
        reader.onload = function (e) {
            //var stage = new NGL.Stage("viewport1");

            stage.setParameters({ backgroundColor: "white" });
            stage.loadFile(new Blob([e.target.result], { type: "text/plain" }), { ext: "pdb", defaultRepresentation: true }).then(function (component) {
                proteinComponent = component;
                component.addRepresentation("cartoon");
                stage.autoView();
                //updateBox();
            });

        };
        reader.readAsText(input.files[0]);
        //SHOWING DEFAULT GRID BOX
        color = hexToRgb(document.getElementById("color").value);
        color_list = [color.r / 255, color.g / 255, color.b / 255];
        var pos = [x_center, y_center, z_center];
        var size = [x_size, y_size, z_size];
        shape = new NGL.Shape('shape');
        shape = new NGL.Shape("shape", { lineWidth: 10, aspectRatio: 10 });
        shape.addBox(pos,               // position
            color_list,        // color
            size[0],           // size
            [0, size[1], 0], // height
            [0, 0, size[2]]); // depth
        shapeComp = stage.addComponentFromObject(shape);
        repr = shapeComp.addRepresentation("buffer", { opacity: 0.5 });
        // shapeComp.autoView();
        stage.viewer.render();
        // alert("stage loaded")


    }
}
//for grid box START

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



function updateBox() {
    var x_center = parseFloat(document.getElementById("x_center").value);
    var y_center = parseFloat(document.getElementById("y_center").value);
    var z_center = parseFloat(document.getElementById("z_center").value);
    var x_size = parseFloat(document.getElementById("x_size").value);
    var y_size = parseFloat(document.getElementById("y_size").value);
    var z_size = parseFloat(document.getElementById("z_size").value);
    var color = document.getElementById("color").value;
    color = hexToRgb(document.getElementById("color").value);
    color_list = [color.r / 255, color.g / 255, color.b / 255];
    var pos = [x_center, y_center, z_center];
    var size = [x_size, y_size, z_size];

    var opacity = parseFloat(document.getElementById("opacity").value) / 100;

    if (shapeComp) {
        // Remove the previous box
        shapeComp.removeRepresentation(shape);
    }

    shape = new NGL.Shape('shape');
    //shape.addBox([x_center, y_center, z_center], [x_size / 2, y_size / 2, z_size / 2], color, 'Box');
    shape.addBox(pos,               // position
        color_list,        // color
        size[0],           // size
        [0, size[1], 0], // height
        [0, 0, size[2]]); // depth
    shapeComp = proteinComponent.stage.addComponentFromObject(shape);
    shapeComp.addRepresentation('buffer', { opacity: opacity });
    stage.viewer.render();
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


//for grid box END
var representationDropdown = document.getElementById("representationDropdown");
if (representationDropdown) {
    representationDropdown.addEventListener('change', function () {
        if (proteinComponent) {
            proteinComponent.removeAllRepresentations();
            proteinComponent.addRepresentation(this.value);
            if (surfaceValues.value !== 'none') {
                proteinComponent.addRepresentation('surface', { color: surfaceValues.value });
            }
        }
    });
}

if (surfaceValues) {
    surfaceValues.addEventListener('change', function () {
        if (proteinComponent) {
            if (surfaceRepresentation) {
                proteinComponent.removeRepresentation(surfaceRepresentation);
            }
            var selectedValue = surfaceValues.value;
            if (selectedValue !== 'none') {
                surfaceRepresentation = proteinComponent.addRepresentation("surface", { opacity: document.getElementById("surfaceOpacity").value, colorScheme: selectedValue });
            }
        }
    });
}
document.getElementById("surfaceOpacity").addEventListener("input", function () {
    if (proteinComponent && surfaceRepresentation) {
        proteinComponent.removeRepresentation(surfaceRepresentation);
        if (surfaceValues.value !== 'none') {
            surfaceRepresentation = proteinComponent.addRepresentation("surface", {
                opacity: document.getElementById("surfaceOpacity").value,
                colorScheme: surfaceValues.value
            });
        }
    }
});


// Other UI functions

// Coordination div Start
var coordinateDiv = document.getElementById("box-coor");
coordinateDiv.style.display = "none";
function toggleGridDiv() {
    var div = document.getElementById("box-coor");
    if (coordinateDiv.style.display === "none" || coordinateDiv.style.display === "") {
        coordinateDiv.style.display = "block";
    } else {
        coordinateDiv.style.display = "none";
    }
}

var proteinView = document.getElementById("protein-view");
if (proteinView) {
    proteinView.style.display = "none";
    // proteinView.style.visibility = "hidden";
}


function showProteinViewDiv() {
    var proteinFileDiv = document.getElementById("protein-file-div");
    if (proteinFileDiv) {
        proteinFileDiv.style.display = "none";
        //  proteinView.style.visibility = "visible";
    }

    if (proteinView) {
        proteinView.style.display = "block";
    }

}
// Coordination div End

//uploading file START

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
    const jobIdField = document.getElementById('job-id');
    var fileInput = document.getElementById('file1');
    var file = fileInput.files[0];
    var csrfToken = localStorage.getItem("token");
    let user = JSON.parse(localStorage.getItem('user'));
    let jobName = localStorage.getItem("jobName") || new Date().getTime();
    localStorage.setItem('jobName', jobName);
    jobIdField.value = localStorage.getItem('jobName');
    console.log("token", csrfToken);
    if (!file) return;
    var formData = new FormData();
    formData.append('file', file);
    formData.append('_csrf', csrfToken);
    formData.append('email', user.email);
    formData.append('jobName', jobName);
    formData.append('coordinates', JSON.stringify(data));

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/GeinDock/dock/serverDockUpload', true);
    xhr.upload.onprogress = function (e) {
        if (e.lengthComputable) {
            var percentComplete = (e.loaded / e.total) * 100;
        }
    };
    xhr.onload = function () {
        if (this.status == 200) {
            alert("upload success");
        } else {
            // handle error here
        }
    };
    xhr.send(formData);
}
//uploading file END
function getJobByName(jobName) {
    // Encode the jobName to ensure it's safe to include in a URL
    const encodedJobName = encodeURIComponent(jobName);

    // Construct the URL with the query parameter
    const url = `/GeinDock/dock/getJobByName?jobName=${encodedJobName}`;

    // Make the GET request to the server
    fetch(url)
        .then(response => {
            // Check if the response is successful
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the JSON response
        })
        .then(jobData => {
            console.log('Job Data:', jobData); // Handle the job data
            addRowToTable(jobData);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}
function addRowToTable(jobData) {
    // Assuming jobData contains the necessary fields
    const table = document.getElementById('result-table').getElementsByTagName('tbody')[0];
    const newRow = table.insertRow(table.rows.length);

    // Insert cells (`<td>`) into the new row
    newRow.insertCell(0).innerHTML = table.rows.length; // Sr.No
    newRow.insertCell(1).innerHTML = jobData.jobName; // Job ID
    newRow.insertCell(2).innerHTML = jobData.proteinFile.split('_').pop(); // Protein
    newRow.insertCell(3).innerHTML = jobData.ligandFiles.split('_').pop(); // Ligand
    // Create a progress bar element
    const progressBar = document.createElement('div');
    progressBar.className = 'progress';
    progressBar.innerHTML = `
            <div id="${jobData.jobName}-progress" class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
        `;

    // Insert the progress bar into the last cell
    newRow.insertCell(4).appendChild(progressBar);
    // Make the row clickable
    newRow.addEventListener('click', function () {
        // Action to be performed on row click
        // For example, redirecting to another page or showing details
        console.log("Row clicked:", jobData.jobName);
        alert("Row clicked:", jobData.jobName);
        // Example: Redirect to a URL with the job ID
        // window.location.href = 'your-url/' + jobData.jobName;
    });
}
function startDocking() {
    // Retrieve user and job details from localStorage
    let user = JSON.parse(localStorage.getItem('user'));
    let jobName = localStorage.getItem("jobName") || new Date().getTime();
    localStorage.removeItem("jobName");
    let csrfToken = localStorage.getItem("token");

    console.log(user.email, jobName);

    // Construct parameters for the checkUpload request
    const params = new URLSearchParams({
        jobName: jobName,
        email: user.email,
        csrfToken: csrfToken
    });

    // Check if the file is uploaded
    fetch('/GeinDock/dock/checkUpload?' + params.toString(), { method: 'GET' })
        .then(response => response.text())
        .then(text => {
            console.log(text);
            if (text.toLowerCase().includes('failed')) {
                alert(text);
                return; // Exit the function if the upload check fails
            }

            // Proceed with docking if upload check is successful
            console.log("start connection");
            //get the job object
            getJobByName(jobName);
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

    eventSource.onmessage = function (event) {
        var message = event.data;
        console.log("Server Update: ", message);
        updateUiWithServerMessage(message);
    };

    eventSource.onerror = function (err) {
        console.error("EventSource failed:", err);
        eventSource.close();
    };
}
function updateUiWithServerMessage(message) {
    //alert("Server Update: " + message);
    try {
        var data = JSON.parse(message);
        if (data && data.status && data.jobName) {
            // Calculate progress percentage (assuming status goes from 1 to 6)
            var progressPercentage = (data.status / 9) * 100;

            // Update the progress bar for the specific job
            updateProgressBar(data.jobName, progressPercentage);
        }
    } catch (error) {
        console.error("Error parsing message:", error);
    }
}
function updateProgressBar(jobName, progress) {
    const progressBar = document.getElementById(`${jobName}-progress`);
    if (progressBar) {
        progressBar.style.width = progress + '%';
        progressBar.setAttribute('aria-valuenow', progress);
        console.log("progress=",progress);
        if(progress>=100){
			// alert("Progress finished for job: " + jobName);
			const row = getTableRowByJobName(jobName);
			 if (row) {
                // Change the content of the current last cell to "Completed"
                let lastCell = row.cells[row.cells.length - 1]; 
                lastCell.innerHTML = "Completed";

                // Add a new cell for the links
                let newCell = row.insertCell(row.cells.length);
               // newCell.innerHTML = `<a href="/view/${jobName}">View</a> | <a href="mailto:someone@example.com?subject=Results for Job ${jobName}">Email</a>`;
             newCell.innerHTML = `<a href="javascript:void(0)" onclick="window.location.href='/GeinDock/geinDockResult?jobId=${jobName}'">View</a> | <a href="mailto:someone@example.com?subject=Results for Job ${jobName}">Email</a>`;
            }
		}
    }
    
}
function getTableRowByJobName(jobName) {
    const progressBarId = `${jobName}-progress`;
    const progressBar = document.getElementById(progressBarId);
    if (!progressBar) {
        console.log('Progress bar not found for jobName:', jobName);
        return null; // Progress bar not found
    }

    let currentElement = progressBar;
    while (currentElement && currentElement.tagName !== 'TR') {
        currentElement = currentElement.parentNode; // Move up in the DOM tree
    }

    if (currentElement && currentElement.tagName === 'TR') {
        return currentElement; // Found the table row
    } else {
        console.log('Table row not found for jobName:', jobName);
        return null; // Table row not found
    }
}

function reset() {
    // Remove the current protein component
    if (proteinComponent) {
        stage.removeComponent(proteinComponent);
        proteinComponent = null;
    }

    // Reset file input
    document.getElementById('file1').value = '';

    // Hide protein view div and show file input div
    var proteinFileDiv = document.getElementById("protein-file-div");
    if (proteinFileDiv) {
        proteinFileDiv.style.display = "block";
    }
    if (proteinView) {
        proteinView.style.display = "none";
    }

}



function populateTable() {
    const email = localStorage.getItem('email');
    if (!email) {
        console.log('No email found in localStorage');
        return;
    }

    fetch(`/GeinDock/dock/getJobs?email=${encodeURIComponent(email)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(jobs => {
            const tableBody = document.getElementById('result-table').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = ''; // Clear existing rows
            jobs.forEach((job, index) => {
                const row = tableBody.insertRow();
                //row.insertCell(0).innerHTML = index + 1; // Sr.No
                row.insertCell(0).innerHTML = job.jobName; // Job ID
                row.insertCell(1).innerHTML = job.proteinFile; // Protein
                row.insertCell(2).innerHTML = job.ligandFiles; // Ligand
                row.insertCell(3).innerHTML = 'Auto AI Docking' // Ligand
                row.insertCell(4).innerHTML = job.status; // Status

                const viewResultCell = row.insertCell(5);

                const viewResultLink = document.createElement('a');
                viewResultLink.textContent = 'View';
                viewResultLink.style.cursor = 'pointer';
                viewResultLink.style.color = 'blue';
                viewResultLink.style.textDecoration = 'underline';

                //viewResultLink.href = `/dockingVinaResult?jobId=${job.jobName}`;
                viewResultLink.addEventListener('click', function () {
                    const jobName = job.jobName;
                    console.log("job select for view");
                    prepareOutput(jobName);
                    
                   // viewResultLink.href = `${pageContext.request.contextPath}/geinDockResult?jobId=${job.jobName}`;
                 viewResultLink.href = `/GeinDock/geinDockResult?jobId=${job.jobName}`;
                });
                viewResultLink.textContent = 'View';
                viewResultCell.appendChild(viewResultLink);

                viewResultCell.appendChild(document.createTextNode(' | '));

                const emailLink = document.createElement('a');
               	
               	
               	emailLink.href =contextPath + `/GeinDock/sendEmail?jobId=${job.jobName}`;
                emailLink.textContent = 'Email';
                viewResultCell.appendChild(emailLink);



                // Make the row clickable
                row.addEventListener('click', function () {
                    console.log("Row clicked:", job.jobName);
                    //  alert("Row clicked:" + job.jobName);

                });
            });
        })
        .catch(error => console.error('Error:', error));
}
function prepareOutput(jobName) {

    let user = JSON.parse(localStorage.getItem('user'));
    let csrfToken = localStorage.getItem("token");
    var formData = new FormData();
    formData.append('_csrf', csrfToken);
    formData.append('email', user.email);
    formData.append('jobName', jobName);
    fetch('/GeinDock/dock/prepareOutput', {
        method: 'POST',
        body: formData,
    })
        .then(response => response.text())
        .then(text => {
            console.log(text);

        })
        .catch(error => console.error('Error initiating docking:', error));

}

