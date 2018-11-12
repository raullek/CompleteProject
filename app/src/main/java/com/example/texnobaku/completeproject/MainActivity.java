package com.example.texnobaku.completeproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name_edit_text, password_edit_text;
    Button button;
    ArrayList<User> users= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_edit_text= findViewById(R.id.name_et);
        password_edit_text= findViewById(R.id.password_et);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String  edit_name = name_edit_text.getText().toString();
               int edit_password= Integer.parseInt(password_edit_text.getText().toString());

               if (edit_name.isEmpty()|| edit_name.length()<=0){
                   Toast.makeText(getApplicationContext(),"Please fill the name field",Toast.LENGTH_SHORT).show();
               }


               else {

                   for (User user : users)
                       if (edit_name.matches(user.getName()) && edit_password) {

                           Intent intent = new Intent(this, SecondActivity.class);
                           startActivity(intent);
                       }


               }


            }
        });


    }




    public String loadJsonFromAssets() {
        String json = null;

        try {
            InputStream inputStream = this.getAssets().open("fake_authorization.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json= new String(buffer,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void parsingIntoObjects(){

        try{
            JSONObject object = new JSONObject(loadJsonFromAssets());
            JSONArray jsonArray = object.getJSONArray("users");



            for (int i =0 ; i <jsonArray.length();i++){
                User user = new User();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name_value = jsonObject.getString("user_name");
                int password_value = jsonObject.getInt("user_password");
                user.setName(name_value);
                user.setPassword(password_value);
                users.add(user);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }







}
