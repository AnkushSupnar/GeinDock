<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>GeinDock Suite</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/output.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.6.0/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdn.rawgit.com/arose/ngl/v2.0.0-dev.39/dist/ngl.js"></script>
    <script type="text/javascript" src="https://unpkg.com/ngl@latest/dist/ngl.js"></script>
    <script defer src="${pageContext.request.contextPath}/js/dockingOutput.js"></script>
    <style>
        .subtitle {
            font-size: 1.2em;
            color: #777;
        }

        .box {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .box {
            background-color: #fff;
            padding: 20px;
            /* Increase padding for more internal space */
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: auto;
            /* Center the box */
            max-width: 1400px;
            /* Increase max width */
            width: auto;
            /* Adjust the width as per your requirement */
        }



        .head h6 {
            color: #555;
        }

        #viewport {
            border: 1px solid #ddd;
            width: 100%;
            /* Ensure viewport takes the full width of its parent */
            height: 500px;
            /* Adjust height if needed */
        }
    </style>
</head>

<body>
    <div class="container custom-container">
        <div class="title">
            <img src="${pageContext.request.contextPath}/images/GeinDock_Suite_logo-Transperent.png" alt="GeinDock Suite Logo" />
            <div class="title-container">
                <span class="title-text">GeinDock Suite</span>
                <span class="subtitle">A Molecular Docking Tool</span>
            </div>
        </div>

        <div class="container box ">
            <h5 class="text-primary mb-4">Docking Result Analysis</h5>
            <div class="row pb-3">
                <div class="col-md-3 mb-3  head-box">
                	
                    	<h6 class="fs--2 annotation mb-1">Submitted Protein</h6>
                    	<div>
                    		<i class="fas fa-download"></i>
                    		<a class="fs--2" href="#" id="proteinFileLink">protein.pdb</a>
                    	</div>
                </div>
                <div class="col-md-3 mb-3 head-box">
                    <h6 class="fs--2 annotation mb-1">Submitted Ligand</h6>
                    <div>
        	        	<i class="fas fa-download"></i>
        	        	<a class="fs--2"  href="#" id="ligandFileLink">ligand.pdb</a>
        	        </div>
                </div>
                <div class="col-md-3 mb-3 head-box">
                    <h6 class="fs--2 annotation mb-1">Complex file</h6>
                    <div>
                    	<i class="fas fa-download"></i>
        	        	<a class="fs--2" href="#" id="complexFileLink">complex.pdb</a>
        	        </div>
                </div>
                <div class="col-md-3 mb-3 head-box">
                    <h6 class="fs--2 annotation mb-1">Number of Cavities</h6>
                   
                    <span class="fs--2" >9</span>
                </div>
            </div>
            <div class="row">
                <div class="col-md-7 shadow-box" id="viewport" style="height:500px;"></div>
                <div class="col-md-5 shadow-box">
                    <div class="table-responsive">
                        <table class="table text-center table-bordered mb-0">
                            <thead class="bg-thead text-thead">
                                <tr>
                                    <th>CurPocket ID</th>
                                    <th>GeinDock score</th>
                                    <th>Coordinates {X,Y,Z}</th>
                                    <th><i class="fas fa-download">Ligand File </i></th>
                                </tr>
                            </thead>
                            <tbody id="tableBody"></tbody>
                        </table>
                    </div>
                </div>																																													
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>