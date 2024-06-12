package com.rmilab.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rmilab.entity.Job;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ServerDockService {
    @Autowired
    private MGLToolsService mglToolsService;

    @Autowired
    private FileService fileService;

    @Autowired
    JobService jobService;

    @Autowired
    AutoDockVinaService autoDockVinaService;

    public String performDocking(MultipartFile file) {
        System.out.println("uploading file" + file.isEmpty());
        // String fileCopyResult =fileService.copyFileToUpload(file);
        String fileCopyResult = uploadFile(file, "", "", "");
        System.out.println("file copy result=" + fileCopyResult);
        if (fileCopyResult.equals("")) {
            return "No File Found";
        }
        String fileType = fileService.getFileType(fileCopyResult);
        if (fileType.equals("unknown")) {
            return "Unknown File Type";
        }
        System.out.println("File type is=" + fileType);
        String convertRersult = "";
        if (fileType.equalsIgnoreCase("protein")) {
            convertRersult = mglToolsService.convertProteinToPDBQT(fileCopyResult);
        }
        if (fileType.equalsIgnoreCase("ligand")) {
            convertRersult = mglToolsService.convertLigandToPDBQT(fileCopyResult);
        }
        System.out.println("Converting result " + convertRersult);
        System.out.println("file copy result=" + fileCopyResult);
        return fileCopyResult;
    }

    public String uploadFile(MultipartFile file, String email, String jobName, String fileType) {
        return fileService.uploadProteinFile(file, email, jobName, fileType);
    }

    public String saveSDFFile(MultipartFile file, String email, String jobName) {
        try {
            return fileService.saveSDFFile(file, email, jobName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Resource> ligandToPBB(MultipartFile file, String email, String jobName) {
        try {
            String filePath = saveSDFFile(file, email, jobName);
            System.out.println("save result = " + filePath);

            // Check if the file is saved successfully
            File savedFile = new File(filePath);
            if (savedFile.exists()) {
                // get the file extension
                String fileName = savedFile.getName();
                String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
                System.out.println("file extension = " + fileExtension);
                Path pdbPath = null;
                String sdfContent = new String(Files.readAllBytes(Paths.get(savedFile.getPath())));
                String pdbContent = "";
                if (fileExtension.contains("sdf")) {
                    System.out.println("converting sdf");
                    pdbContent = fileService.convertSdfToPdb(sdfContent);

                } else if (fileExtension.contains("mol")) {
                    pdbContent = fileService.convertMolToPdb(sdfContent);
                    System.out.println("converting mol");
                } else if (fileExtension.contains("mol2")) {
                    pdbContent = fileService.convertMol2ToPdb(sdfContent);
                    System.out.println("converting mol2");
                } else {
                    return ResponseEntity.internalServerError()
                            .body(new ByteArrayResource("File not supported".getBytes()));
                }

                String fileNameWithoutExt = savedFile.getName().replaceFirst("[.][^.]+$", "");
                pdbPath = Paths.get(savedFile.getParent(), jobName + "_" + fileNameWithoutExt + ".pdb");
                System.out.println("path of pbd file = " + pdbPath.toString());
                System.out.println("path of pbd file = " + pdbPath);
                Files.write(
                        Paths.get(savedFile.getParent() + File.separator + jobName + "_" + fileNameWithoutExt + ".pdb"),
                        pdbContent.getBytes());
                System.out.println("PDB file created: " + savedFile.getParent() + File.separator + jobName + "_"
                        + fileNameWithoutExt + ".pdb");

                Resource fileResource = fileService.loadAsResource(pdbPath.toString());
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header("Content-Disposition",
                                "attachment; filename=\"" + pdbPath.getFileName().toString() + "\"")
                        .body(fileResource);
            } else {
                return ResponseEntity.internalServerError().body(new ByteArrayResource("File not saved".getBytes()));
            }

        } catch (Exception e) {
            System.out.println("error ==> " + e.getMessage());
            return ResponseEntity.internalServerError().body(new ByteArrayResource(e.getMessage().getBytes()));
        }
        // return ResponseEntity.internalServerError().body(new
        // ByteArrayResource("hello".getBytes()));

    }

    public ResponseEntity<Resource> getOutputFile(String jobName, String email) {
        String outputFilePath = fileService.getFilePath(jobName, email, "output");
        System.out.println("output FIle path= " + outputFilePath);
        System.out.println("output FIle path= " + outputFilePath.replace(".pdbqt", ".pdb"));
        outputFilePath = outputFilePath.replace(".pdbqt", ".pdb");
        outputFilePath = "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex_lig\\from_seamdock.pdb";
        outputFilePath = "D:\\RMI\\ankushsupnar@gmail.com\\1705924227523\\output\\complex_lig\\1705924227523_1.pdb";
        File file = new File(outputFilePath);
        InputStreamResource resource = null;

        try {
            resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } catch (FileNotFoundException e) {
            // throw new RuntimeException(e);
            return ResponseEntity.ok().body(new ByteArrayResource(e.getMessage().getBytes()));
        }

        // return ResponseEntity.ok().body(new
        // ByteArrayResource(outputFilePath.replace(".pdbqt",".pdb").getBytes()));
    }

    public ResponseEntity<Resource> getProteinFile(String jobName, String email) {
        String proteinFilePath = fileService.getFilePath(jobName, email, "protein");
        System.out.println("use protein file path for output " + proteinFilePath);

        File file = new File(proteinFilePath);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.ok().body(new ByteArrayResource(e.getMessage().getBytes()));
        }

    }

    public List<String> getDockLigandFileNames(String jobName, String email) {
        String path = fileService.getFilePath(jobName, email, "output");
        String folderPath = fileService.getFolderPath(path);
        System.out.println("lig dock output file path: " + folderPath);
        JSONObject jsonObject = new JSONObject();

        return fileService.findFilesWithPrefix(folderPath, jobName + "_ligand_out_ligand_");
        // return ResponseEntity.ok().body(fileService.findFilesWithPrefix(folderPath,
        // jobName + "_ligand_out_ligand_").get)
    }

    public ResponseEntity<Resource> getDockLigandFiles(String jobName, String email) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);

            String path = fileService.getFilePath(jobName, email, "output");
            String folderPath = fileService.getFolderPath(path);
            System.out.println("lig dock output file path: " + folderPath);
            JSONObject jsonObject = new JSONObject();

            List<String> ligandFilePaths = fileService.findFilesWithPrefix(folderPath + File.separator + "complex_lig",
                    jobName);
            System.out.println("All ligand files=" + ligandFilePaths);
            for (String ligandFilePath : ligandFilePaths) {
                Path ligandPath = Paths.get(ligandFilePath);
                if (Files.exists(ligandPath)) {
                    zos.putNextEntry(new ZipEntry(ligandPath.getFileName().toString()));
                    Files.copy(ligandPath, zos);
                    zos.closeEntry();
                }
            }

            zos.finish();
            zos.close();

            ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"ligands.zip\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> prepareOutput(String jobName, String email) {

        String outputFilePath = fileService.getFilePath(jobName, email, "output");
        System.out.println("output File path for prepare= " + outputFilePath);
        String outputFolder = new File(outputFilePath).getParent();
        System.out.println("output folder path " + outputFolder);
        Job job = jobService.getByNameAndEmail(jobName, email);
        if (!fileService.isFolderEmpty(outputFolder + File.separator + "complex")) {
            System.out.println("folder is not empty");
        } else {

            for (int i = 1; i <= 9; i++) {
                String ligandFile = fileService.getFileNameFromPath(outputFilePath, false);
                // System.out.println("ligand file name=" + ligandFile);
                // job.getOutputFile().replace(".pdbqt", "_" + i + ".pdbqt");
                String ligandFilePath = outputFolder + File.separator + ligandFile + "_ligand_" + i + ".pdbqt";
                System.out.println("liganf full path " + ligandFilePath);
                String complexOutput = autoDockVinaService.generateComplexFile(
                        outputFilePath, ligandFilePath);
                System.out.println(complexOutput);
            }
        }
        return ResponseEntity.ok().body(job);
    }

    public ResponseEntity<String> getOutputTable(String jobName, String email) {
        try {
        	
        	System.out.println("Get Output table Job Name="+jobName+" email="+email);
            String vinaJsonFilePath = fileService.getFilePath(jobName, email, "vinaJSON");
            String proteinFilePath = fileService.getFilePath(jobName, email, "protein");
            List<String> ligandFilesPath = fileService.getDockedLigandFilePaths(jobName, email);
            	
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> myData = objectMapper.readValue(new File(vinaJsonFilePath), new TypeReference<>() {});
            JSONArray jsonArray = new JSONArray();

            int index = 0; // Use counter to track current index
            for (Map<String, Object> map : myData) {
                JSONObject obj = new JSONObject();
                map.forEach(obj::put); // Directly add all key-value pairs to the JSONObject
                obj.put("ligand", ligandFilesPath.get(index));
                jsonArray.put(obj);
                index++; // Increment index for each iteration
            }

            // Consider using a logger here instead of System.out
            System.out.println("JSON Array: " + jsonArray.toString());
            return ResponseEntity.ok(jsonArray.toString()); // Returning the JSON array as a response entity

        } catch (Exception e) {
            // Log the error or send a custom error response
            System.err.println("Error processing output table: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error processing output table");
        }
    }

}
