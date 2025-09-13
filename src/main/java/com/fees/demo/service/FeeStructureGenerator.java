package com.fees.demo.service;



import com.fees.demo.model.Studentdetailmodel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;

public class FeeStructureGenerator {

    public static byte[] generatePdf(Studentdetailmodel student) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font headFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);

        Paragraph title = new Paragraph("Fee Structure", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("\nStudent Name: " + student.getName()));
        document.add(new Paragraph("Roll No: " + student.getRollno()));
        document.add(new Paragraph("Department: " + student.getDepartment()));
        document.add(new Paragraph("Course: " + student.getCourse()));
        document.add(new Paragraph("Year: " + student.getYear()));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80);
        table.setWidths(new int[]{1, 2});

        addTableHeader(table, "Fee Type", "Amount");
        addTableRow(table, "Admission Fee", "₹5,000");
        addTableRow(table, "Tuition Fee", "₹20,000");
        addTableRow(table, "Library Fee", "₹2,000");
        addTableRow(table, "Lab Fee", "₹1,000");
        addTableRow(table, "Total", "₹28,000");

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private static void addTableHeader(PdfPTable table, String col1, String col2) {
        PdfPCell h1 = new PdfPCell(new Phrase(col1));
        h1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(h1);

        PdfPCell h2 = new PdfPCell(new Phrase(col2));
        h2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(h2);
    }

    private static void addTableRow(PdfPTable table, String label, String amount) {
        table.addCell(label);
        table.addCell(amount);
    }
}
