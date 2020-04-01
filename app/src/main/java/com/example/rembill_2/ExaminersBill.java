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

    FileSaveNLoad FSNL = new FileSaveNLoad();

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

//    void SetMA(MainActivity MA) {
//        this.MA = MA;
//    }

    String rootDir;
    public ArrayList<String> fileArray = new ArrayList<String>();

    void CreateInternalPDF()
    {
        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + "New1.pdf";

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
//            PdfPCell cell = new PdfPCell();

//            PdfPTable table1 = new PdfPTable(4); // 4 columns.

            PdfPCell cell = new PdfPCell(new Paragraph(" C - 2 / P2 - 13"));
            cell.setHorizontalAlignment(350);
            cell.setFixedHeight(20);
            table.setWidthPercentage(16);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            doc.add(table);
//            doc.add(new Paragraph(year));
            Paragraph P1 = new Paragraph("MAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION \n" +
                                                "MUMBAI DIVISIONAL BOARD, VASHI, NAVIMUMBAI 400703 \n" +
                                                "H.S.C. PRACTICAL EXAMINATION FEBRUARY/JULY - \n" +
                                                "BILL OF REMUNERATION OF INTERNAL/EXTERNAL EXAMINER" , font1 );
            Paragraph P2 = new Paragraph("The Divisional Secretary, \n" + "Maharashtra State Board of Secondary \n" +
                                                "& Higher Secondary Education \n" + "Mumbai Divisional Board, \n" +
                                                "Vashi, Navi Mumbai 400703" );

            Paragraph P5 = new Paragraph("Amount due, to me as an EXTERNAL/INTERNAL Examiner at \n" +
                                               "the Examination center. Number ofdays of examination = " + "   " + "12" + " \n" +
                                               "Actual Total number of candidates examined by me = " + "   " +" 999 " + " \n" +
                                               "Excluding Absentees \n" +
                                               "Rs. " + "  " +  MA.remunerationPerStudent  + "     " + "per candidate." + "     " + " 05 x 999 = 495 \n" +
                                               "( Minimum of Rs.50/- irrespective of the number of candidates )" );

            Paragraph P6 = new Paragraph("Name of Jr. College where teaching " + "   " + "  St. Andrews College - Bandra. "+ "\n" +
                                               "IndexNo.of Jr.College " + "  " + " J - 31.04.005 " + "\n" +
                                               "certified that the Examiner has actually examined the No. of candidates mentioned above. \n" +
                                               "Counter signature of the Head of the institution with stamp" );



            Paragraph p0 = new Paragraph(" ");
/*            Phrase  p1 = new Paragraph("MAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION", font1 );
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
*/
            Paragraph p10 = new Paragraph("Name Shri/Smt/Miss" + "  "  + MA.internalname );   //   + "  "  + MA.internalname);
//            Paragraph p11 = new Paragraph("Subject" + "  " + " EVINIRONMENTAL SCIENCE" + "     " +
//                    "Practical Examination February / July -");
            Paragraph p11a = new Paragraph("Subject  " + MA.examSubject);
            Paragraph p11b = new Paragraph("Practical Examination February / July - " + MA.examYear);
//            Paragraph p11b = new Paragraph("Practical Examination February / July -" );

//            Paragraph p12 = new Paragraph("at the" + "  P L A C E  O F  E X A M I N A T I O N " +
//                    "                    Index No.of Jr.College - " + "  " + "J - 31.004.005");
            Paragraph p12a = new Paragraph("at the " + " R. M. Bhatt Junior College. Parel");
            Paragraph p12b = new Paragraph("Index No.of Jr.College - " + "  " + "J - 31.004.005" );
//            Paragraph p11 = new Paragraph("Subject" + "  " + " EVINIRONMENTAL SCIENCE");
//            Phrase p12 = new Paragraph("Practical Examination February / July" );
            Phrase  p13 = new Paragraph("Particulars");
            Phrase  p14 = new Paragraph("Amount");
/*            Paragraph  p15 = new Paragraph("Amount due, to me as an EXTERNAL/INTERNAL Examiner at");
            Paragraph  p16 = new Paragraph("the Examination center. Number ofdays of examination =" + "   " + "12");
            Paragraph  p17 = new Paragraph("Actual Total number of candidates examined by me =" + "   " +"999");
            Paragraph  p18 = new Paragraph("Excluding Absentees");
            Paragraph  p19 = new Paragraph("Rs."+ "   05   " + "per candidate." + "          " + " 05 x 999 = 495 " );
            Paragraph  p20 = new Paragraph("( Minimum of Rs.50/- irrespective of the number of candidates )");
*/
            Paragraph  p21 = new Paragraph("I hereby undertake to refund if any amount paid to me in excess of the amount due");

/*            Paragraph  p22 = new Paragraph("Name of Jr. College where teaching" + "   " +" St. Andrews College - Bandra.");
            Paragraph  p23 = new Paragraph("IndexNo.of Jr.College" + "   " + " J - 31.04.005 ");
            Paragraph  p24 = new Paragraph("certified that the Examiner has actually examined the No. of candidates mentioned above.");
            Paragraph  p25 = new Paragraph("Counter signature of the Head of the institution with stamp");
*/
            Phrase  p26 = new Paragraph("Total");
            Paragraph  p27 = new Paragraph("Signature", font2);
            Paragraph  p28a = new Paragraph("Full Postal", font2);
            Paragraph  p28b = new Paragraph("Residential Address", font2);
            Phrase  p29 = new Paragraph("Rs. " + "999");
            Paragraph  p30 = new Paragraph("On revenue ", font2);
            Paragraph  p31 = new Paragraph("stamp, when", font2);
            Paragraph  p32 = new Paragraph("the amount", font2);
            Paragraph  p33 = new Paragraph("exceeds ", font2);
            Paragraph  p34 = new Paragraph(" Rs.5000/- ", font2);
            Paragraph  p35 = new Paragraph("Recieved Payment", font2);
            Paragraph  p36 = new Paragraph("Signature of payee", font2);

            P1.setAlignment(Element.ALIGN_CENTER);
            doc.add(P1);

/*          ((Paragraph) p1).setAlignment(Element.ALIGN_CENTER);
//          doc.add(p1);
            ((Paragraph) p2).setAlignment(Element.ALIGN_CENTER);
            doc.add(p2);
            ((Paragraph) p3).setAlignment(Element.ALIGN_CENTER);
            doc.add(p3);
            ((Paragraph) p4).setAlignment(Element.ALIGN_CENTER);
            doc.add(p4);
*/
            p0.setAlignment(Element.ALIGN_LEFT);
            doc.add(p0);

            P2.setAlignment(Element.ALIGN_LEFT);
            doc.add(P2);

            p0.setSpacingBefore(4f);
            doc.add(p0);

            float [] ColumnWidth1 = { 75F };
            PdfPTable table1 = new PdfPTable(ColumnWidth1);
//            PdfPCell cell1 = new PdfPCell();

            PdfPCell cell1 = new PdfPCell(new Paragraph(p10));
//            cell1.setHorizontalAlignment(350);
            cell1.setBorder(PdfPCell.NO_BORDER);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.addCell(cell1);

            cell1 = new PdfPCell(new Paragraph("  "));
//            cell1.setHorizontalAlignment(350);
            cell1.setBorder(PdfPCell.NO_BORDER);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table1.setSpacingAfter(-7f);
            table1.addCell(cell1);
            doc.add(table1);



            PdfPTable table2 = new PdfPTable(4);

            // Creates another row that only have two columns.
            // The cell 5 and cell 6 width will span two columns
            // in width.
            PdfPCell cell2 = new PdfPCell(new Phrase(p11a));
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setColspan(2);

            PdfPCell cell3 = new PdfPCell(new Phrase(p11b));
            cell3.setColspan(2);
            cell3.setBorder(PdfPCell.NO_BORDER);

            table2.setSpacingAfter(14f);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setSpacingAfter(13f);
            table2.setWidthPercentage(90);
            table2.addCell(cell2);
            table2.addCell(cell3);
            // Adds table to the doc
            doc.add(table2);

            PdfPTable table3 = new PdfPTable(4);
            PdfPCell cell4 = new PdfPCell(new Phrase(p12a));
            cell4.setBorder(PdfPCell.NO_BORDER);
            cell4.setColspan(2);

            PdfPCell cell5 = new PdfPCell(new Phrase(p12b));
            cell5.setColspan(2);
            cell5.setBorder(PdfPCell.NO_BORDER);

            table3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.setWidthPercentage(113);
            table3.addCell(cell4);
            table3.addCell(cell5);
            // Adds table to the doc
            doc.add(table3);





/*            ((Paragraph) p5).setAlignment(Element.ALIGN_LEFT);
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
 */
            p0.setAlignment(Element.ALIGN_LEFT);
            doc.add(p0);

            P5.setSpacingBefore(10f);
            P5.setLeading(22f);
            P5.setAlignment(Element.ALIGN_LEFT);
            doc.add(P5);

            P6.setSpacingBefore(170f);
            P6.setAlignment(Element.ALIGN_LEFT);
            doc.add(P6);

/*            p10.setAlignment(Element.ALIGN_LEFT);
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
*/
// Creating a PdfCanvas object
//            PdfCanvas canvas = new PdfCanvas(pdfPage);
            PdfContentByte canvas = docWriter.getDirectContent();
            CMYKColor blackColor = new CMYKColor(0.f, 0.f, 0.f, 1.f);
            canvas.setColorStroke(blackColor);

            canvas.moveTo((25.5)*x/100, 68.25*y/100);
            canvas.lineTo(95*x/100, 68.25*y/100);          // Line in front of Name

            canvas.moveTo(14*x/100, 65*y/100);
            canvas.lineTo(47*x/100, 65*y/100);             // Line in front of Subject

            canvas.moveTo(82*x/100, 65*y/100);             // Difference of 3.25 in y-coordinates
            canvas.lineTo(95*x/100, 65*y/100);             // Line in front of Examination February / July
            // If we add length of the Subject String
            // i.e x-coordinate of .lineto = x-coordinate of .moveto + l(Subject String) + 5
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

            canvas.moveTo(6.8*x/100, 39*y/100);           // Bottom First Line of the Bill Table
            canvas.lineTo(95*x/100, 39*y/100);            // Bottom First Line of the Bill Table

            canvas.moveTo(66*x/100, 59.5*y/100);           // Vertical Line of the Bill Table
            canvas.lineTo(66*x/100, 39*y/100);             // Vertical Line of the Bill Table

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

            canvas.moveTo(40*x/100, 19.3*y/100);           // Line 4th below signature line
            canvas.lineTo(95*x/100, 19.3*y/100);           // Line 4th below signature line

            canvas.moveTo(28*x/100, 16.8*y/100);           // Line 5th below signature line
            canvas.lineTo(42*x/100, 16.8*y/100);           // Line 5th below signature line

            canvas.moveTo(60*x/100, 41*y/100);             // Vertical Line of the Bill Table -> Total
            canvas.lineTo(60*x/100, 39*y/100);             // Vertical Line of the Bill Table -> Total

            canvas.rectangle(60, 210, 64, 70);

//          ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p10,  96, 576, 0);    // Name Internal

//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p11a, 60, 549, 0);    // Subject
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p11b, 389, 549, 0);   // Year of Exam

