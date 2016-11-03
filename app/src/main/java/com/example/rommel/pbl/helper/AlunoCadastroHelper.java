package com.example.rommel.pbl.helper;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.AlunoDao;
import com.example.rommel.pbl.dao.TurmaDao;
import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 02/10/16.
 */

public class AlunoCadastroHelper {
    private Activity activity;

    private Aluno aluno;
    private AlunoDao alunoDao;
    private TurmaDao turmaDao;
    private ArrayAdapter<Aluno> adapter;
    private ArrayAdapter<Aluno> completar;
    private ArrayList<Aluno> alunosTurma;
    private ArrayList<Aluno> alunos;
    private Disciplina disciplina;

    //Componentes da tela
    private AutoCompleteTextView nomeAluno;
    private Button btnSalvarAluno;
    private ListView listaAlunos;

    public AlunoCadastroHelper(Activity activity){
        this.activity = activity;
        if (activity.getIntent().hasExtra("disciplina")){
            disciplina = (Disciplina) activity.getIntent().getSerializableExtra("disciplina");
        }
        inicializarComponentes();
    }

    private void inicializarComponentes(){
        alunoDao = new AlunoDao(activity.getApplicationContext());
        turmaDao = new TurmaDao(activity.getApplicationContext());
        //alunos = new ArrayList<>();
        nomeAluno = (AutoCompleteTextView) activity.findViewById(R.id.nptNomeAluno);
        btnSalvarAluno = (Button) activity.findViewById(R.id.btnAlunoSalvar);
        listaAlunos = (ListView) activity.findViewById(R.id.listaAlunos);
        acaoBotao(btnSalvarAluno);
        listarAlunos();
        autoCompletar();
    }

    private void listarAlunos(){
        if (disciplina == null) {
            alunosTurma = alunoDao.getAlunos();
        }else {
            alunosTurma = turmaDao.alunosTurma((Integer) activity.getIntent().getSerializableExtra("id"));
            btnSalvarAluno.setText("Adicionar");
            adapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, alunosTurma);
        }
        listaAlunos.setAdapter(adapter);

    }

    private void autoCompletar(){
        alunos = alunoDao.getAlunos();
        completar = new ArrayAdapter<Aluno>(activity, android.R.layout.simple_dropdown_item_1line,alunos);
        nomeAluno.setThreshold(2);
        nomeAluno.setAdapter(completar);
        nomeAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (Aluno a : alunos){
                    if (a.getNome().equals(nomeAluno.getText().toString())){
                        aluno = a;
                    }
                }
            }
        });

    }

    private void acaoBotao (Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disciplina == null){
                    aluno = new Aluno();
                    aluno.setNome(nomeAluno.getText().toString());
                    alunoDao.inserir(aluno);
                }else{
                    turmaDao.inserir(disciplina, aluno, (Integer) activity.getIntent().getSerializableExtra("id"));
                }
                limparCampos();
                listarAlunos();
                //activity.finish();

            }
        });
    }

    private void limparCampos(){
        nomeAluno.setText("");
    }
}
