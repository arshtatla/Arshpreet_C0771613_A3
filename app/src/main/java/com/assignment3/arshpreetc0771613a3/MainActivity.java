package com.assignment3.arshpreetc0771613a3;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Candidate> candidateList;
    private TextView candidateId1, candidateId2, candidateId3;
    private Button btnVote;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidateId1 = findViewById(R.id.candidateId1);
        candidateId2 = findViewById(R.id.candidateId2);
        candidateId3 = findViewById(R.id.candidateId3);

        btnVote = findViewById(R.id.btnVote);

        candidateList = new ArrayList<Candidate>();
        Intent intent = getIntent();

        ArrayList<Candidate> candidates = (ArrayList<Candidate>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateList.add(new Candidate(1,"Jerry Luskey",0));
            candidateList.add(new Candidate(2,"Jag Koner",0));
            candidateList.add(new Candidate(3,"Dominic",0));
        }
        else{
            candidateList = candidates;
        }

        candidateId1.setText(candidateList.get(0).getName()+" : " + candidateList.get(0).getVotes());
        candidateId2.setText(candidateList.get(1).getName()+" : " + candidateList.get(1).getVotes());
        candidateId3.setText(candidateList.get(2).getName()+" : " + candidateList.get(2).getVotes());

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, VoteActivity.class);
                //intent.putExtra("candidates", candidateList);
                //startActivity(intent);
            }
        });


    }
}