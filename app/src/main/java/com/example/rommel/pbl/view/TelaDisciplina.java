package com.example.rommel.pbl.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.helper.TelaDisciplinaHelper;
import com.example.rommel.pbl.model.Disciplina;

public class TelaDisciplina extends AppCompatActivity {
    private TelaDisciplinaHelper telaDisciplinaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina);
        if (this.getIntent().hasExtra("disciplina"))
            telaDisciplinaHelper = new TelaDisciplinaHelper(this, (Disciplina) getIntent().getSerializableExtra("disciplina"));
        else
            telaDisciplinaHelper = new TelaDisciplinaHelper(this);
    }
}
