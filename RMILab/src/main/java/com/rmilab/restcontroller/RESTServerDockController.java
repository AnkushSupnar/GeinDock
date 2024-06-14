package com.rmilab.restcontroller;

import com.rmilab.dto.Interaction;
import com.rmilab.dto.InteractionType;
import com.rmilab.entity.Job;
import com.rmilab.entity.JobStatus;
import com.rmilab.service.*;
//import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dock")
public class RESTServerDockController {
    @Autowired
    ServerDockService serverDockService;
    @Autowired
    JobService jobService;
    @Autowired
    DockService dockService;
    @Autowired
    private RESTDockindSSEController sseController;
    @Autowired
    FileService fileService;
    @Autowired
    ComplexFile complexFile;

    @Autowired
    InteractionService interactionService;

    @PostMapping("/serverDockUpload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, String _csrf, String email,
            String jobName, @RequestParam("coordinates") String coordinatesJson) {
        System.out.println("email=" + email);
        System.out.println("_csrf=" + _csrf);
        System.out.println("jobName=" + jobName);
        System.out.println("coordinates" + coordinatesJson);
        Map<String, String> response = new HashMap<>();

        response.put("ProteinUpload", serverDockService.uploadFile(file, email, jobName, "protein"));
        if (response.get("ProteinUpload").contains("Success")) {
            Job job = jobService.getJobByName(jobName);
            job.setEmail(email);
            job.setProteinFile(jobName + "_" + file.getOriginalFilename());
            job.setCoordinates(coordinatesJson);
            job.setStatus(JobStatus.PENDING);

            System.out.println("Got Job=" + job);
            jobService.saveJob(job);
            response.put("JobCreate", "Success");
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/serverDockUploadLigand")
    public ResponseEntity<?> uploadLigandFile(@RequestParam("file") MultipartFile file, String _csrf, String email,
            String jobName) {
        System.out.println("email=" + email);
        System.out.println("_csrf=" + _csrf);
        System.out.println("jobName=" + jobName);
        Map<String, String> response = new HashMap<>();

        // Check file format
        String originalFileName = file.getOriginalFilename();
        System.out.println("ligand file original= " + originalFileName);
        if (originalFileName != null && !(originalFileName.endsWith(".sdf") || originalFileName.endsWith(".pdb")
                || originalFileName.endsWith(".mol") || originalFileName.endsWith(".mol2"))) {
            response.put("Error", "Invalid file format. Only .sdf,.pdb,.mol,.mol2 files are accepted.");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("ProteinUpload", serverDockService.uploadFile(file, email, jobName, "ligand"));
        if (response.get("ProteinUpload").contains("Success")) {
            Job job = jobService.getJobByName(jobName);
            job.setEmail(email);
            job.setLigandFiles(jobName + "_" + file.getOriginalFilename());
            job.setStatus(JobStatus.PENDING);

            System.out.println("Got Job=" + job);
            jobService.saveJob(job);
            response.put("JobCreate", "Success");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/checkUpload")
    public ResponseEntity<?> checkFileIsUploaded(@RequestParam("jobName") String jobName,
            @RequestParam("email") String email) {
        return ResponseEntity.ok().body(jobService.checkFileIsUploaded(jobName, email));
    }

    @GetMapping("/getJobByName")
    public ResponseEntity<Job> getJobByName(@RequestParam("jobName") String jobName) {
        return ResponseEntity.ok().body(jobService.getJobByName(jobName));
    }

    @PostMapping("/serverDock")
    public String processFile(@RequestParam("jobName") String jobName, @RequestParam("email") String email,
            @RequestParam("_csrf") String token) {
        sseController.sendUpdateToClients("{\"jobName\":\"" + jobName + "\",\"message\":\"step done\",\"status\":1}");
        String dockingResult = dockService.startDocking(jobName, email);
        System.out.println("docking result=" + dockingResult);
        return "redirect:/?message=File processing started.";
    }

    @GetMapping("/getJobs")
    public ResponseEntity<List<Job>> getJobsByEmail(@RequestParam String email) {
        List<Job> jobs = jobService.getByEmail(email);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/getLigandpdb")
    public ResponseEntity<Resource> getLiganToPDB(@RequestParam("file") MultipartFile file, String _csrf, String email,
            String jobName) {
        return serverDockService.ligandToPBB(file, email, jobName);
    }

    @GetMapping("/getOutput")
    public ResponseEntity<Resource> getOutputFile(@RequestParam("jobName") String jobName,
            @RequestParam("email") String email) {

        return serverDockService.getOutputFile(jobName, email);
    }

    @GetMapping("/getProteinFile")
    public ResponseEntity<Resource> getProteinFile(@RequestParam("jobName") String jobName,
            @RequestParam("email") String email) {
        System.out.println("got jobName to get protein file " + jobName);
       // complexFile.generatingComplexFile(email, jobName);
        return serverDockService.getProteinFile(jobName, email);
    }

    @GetMapping("/getDockLigFilesName")
    public ResponseEntity<Map<String, Object>> getDockLigFilesName(@RequestParam("jobName") String jobName,
            @RequestParam("email") String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<String> fileNames = serverDockService.getDockLigandFileNames(jobName, email);
            response.put("success", true);
            response.put("message", "File names retrieved successfully.");
            response.put("data", fileNames);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/getDockLigFiles")
    public ResponseEntity<Resource> getDockLigFiles(@RequestParam("jobName") String jobName,
            @RequestParam("email") String email) {
        System.out.println("calling getDockLigandFiles");

        return serverDockService.getDockLigandFiles(jobName, email);
    }
    @GetMapping("/getInteraction")
    public ResponseEntity<List<Interaction>> getInteraction(@RequestParam("jobName") String jobName,
                                                            @RequestParam("email") String email) {
/*fileService.getDockedLigandFilePaths(jobName,email);
        List<Interaction> intList = interactionService.analyzeInteractions("D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\protein\\1705924227523_protein.pdb",
                Arrays.asList("D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_1.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_2.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_3.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_4.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_5.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_6.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_7.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_8.pdb",
                        "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex\\1705924227523_ligand_out_ligand_9.pdb"));
        System.out.println("interction result="+intList);*/
        List<Interaction>interaction = interactionService.analyzeInteractions(fileService.getFilePath(jobName, email, "protein"),fileService.getDockedLigandFilePaths(jobName,email));


        for(Interaction inte:interaction){
            //System.out.println("interaction analysis result: "+inte.getType()+" Distance="+inte.getDistance());
           // System.out.println("interaction==> "+inte);
            if(inte.getType().equals(InteractionType.SALT_BRIDGE)){
                System.out.println("interaction==> "+inte);
            }
        }
       // serverDockService.getOutputTable(jobName,email);
        return ResponseEntity.ok(interaction);
    }
    @GetMapping("/getOutputTable")
    public ResponseEntity<String>getOutputTable(@RequestParam("jobName") String jobName,@RequestParam("email") String email){
    	complexFile.generatingComplexFile(email, jobName);
    	return serverDockService.getOutputTable(jobName,email);
    }

    @GetMapping("/download")//download the file from server
    public ResponseEntity<Resource> downloadFile(@RequestParam("jobName") String jobName,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("fileType") String fileType,
                                                 @RequestParam("fileName") String fileName) {
        try {
            // Use FileService to construct the file path
            String filePathString = fileService.getFilePathFromName(jobName, email, fileType, fileName);
            System.out.println("Got file path to download "+filePathString);
            Path filePath = Paths.get(filePathString).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                String contentType = "application/octet-stream"; // Default content type

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/prepareOutput")
    public ResponseEntity<?> prepareOutput(@RequestParam String jobName, @RequestParam String email) {

        // return ResponseEntity.ok("Output prepared successfully");
        return serverDockService.prepareOutput(jobName, email);
    }

}
