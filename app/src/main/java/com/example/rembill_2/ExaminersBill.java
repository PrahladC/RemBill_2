package com.example.rembill_2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.itextpdf.text.pdf.ColumnText;
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
            Paragraph  p15 = new Paragraph("Amount due, to me as an EXTERNAL/INTERNAL Examiner at");
            Paragraph  p16 = new Paragraph("the Examination center. Number ofdays of examination =" + "   " + "12");
            Paragraph  p17 = new Paragraph("Actual Total number of candidates examined by me =" + "   " +"999");
            Paragraph  p18 = new Paragraph("Excluding Absentees");
            Paragraph  p19 = new Paragraph("Rs."+ "   05   " + "per candidate." + "          " + " 05 x 999 = 495 " );
            Paragraph  p20 = new Paragraph("( Minimum of Rs.50/- irrespective of the number of candidates )");
            Paragraph  p21 = new Paragraph("I hereby undertake to refund if any amount paid to me in excess of the amount due");
            Paragraph  p22 = new Paragraph("Name of Jr. College where teaching" + "   " +"St. Andrews College - Bandra.");
            Paragraph  p23 = new Paragraph("IndexNo.of Jr.College" + "   " +" J - 31.04.005 ");
            Paragraph  p24 = new Paragraph("certified that the Examiner has actually examined the No. of candidates mentioned above.");
            Paragraph  p25 = new Paragraph("Counter signature of the Head of the institution with stamp");
            Paragraph  p26 = new Paragraph("Total", font1);
            Paragraph  p27 = new Paragraph("Signature", font2);
            Paragraph  p28 = new Paragraph("Residential Address", font2);
            Paragraph  p29 = new Paragraph("Rs. " + "999", font1);

            ((Paragraph) p1).setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
            ((Paragraph) p2).setAlignment(Element.ALIGN_CENTER);
            doc.add(p2);
            ((Paragraph) p3).setAlignment(Element.ALIGN_CENTER);
            doc.add(p3);
            ((Paragraph) p4).setAlignment(Element.ALIGN_CENTER);
            doc.add(p4);

            Paragraph p0 = new Paragraph(" ");
            p0.setAlignment(Element.ALIGN_LEFT);
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
            p0.setAlignment(Element.ALIGN_LEFT);
            doc.add(p0);
            p10.setAlignment(Element.ALIGN_LEFT);
            p10.setSpacingAfter(9f);
            doc.add(p10);
//            ((Paragraph) p0).setAlignment(Element.ALIGN_LEFT);
//            doc.add(p0);
            p11.setAlignment(Element.ALIGN_LEFT);
            p11.setSpacingAfter(9f);
            doc.add(p11);
            p12.setAlignment(Element.ALIGN_LEFT);
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

            canvas.moveTo(6.8*x/100, 59.5*y/100);          // Top First Line of the Bill Table
            canvas.lineTo(95*x/100, 59.5*y/100);           // Top First Line of the Bill Table

            canvas.moveTo(6.8*x/100, 57*y/100);            // Top Second Line of the Bill Table
            canvas.lineTo(95*x/100, 57*y/100);             // Top Second Line of the Bill Table

            canvas.moveTo(6.8*x/100, 41*y/100);            // Bottom Second Line of the Bill Table
            canvas.lineTo(95*x/100, 41*y/100);             // Bottom Second Line of the Bill Table

            canvas.moveTo(6.8*x/100, 38.5*y/100);            // Bottom First Line of the Bill Table
            canvas.lineTo(95*x/100, 38.5*y/100);             // Bottom First Line of the Bill Table

            canvas.moveTo(66*x/100, 59.5*y/100);           // Vertical Line of the Bill Table
            canvas.lineTo(66*x/100, 38.5*y/100);             // Vertical Line of the Bill Table

            canvas.moveTo(58*x/100, 52*y/100);             // Line after equal to sign in side the Bill Table
            canvas.lineTo(64*x/100, 52*y/100);             // Line after equal to sign in side the Bill Table

            canvas.moveTo(55*x/100, 49.4*y/100);           // Line after equalt sign in side the Bill Table
            canvas.lineTo(63*x/100, 49.4*y/100);           // Line after equalt sign in side the Bill Table

            canvas.moveTo(10.5*x/100, 44*y/100);           // Remuneration in front Rs. in side the Bill Table
            canvas.lineTo(14.5*x/100, 44*y/100);           // Remuneration in front Rs. in side the Bill Table

            canvas.moveTo(33*x/100, 44*y/100);             // Calculation of Remuneratio Line in side the Bill Table
            canvas.lineTo(50*x/100, 44*y/100);             // Calculation of Remuneratio Line in side the Bill Table

            canvas.moveTo(50*x/100, 33*y/100);             // Signature line out side the Bill Table
            canvas.lineTo(95*x/100, 33*y/100);             // Signature line out side the Bill Table

            canvas.moveTo(50*x/100, 30*y/100);             // Line 1st below signature line
            canvas.lineTo(95*x/100, 30*y/100);             // Line 1st below signature line

            canvas.moveTo(50*x/100, 27*y/100);             // Line 2nd below signature line
            canvas.lineTo(95*x/100, 27*y/100);             // Line 2nd below signature line

            canvas.moveTo(50*x/100, 24*y/100);             // Line 3rd below signature line
            canvas.lineTo(95*x/100, 24*y/100);             // Line 3rd below signature line

            canvas.moveTo(40*x/100, 19.5*y/100);             // Line 4th below signature line
            canvas.lineTo(95*x/100, 19.5*y/100);             // Line 4th below signature line

            canvas.moveTo(28*x/100, 16.8*y/100);             // Line 5th below signature line
            canvas.lineTo(42*x/100, 16.8*y/100);             // Line 5th below signature line

            canvas.moveTo(60*x/100, 41*y/100);           // Vertical Line of the Bill Table
            canvas.lineTo(60*x/100, 38.5*y/100);             // Vertical Line of the Bill Table

            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p26, 374, 332, 0);     // Total
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p27, 274, 280, 0);     // Signature
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p28, 252, 252, 0);     // Residential address
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 450, 465, 0);     // Total Amount on Toop
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 450, 332, 0);     // Total Amount at Bottom


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

