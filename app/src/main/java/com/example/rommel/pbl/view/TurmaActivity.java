package com.example.rommel.pbl.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.helper.TurmaActivityHelper;

public class TurmaActivity extends AppCompatActivity {
    private TurmaActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma);
        helper = new TurmaActivityHelper(this);
    }
}
