package com.example.rommel.pbl.helper;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.AlunoDao;
import com.example.rommel.pbl.dao.Banco;
import com.example.rommel.pbl.model.Aluno;

import java.util.ArrayList;

/**
 * Created by rommel on 02/10/16.
 */

public class AlunoCadastroHelper {
    private Activity activity;

    private Aluno aluno;
    private AlunoDao alunoDao;
    private ArrayAdapter<Aluno> adapter;
    private ArrayList<Aluno> alunos;

    //Componentes da tela
    private EditText nomeAluno;
    private Button btnSalvarAluno;
    private ListView listaAlunos;

    public AlunoCadastroHelper(Activity activity){
        this.activity = activity;
        inicializarComponentes();
    }

    private void inicializarComponentes(){
        alunoDao = new AlunoDao(activity.getApplicationContext());
        nomeAluno = (EditText) activity.findViewById(R.id.nptNomeAluno);
        btnSalvarAluno = (Button) activity.findViewById(R.id.btnAlunoSalvar);
        listaAlunos = (ListView) activity.findViewById(R.id.listaAlunos);
        acaoBotao(btnSalvarAluno);
        listarAlunos();
    }

    private void listarAlunos(){
        alunos = alunoDao.getAlunos();
        adapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

    }

    private void acaoBotao (Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast torrada = Toast.makeText(activity, "Teste Botão", Toast.LENGTH_SHORT);
                torrada.show();


                aluno = new Aluno();
                aluno.setNome(nomeAluno.getText().toString());
                System.out.println("AlunoCadastroHelper nome do aluno: " + aluno.getNome());
                alunoDao.inserir(aluno);
                listarAlunos();
                //activity.finish();

            }
        });
    }
}
