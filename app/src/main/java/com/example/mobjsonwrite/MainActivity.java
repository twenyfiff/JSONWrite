package com.example.mobjsonwrite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.intellij.lang.annotations.Identifier;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    FileWriter fileWriter;
    FileReader fileReader;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;

    String Name, LName, Email, papildomas;
    String FILENAME = "mobdata.json";
    String space = "\\n";

    EditText editTextTextPersonName;
    EditText editTextTextPersonName2;
    EditText editTextEmail;
    EditText editTextTextPersonName3;
    Button inputButton;

    //private static final String FILE_NAME = "file-name";
    //File file = new File(this.getFilesDir(), FILE_NAME);

    public class CompositionJso extends JSONObject {



        public JSONObject makeJSONObject (String Name, String LName, String Email, String papildomas) {

            JSONObject obj = new JSONObject() ;

            try {
                obj.put("name", Name);
                obj.put("last name", LName);
                obj.put("email", Email);
                obj.put("papildomas", papildomas);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return obj;
        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = (EditText) findViewById(R.id.editTextTextPersonName3) ;
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        inputButton = (Button) findViewById(R.id.inputButton);

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = editTextTextPersonName.getText().toString();
                LName = editTextTextPersonName2.getText().toString();
                Email = editTextEmail.getText().toString();
                papildomas = editTextTextPersonName3.getText().toString();


                //writeToFile(this, "JsonObj.txt", MainActivity.toJsonString());

                //setName();
                //createJSONFolder();
                CompositionJso compositionJso = new CompositionJso();
                JSONObject obj;
                obj = compositionJso.makeJSONObject(Name, LName, Email, papildomas);
                //MyCompositionsListActivity.buildList();

                try {
                    Writer output;
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + FILENAME);
                    //File file = new File("android.resource://"+getPackageName()+"/raw/mobdata.json");
                    /*File file = new File(String.valueOf(getResources().getIdentifier("mobdata",
                            "raw", getPackageName())));*/
                    output = new BufferedWriter(new FileWriter(file, true));
                    output.write(obj.toString());
                    output.close();
                    Toast.makeText(getApplicationContext(), "Composition saved", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
/*
    private static Object toJsonString() {
        return "{\n" +
                "\"Name\":\"Vardas\",\n" +
                "\"Last name\":\"Pavarde\",\n" +
                "\"Email\":\"Elpastas@gmail.com\"\n" +
                "}";
    }

    public static void writeToFile (Context context, String fileName, String str){
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(), 0, str.length());
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }*/
}
