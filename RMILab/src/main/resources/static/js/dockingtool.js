document.getElementById('docking-form').addEventListener('submit', (event) => {
  event.preventDefault();
  submitJob();
});

async function submitJob() {
alert("submitting job");
  const form = new FormData(document.getElementById('docking-form'));

  const response = await fetch('/submit', {
    method: 'POST',
    body: form
  });

  if (response.ok) {
    const result = await response.json();
    addSubmittedJob(result);
  } else {
    console.error('Error submitting job:', response.statusText);
  }
}

function addSubmittedJob(job) {
  const table = document.getElementById('submitted-jobs').getElementsByTagName('tbody')[0];
  const newRow = table.insertRow();

  newRow.insertCell().innerHTML = job.submitDateTime;
  newRow.insertCell().innerHTML = job.protein;
  newRow.insertCell().innerHTML = job.ligand;
  newRow.insertCell().innerHTML = job.jobType;

  const progressCell = newRow.insertCell();
  if (job.progress === 100) {
    progressCell.innerHTML = `<a href="/results/${job.id}">View Results</a>`;
  } else {
    progressCell.innerHTML = `${job.progress}%`;
  }
}
