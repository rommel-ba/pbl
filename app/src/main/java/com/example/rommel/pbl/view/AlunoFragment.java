package com.example.rommel.pbl.view;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.AlunoDao;
import com.example.rommel.pbl.dao.TurmaDao;
import com.example.rommel.pbl.model.Aluno;
import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

public class AlunoFragment extends Fragment {
    //Componentes da tela
    private AutoCompleteTextView nomeAluno;
    private Button btnSalvarAluno;
    private ListView listaAlunos;

    private Aluno aluno;
    private AlunoDao alunoDao;
    private TurmaDao turmaDao;
    private ArrayAdapter<Aluno> adapter;
    private ArrayAdapter<Aluno> completar;
    private ArrayList<Aluno> alunosTurma;
    private ArrayList<Aluno> alunos;
    private Disciplina disciplina;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aluno_cadastro, container, false);
        nomeAluno = (AutoCompleteTextView) view.findViewById(R.id.nptNomeAluno);
        btnSalvarAluno = (Button) view.findViewById(R.id.btnAlunoSalvar);
        listaAlunos = (ListView) view.findViewById(R.id.listaAlunos);
        inicializarComponentes();
        return view;
    }

    private void inicializarComponentes(){
        alunoDao = new AlunoDao(getActivity().getApplicationContext());
        turmaDao = new TurmaDao(getActivity().getApplicationContext());
        alunos = new ArrayList<>();
        if (getArguments() != null)
            disciplina = (Disciplina) getArguments().getSerializable("disciplina");
        acaoBotao(btnSalvarAluno);
        listarAlunos();
        autoCompletar();
    }

    private void listarAlunos(){
        if (disciplina == null) {
            alunosTurma = alunoDao.getAlunos();
        }else {
            alunosTurma = turmaDao.alunosTurma((Integer) getArguments().getSerializable("id"));
            btnSalvarAluno.setText("Adicionar");
            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, alunosTurma);
        }
        listaAlunos.setAdapter(adapter);

    }

    private void autoCompletar(){
        alunos = alunoDao.getAlunos();
        completar = new ArrayAdapter<Aluno>(getActivity(), android.R.layout.simple_dropdown_item_1line,alunos);
        nomeAluno.setThreshold(2);
        nomeAluno.setAdapter(completar);
        nomeAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (Aluno a : alunos){
                    if (a.getNome().equals(nomeAluno.getText().toString())){
                        aluno = a;
                        System.out.println("Autocompletar " + aluno.getId() + " " +aluno.getNome());
                    }
                }
            }
        });

    }

    private void acaoBotao (Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(aluno.getId() + " " + aluno.getNome());
                if (disciplina == null) {
                    aluno = new Aluno();
                    aluno.setNome(nomeAluno.getText().toString());
                    alunoDao.inserir(aluno);
                } else {
                    turmaDao.inserir(disciplina, aluno, (Integer) getArguments().getSerializable("id"));
                    System.out.println("Cadastrando aluno na turma");
                }
                limparCampos();
                listarAlunos();
            }
        });

    }
    private void limparCampos(){
        nomeAluno.setText("");
    }
}
