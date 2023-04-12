package com.example.contactsensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text1 = (TextView) findViewById(R.id.txt1);
        TextView text2 = (TextView) findViewById(R.id.txt2);
        TextView text3 = (TextView) findViewById(R.id.txt3);
        TextView text4 = (TextView) findViewById(R.id.txt4);

        ArrayList<String> peopleList = new ArrayList<String>();
        peopleList.add("John");
        peopleList.add("James");
        peopleList.add("Jenny");
        peopleList.add("Jamie");

        ArrayList<String> numberList = new ArrayList<String>();
        numberList.add("1111111111");
        numberList.add("2222222222");
        numberList.add("3333333333");
        numberList.add("4444444444");



        for (int i = 0; i < peopleList.size(); i++)
        {
            long phoneFmt = Long.parseLong(numberList.get(i));
            //get a 12 digits String, filling with left '0' (on the prefix)
            DecimalFormat phoneDecimalFmt = new DecimalFormat("000000000");
            String phoneRawString= phoneDecimalFmt.format(phoneFmt);

            java.text.MessageFormat phoneMsgFmt=new java.text.MessageFormat("({0})-{1}-{2}");
            //suposing a grouping of 3-3-4
            String[] phoneNumArr={phoneRawString.substring(0, 3),
                    phoneRawString.substring(3,6),
                    phoneRawString.substring(6)};
            switch (i)
            {
                case 0:


                    text1.setText(peopleList.get(i) + "\t\t\t\t\t" + phoneMsgFmt.format(phoneNumArr));

                    break;
                case 1:

                    text2.setText(peopleList.get(i) + "\t\t\t\t\t" + phoneMsgFmt.format(phoneNumArr));
                    break;
                case 2:
                    text3.setText(peopleList.get(i) + "\t\t\t\t\t" + phoneMsgFmt.format(phoneNumArr));
                    break;
                case 3:
                    text4.setText(peopleList.get(i) + "\t\t\t\t\t" + phoneMsgFmt.format(phoneNumArr));
                    break;
            }

        }
        //Performing action on button click
        text1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Uri number = Uri.parse("tel:"+numberList.get(0));
                checkPhonePermissions(number);
            }

        });
        text2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                Uri number = Uri.parse("tel:"+numberList.get(1));
                checkPhonePermissions(number);
            }

        });
        text3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Uri number = Uri.parse("tel:"+numberList.get(2));
                checkPhonePermissions(number);
            }

        });
        text4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Uri number = Uri.parse("tel:"+numberList.get(3));
                checkPhonePermissions(number);
            }

        });

    }

    private void checkPhonePermissions(Uri number)
    {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL_PHONE_PERMISSION);
        }
        else
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(number);
            startActivity(callIntent);
        }
    }
}