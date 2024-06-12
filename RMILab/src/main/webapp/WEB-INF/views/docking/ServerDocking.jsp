<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GeinDock Suite</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeinDock.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/110/three.min.js"></script>
    </head>
    <style>
body {
    /* Using the primary color (light blue) for the background */
    background: #E0EFFF;
    font-family: 'Arial', sans-serif; /* Consistent font family */
}

/* Title and subtitles with the secondary color (dark gray) */
.title-container .title-text, .subtitle {
    color: #37474F;
}

/* Custom button styling with the accent color (coral) */
.btn-custom {
    background-color: #FF6B6B; /* Accent color for buttons */
    color: #FFFFFF; /* White text for readability */
    border-radius: 5px; /* Rounded edges for modern look */
    border: none; /* No border for cleaner look */
    padding: 10px 20px; /* Adequate padding for tap targets */
    transition: background-color 0.2s ease-in-out; /* Smooth transition for hover effect */
}

.btn-custom:hover {
    background-color: darken(#FF6B6B, 10%); /* Slightly darker on hover for feedback */
    color: #E0EFFF; /* Primary color text on hover for contrast */
}

/* Adjustments for input groups to fit the secondary color theme */
.input-group-text {
    background-color: #E0EFFF; /* Lighter background for contrast */
    color: #37474F; /* Secondary color for text */
    border: 1px solid #37474F; /* Border color matched with text */
}

.form-control {
    border: 1px solid #37474F; /* Consistent border color */
}

/* Styling for the protein viewer container for better visibility */
#viewerContainer {
    border-color: #FF6B6B; /* Accent color for emphasis */
}

/* Additional elements like dropdowns, range inputs, and checkboxes */
.select, .form-control-range, .form-check-label {
    color: #37474F; /* Secondary color for consistency */
}

/* Modify table styling to fit the color scheme */
.table {
    color: #37474F; /* Text color for readability */
}

.table thead {
    background-color: #FF6B6B; /* Accent color for table headers */
    color: #FFFFFF; /* White text for contrast */
}

/* Ensuring links and other interactive elements align with the theme */
a, .form-check-input:checked {
    color: #FF6B6B; /* Use accent color for links and checked inputs */
}

/* Responsive tweaks might be needed here */
@media (max-width: 768px) {
    /* Responsive styles if necessary */
}

