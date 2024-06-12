let viewer;
function showUploadProteinDemo()
{
    var uploadProtein = document.getElementById('upload-protein');
    const viewerContainer = document.getElementById('viewerContainer');
    if (viewerContainer.style.display === 'none')
    {
        viewerContainer.style.display = 'block';
        if (!viewer)
        {
          viewer = new NGL.Stage("viewer");
        }
    }else
    {
     viewerContainer.style.display = 'none';
    }
    uploadProtein.classList.add("show-div");
    uploadProtein.classList.remove("hide-div");

}