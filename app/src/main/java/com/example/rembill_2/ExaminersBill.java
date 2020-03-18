package com.example.rembill_2;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
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


    int listfiles(String path) {
        FilenameFilter mrkFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".rmb")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        fileArray.removeAll(fileArray);
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(mrkFilter);
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileArray.add(listOfFiles[i].getAbsolutePath());
            }
        }
        int Total = fileArray.size();
        return Total;
    }


    static  void Add(Document document,
                     String Zone,
                     String MonthYear,
                     String BatchNo,
                     String Date,
                     String BatchTime,
                     String School,
                     String Index,
                     String Strim,
                     String Standard,
                     String Subject,
                     String SubjectCode,
                     String Medium,
                     String Type,
                     String BatchCreator,
                     String BatchSession,
                     String froll,
                     String lroll

    ) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);

        PdfPCell cell = new PdfPCell(new Phrase("Maharashtra State Board of Secondary & Higher Secondary Education",normal));cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(Zone,normal));cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("HSC - Practical - "+MonthYear,normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Attendance Sheet",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        //////////   table row
        float col3[]= {12,12,7};
        PdfPTable table2 = new PdfPTable(col3);
        table2.setWidthPercentage(95);
        cell = new PdfPCell(new Phrase("School/College/Center : "+School,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Batch No : "+BatchNo,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        //////////   table row

        cell = new PdfPCell(new Phrase("Subject : "+Subject,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Date : "+Date,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);


//////////table row

        cell = new PdfPCell(new Phrase("Seat No's From : "+froll+" - "+lroll,
                normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Time : "+BatchTime,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

//////////table row

        cell = new PdfPCell(new Phrase("Extra Seat No's: ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        table2.setSpacingAfter(8f);


        document.add(table);
        document.add(table2);

    }

    void CreatePDF()
    {
        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + FSNL.externalname + ".pdf";
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
    }

}
