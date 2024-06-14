
const params = new URLSearchParams(window.location.search);
const jobId = params.get('jobId');
var stage_dock = new NGL.Stage("viewport", { backgroundColor: "white", cameraType: "orthographic" });
let loadedComponentsMap = {};

/*var stage_dock = new NGL.Stage("view3d_dock",
    {
        backgroundColor: "white",
        cameraType: "orthographic"
    });*/


const email = localStorage.getItem("email");
//alert(email);
//alert(jobId);
// Function to call the getJobByName API
var coordinates;
function getJobByName(jobName) {
    const encodedJobName = encodeURIComponent(jobName);
    const url = `/GeinDock/dock/getJobByName?jobName=${encodedJobName}`;
    fetch(url)
        .then(response => {
            // Check if the request was successful
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON in the response
        })
        .then(job => {
          //  console.log(job);
            console.log('Job object:', job);
            
           var coordinatesStr =  job.coordinates;
            const jsonObject = JSON.parse(coordinatesStr);
            //coordinates  = `Center:{x: ${jsonObject.center.x}, y: ${jsonObject.center.y}, z: ${jsonObject.center.z}} Size:{${jsonObject.size.x}, ${jsonObject.size.y}, ${jsonObject.size.z}}`;
           coordinates = `Center:{x: ${jsonObject.center.x.toFixed(2)}, y: ${jsonObject.center.y.toFixed(2)}, z: ${jsonObject.center.z.toFixed(2)}} Size:{x: ${jsonObject.size.x.toFixed(2)}, y: ${jsonObject.size.y.toFixed(2)}, z: ${jsonObject.size.z.toFixed(2)}}`;
			coordinates = `{${jsonObject.center.x.toFixed(2)},${jsonObject.center.y.toFixed(2)},${jsonObject.center.z.toFixed(2)}}`;
            const proteinFileLink = document.getElementById('proteinFileLink');
            //proteinFileLink.textContent = job.proteinFile;
            

            const ligandFileLink = document.getElementById('ligandFileLink');
            //ligandFileLink.textContent = job.ligandFiles;
            
            const complexFileLink = document.getElementById('complexFileLink');
            //complexFileLink.textContent = job.outputFile;
            
            var url = `/GeinDock/dock/download?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}&fileType=${encodeURIComponent('protein')}&fileName=${encodeURIComponent(job.proteinFile)}`;
           console.log("got url ",url);
            proteinFileLink.href = `/GeinDock/dock/download?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}&fileType=protein&fileName=${encodeURIComponent(job.proteinFile)}`;
            ligandFileLink.href = `/GeinDock/dock/download?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}&fileType=ligand&fileName=${encodeURIComponent(job.ligandFiles)}`;
			complexFileLink.href = `/GeinDock/dock/download?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}&fileType=complex&fileName=${encodeURIComponent(job.outputFile)}`;          
          //  proteinFileLink.target = url;
        })
        .catch(error => {
            console.error('There was an error fetching the job:', error);
        });
}
//console.log(getJobByName(jobId));

//3D viewer

//console.log("stage created");
window.addEventListener("resize", function (event) {
    //  stage_rec.handleResize();
    // stage_lig.handleResize();
    stage_dock.handleResize();
}, false);
dock_view_setter = undefined
function loadNGL_dock() {
    stage_dock.removeAllComponents();
    var fileName = getDockLigFilesName(jobId, email);
  //  console.log(fileName);
  //  console.log("Getting Output file");
    // downloadProteinFile(jobId, email)
    downloadOutputFile(jobId, email)
        // downloadOutputFile(jobId, email)
        .then(blobUrl => {
            // Load the model into NGL Viewer
           // console.log("blob url=", blobUrl);
            stage_dock.loadFile(blobUrl, { ext: "pdb" }).then(function (component) {
                component.addRepresentation("cartoon");
                component.autoView();
            });


        })
        .catch(error => {
            console.error('Error loading file into NGL Viewer:', error);
        });
}

