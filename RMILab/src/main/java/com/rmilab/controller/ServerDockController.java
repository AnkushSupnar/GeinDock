package com.rmilab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ServerDockController {

    @RequestMapping(value = "/docking", method = RequestMethod.GET)
    String getServerDockHome() {

        return "docking/serverDockHome";
        // return "docking/ServerDocking";
    }

    @RequestMapping(value = "/geinDock", method = RequestMethod.GET)
    String getServerDockVinaHome() {

        // return "docking/serverDockHome";
        return "docking/ServerDocking";
    }

    @RequestMapping(value = "/geinDockResult", method = RequestMethod.GET)
    String getServerDockVinaOutput() {

        // return "docking/serverDockHome";
        return "docking/DockingOutput";
    }

    @PostMapping("/docking/serverDockUpload")
    String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        System.out.println("File uploading called");
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");

            return "redirect:/docking/serverDockHome";
        }
        try {
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            System.out.println("File Name=" + file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",
                    "An error occurred while uploading the file: " + e.getMessage());
        }
        return "redirect:/docking/serverDockHome";
    }

}
