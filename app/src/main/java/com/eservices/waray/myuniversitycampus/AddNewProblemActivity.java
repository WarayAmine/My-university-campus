package com.eservices.waray.myuniversitycampus;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.model.ProblemViewModel;

import java.util.Date;

public class AddNewProblemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    Button button;
    TextView textViewAddress;
    TextView textViewDescription;
    Problem.ProblemType problemType;
    Problem problem;
    ProblemViewModel problemViewModel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_problem);

        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);

        intent = new Intent(this,ProblemListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<Problem.ProblemType>(this, android.R.layout.simple_dropdown_item_1line, Problem.ProblemType.values()));
        spinner.setOnItemSelectedListener(this);

        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        button = (Button) findViewById(R.id.buttonCreate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problem = new Problem(textViewAddress.getText().toString(),textViewDescription.getText().toString(),500,400,problemType,false,new Date(),new Date());
                problemViewModel.insertProblem(problem);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        problemType = Problem.ProblemType.getProblemType(position);
        Toast.makeText(getApplicationContext(),problemType.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