function downloadOutputFile(jobName, email) {
    const encodedJobName = encodeURIComponent(jobName);
    const encodedEmail = encodeURIComponent(email);
    const url = `/GeinDock/dock/getOutput?jobName=${encodedJobName}&email=${encodedEmail}`;
   // console.log(url);
    // Use the fetch API to call the endpoint
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const contentDisposition = response.headers.get('Content-Disposition');
            let fileName = 'downloaded_file';
            if (contentDisposition) {
                const fileNameMatch = contentDisposition.match(/filename="?(.+)"?/);
                if (fileNameMatch.length > 1) {
                    fileName = fileNameMatch[1];
                }
            }
           // console.log('Received file name:', fileName);




            return response.blob();
        })
        .then(blob => {
            // Create a Blob URL
            return window.URL.createObjectURL(blob);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            throw error; // Rethrow to handle it in the calling code
        });
}
function downloadProteinFile(jobName, email) {
    const encodedJobName = encodeURIComponent(jobName);
    const encodedEmail = encodeURIComponent(email);
    const url = `/GeinDock/dock/getProteinFile?jobName=${encodedJobName}&email=${encodedEmail}`;

    // Use the fetch API to call the endpoint
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
          //  console.log("API response: ", response.body);
            return response.blob();
        })
        .then(blob => {
            // Create a Blob URL
            return window.URL.createObjectURL(blob);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            throw error; // Rethrow to handle it in the calling code
        });
}
function getDockLigFilesName(jobName, email) {
    // Construct the URL with query parameters

    const encodedJobName = encodeURIComponent(jobName);
    const encodedEmail = encodeURIComponent(email);
    const url = `/GeinDock/dock/getDockLigFilesName?jobName=${encodedJobName}&email=${encodedEmail}`;




    // Making the API call
    fetch(url)
        .then(response => {
            // Check if the response is successful
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
              //  console.log('File names retrieved:', data.data);
            } else {
                console.error('Failed to retrieve file names:', data.message);
            }
        })
        .catch(error => {
            console.error('There was an error fetching the file names:', error);
        });
}

