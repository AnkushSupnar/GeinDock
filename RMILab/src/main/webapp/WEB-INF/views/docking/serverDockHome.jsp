<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <!DOCTYPE html>
  <html>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <head>
    <meta charset="ISO-8859-1">
    <title>RMI Laboratory</title>
    <!--<link rel="stylesheet" href="static/css/navbar1.css">-->
    <link rel="stylesheet" href="/css/dock.css">
    <link rel="stylesheet" href="/css/progressbar.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/ngl.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/110/three.min.js"></script>

  </head>

  <body>
    <!--
        <div class="container">
            <h1>Upload File</h1>
            <form method="POST" id="uploadPdb-form" enctype="multipart/form-data" action="/dock/serverDockUpload">
                <input type="input" name="${_csrf.parameterName}" value="${_csrf.token}" placeholder="token" />
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


    <div class="jumbotron jumbotron-fluid  text-white head-logo">
      <div class="container">
        <h1 class="display-4">GeinDock Suite</h1>
        <span class="subtitle">A Molecular Docking Tool</span>
        <p class="lead"></p>
      </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown1" data-toggle="dropdown" aria-haspopup="true"
              aria-expanded="false">
              Upload
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown1">
              <a class="dropdown-item" onclick="showUploadProtein()">Upload Protein</a>
              <a class="dropdown-item" onclick="showUploadLigand()">Upload Ligand</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown2" data-toggle="dropdown" aria-haspopup="true"
              aria-expanded="false">
              Edit
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown2">
              <a class="dropdown-item" onclick="showBoxCoordinates()">Box Coordinates</a>
              <a class="dropdown-item" href="#">Item 2</a>
              <a class="dropdown-item" href="#">Item 3</a>
            </div>
          </li>
          <li class="nav-item dropdown">

            <a class="nav-link" href="#" onClick="startDocking()">Start Docking

            </a>
          </li>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown4" data-toggle="dropdown" aria-haspopup="true"
              aria-expanded="false">
              Analys Result
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown4">
              <a class="dropdown-item" href="#">Item 1</a>
              <a class="dropdown-item" href="#">Item 2</a>
              <a class="dropdown-item" href="#">Item 3</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown5" data-toggle="dropdown" aria-haspopup="true"
              aria-expanded="false">
              Export Result
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown5">
              <a class="dropdown-item" href="#">Item 1</a>
              <a class="dropdown-item" href="#">Item 2</a>
              <a class="dropdown-item" href="#">Item 3</a>
            </div>
          </li>
        </ul>
      </div>
    </nav>
    <!-- Progress Bar START-->
    <div class="row" id="progrss-bar">
      <div class="col" id="first-step"></div>
      <div class="col" id="second-step"></div>
      <div class="col" id="third-step">
        <p></p>
      </div>
    </div>
    <!-- Progress Bar END-->
    <div class="container-fluid hide-div" id="upload-protein">
      <div class="row">
        <div class="col-md-4">
          <form method="POST" id="uploadPdb-form" enctype="multipart/form-data" action="/dock/serverDockUpload">
            <div class="form-group">
              <label for="file" class="file-upload">
                <span>Choose or Drop File Here</span>
                <input type="file" id="file" name="file" class="form-control-file" accept=".pdb" required>
              </label>
            </div>
            <button type="button" class="btn btn-primary" id="upload-btn" onclick="uploadFile()">
              Upload
              <span id="loadingIcon" style="display: none;">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
                  <circle cx="8" cy="8" r="7" stroke-width="2" stroke="#fff" fill="none" />
                  <circle cx="8" cy="8" r="7" stroke-width="2" stroke="#000" fill="none" stroke-dasharray="0 54"
                    stroke-linecap="round" transform="rotate(90 8 8)">
                    <animate attributeName="stroke-dasharray" from="0 54" to="54 0" dur="1.5s"
                      repeatCount="indefinite" />
                  </circle>
                </svg>
              </span>
            </button>
            <button type="button" class="btn btn-primary hide-div" id="showGrid-btn"
              onclick="showBoxCoordinates()">Show Grid</button>
            <button type="button" class="btn btn-primary hide-div" id="showGrid-btn"
              onclick=" showBoxCoordinates()">Blind Docking</button>
          </form>
          <!--Coordinate div-->
          <div class="box-coor " id="box-coor">
            <h3>Box Coordinates:</h3>
            Center:
            <div>
              <label for="x_center"> x :</label>
              <input type="range" min="-100" max="100" value="0" class="slider" id="x_center" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="x_center_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="y_center"> y :</label>
              <input type="range" min="-100" max="100" value="0" class="slider" id="y_center" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="y_center_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="z_center"> z :</label>
              <input type="range" min="-100" max="100" value="0" class="slider" id="z_center" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="z_center_label"> 0 </span> &#8491;
            </div>
            Size:
            <div>
              <label for="x_size"> x :</label>
              <input type="range" min="1" max="150" value="20" class="slider" id="x_size" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="x_size_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="y_size"> y :</label>
              <input type="range" min="1" max="100" value="20" class="slider" id="y_size" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="y_size_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="z_size"> z :</label>
              <input type="range" min="1" max="100" value="20" class="slider" id="z_size" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="z_size_label"> 0 </span> &#8491;
            </div>
            <h3> Box Appearance </h3>
            <div>
              Opacity:
              <input type="range" min="0" max="100" value="50" class="slider" id="opacity" onchange="move();"
                oninput="move();" step="1">
              <div>
                Color:
                <input type="color" id="color" value="#000000" onchange="move();">
                Wireframe :
                <span id='wire_tog'>
                  <input class="cmn-toggle cmn-toggle-round" type="checkbox" id="wire" onchange="move();"
                    oninput="move();">
                  <label for="wire"> </label>
                </span>
              </div>
              <div id="rec_loader" class="loader"></div>
            </div>
          </div>
          <!--Coordinate div-->

        </div>
        <div class="col-md-8 border">
          <div class="row bg-light">
            <div class="col-md-4">
              <div class="form-group">
                <label for="representationDropdown">Representation</label>
                <select class="form-control" id="representationDropdown" disabled>
                  <option value="cartoon" selected>Cartoon</option>
                  <option value="licorice">Licorice</option>
                  <option value="ball+stick">Ball + Stick</option>
                  <option value="line">Line</option>
                  <option value="spacefill">Spacefill</option>
                </select>
              </div>
            </div>

            <div class="col-md-4">
              <label for="surfaceDropdown">Surface</label>
              <select id="surfaceDropdown" class="form-control" onchange="changeSurface(this)" disabled>
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

            <div class="col-md-4">
              <div class="form-group">
                <label for="surfaceOpacity">Surface Opacity</label>
                <label>&nbsp;</label> <!-- Placeholder for alignment --><br>
                <input type="range" class="form-control-range" id="surfaceOpacity" min="0" max="1" step="0.1" value="1"
                  disabled>
              </div>
            </div>
          </div>
          <div class="bg-light border " id="viewerContainer" style="display: none;">
            <div id="viewport" style="width:100%; height:500px;"></div>
          </div>
        </div>
      </div>
    </div>
    <!-- For ligand file preparation START -->
    <div class="container-fluid hide-div" id="upload-ligand">
      <div class="row">
        <div class="col-md-4">
          <form method="POST" id="uploadLigand-form" enctype="multipart/form-data" action="/dock/serverDockUpload">
            <div class="form-group">
              <label for="file" class="file-upload">
                <span>Choose or Drop File Here</span>
                <input type="file" id="file-ligand" name="file-ligand" class="form-control-file"
                  accept=".pdb,.mol2,.sdf" required>
              </label>
            </div>
            <button type="button" class="btn btn-primary" id="upload-ligang-btn" onclick="uploadLigandFile()">
              Upload
              <span id="loadingIcon" style="display: none;">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
                  <circle cx="8" cy="8" r="7" stroke-width="2" stroke="#fff" fill="none" />
                  <circle cx="8" cy="8" r="7" stroke-width="2" stroke="#000" fill="none" stroke-dasharray="0 54"
                    stroke-linecap="round" transform="rotate(90 8 8)">
                    <animate attributeName="stroke-dasharray" from="0 54" to="54 0" dur="1.5s"
                      repeatCount="indefinite" />
                  </circle>
                </svg>
              </span>
            </button>
          </form>
        </div>
        <div class="col-md-8 border border-primary">
          <div class="bg-light border border-success " id="ligand-viewer-Container" style="display: none;">
            <div id="view3d_lig" style="width:100%; height:500px;">

            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- For ligand file preparation END -->



    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h3 class="modal-title" id="myModalLabel">Box Coordinates</h3>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body ">

            Center:
            <div>
              <label for="x_center"> x :</label>
              <input type="range" min="-50" max="50" value="0" class="slider" id="x_center" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="x_center_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="y_center"> y :</label>
              <input type="range" min="-50" max="50" value="0" class="slider" id="y_center" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="y_center_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="z_center"> z :</label>
              <input type="range" min="-50" max="50" value="0" class="slider" id="z_center" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="z_center_label"> 0 </span> &#8491;
            </div>
            Size:
            <div>
              <label for="x_size"> x :</label>
              <input type="range" min="1" max="50" value="20" class="slider" id="x_size" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="x_size_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="y_size"> y :</label>
              <input type="range" min="1" max="50" value="20" class="slider" id="y_size" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="y_size_label"> 0 </span> &#8491;
            </div>
            <div>
              <label for="z_size"> z :</label>
              <input type="range" min="1" max="50" value="20" class="slider" id="z_size" onchange="move();"
                oninput="move();" step="1">
              <span class="coor" id="z_size_label"> 0 </span> &#8491;
            </div>
            <h3> Box Appearance </h3>
            <div>
              Opacity:
              <input type="range" min="0" max="100" value="50" class="slider" id="opacity" onchange="move();"
                oninput="move();" step="1">
              <div>
                Color:
                <input type="color" id="color" value="#000000" onchange="move();">
                Wireframe :
                <span id='wire_tog'>
                  <input class="cmn-toggle cmn-toggle-round" type="checkbox" id="wire" onchange="move();"
                    oninput="move();">
                  <label for="wire"> test </label>
                </span>
              </div>
              <div id="rec_loader" class="loader"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>
    <script defer src="/js/dock.js"></script>
    <script defer src="/js/ligand.js"></script>
    <script defer src="/js/dockMenu.js"></script>
    <script defer src="/js/progressbar.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
      crossorigin="anonymous"></script>

  </body>

  </html>