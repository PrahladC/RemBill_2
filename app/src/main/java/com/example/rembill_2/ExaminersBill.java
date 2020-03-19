package com.example.rembill_2;

import android.os.Environment;

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
import java.util.ArrayList;

public class ExaminersBill {

    static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);
    FileSaveNLoad FSNL = new FileSaveNLoad();

//    void SetMA(MainActivity MA) {
//        this.MA = MA;
//    }

    String rootDir;
    public ArrayList<String> fileArray = new ArrayList<String>();



    void CreatePDF()
    {
/*        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + FSNL.externalname + ".pdf";
//        String pdfFileNameWithPath = rootDir + "/" + FSNL.externalname + ".pdf";
        int totalfiles = listfiles(rootDir);
//        MA.show(String.format("Total Batches %d",totalfiles));

        try {

            File myFile = new File(pdfFileNameWithPath);
            OutputStream output = new FileOutputStream(myFile);
            Document document = new Document();
            document = new Document(PageSize.A4);
            document.setMargins(50, 30, 15, 2);
            PdfWriter.getInstance(document, output);

            document.open();
            String eText = FSNL.examEndDate;
            document.add(new Paragraph(eText));
            document.add(new Paragraph("Maharashtra State Board of Secondary & Higher Secondary Education"));
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        String pdfFileNameWithPath = "New.pdf";

        try {
            Document doc = new Document();
            File pdfFile =new File(pdfFileNameWithPath);
            PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
            doc.open();


            doc.add(new Paragraph("Maharashtra State Board of Secondary & Higher Secondary Education"));
            //list all the products sold to the customer
            float[] columnWidths = {1.5f, 2f, 5f, 2f,2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setTotalWidth(500f);

            PdfPCell cell = new PdfPCell(new Phrase("Qty"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Item Number"));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Item Description"));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Price"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Ext Price"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            table.setHeaderRows(1);



            doc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
