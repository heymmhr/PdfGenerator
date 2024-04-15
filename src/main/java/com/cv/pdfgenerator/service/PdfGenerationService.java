package com.cv.pdfgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfGenerationService {

    @Autowired
    TemplateEngine templateEngine;


    public void generatePdf(String fileName) {
        String htmlContent = generateHtmlContent();
        System.out.println("------------: " + htmlContent);
        try {
            generatePdfFromHtml(htmlContent, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private String generateHtmlContent() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("to", "Baeldung");
       // context.setVariable("data", ser);

        return templateEngine.process("demo", context);
    }

    private void generatePdfFromHtml(String html, String fileName) throws IOException {
        String outputFilePath = System.getProperty("user.home") + File.separator + fileName;
        OutputStream outputStream = new FileOutputStream(outputFilePath);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