</style>


    <body onload="populateTable()">
        <div class="title">
            <img src="${pageContext.request.contextPath}/images/GeinDock_Suite_logo-Transperent.png" alt="GeinDock Suite Logo" />
            <div class="title-container">
                <span class="title-text">GeinDock Suite</span>
                <span class="subtitle">A Molecular Docking Tool</span>
            </div>
        </div>

        <div class="container">
            <div class="job-id-section">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Job ID:</span>
                    </div>
                    <input type="text" id="job-id" class="form-control" placeholder="Current Job ID"
                        aria-label="Job ID:" aria-describedby="basic-addon1" readonly value="123456">
                </div>
            </div>
           <!--  <div class="user-icon">
                <h3>User Name</h3>
                <p>User description</p>
            </div> -->
            <form method="POST" id="uploadPdb-form" enctype="multipart/form-data" action="/dock/serverDockUpload">
                <h5 class="text-primary position-relative mb-0">
                    <span class="pe-2"> Upload Protein</span>
                </h5>
                <p class="mb-0 annotation">Select a protein file (pdb format)</p>
                <div class="protein">
                    <div class="row">
                        <div class="col-md-12" id="protein-file-div">
                            <label for="file1" class="file-upload">
                                <span><i class="fas fa-cloud-upload-alt"></i> Choose or Drop Protein File Here</span>
                                <span class="file-name"></span>
                                <input type="file" id="file1" name="file1" class="form-control-file" accept=".pdb"
                                    required onchange="loadProtein(this)">
                            </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col" id="protein-view">
                            <div class="row">
                                <div class="col-md-4 border ">
                                    <div class="form-group">
                                        <label for="representationDropdown">Representation</label>
                                        <select class="form-control" id="representationDropdown">
                                            <option value="cartoon" selected>Cartoon</option>
                                            <option value="licorice">Licorice</option>
                                            <option value="ball+stick">Ball + Stick</option>
                                            <option value="line">Line</option>
                                            <option value="spacefill">Spacefill</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4 border">
                                    <label for="surfaceDropdown">Surface</label>
                                    <select id="surfaceDropdown" class="form-control">
                                        <option value="none">None</option>
                                        <option value="residueindex">Residueindex</option>
                                        <option value="unifirm">Unifirm</option>
                                        <option value="electrostatic">Electrostatic</option>
                                        <option value="hydrophobicity">Hydrophobicity</option>
                                        <option value="element">Element</option>
                                        <option value="bfactor">Bfactor</option>
                                        <option value="random">Random</option>
                                        <option value="resname">Resname</option>
                                        <option value="sstruck">Sstruck</option>
                                    </select>
                                </div>
                                <div class="col-md-4 border">
                                    <div class="form-group">
                                        <label for="surfaceOpacity">Surface Opacity</label>
                                        <label>&nbsp;</label> <!-- Placeholder for alignment --><br>
                                        <input type="range" class="form-control-range" id="surfaceOpacity" min="0"
                                            max="1" step="0.1" value="1">
                                    </div>
                                </div>
                            </div>
                            <div class="mb-5 bg-light border border-success " id="viewerContainer">
                                <div class="border border-primary" id="viewport1" style="width:100%; height:500px;">
                                </div>
                            </div>
                        </div>
                    </div>
            </form>
        </div>
        <div class="border protein">
            <div class="row">
                <div class="p-2">
                    <button class="btn btn-custom" onclick="uploadFile()">
                        <i class="fas fa-upload"></i> Upload
                    </button>
                </div>
                <div class="p-2">
                    <button class="btn btn-custom" onclick="toggleGridDiv()">
                        Grid Selection
                        <i class="fas fa-chevron-down"></i>
                    </button>
                </div>
                <div class="p-2">
                    <input type="checkbox" id="aiGeneratedSelection" name="aiGeneratedSelection">
                    <label for="aiGeneratedSelection">
                        AI Generated Active Site
                    </label>
                </div>
            </div>
        </div>
        <!--Coordinate div-->
        <div class="box-coor protein " id="box-coor">
            <h3>Grid Coordinates:</h3>
            <div class="row mb-auto p-2">
                <div class="col-md-4 border ">
                    Center:
                    <label for="x_center"> x :</label>
                    <input type="range" min="-100" max="100" value="0" class="slider" id="x_center" onchange="move();"
                        oninput="move();" step="1">
                    <span class="coor" id="x_center_label"> 0 </span> &#8491;
                </div>
                <div class="col-md-3 border ">
                    <label for="y_center"> y :</label>
                    <input type="range" min="-100" max="100" value="0" class="slider" id="y_center" onchange="move();"
                        oninput="move();" step="1">
                    <span class="coor" id="y_center_label"> 0 </span> &#8491;
                </div>
                <div class="col-md-3 border">
                    <label for="z_center"> z :</label>
                    <input type="range" min="-100" max="100" value="0" class="slider" id="z_center" onchange="move();"
                        oninput="move();" step="1">
                    <span class="coor" id="z_center_label"> 0 </span> &#8491;
                </div>
                <div class="col-md-2"></div>
            </div>

            <div class="row mb-auto p-2">
                <div class="col-md-4 border">
                    Size:
                    <label for="x_size">&nbsp;&nbsp;&nbsp;&nbsp;x :</label>
                    <input type="range" min="1" max="150" value="20" class="slider" id="x_size" onchange="move();"
                        oninput="move();" step="1">
                    <span class="coor" id="x_size_label"> 0 </span> &#8491;
                </div>
                <div class="col-md-3 border">
                    <label for="y_size"> y :</label>
                    <input type="range" min="1" max="100" value="20" class="slider" id="y_size" onchange="move();"
                        oninput="move();" step="1">
                    <span class="coor" id="y_size_label"> 0 </span> &#8491;
                </div>
                <div class="col-md-3 border">
                    <label for="z_size"> z :</label>
                    <input type="range" min="1" max="100" value="20" class="slider" id="z_size" onchange="move();"
                        oninput="move();" step="1">
                    <span class="coor" id="z_size_label"> 0 </span> &#8491;
                </div>
                <div class="col-md-2"></div>
            </div>
            <h3> Grid Appearance </h3>
            <div class="row">
                <div class="col-md-3 border">
                    Opacity:
                    <input type="range" min="0" max="100" value="50" class="slider" id="opacity" onchange="move();"
                        oninput="move();" step="1">
                </div>
                <div class="col-md-3 border">
                    Color:
                    <input type="color" id="color" value="#000000" onchange="move();">
                    Wireframe :
                    <span id='wire_tog'>
                        <input class="cmn-toggle cmn-toggle-round" type="checkbox" id="wire" onchange="move();"
                            oninput="move();">
                        <label for="wire"> </label>
                    </span>
                </div>
            </div>
        </div>
        <!--Coordinate div-->


        <!--<h2>Upload Ligand</h2>-->
        <h5 class="text-primary position-relative mb-0">
            <span class="pe-2"> Upload Ligand</span>
        </h5>
        <p class="mb-0 annotation">Select a ligand file (mol2, mol, sdf, pdb format)
            <span id="show_ligand_name"></span>
        </p>
        <div class="protein">
            <div class="row" id="ligand-file-div">
                <label for="file-ligand" class="file-upload">
                    <span><i class="fas fa-cloud-upload-alt"></i> Choose or Drop Ligand File Here</span>
                    <span class="file-name">file.pdb</span>
                    <input type="file" id="file-ligand" name="ligand-file" class="form-control-file"
                        accept=".pdb,.mol2,.sdf" required>
                </label>
            </div>
            <div id="ligand-viewer" style="width:100%; height:400px; border: 1px solid black;">
                <div class="border border-primary" id="view3d_lig" style="width:100%; height:100%;"></div>
            </div>
        </div>
        <div class="border protein">
            <div class="row">
                <div class="p-2">
                    <button class="btn btn-custom" id="upload-ligang-btn" onclick="uploadLigandFile()">
                        <i class="fas fa-upload"></i> Upload
                    </button>
                </div>
            </div>
        </div>
        <div class="container protein">
            <button type="button" class="btn btn-success" onClick="startDocking()">Start Docking</button>
            <button type="button" class="btn btn-success" onClick="reset(); resetLigandViewer();">New Job</button>
        </div>
        <!--Docking Result-->
        <h5 class="text-primary position-relative mb-0">
            <span class="pe-2"> Submitted Jobs</span>
        </h5>
        <p class="mb-0 annotation">Click on view to see the output</p>
        <div class="container protein table-responsive">
            <table class="table" id="result-table">
                <thead>
                    <tr>
                        <th>Submit</th>
                        <th>Protein</th>
                        <th>Ligand</th>
                        <th>Job Type</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        


        </div>

        <!--<script defer src="/js/dock.js"></script>-->
        <!-- <script defer src="/js/ligand.js"></script>-->


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>


        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/ngl.js"></script>
        <script src="${pageContext.request.contextPath}/js/protein.js"></script>
        <script  src="${pageContext.request.contextPath}/js/ligandNew.js"></script>
        <script  src="${pageContext.request.contextPath}/js/GeinDock.js"></script>

    </body>

    </html>