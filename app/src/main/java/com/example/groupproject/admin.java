package com.example.groupproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    private EditText userFirstName, userSurname, username, userPassword, userRole;
    private Button buttonInsert, buttonUpdate, buttonDelete, button4;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        userFirstName = findViewById(R.id.regUserFirstName);
        userSurname = findViewById(R.id.regUserSurname);
        username = findViewById(R.id.regUsername);
        userPassword = findViewById(R.id.regUserPassword);
        userRole = findViewById(R.id.regUserRole);

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate =findViewById(R.id.buttoUpdate);
        buttonDelete =findViewById(R.id.buttonDelete);
        button4=findViewById(R.id.button4);

        DB=new DatabaseHelper(this);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameTxt = userFirstName.getText().toString();
                String surnameTxt = userSurname.getText().toString();
                String usernameTxt = username.getText().toString();
                String passwordTxt = userPassword.getText().toString();
                String roleTxt = userRole.getText().toString();

                Boolean checkinsertdata = DB.insertUserData(firstNameTxt, surnameTxt, usernameTxt, passwordTxt, roleTxt);

                if(checkinsertdata==true)
                    Toast.makeText(admin.this,"New entry inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(admin.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameTxt = userFirstName.getText().toString();
                String surnameTxt = userSurname.getText().toString();
                String usernameTxt = username.getText().toString();
                String passwordTxt = userPassword.getText().toString();
                String roleTxt = userRole.getText().toString();

                boolean
                checkupdatedata=DB.updateUserData(firstNameTxt, surnameTxt, usernameTxt, passwordTxt, roleTxt);

                if(checkupdatedata==true)
                    Toast.makeText(admin.this," Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(admin.this," Entry Not Updated",Toast.LENGTH_SHORT).show();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();

                Boolean checkdeletedata=DB.deleteUserData(usernameTxt);

                if(checkdeletedata==true)
                    Toast.makeText(admin.this," Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(admin.this," Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res=DB.getUserData();
                if (res.getCount()==0){
                    Toast.makeText(admin.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : "+res.getString(0)+"\n");
                    buffer.append("idno : "+res.getString(1)+"\n");
                    buffer.append("Class : "+res.getString(2)+"\n");
                    buffer.append("Username : "+res.getString(3)+"\n");
                    buffer.append("Password : "+res.getString(4)+"\n\n");
                    buffer.append("role : "+res.getString(5)+"\n\n");
                }

                AlertDialog.Builder builder=new
                AlertDialog.Builder(admin.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }

}