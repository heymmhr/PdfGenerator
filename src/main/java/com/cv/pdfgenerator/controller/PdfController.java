package com.cv.pdfgenerator.controller;

import com.cv.pdfgenerator.service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;


    @GetMapping("/generatePdf")
    public String generatePdf(@RequestParam String fileName) {

        pdfGenerationService.generatePdf(fileName);
        return "Pdf generated successfully";
    }


}
