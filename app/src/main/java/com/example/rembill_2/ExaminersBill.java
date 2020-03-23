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
import com.itextpdf.text.pdf.CMYKColor;
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
    public void Show(float floattemp)
    {
        Toast.makeText(MA, (int) floattemp,Toast.LENGTH_SHORT).show();
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
            float x = doc.getPageSize().getWidth();     //  x = 595
            float y = doc.getPageSize().getHeight();    //  y = 840

            float [] pointColumnWidth = {150F};
            PdfPTable table = new PdfPTable(pointColumnWidth);
            PdfPCell cell = new PdfPCell();
//            String year;
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
            Phrase  p1 = new Paragraph("MAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION", font1 );
            Phrase  p2 = new Paragraph("MUMBAI DIVISIONAL BOARD, VASHI, NAVIMUMBAI 400703", font1);
            Phrase  p3 = new Paragraph("H.S.C. PRACTICAL EXAMINATION FEBRUARY/JULY - " , font1);
            Phrase  p4 = new Paragraph("BILL OF REMUNERATION OF INTERNAL/EXTERNAL EXAMINER", font1);
//            doc.add( Chunk.NEWLINE );
//            doc.add(new Paragraph("\n"));
//            doc.add(new Paragraph(year));
            Phrase  p5 = new Paragraph("The Divisional Secretary,");
            Phrase  p6 = new Paragraph("Maharashtra State Board of Secondary");
            Phrase  p7 = new Paragraph("& Higher Secondary Education");
            Phrase  p8 = new Paragraph("Mumbai Divisional Board,");
            Phrase  p9 = new Paragraph("Vashi, Navi Mumbai 400703");
            Paragraph p10 = new Paragraph("Name Shri/Smt/Miss" + "  " + " PC ");
            Paragraph p11 = new Paragraph("Subject" + "  " + " EVINIRONMENTAL SCIENCE" + "     " +
                                                 "Practical Examination February / July -");
            Paragraph p12 = new Paragraph("at the" + "  P L A C E  O F  E X A M I N A T I O N " +
                                                "                    Index No.of Jr.College - " + "  " + "J - 31.004.005");

//            Paragraph p11 = new Paragraph("Subject" + "  " + " EVINIRONMENTAL SCIENCE");
//            Phrase p12 = new Paragraph("Practical Examination February / July" );
            Phrase  p13 = new Paragraph("Particulars");
            Phrase  p14 = new Paragraph("Amount");



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
//          Phrase p0 = new Paragraph(" ");
            ((Paragraph) p0).setAlignment(Element.ALIGN_LEFT);
            doc.add(p0);
            ((Paragraph) p10).setAlignment(Element.ALIGN_LEFT);
            p10.setSpacingAfter(9f);
            doc.add(p10);
//            ((Paragraph) p0).setAlignment(Element.ALIGN_LEFT);
//            doc.add(p0);
           ((Paragraph) p11).setAlignment(Element.ALIGN_LEFT);
            p11.setSpacingAfter(9f);
           doc.add(p11);
            ((Paragraph) p12).setAlignment(Element.ALIGN_LEFT);
            p12.setSpacingAfter(22f);
            doc.add(p12);



// Creating a PdfCanvas object
//            PdfCanvas canvas = new PdfCanvas(pdfPage);
            PdfContentByte canvas = docWriter.getDirectContent();
            CMYKColor blackColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
            canvas.setColorStroke(blackColor);


            canvas.moveTo((25.5)*x/100, 68.25*y/100);
            canvas.lineTo(95*x/100, 68.25*y/100);          // Line in front of Name

            canvas.moveTo(14*x/100, 65*y/100);
            canvas.lineTo(45*x/100, 65*y/100);             // Line in front of Subject

            canvas.moveTo(80*x/100, 65*y/100);             // Difference of 3.25 in y-coordinates
            canvas.lineTo(95*x/100, 65*y/100);             // Line in front of Examination February / July
            // If we add length of the Subject String
            // i.e x-coordinate of .lineto = x-coordinate of .moveto + l(Sunject String) + 5
            //  makes the line flexible
            canvas.moveTo(12*x/100, 61.75*y/100);          // Difference of 3.25 in y-coordinates
            canvas.lineTo(58*x/100, 61.75*y/100);          // Line in front of at the

            canvas.moveTo(80*x/100, 61.75*y/100);          // Difference of 3.25 in y-coordinates
            canvas.lineTo(95*x/100, 61.75*y/100);          // Line in front of Examination February / July

            canvas.moveTo(6.8*x/100, 59*y/100);            // Top Line of the Bill Table
            canvas.lineTo(95*x/100, 59*y/100);             // Top Line of the Bill Table

            canvas.moveTo(6.8*x/100, 56*y/100);            // Second Line of the Bill Table
            canvas.lineTo(95*x/100, 56*y/100);             // Second Line of the Bill Table


            canvas.closePathStroke();

            float col2[]= {2*x/3,x/3};
            PdfPTable table2 = new PdfPTable(col2);
            table2.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase(p13));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(p14));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cell);

            table2.setSpacingBefore(5f);

            doc.add(table2);

/*            Paragraph paragraph1 = new Paragraph("First paragraph");
            paragraph1.setSpacingAfter(72f);
            doc.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("Second paragraph");
            doc.add(paragraph2);
*/
//            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
//            table.addCell(cell);

            doc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
