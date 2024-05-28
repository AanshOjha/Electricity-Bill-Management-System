package com.AanshProject;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class PDFGenerator
{
    CustomerInfo c1;
    MeterInfo m1;
    CalculateBill cb1;
    TariffInfo t1;
    
    public PDFGenerator(CustomerInfo c1, MeterInfo m1, CalculateBill cb1, TariffInfo t1) {
        this.c1 = c1;
        this.m1 = m1;
        this.cb1 = cb1;
        this.t1 = t1;
    }

    public void statement() throws FileNotFoundException {
        String path = String.format("C:\\Users\\itsaa\\Downloads\\%d-bill-%s.pdf", c1.getMeterId(),
                new SimpleDateFormat("MMyyyy", Locale.ENGLISH)
                .format(m1.getReadingDate())
                .replace("-", ""));
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfdoc = new PdfDocument(pdfWriter);
        pdfdoc.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfdoc);

        document.add(new Paragraph("Electricity Bill")
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(35));

        Border bd = new SolidBorder(Color.GRAY, 1);
        Table divider1 = new Table(100);
        divider1.setBorder(bd);
        document.add(divider1);

        String p1 = String.format("""
        Bill number: %s
        Date: %s
        """, new SimpleDateFormat("MMyyyy", Locale.ENGLISH)
                        .format(m1.getReadingDate())
                        .replace("-", "") + c1.getMeterId(),
                LocalDate.now(ZoneId.of("Asia/Kolkata")));

        String p2 = String.format("""
        Name: %s
        Address: %s
        Email: %s
        Meter ID: %d
        """, c1.getName(), c1.getAddress(), c1.getEmail(), c1.getMeterId());

        String p3 = String.format("""
        Bill for month: %s
        This month reading: %.2f
        Last month reading: %.2f
        Units Consumed: %.2f
        Unit Price: %.2f
        Bill: %.2f
        18%% GST: %.2f
        """, new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(m1.getReadingDate()),
                m1.getCurrentReading(), m1.getLastReading(), cb1.calculateUnits(), t1.getTariff(),
                cb1.calculateBill(), cb1.calculateGST());

        String p4 = String.format("Payable Amount: %.2f",
                cb1.payableAmount());

        document.add(new Paragraph(p1));
        document.add(divider1);
        document.add(new Paragraph(p2));
        document.add(divider1);
        document.add(new Paragraph(p3));
        document.add(divider1);
        document.add(new Paragraph(p4));
        document.close();
        System.out.println("Saved successfully to "+path);
    }
}