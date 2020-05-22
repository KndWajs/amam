package pl.fit_amam.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fit_amam.api.Globals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController() {
    }

    @GetMapping(path = "/log/")
    public @ResponseBody
    ResponseEntity getLogs() throws FileNotFoundException {
        String defaultDir = System.getProperty("user.dir");
        InputStream is = new FileInputStream(new File(defaultDir + "/logs/spring-boot-logger.log"));
        InputStreamResource resource = new InputStreamResource(is);
        logger.info("Get logs");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=amam_log.log")
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);
    }
}