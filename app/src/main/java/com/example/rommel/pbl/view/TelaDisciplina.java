package com.example.rommel.pbl.view;

import android.content.Intent;
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
        Intent intent = this.getIntent();

        Disciplina disciplina = (Disciplina) intent.getSerializableExtra("disciplina");
        System.out.println("Tela Disciplina - disciplina.getId " + disciplina.getCodigo());
        telaDisciplinaHelper = new TelaDisciplinaHelper(this, disciplina);
    }
}
