package com.example.rommel.pbl.helper;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.Banco;
import com.example.rommel.pbl.dao.DisciplinaDao;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.view.DisciplinaActivity;

/**
 * Created by rommel on 01/10/16.
 */

public class TelaDisciplinaHelper {
    private Activity activity;
    private Disciplina disciplina;
    private DisciplinaDao disciplinaDao;

    private EditText nptDisciplina;
    private Button btnSalvar;


    public TelaDisciplinaHelper(DisciplinaActivity activity){
        this.activity = activity;
        inicializarComponentes();
    }

    public TelaDisciplinaHelper(DisciplinaActivity disciplinaActivity, Disciplina disciplina){
        this.activity = disciplinaActivity;
        this.disciplina = disciplina;
        inicializarComponentes();
    }

    private void inicializarComponentes(){
        disciplinaDao = new DisciplinaDao(activity.getApplicationContext());
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
                if(disciplina == null)
                    disciplina = new Disciplina();
                disciplina.setNome(nptDisciplina.getText().toString());
                Banco banco = new Banco(activity.getApplicationContext());
                disciplinaDao.inserir(disciplina);
                activity.finish();
            }
        });
    }
}
