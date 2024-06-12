var fileLigand = document.getElementById('file-ligand');
var ligandView = document.getElementById('ligand-viewer');

var ligandFileDiv= document.getElementById('ligand-file-div');
var LigStage = new NGL.Stage("view3d_lig", { backgroundColor: "white", cameraType: "orthographic" });
ligandView.style.display='none';
var fileToUpload=null;
window.addEventListener("resize", function (event) {
    LigStage.handleResize();
    //stage_lig.handleResize();
    //stage_dock.handleResize();
}, false);

//document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('file-ligand').addEventListener('change', async function(event) { // Mark this function as async
        var proteinFile = document.getElementById('file1');
        if (proteinFile.files.length > 0) {
            console.log("File selected:", proteinFile.files[0].name);
        } else {
            console.log("No file selected.");
            alert("Please select Protein File First");
            return;
        }

        var file = event.target.files[0];
        if (file) {
            var fileName = file.name;
            var fileExtension = fileName.split('.').pop().toLowerCase();
            if (fileExtension === 'mol' || fileExtension === 'sdf' || fileExtension === 'mol2') {
                try {
                    file = await uploadAndConvertFile(file); // Wait for the file conversion
                    alert(file.name);
                } catch (error) {
                    console.error('Error during file conversion:', error);
                    return;
                }
            }

            ligandFileDiv.style.display = 'none';
            ligandView.style.display = 'block';

//            if (!LigStage) {
//                LigStage = new NGL.Stage("view3d_lig", { backgroundColor: "white", cameraType: "orthographic" });
//
//            }

            var reader = new FileReader();
          //  LigStage.removeAllComponents();
            reader.onload = function(e) {
                var contents = e.target.result;
                LigStage.loadFile(new Blob([contents], {type: 'text/plain'}), {ext: 'pdb', defaultRepresentation: true}).then(function(component) {
                    component.addRepresentation("ball+stick");
                    component.autoView();
                });
            };
            reader.readAsText(file); // This should now be the converted file
            fileToUpload = file;
        }
    });
//});

async function uploadAndConvertFile(file) {

//upload file first


    var formData = new FormData();

    formData.append('file', file);
    formData.append('_csrf', localStorage.getItem("token"));
    formData.append('email',JSON.parse(localStorage.getItem('user')).email );
    formData.append('jobName',localStorage.getItem("jobName"));

    const response = await fetch('/GeinDock/dock/getLigandpdb', {
        method: 'POST',
        body: formData
    });

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    const blob = await response.blob();
    return new File([blob], "converted.pdb"); // Return the converted file
}


async function uploadAndConvertFile1(file) {


    var formData = new FormData();
    var csrfToken = localStorage.getItem("token");
    formData.append('file', file);
    formData.append('_csrf', csrfToken);

    return fetch('/GeinDock/dock/getLigandpdb', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok: ' + response.statusText);
        }
        return response.blob();
    })
    .then(blob => {
        return new File([blob], "converted.pdb");
    })
    .catch(error => {
        console.error('Error converting file:', error);
        throw error; // Re-throw the error for the caller to handle
    });
}
function loadFileInNGL(file) {
    var reader = new FileReader();
    reader.onload = function(e) {
        var contents = e.target.result;
        if (!LigStage) {
            LigStage = new NGL.Stage("view3d_lig", { backgroundColor: "white", cameraType: "orthographic" });
        }
        LigStage.loadFile(new Blob([contents], {type: 'text/plain'}), {ext: 'pdb'}).then(function(component) {
            component.addRepresentation("ball+stick");
            component.autoView();
        });
    };
    reader.readAsText(file);
}

function uploadLigandFile(){
    alert("uploading ligand");
    var proteinFile = document.getElementById('file1');
    if (proteinFile.files.length > 0) {

        console.log("File selected:", proteinFile.files[0].name);
    } else {
        // No file is selected
        console.log("No file selected.");
        alert("Please select protein file first");
        return;
    }

    var uploadLigandbtn = document.getElementById('upload-ligang-btn');
    var fileInput = document.getElementById('file-ligand');
    //var file = fileInput.files[0];
    var file = fileToUpload;
    var csrfToken = localStorage.getItem("token");
    let user = JSON.parse(localStorage.getItem('user'));
    let jobName = localStorage.getItem("jobName") || new Date().getTime();
    localStorage.setItem('jobName', jobName);
    console.log("token",csrfToken);
    if (!file) return;
    uploadLigandbtn.disabled = true;

    var formData = new FormData();
      formData.append('file', file);
      formData.append('_csrf', csrfToken);
      formData.append('email',user.email );
      formData.append('jobName',jobName);

       var xhr = new XMLHttpRequest();
        xhr.open('POST', '/GeinDock/dock/serverDockUploadLigand', true);
         xhr.upload.onprogress = function (e) {
            if (e.lengthComputable) {
            console.log("progress",e.lengthComputable)
            }
          };
           xhr.onload = function () {
              if (this.status == 200) {
                alert("ligand upload success");
                //document.getElementById('second-step').classList.add('bg-success');
              } else {
               alert("Error in Uploading File");
              }
            };
            xhr.send(formData);
}
function resetLigandViewer() {
    // Clear the file input
    fileLigand.value = '';

    // Remove all components from the NGL stage
    if (LigStage) {
        LigStage.removeAllComponents();
    }

    // Reset UI elements
    ligandFileDiv.style.display = 'block';
    ligandView.style.display = 'none';

    // Re-enable the upload button if it's disabled
    var uploadLigandbtn = document.getElementById('upload-ligang-btn');
    if (uploadLigandbtn) {
        uploadLigandbtn.disabled = false;
    }

    // Add any other reset logic specific to the ligand viewer here...
}