function downloadOutputFile1(jobName, email) {

    const encodedJobName = encodeURIComponent(jobName);
    const encodedEmail = encodeURIComponent(email);
    const url = `/GeinDock/dock/getOutput?jobName=${encodedJobName}&email=${encodedEmail}`;
    // Use the fetch API to call the endpoint
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.blob();
        })
        .then(blob => {
            // Create a link element to download the file
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            // Assuming you want to use the jobName as the filename
            a.download = jobName;
            document.body.appendChild(a);
            //a.click();
            window.URL.revokeObjectURL(url);
            //  document.body.removeChild(a);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

//load one proteing and all ligand files START

function loadNGL() {
  //  console.log("loding all files once");
    // var stage = new NGL.Stage("viewport", { backgroundColor: "white" });
    stage_dock.removeAllComponents();
    // Replace these with the actual job name and email
    var jobName = params.get('jobId');
    var email = localStorage.getItem("email");

    // Load the protein
    downloadAndLoadProtein(stage_dock, jobName, email);

    // Download and load ligand files
   // downloadAndLoadLigands(stage_dock, jobName, email);
    //downloadInteraction(stage_dock,jobName,email);
}

function downloadAndLoadProtein(stage, jobName, email) {

    var url = `/GeinDock/dock/getProteinFile?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}`;
    fetch(url)
        .then(response => response.blob())
        .then(blob => {
            var blobUrl = URL.createObjectURL(blob);
            //loadFileIntoStage(stage, blobUrl, "cartoon", { color: "blue" });

            loadFileIntoStage(stage, blobUrl, "cartoon", { backgroundColor: "white", cameraType: "orthographic" },'pdb',0);

        })
        .catch(error => {
            console.error('Error loading protein file:', error);
        });
}

function downloadAndLoadLigands(stage, jobName, email) {
    const encodedJobName = encodeURIComponent(jobName);
    const encodedEmail = encodeURIComponent(email);
    var url = `/GeinDock/dock/getDockLigFiles?jobName=${encodedJobName}&email=${encodedEmail}`;
    fetch(url)
        .then(response => response.blob())
        .then(blob => {
            var jszip = new JSZip();
            return jszip.loadAsync(blob);
        })
        .then(zip => {

            Object.keys(zip.files).forEach(filename => {
                zip.files[filename].async("blob").then(blob => {

                    var blobUrl = URL.createObjectURL(blob);
                    //loadFileIntoStage(stage, blobUrl, "ball+stick", { color: "red" });
                    loadFileIntoStage(stage, blobUrl, "ball+stick", { backgroundColor: "white", cameraType: "orthographic" },'pdb',0);


                });
            });
        })
        .catch(error => {
            console.error('Error loading ligand files:', error);
        });
}
function loadFileIntoStage(stage, filePath, representationType, representationParams, fileExtension,index) {

    stage.loadFile(filePath, { ext: "pdb" }).then(function (component) {
        component.addRepresentation(representationType, representationParams);
        component.autoView();
      //  console.log("Adding to map with index:", index);
         loadedComponentsMap[index] = component;
    });
}
function removeComponentFromStage(stage, index) {

    const component = loadedComponentsMap[index];
    if (component) {
   // console.log("Current stage components before removal:", stage.compList);
        stage.removeComponent(component);
     //   console.log("Removing from map with index:", index);
        delete loadedComponentsMap[index];
      //  console.log("Current stage components after removal:", stage.compList);
        // component.removeAllRepresentations();
        // stage.autoView();
        // stage.requestRender();
    } else {
        console.error(`Component ${index} not found.`);
    }
}
function downloadInteraction(stage,jobName, email) {
    var url = `/GeinDock/dock/getInteraction?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}`;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            //
            // console.log(response.json());
            return response.json();
        })
        .then(interactionList => {
            // Visualize interactions
          //  console.log(JSON.stringify(interactionList, null, 2));
            displayInteractions(stage, interactionList);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}
function displayInteractions(stage, interactionList)
{
    const shape = new NGL.Shape("Interactions");
     //const shape = new NGL.Shape("Interactions");
        const shapeComp = stage.addComponentFromObject(shape);
    interactionList.forEach(interaction =>
     {
        const { coords1, coords2, atom1, atom2, type } = interaction;
        const startPos = new NGL.Vector3(...coords1);
        const endPos = new NGL.Vector3(...coords2);

        // Color customization
        let color;
        switch (interaction.type)
        {
            case 'HYDROGEN_BOND':
                 color = [0, 0, 1]; // Blue
                // color = [0, 1, 1]; // Cyan (or a brighter blue like [0, 0.8, 1])
                break;
            case 'HYDROPHOBIC':
                color = [1, 1, 0]; // Yellow
                break;
            case 'IONIC':
                color = [1, 0, 1]; // Magenta
                break;
            case 'SALT_BRIDGE':
                color = [0, 1, 1]; // Cyan
                break;
            case 'PI_STACKING':
                color = [1, 0.5, 0]; // Orange
                break;
            case 'OTHER':
                color = [0.5, 0.5, 0.5]; // Gray
                break;
            default:
                color = [1, 0, 0]; // Red (fallback)
        }
        const radius = 0.1;
        // Shape selection
        if (type === 'HYDROGEN_BOND')
        {
            const radius = 0.1;
            const dashLength = 0.3; // Slightly longer dashes
            const gapLength = 0.15; // Slightly wider gaps
            // Dashed cylinder with emphasis
            const numDashes = Math.floor(startPos.distanceTo(endPos) / (dashLength + gapLength));
            const direction = endPos.clone().sub(startPos).normalize();
            for (let i = 0; i < numDashes; i++)
            {
                const dashStart = startPos.clone().add(direction.clone().multiplyScalar(i * (dashLength + gapLength)));
                const dashEnd = dashStart.clone().add(direction.clone().multiplyScalar(dashLength));
                // Increase radius slightly for hydrogen bonds
                shape.addCylinder(dashStart, dashEnd, color, radius * 1.2, `${atom1}-${atom2}-dash-${i}`);
            }
        }
        else if (type === 'HYDROPHOBIC')
        {
            // Let's use a solid cylinder for hydrophobic interactions
            shape.addCylinder(startPos, endPos, color, radius, `${atom1}-${atom2}`);
              // console.log("adding HYDROPHOBIC");
             //  shapeComp.addRepresentation("tube", { sele: `${atom1} or ${atom2}`, color: [1, 1, 0, 0.8], radius: 0.2 });


        }
        else if (interaction.type === 'SALT_BRIDGE')
        {
            // Let's use arrows for salt bridges
            shape.addArrow(startPos, endPos, color, radius);
        }
        else if (interaction.type === 'PI_STACKING')
        {
            // We'll need a custom representation for pi-stacking (more on this later)
            shape.addRepresentation('cartoon', { sele: `${atom1} or ${atom2}` }); // Temporarily highlight the residues
        }
        else
        {
            // Spheres for other interaction types
          //  shape.addSphere(startPos, color, radius);
            shape.addSphere(endPos, color, radius);
        }
        // Optional labeling (adjust the condition as needed)
        if (interactionList.length <= 10)
        {
            shape.addText(startPos.add(endPos).multiplyScalar(0.5), color, `${type}`, 0.5);
        }
    });

    // Add the shape to the NGL stage
  //  const shapeComp = stage.addComponentFromObject(shape);
    shapeComp.addRepresentation("buffer");
    stage.autoView();
}


//download docking output table
async function downloadDockingTable(jobName, email) {

   // console.log("got JobName",jobName);
   // console.log("got email",email);
    var url = `/GeinDock/dock/getOutputTable?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}`;
    fetch(url, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
       // console.log(response.json);
        return response.json(); // Parse JSON response
    })
    .then(data => {
     //   console.log(data); // Handle the data from the response
     const tableBody = document.getElementById('tableBody');
     tableBody.innerHTML = '';
        data.forEach(item => {
         const row = document.createElement('tr');

         const modeCell = document.createElement('td');
         modeCell.textContent = item.mode;
         row.appendChild(modeCell);

         
         
          const vinaScore = document.createElement('td');
         vinaScore.textContent = item.affinity;
         row.appendChild(vinaScore);
         
         const coordinatescol = document.createElement('td');
         coordinatescol.textContent = coordinates;
          coordinatescol.classList.add('small-td');
         row.appendChild(coordinatescol);

          const ligandFileCell = document.createElement('td');
          const fileName = item.ligand.split("\\").pop() || item.ligand.split("/").pop();
          const fileLink = document.createElement('a');
          fileLink.classList.add('fs--2');
          
          fileLink.href = `/GeinDock/dock/download?jobName=${encodeURIComponent(jobName)}&email=${encodeURIComponent(email)}&fileType=output&fileName=${encodeURIComponent(fileName)}`;
           fileLink.textContent = fileName;
           //fileLink.setAttribute('target', '_blank');
           ligandFileCell.appendChild(fileLink);
           row.appendChild(ligandFileCell);
              row.addEventListener('click', (event) => {
                     // Check if the click was not on the link
                     if (event.target.tagName.toLowerCase() !== 'a') {
                          if (row.classList.contains('selected')) {
                               // If the row is already selected, remove the 'selected' class
                               row.classList.remove('selected');
                              // console.log("got file name to remove ",typeof item.mode);
                              // console.log("got file name to remove ",item.mode);
                              // console.log("loadedComponentsMap ",loadedComponentsMap)
                              // console.log(Object.keys(loadedComponentsMap).toString());
                                removeComponentFromStage(stage_dock, item.mode);
                               } else {
                                 // If the row is not selected, add the 'selected' class
                                 row.classList.add('selected');
                                 // console.log(`Row clicked - Mode: ${item.mode}, Affinity: ${item.affinity}, Ligand File: ${fileName}`);
                                 // alert("column clicked");
                                  downloadAndLoadOneLigand(stage_dock, jobName, email, 'output', fileName,item.mode);
                               }


                     }
                 });
          tableBody.appendChild(row);
            // Access each property of the item
          /*  console.log(`Mode: ${item.mode}`);
            console.log(`Distance from best mode RMSD (L.B.): ${item['dist_from_best_mode_rmsd_l.b.']}`);
            console.log(`Distance from best mode RMSD (U.B.): ${item['dist_from_best_mode_rmsd_u.b.']}`);
            console.log(`Ligand File Path: ${item.ligand}`);
            console.log(`Affinity: ${item.affinity}`);
            console.log('--------------------------');*/
        });
    })
    .catch(error => {
        console.error('Error fetching data:', error); // Handle any errors
    });
}
function downloadAndLoadOneLigand(stage, jobName, email, fileType, fileName,index) {
    // Encode URI components
    const encodedJobName = encodeURIComponent(jobName);
    const encodedEmail = encodeURIComponent(email);
    const encodedFileType = encodeURIComponent(fileType);
    const encodedFileName = encodeURIComponent(fileName);

    // Construct the URL with query parameters for jobName, email, fileType, and fileName
    var url = `/GeinDock/dock/download?jobName=${encodedJobName}&email=${encodedEmail}&fileType=${encodedFileType}&fileName=${encodedFileName}`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.blob(); // Convert the response to a Blob
        })
        .then(blob => {
            var blobUrl = URL.createObjectURL(blob); // Create a URL for the Blob
            // Call a function to load the file into the stage, adjust settings as needed
            loadFileIntoStage(stage, blobUrl, "ball+stick", { backgroundColor: "white", cameraType: "orthographic" },'pdb',index);
           //  loadFileIntoStage(stage, blobUrl, "ball+stick", { backgroundColor: "white", cameraType: "orthographic" });
        })
        .catch(error => {
           // console.error('Error loading ligand file:', error);
        });
}









// Example usage
//downloadOutputFile(jobId, email);
window.onload = function () {
    const params = new URLSearchParams(window.location.search);
    const jobId = params.get('jobId');
    const email = localStorage.getItem("email");
    loadedComponentsMap = {}

    // Initialize your NGL Viewer stages here, if not already initialized
    var stage_rec = new NGL.Stage("view3d_rec", { backgroundColor: "white", cameraType: "orthographic" });
    var stage_lig = new NGL.Stage("view3d_lig", { backgroundColor: "white", cameraType: "orthographic" });
    // var stage_dock = new NGL.Stage("viewport", {backgroundColor: "white", cameraType: "orthographic"});

    // Load the job
    getJobByName(jobId);

    // Resize event for NGL Viewer stages
    window.addEventListener("resize", function (event) {
        stage_rec.handleResize();
        stage_lig.handleResize();
        stage_dock.handleResize();
    }, false);

    // Load the file into NGL Viewer
    //loadNGL_dock();
    loadNGL();
    downloadDockingTable(params.get('jobId'),localStorage.getItem("email"));

};
