package com.example.rembill_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FileSaveNLoad FSNL = new FileSaveNLoad();
    ExaminersBill EB = new ExaminersBill();
    RelievingOrder RO = new RelievingOrder();
    Msg msg = new Msg();

    String fylenemwithpsth = Environment.getExternalStorageDirectory().getPath();

    String examSubject, examYear, examStartDate, examEndDate, noExamDates, examNoOfDays, NoOfStudents, remunerationPerStudent;
    private Button buttonExternal, buttonInternal, buttonExaminationDetails, PrintInternalBill, PrintExternalBill,
            PrintRelieveOrder, buttonLoad, buttonExit, PrintAllCombined;
    String internalname, internalCollegeName, internalColIndex, internalAddressLine1, internalAddressLine2, internalAddressLine3;
    String externalname, externalCollegeName, externalColIndex, externalAddressLine1, externalAddressLine2, externalAddressLine3;
    String E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12;
    String E13, E14, E15, E16, E17, E18, E19, E20;


    ArrayList<String> ExamRelatedDetails = new ArrayList<>();

//    private EditText Internalname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoragePermissionGranted();

        EB.SetMA(this);
        RO.SetMA(this);
        msg.SetMA(this);
//        if(!StoragePermissionGranted()) { return;}

        /// Iniitialized array with 22 empty strings
        for (int i = 0; i < 23; i++) ExamRelatedDetails.add("");

        buttonLoad = findViewById(R.id.Load);
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
                OpenFileDialog();
                LoadFile(fylenemwithpsth);

            }
        });

        buttonExit = findViewById(R.id.Exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonInternal = findViewById(R.id.internal_button);
        buttonInternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                E1 = ExamRelatedDetails.get(10);
                E2 = ExamRelatedDetails.get(11);
                E3 = ExamRelatedDetails.get(12);
                E4 = ExamRelatedDetails.get(13);
                E5 = ExamRelatedDetails.get(14);
                E6 = ExamRelatedDetails.get(15);

                FSNL.ShowInternalExaminerDetails(MainActivity.this, E1, E2, E3, E4, E5, E6);
                internalname = E1;          internalCollegeName = E2;   internalColIndex = E3;
                internalAddressLine1 = E4;  internalAddressLine2 = E5;  internalAddressLine3 = E6;
            }

        });

        buttonExternal = findViewById(R.id.external_button);
        buttonExternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                E7 = ExamRelatedDetails.get(17);
                E8 = ExamRelatedDetails.get(18);
                E9 = ExamRelatedDetails.get(19);
                E10 = ExamRelatedDetails.get(20);
                E11 = ExamRelatedDetails.get(21);
                E12 = ExamRelatedDetails.get(22);

//                show(E7); show(E8); show(E9); show(E10); show(E11); show(E12);

                FSNL.ShowExternalExaminerDetails(MainActivity.this, E7, E8, E9, E10, E11, E12);
                externalname = E7;          externalCollegeName = E8;    externalColIndex = E9;
                externalAddressLine1 = E10; externalAddressLine2 = E11;  externalAddressLine3 = E12;
            }

        });

        buttonExaminationDetails = findViewById(R.id.ExamDetails_button);
        buttonExaminationDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                E13 = ExamRelatedDetails.get(1);   //  Msg.Show(E13, getApplicationContext());
                E14 = ExamRelatedDetails.get(2);   //  Msg.Show(E14, getApplicationContext());
                E15 = ExamRelatedDetails.get(3);   //  Msg.Show(E15, getApplicationContext());
                E16 = ExamRelatedDetails.get(4);   //  Msg.Show(E16, getApplicationContext());
                E17 = ExamRelatedDetails.get(5);   //  Msg.Show(E17, getApplicationContext());
                E18 = ExamRelatedDetails.get(6);   //  Msg.Show(E18, getApplicationContext());
                E19 = ExamRelatedDetails.get(7);   //  Msg.Show(E19, getApplicationContext());
                E20 = ExamRelatedDetails.get(8);   //  Msg.Show(E20, getApplicationContext());

//              show(E13); show(E14); show(E15); show(E16); show(E17); show(E18); show(E19);

                FSNL.ShowExamDetails(MainActivity.this, E13, E14, E15, E16, E17, E18, E19, E20);
                examSubject = E13;          examYear = E14;         examStartDate = E15;    examEndDate = E16;
                noExamDates = E17;          examNoOfDays = E18;     NoOfStudents = E19;     remunerationPerStudent = E20;
            }

        });

        //  internal_Bill_Print

        PrintInternalBill = findViewById(R.id.internal_Bill_Print);
        PrintInternalBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                EB.CreateInternalPDF();
              msg.Show("PDF of Internal Examiner's \n Bill Created",getApplicationContext());
            }

        });

        PrintExternalBill = findViewById(R.id.external_Bill_Print);
        PrintExternalBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                EB.CreateExternalPDF();
                msg.Show("PDF of External Examiner's \n Bill Created",getApplicationContext());
            }

        });

        PrintRelieveOrder = findViewById(R.id.external_Relieve_Print);
        PrintRelieveOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                RO.RelieveOrderPDF();
                msg.Show("PDF of External Examiner's \n Relieving Order Created",getApplicationContext());
            }

        });

        PrintAllCombined = findViewById(R.id.PrintAllPDFs);
        PrintAllCombined.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

//                RO.RelieveOrderPDF();
                msg.Show("Combined PDF of all \n is yet to be done",getApplicationContext());
            }

        });

    }


    public boolean StoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
//                Msg.show("Permission Granted", this);
//                Msg.show("Permission Granted");

                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //  Log.v(TAG,"Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            msg.Show("Permission Granted",getApplicationContext());

    }

    void OpenFileDialog() {
        String tempstr;
        //OpenNow=false;
        String rootDir = Environment.getExternalStorageDirectory().getPath();
        List<String> listItems = new ArrayList<String>();
        File Pfile = new File(rootDir);
        File[] list = Pfile.listFiles();
        String tempupper;
        for (int i = 0; i < Pfile.listFiles().length; i++) {
            tempstr = list[i].getAbsolutePath();
            tempupper = tempstr.toUpperCase();
            if (tempupper.endsWith(".RMB"))
                listItems.add(list[i].getAbsolutePath());
        }

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select File To Open...");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selected = (String) items[item];
                LoadFile(selected);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    void LoadFile(String fylenamewithpath) {

        try {

            File myFile = new File(fylenamewithpath);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String DataRow = "";

            ExamRelatedDetails.removeAll(ExamRelatedDetails);
            while ((DataRow = myReader.readLine()) != null) {
                ExamRelatedDetails.add(DataRow);
            }

            myReader.close();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

}

/*    public void ShowInternalExaminerDetails(Activity activity)
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

                FSNL.SaveToFile();
                dialog.dismiss();
            }
        });
        dialog.show();

        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnLoad = (Button) dialog.findViewById(R.id.LoadexaminerDetails);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
                dialog.dismiss();
            }
        });

        dialog.show();

    }
*/

/*    public void ShowExternalExaminerDetails(Activity activity)
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

                FSNL.SaveToFile();
                dialog.dismiss();
            }
        });
        dialog.show();

        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnLoad = (Button) dialog.findViewById(R.id.LoadexaminerDetails);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();

    }
*/

/*    public void ShowExamDetails(Activity activity)
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

        Button btnSubmit = (Button) dialog.findViewById(R.id.closeButton);
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

                FSNL.SaveToFile();
                dialog.dismiss();
            }
        });

        dialog.show();
        //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Button btnLoad = (Button) dialog.findViewById(R.id.LoadexamDetails);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
*/




