package com.example.rommel.pbl.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.TurmaDao;
import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.model.Turma;
import com.example.rommel.pbl.view.AlunoCadastro;
import com.example.rommel.pbl.view.TurmaActivity;

import java.util.ArrayList;

/**
 * Created by rommel on 10/10/16.
 */

public class TurmaActivityHelper {

    private TurmaActivity activity;
    private TurmaDao turmaDao;
    private ArrayList<Turma> turmas;
    private ArrayAdapter adapter;

    //Componentes Gráficos
    private Button btnNovaTurma;
    private ListView lstTurmas;

    public TurmaActivityHelper (TurmaActivity activity){
        this.activity = activity;
        inicializarComponentes();
    }

    private void listarTurmas(){
        turmas = turmaDao.listaTurmas((Disciplina) activity.getIntent().getSerializableExtra("disciplina"));
        adapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, turmas);
        lstTurmas.setAdapter(adapter);
    }

    private void inicializarComponentes(){
        btnNovaTurma = (Button) activity.findViewById(R.id.btnNovaTurma);
        lstTurmas = (ListView) activity.findViewById(R.id.lstTurmas);
        turmaDao = new TurmaDao(activity);
        listarTurmas();
        acaoBotao();

    }

    private void acaoBotao(){
        final Disciplina disciplina = (Disciplina) activity.getIntent().getSerializableExtra("disciplina");
        btnNovaTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AlunoCadastro.class);
                intent.putExtra("disciplina", disciplina);
                intent.putExtra("id", turmaDao.getIdTurma());
                System.out.println(intent.getSerializableExtra("id"));
                activity.startActivity(intent);
            }
        });
    }
}
