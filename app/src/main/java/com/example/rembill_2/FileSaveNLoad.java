package com.example.rembill_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileSaveNLoad {

    private static  MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}
    Message Msg = new Message();

    String internalname, internalCollegeName, internalColIndex, internalAddressLine1, internalAddressLine2, internalAddressLine3;
    String externalname, externalCollegeName, externalColIndex, externalAddressLine1, externalAddressLine2, externalAddressLine3;
    String examYear, examStartDate, examEndDate, noExamDates, examNoOfDays, NoOfStudents, remunerationPerStudent;


    void OpenFileDialog()
    {	String tempstr;
        //OpenNow=false;
        String rootDir= Environment.getExternalStorageDirectory().getPath();
        List<String> listItems = new ArrayList<String>();
        File mfile=new File(rootDir);
        File[] list=mfile.listFiles();
        String tempupper;
        for(int i=0;i<mfile.listFiles().length;i++)
        {
            tempstr=list[i].getAbsolutePath();
//            tempupper=tempstr.toUpperCase();
//            if(tempupper.endsWith(".RMB") )
            if(tempstr.endsWith(".rmb")){
            listItems.add(list[i].getAbsolutePath());
             }
        }

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(MA);
        builder.setTitle("Select File To Open...");
        builder.setItems(items, new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface dialog, int item)
            {String ttt= (String) items[item];
                LoadFile(ttt);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    void LoadFile(String fylenamewithpath){

        try {

            File myFile = new File(fylenamewithpath);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";

            aDataRow=myReader.readLine(); /// blank line separator

            String temp[],stemp;


        }
        catch (Exception e)
        {
//            Toast.makeText(MA,e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }



    void SaveToFile() {

        String FileNameWithPath="";
        FileNameWithPath = "/sdcard/"; // Environment.getExternalStorageDirectory().getAbsolutePath();

        FileNameWithPath += externalname + " - " + "RemBill.rmb";

        //  EditText Internalname = (EditText) findViewById(R.id.ExaminerName);


        try {
            File myFile = new File(FileNameWithPath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
//            myOutWriter.append(FileNameWithPath);

            myOutWriter.append("\n");

            myOutWriter.append("==== Details of Examination ====");

            myOutWriter.append("\n");myOutWriter.append(examYear);
            myOutWriter.append("\n");
            myOutWriter.append(examStartDate);
            myOutWriter.append("\n");
            myOutWriter.append(examEndDate);
            myOutWriter.append("\n");
            myOutWriter.append(noExamDates);
            myOutWriter.append("\n");
            myOutWriter.append(examNoOfDays);
            myOutWriter.append("\n");
            myOutWriter.append(NoOfStudents);
            myOutWriter.append("\n");
            myOutWriter.append(remunerationPerStudent);

            myOutWriter.append("\n");
            myOutWriter.append("\n");

            myOutWriter.append("==== Details of Internal Examiner ====");

            myOutWriter.append("\n");
            myOutWriter.append("\n");

            myOutWriter.append(internalname);
            myOutWriter.append("\n");
            myOutWriter.append(internalCollegeName);
            myOutWriter.append("\n");
            myOutWriter.append(internalColIndex);
            myOutWriter.append("\n");
            myOutWriter.append(internalAddressLine1);
            myOutWriter.append("\n");
            myOutWriter.append(internalAddressLine2);
            myOutWriter.append("\n");
            myOutWriter.append(internalAddressLine3);

            myOutWriter.append("\n");
            myOutWriter.append("\n");

            myOutWriter.append("==== Details of EXternal Examiner ====");

            myOutWriter.append("\n");
            myOutWriter.append("\n");

            myOutWriter.append(externalname);
            myOutWriter.append("\n");
            myOutWriter.append(externalCollegeName);
            myOutWriter.append("\n");
            myOutWriter.append(externalColIndex);
            myOutWriter.append("\n");
            myOutWriter.append(externalAddressLine1);
            myOutWriter.append("\n");
            myOutWriter.append(externalAddressLine2);
            myOutWriter.append("\n");
            myOutWriter.append(externalAddressLine3);

            myOutWriter.append("\n");

            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Toast.makeText(MA.getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();


        }

//        show(" File Saved !!!");
    }

    public void ShowInternalExaminerDetails(Activity activity)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle("Internal Examiner Details");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.examinerdetails);

        final EditText et1 = dialog.findViewById(R.id.ExaminerName);
        final EditText et2 = dialog.findViewById(R.id.CollegeName);
        final EditText et3 = dialog.findViewById(R.id.collegeIndexNo);
        final EditText et4 = dialog.findViewById(R.id.addressLine1);
        final EditText et5 = dialog.findViewById(R.id.addressLine2);
        final EditText et6 = dialog.findViewById(R.id.addressLine3);

        Button btnSave = (Button) dialog.findViewById(R.id.SaveExaminerDetails);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                internalname = et1.getText().toString();
                internalCollegeName = et2.getText().toString();
                internalColIndex = et3.getText().toString();
                internalAddressLine1 = et4.getText().toString();
                internalAddressLine2 = et5.getText().toString();
                internalAddressLine3 = et6.getText().toString();

                SaveToFile();
                dialog.dismiss();
            }
        });
        dialog.show();

        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public void ShowExternalExaminerDetails(Activity activity)
    {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle("Internal Examiner Details");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.examinerdetails);

        final EditText et7 = dialog.findViewById(R.id.ExaminerName);
        final EditText et8 = dialog.findViewById(R.id.CollegeName);
        final EditText et9 = dialog.findViewById(R.id.collegeIndexNo);
        final EditText et10 = dialog.findViewById(R.id.addressLine1);
        final EditText et11 = dialog.findViewById(R.id.addressLine2);
        final EditText et12 = dialog.findViewById(R.id.addressLine3);

        Button btnSave = (Button) dialog.findViewById(R.id.SaveExaminerDetails);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                externalname = et7.getText().toString();
                externalCollegeName = et8.getText().toString();
                externalColIndex = et9.getText().toString();
                externalAddressLine1 = et10.getText().toString();
                externalAddressLine2 = et11.getText().toString();
                externalAddressLine3 = et12.getText().toString();

                SaveToFile();
                dialog.dismiss();
            }
        });
        dialog.show();

        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void ShowExamDetails(Activity activity)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.examdetails);

        final EditText et13 = dialog.findViewById(R.id.ExamYear);
        final EditText et14 = dialog.findViewById(R.id.ExamStartDate);
        final EditText et15 = dialog.findViewById(R.id.ExamEndDate);
        final EditText et16 = dialog.findViewById(R.id.NoExamDate);
        final EditText et17 = dialog.findViewById(R.id.NoOfStudents);
        final EditText et18 = dialog.findViewById(R.id.RemPerStudent);

        Button btnSubmit = (Button) dialog.findViewById(R.id.submitButton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                examYear = et13.getText().toString();
                examStartDate = et14.getText().toString();
                examEndDate = et15.getText().toString();
                noExamDates = et16.getText().toString();
                examNoOfDays = et17.getText().toString();
                NoOfStudents = et18.getText().toString();
                remunerationPerStudent = et18.getText().toString();

//                examEndDate = et18.getText().toString();

                SaveToFile();
                dialog.dismiss();
            }
        });

        dialog.show();
        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnCancel = (Button) dialog.findViewById(R.id.cancelExamDetails);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }



}
