package com.example.groupproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class tAssignments extends Fragment {
    private Button buttonSubmitAssmntDetails, buttonNewAssmnt;
    private EditText assmntTitle, subjectName, assmntDescription;

    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = new DatabaseHelper(getActivity());
        List<String> values = new ArrayList<String>();

        // Get values from database
        Cursor res = db.getAssignmentData();
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "No Entries Exist", Toast.LENGTH_SHORT).show();
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Assignment Title: " + res.getString(1) + "\n");
            buffer.append("Subject: " + res.getString(2) + "\n");
            buffer.append("Details: " + res.getString(3) + "\n");

            values.add(buffer.toString());
        }

        return inflater.inflate(R.layout.tassignments,container,false);
    }
}
