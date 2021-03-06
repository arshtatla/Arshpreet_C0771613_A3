package com.assignment3.arshpreetc0771613a3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {
    private Spinner spinner;
    ToggleButton toggleButton;
    Button button;
    EditText inputName, inputId;
    private ArrayList<Candidate> candidateList;
    ArrayList<Voter> voters;
    private boolean accepted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        voters = new ArrayList<Voter>();
        spinner = findViewById(R.id.spinnerId);
        toggleButton = findViewById(R.id.btntoggle);
        button = findViewById(R.id.btnId);
        inputName = findViewById(R.id.textPersonName);
        inputId = findViewById(R.id.txtID);


        Intent intent = getIntent();
        ArrayList<Candidate> candidates = (ArrayList<Candidate>) intent.getSerializableExtra("candidates");
        candidateList = candidates;

        ArrayAdapter<Candidate> adapter = new ArrayAdapter<Candidate>(this,
                android.R.layout.simple_spinner_item, candidateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputName.getText().toString().isEmpty()){
                    Toast.makeText(VoteActivity.this, "Do not Leave empty name field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(inputId.getText().toString().isEmpty()){
                    Toast.makeText(VoteActivity.this, "Do not leave empty Id field", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Voter V : voters) {
                    if(V.getId() == Integer.parseInt(inputId.getText().toString())){
                        Toast.makeText(VoteActivity.this, "Id already used!!!! Pick New", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!toggleButton.isChecked()){
                    Toast.makeText(VoteActivity.this, "First accept the terms and condition", Toast.LENGTH_SHORT).show();
                    return;
                }

                voters.add(new Voter(Integer.parseInt(inputId.getText().toString()), inputName.getText().toString()));
                int selectedCandidateIndex = spinner.getSelectedItemPosition();
                Candidate selectedCandidate = candidateList.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(VoteActivity.this, "Your vote has been casted !!", Toast.LENGTH_SHORT).show();


            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    toggleButton.setTextOn("Refuse");

                } else {

                    toggleButton.setTextOff("Accept");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(VoteActivity.this, MainActivity.class);
        intent.putExtra("candidates", candidateList);
        startActivity(intent);
    }
}