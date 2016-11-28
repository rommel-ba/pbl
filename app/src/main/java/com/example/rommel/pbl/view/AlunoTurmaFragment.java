package com.example.rommel.pbl.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by rommel on 20/11/16.
 */

public class AlunoTurmaFragment extends Fragment {

    private AutoCompleteTextView nome;
    private Button adicionar;
    private ListView listaAlunos;

    private TurmaDao turmaDao;
    private AlunoDao alunoDao;
    private Aluno aluno;
    private ArrayList<Aluno> alunos;
    private Disciplina disciplina;
    private ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aluno_disciplina, container, false);
        nome = (AutoCompleteTextView) view.findViewById(R.id.nptNomeAluno);
        adicionar = (Button) view.findViewById(R.id.btnAlunoAdicionar);
        listaAlunos = (ListView) view.findViewById(R.id.listaAlunosTurma);
        inicializarComponentes();
        return view;
    }

    private void inicializarComponentes(){
        alunoDao = new AlunoDao(getActivity().getApplicationContext());
        turmaDao = new TurmaDao(getActivity().getApplicationContext());
        alunos = new ArrayList<>();
        if (getArguments() != null)
            disciplina = (Disciplina) getArguments().getSerializable("disciplina");
        acaoBotao(adicionar);
        listarAlunos();
        autoCompletar();
    }

    private void listarAlunos(){
            alunos = turmaDao.alunosTurma((Integer) getArguments().getSerializable("id"));
            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

    }

    private void autoCompletar(){
        alunos = alunoDao.getAlunos();
        adapter = new ArrayAdapter<Aluno>(getActivity(), android.R.layout.simple_dropdown_item_1line,alunos);
        nome.setThreshold(2);
        nome.setAdapter(adapter);
        nome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (Aluno a : alunos){
                    if (a.getNome().equals(nome.getText().toString())){
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
                turmaDao.inserir(disciplina, aluno, (Integer) getArguments().getSerializable("id"));
                System.out.println("Cadastrando aluno na turma");
                limparCampos();
                listarAlunos();
            }
        });

    }
    private void limparCampos(){
        nome.setText("");
    }
}
