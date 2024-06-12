var stage_ligand = new NGL.Stage("view3d_lig", { backgroundColor: "white", cameraType: "orthographic" });
const ligand_viewerContainer = document.getElementById('ligand-viewer-Container');
var uploadLigand = document.getElementById('upload-ligand');
var fileLigand = document.getElementById('file-ligand');

var uploadProtein = document.getElementById('upload-protein');
var uploadLigandbtn = document.getElementById('upload-ligang-btn');
function showUploadLigand() {
    ligand_viewerContainer.style.display = 'block';
    uploadLigand.classList.add("show-div");
    uploadLigand.classList.remove("hide-div");
    //hide protein div
    uploadProtein.classList.add("hide-div");
    uploadProtein.classList.remove("show-div");
    if (window.NGL) {
        loadLigandNGL();
    } else {
        const script = document.createElement("script");
        script.src = "/js/ngl.js";
        script.onload = loadLigandNGL;
        document.head.appendChild(script);
    }
}
function loadLigandNGL() {
    // stage_ligand = new NGL.Stage("view3d_lig");
    // stage_ligand.setParameters({ backgroundColor: "white" });
    stage_ligand.removeAllComponents();
    stage_ligand = new NGL.Stage("view3d_lig", { backgroundColor: "white", cameraType: "orthographic" });
    // stage =new NGL.Stage("view3d_rec",{backgroundColor: "black",cameraType: "orthographic"} );
}
fileLigand.addEventListener('change', function () {
    var file = this.files[0];
    if (file) {

        stage_ligand.removeAllComponents();
        stage_ligand.loadFile(file).then(function (component) {
            component.addRepresentation("ball+stick");
            stage_ligand.autoView();
        });
    }
});
function uploadLigandFile(){
    alert("uploading ligand");
    var fileInput = document.getElementById('file-ligand');
    var file = fileInput.files[0];
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
                document.getElementById('second-step').classList.add('bg-success');
              } else {
               alert("Error in Uploading File");
              }
            };
            xhr.send(formData);
}
