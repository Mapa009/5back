package kr.co.yooooon.base.controller;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadController {

    private static final String FILE_PATH = "C:\\\\Apache2\\\\htdocs\\\\downlodable\\\\1128.jpg";
    
    
    @RequestMapping(value = "/base/download")
    @ResponseBody
    public ResponseEntity<Resource> download(@RequestParam String filename) throws IOException {
        File file = new File(FILE_PATH , filename);

        HttpHeaders header = new HttpHeaders();
        String fn = new String(file.getName().getBytes(), "utf-8");
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fn + "\"");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}