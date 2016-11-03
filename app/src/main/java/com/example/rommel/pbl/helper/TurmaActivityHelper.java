package com.example.rommel.pbl.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.TurmaDao;
import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.view.AlunoCadastro;
import com.example.rommel.pbl.view.TurmaActivity;

/**
 * Created by rommel on 10/10/16.
 */

public class TurmaActivityHelper {

    private TurmaActivity activity;
    private TurmaDao turmaDao;

    //Componentes Gráficos
    private Button btnNovaTurma;
    private ListView lstTurmas;

    public TurmaActivityHelper (TurmaActivity activity){
        this.activity = activity;
        inicializarComponentes();
    }

    private void inicializarComponentes(){
        btnNovaTurma = (Button) activity.findViewById(R.id.btnNovaTurma);
        lstTurmas = (ListView) activity.findViewById(R.id.lstTurmas);
        turmaDao = new TurmaDao(activity);
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
                activity.startActivity(intent);
            }
        });

    }
}
