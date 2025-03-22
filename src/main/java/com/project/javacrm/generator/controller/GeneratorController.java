package com.project.javacrm.generator.controller;

import com.project.javacrm.generator.ColumnDetails;
import com.project.javacrm.generator.ColumnRetriever;
import com.project.javacrm.generator.PackageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private ColumnRetriever columnRetriever;

    @Autowired
    private PackageGenerator packageGenerator;

    @GetMapping("/generate")
    public ResponseEntity<List<ColumnDetails>> generate() {
        List<ColumnDetails> columnDetails = columnRetriever.getColumns("botry");
        return ResponseEntity.ok(columnDetails);
    }

    @GetMapping("generate/{tableName}")
    public ResponseEntity<String> generatePackage(@PathVariable String tableName) {
        List<ColumnDetails> columns = columnRetriever.getColumns(tableName);

//        Map<String, String> properties = new HashMap<>();
//        properties.put("className", tableName);
//        properties.put("groupLink", "com.project.backspring");
//        properties.put("projectName", "BackSpring");

        packageGenerator.generatePackage(tableName, columns);

        return ResponseEntity.ok("Package for table '" + tableName + "' generated successfully.");
    }


}
