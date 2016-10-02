package com.example.rommel.pbl.helper;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.DisciplinaDao;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.view.TelaDisciplina;

/**
 * Created by rommel on 01/10/16.
 */

public class TelaDisciplinaHelper {
    private Activity activity;
    private Disciplina disciplina;

    private EditText nptDisciplina;
    private Button btnSalvar;


    public TelaDisciplinaHelper(TelaDisciplina activity){
        this.activity = activity;
        inicializarComponentes();
    }

    public TelaDisciplinaHelper(TelaDisciplina telaDisciplina, Disciplina disciplina){
        this.activity = telaDisciplina;
        this.disciplina = disciplina;
    }

    private void inicializarComponentes(){
        nptDisciplina = (EditText) activity.findViewById(R.id.nptDisciplina);
        btnSalvar = (Button) activity.findViewById(R.id.btnSalvar);
        acaoBotao(btnSalvar);
        if(disciplina != null) {
            nptDisciplina.setText(disciplina.getNome());
            System.out.println("Disciplina não é nulo");
        }
    }

    private void acaoBotao(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(activity.getApplicationContext(), "Botão Salvar", Toast.LENGTH_LONG);
                Disciplina disciplina = new Disciplina();
                disciplina.setNome(nptDisciplina.getText().toString());
                DisciplinaDao disciplinaDao = new DisciplinaDao(activity.getApplicationContext());
                disciplinaDao.inserir(disciplina);
                activity.finish();
            }
        });
    }
}
