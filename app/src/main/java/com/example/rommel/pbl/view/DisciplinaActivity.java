package com.example.rommel.pbl.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.helper.TelaDisciplinaHelper;
import com.example.rommel.pbl.model.Disciplina;

public class DisciplinaActivity extends AppCompatActivity {
    private TelaDisciplinaHelper telaDisciplinaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_disciplina);
        Intent intent = this.getIntent();

        Disciplina disciplina = (Disciplina) intent.getSerializableExtra("disciplina");
        telaDisciplinaHelper = new TelaDisciplinaHelper(this, disciplina);
    }
}
