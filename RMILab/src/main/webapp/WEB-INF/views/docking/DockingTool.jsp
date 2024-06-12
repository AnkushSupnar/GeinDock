<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Docking Tool</title>

  <link rel="stylesheet" href="/css/dockingtool.css">

</head>
<body>
  <h1>Protein-Ligand Docking Tool</h1>

  <form id="docking-form">
    <label for="protein">Protein (.pdb file):</label>
    <input type="file" id="protein" name="protein" accept=".pdb" required><br><br>

    <label for="ligand">Ligand (.mol2, .mol, .sdf, .pdb or Draw structure):</label>
    <input type="file" id="ligand" name="ligand" accept=".mol2,.mol,.sdf,.pdb" required><br><br>

    <label for="cavities">Number of cavities for docking:</label>
    <input type="number" id="cavities" name="cavities" min="1" required><br><br>

    <label for="email">Enter your email address to receive the results (optional):</label>
   <!-- <input type="email" id="email" name="email"><br><br>-->

    <button type="button" id="search-cavities">Search Cavities</button>
    <button type="button" id="auto-blind-docking" onclick="submitJob()">Auto Blind Docking</button>
  </form>

  <h2>Submitted Jobs</h2>
  <table id="submitted-jobs">
    <thead>
      <tr>
        <th>Submit (Date and Time)</th>
        <th>Protein</th>
        <th>Ligand</th>
        <th>Job type</th>
        <th>Progress (%)</th>
      </tr>
    </thead>
    <tbody>
      <!-- Submitted jobs will be populated here -->
    </tbody>
  </table>

  <script defer src="/js/dockingtool.js"></script>
</body>
</html>
