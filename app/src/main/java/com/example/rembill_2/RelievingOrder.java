package com.example.rembill_2;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class RelievingOrder {

    public MainActivity MA;
    public void SetMA(MainActivity MA){this.MA=MA;}

    FileSaveNLoad FSNL = new FileSaveNLoad();
    ExaminersBill EB = new ExaminersBill();

    static Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    static Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);


    String rootDir;
    void RelieveOrderPDF() {
        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + MA.externalname+ "- Relieve" +".pdf";

        try {
            Document doc = new Document();
            doc = new Document(PageSize.A4);
            doc.setMargins(80, 25, 30, 30);

            File pdfFile =new File(pdfFileNameWithPath);
            PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
//            PdfContentByte pcb = docWriter.getDirectContent();
            doc.open();

// Creating a table object
//          float [] pointColumnWidths = {150F, 150F, 150F, 150F};
            float x = doc.getPageSize().getWidth();     //  x = 595
            float y = doc.getPageSize().getHeight();    //  y = 840

            Paragraph P0  = new Paragraph(" ");
            Paragraph P1  = new Paragraph("A T T E N D A N C E   C E R I F I C A T E", font1 );
            Paragraph P2  = new Paragraph("T O   W H O M S O E V E R   I T   M A Y   C O N C E R N", font1);
            Paragraph P3  = new Paragraph(" This is to certify that Mr./Mrs./Miss. "   + "  "  + MA.externalname);
            Paragraph P4  = new Paragraph(" From  " + MA.externalCollegeName);
            Paragraph P5  = new Paragraph(" has conducted Practical / Project / Oral");
            Paragraph P6  = new Paragraph(" Examination in " + MA.examSubject);
            Paragraph P7  = new Paragraph("at our college, from    " + MA.examStartDate);
            Paragraph P8  = new Paragraph("to  " + MA.examEndDate);
            Paragraph P9  = new Paragraph(" except on  " + MA.noExamDates);
            Paragraph P10 = new Paragraph(" He / She relieved from duties.");
            Paragraph P11 = new Paragraph(" Principal / Vice Principal");

            PdfContentByte canvas = docWriter.getDirectContent();
            CMYKColor blackColor = new CMYKColor(0.f, 0.f, 0.f, 1.f);
            canvas.setColorStroke(blackColor);

            canvas.moveTo(40, 30);              // Left Vertical Line
            canvas.lineTo(40, y - 30);          // Left Vertical Line

            canvas.moveTo(40, 30);              // Bottom Horizontal Line
            canvas.lineTo(x - 25, 30);          // Bottom Horizontal Line

            canvas.moveTo(x - 25, 30);          // Right Vertical Line
            canvas.lineTo(x - 25, y - 30);      // Right Vertical Line

            canvas.moveTo(40, y - 30);          // Top Horizontal Line
            canvas.lineTo(x - 25, y - 30);      // Top Horizontal Line

            canvas.moveTo(32*x/100, 62*y/100);          // Attendance certificate
            canvas.lineTo(71*x/100, 62*y/100);          // Attendance certificate

            canvas.moveTo(25*x/100, 57*y/100);          // To whomsoever concern
            canvas.lineTo(78*x/100, 57*y/100);          // To whomsoever concern

            canvas.moveTo(46*x/100, 50.5*y/100);          // Name of teacher
            canvas.lineTo(88*x/100, 50.5*y/100);          // Name of teacher


//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, P4, 17*x/100, 48*y/100, 0);    // From ... College
//            canvas.moveTo(20*x/100, 47.5*y/100);          // Name of teacher
//            canvas.lineTo(57*x/100, 47.5*y/100);          // Name of teacher

//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, P5, 73*x/100, 48*y/100, 0);    //
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, P6, 23*x/100, 44*y/100, 0);    //

            canvas.stroke();

            P0.setSpacingAfter(250);

            P1.setAlignment(Element.ALIGN_CENTER);
            P1.setSpacingAfter(24);
            P1.setIndentationLeft(-38);
            P2.setAlignment(Element.ALIGN_CENTER);
            P2.setIndentationLeft(-40);
            P2.setSpacingAfter(40);
            doc.add(P0);
            doc.add(P1);
            doc.add(P2);

            float [] ColumnWidth1 = { 150F };
            PdfPTable table1 = new PdfPTable(ColumnWidth1);

            PdfPCell cell1 = new PdfPCell(new Paragraph(P3));
            cell1.setBorder(PdfPCell.NO_BORDER);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell1);

            cell1 = new PdfPCell(new Paragraph("  "));
            cell1.setBorder(PdfPCell.NO_BORDER);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell1);

            PdfPTable table2 = new PdfPTable(4);

            PdfPCell cell2 = new PdfPCell(new Phrase(P4));
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setColspan(2);

            PdfPCell cell3 = new PdfPCell(new Phrase(P5));
            cell3.setColspan(2);
            cell3.setBorder(PdfPCell.NO_BORDER);

//            table2.setSpacingBefore(5f);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setWidthPercentage(98);
            table2.addCell(cell2);
            table2.addCell(cell3);

            PdfPTable table3 = new PdfPTable(5);
            PdfPCell cell4 = new PdfPCell(new Phrase(P6));

            cell4.setBorder(PdfPCell.NO_BORDER);
            cell4.setColspan(2);

            PdfPCell cell5 = new PdfPCell(new Phrase(P7));
            cell5.setColspan(2);
            cell5.setBorder(PdfPCell.NO_BORDER);

            PdfPCell cell6 = new PdfPCell(new Phrase(P8));
            cell6.setColspan(2);
            cell6.setBorder(PdfPCell.NO_BORDER);

            table3.setSpacingBefore(10f);
            table3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.setWidthPercentage(96);
            table3.addCell(cell4);
            table3.addCell(cell5);
            table3.addCell(cell6);

            PdfPTable table4 = new PdfPTable(2);
            PdfPCell cell7 = new PdfPCell(new Phrase(P9));

            cell7.setBorder(PdfPCell.NO_BORDER);
            cell7.setColspan(2);

            table4.setSpacingBefore(10f);
            table4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cell7);

            doc.add(table1);
            doc.add(table2);
            doc.add(table3);
            doc.add(table4);
            P10.setSpacingBefore(10);
            doc.add(P10);
            P11.setSpacingBefore(50);
            doc.add(P11);


            doc.close();
//            Msg.Show("From CreatePDF, in ExminersBill class",MA);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
