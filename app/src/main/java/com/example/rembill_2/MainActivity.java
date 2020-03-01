package com.example.rembill_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    FileSaveNLoad FSNL = new FileSaveNLoad();
    Message Msg = new Message();
    private Button buttonExternal, buttonInternal, buttonExaminationDetails, Load, Exit,PrintAllCombined;
//    private EditText Internalname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoragePermissionGranted();
//        if(!StoragePermissionGranted()) finish();

        Button btnLoad = (Button) findViewById(R.id.Load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
//                finish();
//                System.exit(0);
                FSNL.OpenFileDialog();

            }
        });

        Button btnCancel = (Button) findViewById(R.id.Exit);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FSNL.OpenFileDialog();
                finish();
                System.exit(0);
            }
        });

        buttonInternal = (Button) findViewById(R.id.internal_button);
        buttonInternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {

                FSNL.ShowInternalExaminerDetails(MainActivity.this);
             }

        });

        buttonExternal = (Button) findViewById(R.id.external_button);
        buttonExternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                FSNL.ShowExternalExaminerDetails(MainActivity.this);
            }

        });

        buttonExaminationDetails = (Button) findViewById(R.id.ExamDetails_button);
        buttonExaminationDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0){

                FSNL.ShowExamDetails(MainActivity.this);
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
            Msg.show("Permission Granted");


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
*/

}





