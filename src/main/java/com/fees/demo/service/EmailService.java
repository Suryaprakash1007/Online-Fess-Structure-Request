package com.fees.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ByteArrayResource;
import com.fees.demo.model.Studentdetailmodel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    // Optional: Set from address if needed (and configured)
    private static final String FROM_EMAIL = "ishankishen454545@gmail.com";

    public void sendMail(String to, String subject, String text) {
        if (to == null || subject == null || text == null) {
            logger.warn("Email not sent: to, subject, or text is null");
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(FROM_EMAIL); // Optional, ensure it matches your SMTP config

            mailSender.send(message);
            logger.info("Email sent to {}", to);
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", to, e.getMessage(), e);
        }
    }


public void sendApprovalWithAttachment(Studentdetailmodel student) {
    try {
        byte[] pdfData = FeeStructureGenerator.generatePdf(student);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(student.getEmail());
        helper.setSubject("Your Request Approved - Fee Structure Attached");
        helper.setText("Hi " + student.getName() + ",\n\nYour request has been approved. Please find attached your fee structure.\n\nThanks.");
        helper.setFrom(FROM_EMAIL);

        helper.addAttachment("Fee_Structure.pdf", new ByteArrayResource(pdfData));

        mailSender.send(message);
        logger.info("Approval email with attachment sent to {}", student.getEmail());

    } catch (Exception e) {
        logger.error("Failed to send approval email with PDF: {}", e.getMessage(), e);
    }
}
}
