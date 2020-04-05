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

//import static com.example.rembill_2.Msg.show;

public class FileSaveNLoad {

    public MainActivity MA;
    public void SetMA(MainActivity MA){this.MA=MA;}
    Message Msg = new Message();

    public void show(String tempstring)
    {
        Toast.makeText(MA,tempstring,Toast.LENGTH_SHORT).show();
    }

    String internalname, internalCollegeName, internalColIndex, internalAddressLine1, internalAddressLine2, internalAddressLine3;
    String externalname, externalCollegeName, externalColIndex, externalAddressLine1, externalAddressLine2, externalAddressLine3;
    String examSubject, examYear, examStartDate, examEndDate, noExamDates, examNoOfDays, NoOfStudents, remunerationPerStudent;



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
//                LoadFile(ttt);
                show(ttt);
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
            String DataRow = "";

            DataRow=myReader.readLine(); /// blank line separator
            DataRow=myReader.readLine(); /// blank line separator

//            String tempstr;
            MA.ExamRelatedDetails.removeAll(MA.ExamRelatedDetails);
            while ((DataRow = myReader.readLine()) != null)

            {
                MA.ExamRelatedDetails.add(DataRow);
            }

            myReader.close();

        }
        catch (Exception e)
        {
            Toast.makeText(MA,e.getMessage(),Toast.LENGTH_SHORT).show();
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

//            myOutWriter.append("\n");

            myOutWriter.append("==== Details of Examination ====");

            myOutWriter.append("\n");
            myOutWriter.append(examSubject);
            myOutWriter.append("\n");
            myOutWriter.append(examYear);
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
            myOutWriter.append("==== Details of Internal Examiner ====");
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
            myOutWriter.append("==== Details of EXternal Examiner ====");
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

    public void ShowInternalExaminerDetails(Activity activity, String e1,final String e2, final String e3,final String e4,
                                            final String e5, final String e6)
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

        et1.setText(e1); et2.setText(e2);  et3.setText(e3); et4.setText(e4);  et5.setText(e5); et6.setText(e6);

        dialog.show();

        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnCancel = (Button) dialog.findViewById(R.id.ok);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public void ShowExternalExaminerDetails(Activity activity, final String e7,final String e8, final String e9,final String e10,
                                            final String e11, final String e12)
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

        et7.setText(e7); et8.setText(e8);  et9.setText(e9); et10.setText(e10);  et11.setText(e11); et12.setText(e12);

        dialog.show();

        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnCancel = (Button) dialog.findViewById(R.id.ok);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void ShowExamDetails(Activity activity, final String e13, final String e14, final String e15, final String e16,
                                 final String e17, final String e18, final String e19, final String e20)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.examdetails);

        final EditText et13 = dialog.findViewById(R.id.ExamSubject);
        final EditText et14 = dialog.findViewById(R.id.ExamYear);
        final EditText et15 = dialog.findViewById(R.id.ExamStartDate);
        final EditText et16 = dialog.findViewById(R.id.ExamEndDate);
        final EditText et17 = dialog.findViewById(R.id.NoExamDate);
        final EditText et18 = dialog.findViewById(R.id.NoOfDaysOfExam);
        final EditText et19 = dialog.findViewById(R.id.NoOfStudents);
        final EditText et20 = dialog.findViewById(R.id.RemPerStudent);

        Button btnSubmit = (Button) dialog.findViewById(R.id.submitButton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                examSubject = et13.getText().toString();
                examYear = et14.getText().toString();
                examStartDate = et15.getText().toString();
                examEndDate = et16.getText().toString();
                noExamDates = et17.getText().toString();
                examNoOfDays = et18.getText().toString();
                NoOfStudents = et19.getText().toString();
                remunerationPerStudent = et20.getText().toString();

//                examEndDate = et18.getText().toString();

                SaveToFile();
                dialog.dismiss();
            }
        });

        et19.setText(e19);    et13.setText(e13);  et14.setText(e14);  et15.setText(e15);
        et16.setText(e16);    et17.setText(e17);  et18.setText(e18);  et20.setText(e20);

        dialog.show();
        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnCancel = (Button) dialog.findViewById(R.id.OKExamDetails);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }



}
