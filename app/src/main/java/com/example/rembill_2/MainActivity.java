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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    String name;

    Message Msg = new Message();
    private Button buttonExternal, buttonInternal, buttonExaminationDetails;
    private EditText Internalname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonExternal = (Button) findViewById(R.id.external_button);
        buttonExternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


            }

        });


        buttonExaminationDetails = (Button) findViewById(R.id.ExamDetails_button);
        buttonExaminationDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {


            }

        });


        buttonInternal = (Button) findViewById(R.id.internal_button);
        buttonInternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                ShowMyDialog(MainActivity.this);

                /*
                // Create custom dialog object
                final Dialog dialog = new Dialog(MainActivity.this);
                // Include examinerdetails.xml file
                dialog.setContentView(R.layout.examinerdetails);
                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Details of Internal Examiner");

                dialog.show();

                if (!StoragePermissionGranted()) ;
                Button SaveTrDetails = (Button) dialog.findViewById(R.id.SaveExaminerDetails);
                SaveTrDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog

                        name = Internalname.getText().toString();
                        SaveToFile();
                        dialog.dismiss();
                    }
                });
                //  To Scroll the dialog box up when Virtual (Soft) keyboard appears
                dialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                */
            }

        });

/*
        if(!StoragePermissionGranted()) ;
//        if(!StoragePermissionGranted()) finish();

        Button Save = (Button) findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SaveToFile();

//               show(" Hello World !!!");

            }
        });

*/
    }


    private void SaveToFile() {

        String FileNameWithPath = "/sdcard/"; // Environment.getExternalStorageDirectory().getAbsolutePath();
        FileNameWithPath += "test3.rmb";

        //  EditText Internalname = (EditText) findViewById(R.id.ExaminerName);


        try {
            File myFile = new File(FileNameWithPath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
//            myOutWriter.append(FileNameWithPath);
            myOutWriter.append("==== Details of Internal Examiner ====");
            //          myOutWriter.append(Externalname.getText());
            myOutWriter.append("\n");
            myOutWriter.append("==== Details of Internal Examiner ====");
            myOutWriter.append(name);
            myOutWriter.append("\n");


            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

//        show(" File Saved !!!");
    }


    public boolean StoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
//                Msg.show("Permission Granted", this);
//                Msg.Show("Permission Granted");

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
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Msg.show("Permission Granted", this);


        }


    }





    public void ShowMyDialog(Activity activity)
    {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.examinerdetails);

        final EditText et = dialog.findViewById(R.id.ExaminerName);

        Button btnok = (Button) dialog.findViewById(R.id.SaveExaminerDetails);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=et.getText().toString();
                SaveToFile();
                dialog.dismiss();
            }
        });
        dialog.show();
    }







}





