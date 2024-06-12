<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta charset="ISO-8859-1">
<title>RMI Laboratory</title>
<!--<link rel="stylesheet" href="static/css/navbar1.css">-->
<link rel="stylesheet" href="/css/dock.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<!--
        <div class="container">
            <h1>Upload File</h1>
            <form method="POST" id="uploadPdb-form" enctype="multipart/form-data" action="/dock/serverDockUpload">
                <div class="form-group">
                    <label for="file" class="file-upload">
                        <span>Choose or Drop File Here</span>
                        <input type="file" id="file" name="file" class="form-control-file" accept=".pdb" required>
                    </label>
                </div>
                <button type="button" class="btn btn-primary" onclick="uploadFile()">Upload</button>
            </form>
             <div class="progress mt-3" style="height: 20px;">
                <div id="progress-bar" class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
             </div>
             <div id="viewport" style="width:500px; height:500px;"></div>
              <input type="range" class="custom-range" min="0" max="1" step="0.1" id="opacityRange" value="0.5">
        </div>
-->


<div class="jumbotron jumbotron-fluid  text-white head-logo" style="margin-bottom: 0;">
  <div class="container">
    <h1 class="display-4">RMI Laboratory</h1>
    <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
  </div>
</div>
<!--Menu Buttons START -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <ul class="navbar-nav">
            <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Upload
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdown1">
                    <a class="dropdown-item" href="#" onclick="showUploadProtein()">Upload Protein</a>
                    <a class="dropdown-item" href="#">Upload Ligand</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Edit
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdown2">
                    <a class="dropdown-item" href="#">Action 1</a>
                    <a class="dropdown-item" href="#">Action 2</a>
                    <a class="dropdown-item" href="#">Action 3</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Start Docking
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdown3">
                    <a class="dropdown-item" href="#">Action 1</a>
                    <a class="dropdown-item" href="#">Action 2</a>
                    <a class="dropdown-item" href="#">Action 3</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Analyse Result
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdown4">
                    <a class="dropdown-item" href="#">Action 1</a>
                    <a class="dropdown-item" href="#">Action 2</a>
                    <a class="dropdown-item" href="#">Action 3</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown5" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Export Result
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdown5">
                    <a class="dropdown-item" href="#">Action 1</a>
                    <a class="dropdown-item" href="#">Action 2</a>
                    <a class="dropdown-item" href="#">Action 3</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<!--Menu Buttons end -->
 <div class="container hide-div " id="upload-protein-div">
        <div class="row">
            <div class="col-md-4">
                <form method="POST" id="uploadPdb-form" enctype="multipart/form-data" action="/dock/serverDockUpload">
                    <div class="form-group">
                        <label for="file" class="file-upload">
                            <span>Choose or Drop File Here</span>
                            <input type="file" id="file" name="file" class="form-control-file" accept=".pdb" required>
                        </label>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="uploadFile()">Upload</button>
                </form>
            </div>

            <div class="col-md-8 border border-primary">
                <div class="row bg-light">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="representationDropdown">Representation</label>
                            <select class="form-control" id="representationDropdown" disabled >
                                <option value="cartoon" selected>Cartoon</option>
                                <option value="licorice">Licorice</option>
                                <option value="ball+stick">Ball + Stick</option>
                                <option value="line">Line</option>
                                <option value="spacefill">Spacefill</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label for="representationDropdown">Surface</label>
                        <select id="surfaceDropdown" class="form-control" onchange="changeSurface()">
                                <option value="">None</option>
                                <option value="residueindex">Residueindex</option>
                                <option value="unifirm">Ynifirm</option>
                                <option value="electrostatic">Electrostatic</option>
                                <option value="hydrophobicity">Hydrophobicity</option>
                                <option value="element">Element</option>
                                <option value="bfactor">Bfactor</option>
                                <option value="random">Random</option>
                                <option value="resname">Resname</option>
                                <option value="sstruck">Sstruck</option>

                            </select>
                    </div>

                    <div class="col-md-4">
                       <div class="form-group">
                            <label for="surfaceOpacity">Surface Opacity</label>
                             <label>&nbsp;</label> <!-- Placeholder for alignment --><br>
                            <input type="range" class="form-control-range" id="surfaceOpacity" min="0" max="1" step="0.1" value="1" disabled>
                       </div>
                    </div>
                </div>
                   <div id="viewport" style="width:100%; height:400px;"></div>
            </div>
        </div>
    </div>
<script defer src="/js/ngl.js"></script>
<script defer src="/js/dockMenu.js"></script>
<script defer src="/js/dock.js"></script>
 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>