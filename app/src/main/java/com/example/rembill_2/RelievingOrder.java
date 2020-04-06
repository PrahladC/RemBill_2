package com.example.rembill_2;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
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

    String rootDir;
    void RelieveOrderPDF() {
        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + "MA.externalname" + "- Relieve" +".pdf";

        try {
            Document doc = new Document();
            doc = new Document(PageSize.A4);
            doc.setMargins(40, 25, 30, 30);

            File pdfFile =new File(pdfFileNameWithPath);
            PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
//            PdfContentByte pcb = docWriter.getDirectContent();
            doc.open();

// Creating a table object
//          float [] pointColumnWidths = {150F, 150F, 150F, 150F};
            float x = doc.getPageSize().getWidth();     //  x = 595
            float y = doc.getPageSize().getHeight();    //  y = 840

            Paragraph P1 = new Paragraph("Shri/Smt/Miss" );   //   + "  "  + MA.internalname);



            PdfContentByte canvas = docWriter.getDirectContent();
            CMYKColor blackColor = new CMYKColor(0.f, 0.f, 0.f, 1.f);
            canvas.setColorStroke(blackColor);

            canvas.moveTo(40, 30);
            canvas.lineTo(40, y - 30);

            canvas.moveTo(40, 30);
            canvas.lineTo(x - 25, 30);

            canvas.moveTo(x - 25, 30);
            canvas.lineTo(x - 25, y - 30);

            canvas.moveTo(40, y - 30);
            canvas.lineTo(x - 25, y - 30);



//            P1.setAlignment(Element.ALIGN_LEFT);
            doc.add(P1);


            canvas.stroke();
            doc.close();
//            Msg.Show("From CreatePDF, in ExminersBill class",MA);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