/*            cell = new PdfPCell(new Phrase(p15));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
//            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" PC "));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cell);
*/

            //           table2.setSpacingBefore(5f);

            doc.add(table2);

            p15.setSpacingBefore(4f);
            doc.add(p15);
            p16.setSpacingBefore(4f);
            doc.add(p16);
            p17.setSpacingBefore(4f);
            doc.add(p17);
            p18.setSpacingBefore(4f);
            doc.add(p18);
            p19.setSpacingBefore(4f);
            doc.add(p19);
            p20.setSpacingBefore(4f);
            doc.add(p20);
//            p26.setSpacingBefore(4f);
//            p26.setAlignment(Element.ALIGN_CENTER);
//            doc.add(p26);
//            p0.setSpacingBefore(0f);  doc.add(p0);
            p0.setSpacingBefore(0f);  doc.add(p0);
            p21.setAlignment(Element.ALIGN_CENTER);
            p21.setSpacingBefore(4f); doc.add(p21);

//            Paragraph  p27 = new Paragraph("Signature", font2);
//            p27.setAlignment(Element.ALIGN_CENTER);
//            p27.setSpacingBefore(4f); doc.add(p27);

            float col3[]= {x/2, x/2};
            PdfPTable table3 = new PdfPTable(col3);
            table3.setWidthPercentage(90);

            cell = new PdfPCell(new Phrase("  "));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table3.addCell(cell);

            cell = new PdfPCell(new Phrase("  " ));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            table3.addCell(cell);

            float [] ColumnWidth = {50F};
//            PdfPTable table3 = new PdfPTable(ColumnWidth);

            cell = new PdfPCell(new Paragraph("On revenue stamp\n" + "when the amount\n" +
                    "exceeds Rs.5000/-", font2));
            cell.setHorizontalAlignment(100);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(70);
            table3.setWidthPercentage(25);
            table3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cell);
            doc.add(table3);

            cell = new PdfPCell(new Phrase("  " ));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setBorder(PdfPCell.NO_BORDER);
            table3.addCell(cell);

            doc.add(table3);

            p0.setSpacingBefore(4f);  doc.add(p0);
//            p0.setSpacingBefore(4f);  doc.add(p0);
//            p0.setSpacingBefore(4f);  doc.add(p0);
//            p0.setSpacingBefore(4f);  doc.add(p0);
//            p0.setSpacingBefore(4f);  doc.add(p0);

            p22.setAlignment(Element.ALIGN_LEFT);
            p22.setSpacingBefore(4f); doc.add(p22);
            p23.setAlignment(Element.ALIGN_LEFT);
            p23.setSpacingBefore(4f); doc.add(p23);
            p24.setAlignment(Element.ALIGN_LEFT);
            p24.setSpacingBefore(4f); doc.add(p24);
            p25.setAlignment(Element.ALIGN_LEFT);
            p25.setSpacingBefore(4f); doc.add(p25);


            doc.close();
//            Msg.Show("From CreatePDF, in ExminersBill class",MA);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}