//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p12a, 55, 522, 0);    // at - College Name
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p12b, 455, 522, 0);   // Index No. of College

            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p13, 216, 487, 0);    // Particulars
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p14, 481, 487, 0);    // Amount

//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p15, 205, 460, 0);    // 1st line inside Bill
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p16, 203, 438, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p17, 197, 416, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p18, 96, 394, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p19, 162, 372, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p20, 208, 350, 0);

            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p21, 305, 314, 0);

//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p22, 221, 165, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p23, 139, 144, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p24, 278, 121, 0);
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p25, 194, 99, 0);

//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p26, 374, 332, 0);     // Total
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p27, 274, 280, 0);     // Signature
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p28, 252, 252, 0);     // Residential address
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 450, 465, 0);     // Total Amount on Toop
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 450, 332, 0);     // Total Amount at Bottom
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p26, 63*x/100, 40*y/100 - 4, 0); // Total
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p27, 46*x/100, 33*y/100, 0);     // Signature
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p28a, 42*x/100, 30*y/100, 0);     // Residential address
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p28b, 42*x/100, 29*y/100, 0);     // Residential address
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 76*x/100, 55*y/100, 0);     // Total Amount on Toop
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 76*x/100, 40*y/100-4, 0);   // Total Amount at Bottom
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p29, 84, 265, 0);   // Total Amount at Bottom
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p30, 90, 266, 0);   // Revenue Stamp
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p31, 90, 254, 0);   // Revenue Stamp
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p32, 90, 242, 0);   // Revenue Stamp
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p33, 90, 230, 0);   // Revenue Stamp
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p34, 90, 218, 0);   // Revenue Stamp
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p35, 90, 285, 0);   // Payment Recieved
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, p36, 90, 200, 0);   // Signature of payee


            canvas.closePathStroke();

/*            doc.add(p0);
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
            p0.setSpacingBefore(0f);
            doc.add(p0);
            p21.setAlignment(Element.ALIGN_CENTER);
            p21.setSpacingBefore(4f);
            doc.add(p21);

            p0.setSpacingBefore(105f);
            doc.add(p0);

            p22.setAlignment(Element.ALIGN_LEFT);
            p22.setSpacingBefore(4f); doc.add(p22);
            p23.setAlignment(Element.ALIGN_LEFT);
            p23.setSpacingBefore(4f); doc.add(p23);
            p24.setAlignment(Element.ALIGN_LEFT);
            p24.setSpacingBefore(4f); doc.add(p24);
            p25.setAlignment(Element.ALIGN_LEFT);
            p25.setSpacingBefore(4f); doc.add(p25);
*/

            doc.close();
//            Msg.Show("From CreatePDF, in ExminersBill class",MA);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}