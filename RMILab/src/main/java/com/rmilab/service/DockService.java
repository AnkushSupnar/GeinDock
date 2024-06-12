package com.rmilab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmilab.dto.Coordinates;
import com.rmilab.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DockService {

    @Autowired
    JobService jobService;
    @Autowired
    TemplateBasedDockingService templateBasedDockingService;
    @Autowired
    AutoDockVinaService autoDockVinaService;

    @Async
    public Coordinates getGrid(String jobName, String email) {
        Job job = jobService.getByNameAndEmail(jobName, email);
        System.out.println("Job to docking" + job);
        String grid = job.getCoordinates();
        Coordinates coordinate = null;
        if (!grid.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                coordinate = objectMapper.readValue(grid, Coordinates.class);
                System.out.println("Got coordinates=" + coordinate);
            } catch (Exception e) {
                System.out.println("Exception in converting coordinates" + e.getMessage());
            }
        }
        System.out.println("Job to docking");
        return coordinate;
    }

    // @Async
    public String startDocking(String jobName, String email) {
        Coordinates coordinate = getGrid(jobName, email);
        boolean isGrid = coordinate != null && coordinate.getCenter().getX() != 0 && coordinate.getCenter().getY() != 0
                && coordinate.getCenter().getZ() != 0;

        return autoDockVinaService.blindDocking(jobName, email);
        /*
         * if(isGrid){
         * //structure based docking
         * return "structure based";
         * }
         * else{
         * //template based docking
         * return templateBasedDockingService.templateBasedDocking(jobName,email);
         * // return "template based";
         * }
         */
    }

}
