package com.example.rembill_2;

import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;

public class ExaminersBill {


    public MainActivity MA;
    public void SetMA(MainActivity MA){this.MA=MA;}

    public void show(String tempstring)
    {
        Toast.makeText(MA,tempstring,Toast.LENGTH_SHORT).show();
    }

    static Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    static Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);

    FileSaveNLoad FSNL = new FileSaveNLoad();

//    void SetMA(MainActivity MA) {
//        this.MA = MA;
//    }

    String rootDir;
    public ArrayList<String> fileArray = new ArrayList<String>();



    void CreatePDF()
    {
        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + "New.pdf";

        try {
            Document doc = new Document();
            doc = new Document(PageSize.A4);
            doc.setMargins(40, 25, 30, 5);

            File pdfFile =new File(pdfFileNameWithPath);
            PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
//            PdfContentByte pcb = docWriter.getDirectContent();
            doc.open();

// Creating a table object
//          float [] pointColumnWidths = {150F, 150F, 150F, 150F};
            float [] pointColumnWidth = {150F};
            PdfPTable table = new PdfPTable(pointColumnWidth);
            PdfPCell cell = new PdfPCell();
            String year;
//            year = MA.examYear;     //   show(year);

//            PdfPTable table1 = new PdfPTable(4); // 4 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph(" C - 2 / P2 - 13"));
            cell1.setHorizontalAlignment(350);
            cell1.setFixedHeight(20);
            table.setWidthPercentage(16);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell1);
            doc.add(table);
//            doc.add(new Paragraph(year));
            Phrase p1 = new Paragraph("MAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION", font1 );
            Phrase p2 = new Paragraph("MUMBAI DIVISIONAL BOARD, VASHI, NAVIMUMBAI 400703", font1);
            Phrase p3 = new Paragraph("H.S.C. PRACTICAL EXAMINATION FEBRUARY/JULY - " , font1);
            Phrase p4 = new Paragraph("BILL OF REMUNERATION OF INTERNAL/EXTERNAL EXAMINER", font1);
//            doc.add( Chunk.NEWLINE );
//            doc.add( Chunk.NEWLINE );
//            doc.add(new Paragraph("\n"));
//            doc.add(new Paragraph(year));
            Phrase p5 = new Paragraph("The Divisional Secretary,");
            Phrase p6 = new Paragraph("Maharashtra State Board of Secondary");
            Phrase p7 = new Paragraph("& Higher Secondary Education");
            Phrase p8 = new Paragraph("Mumbai Divisional Board,");
            Phrase p9 = new Paragraph("Vashi, Navi Mumbai 400703");

            ((Paragraph) p1).setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
            ((Paragraph) p2).setAlignment(Element.ALIGN_CENTER);
            doc.add(p2);
            ((Paragraph) p3).setAlignment(Element.ALIGN_CENTER);
            doc.add(p3);
            ((Paragraph) p4).setAlignment(Element.ALIGN_CENTER);
            doc.add(p4);

//            ((Paragraph) p1).setAlignment(Element.ALIGN_CENTER);
//            doc.add(p1);

           Phrase p0 = new Paragraph(" ");
            ((Paragraph) p0).setAlignment(Element.ALIGN_LEFT);
            doc.add(p0);

            ((Paragraph) p5).setAlignment(Element.ALIGN_LEFT);
            doc.add(p5);
            ((Paragraph) p6).setAlignment(Element.ALIGN_LEFT);
            doc.add(p6);
            ((Paragraph) p7).setAlignment(Element.ALIGN_LEFT);
            doc.add(p7);
            ((Paragraph) p8).setAlignment(Element.ALIGN_LEFT);
            doc.add(p8);
            ((Paragraph) p9).setAlignment(Element.ALIGN_LEFT);
            doc.add(p9);



//            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
//            table.addCell(cell);

            doc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
