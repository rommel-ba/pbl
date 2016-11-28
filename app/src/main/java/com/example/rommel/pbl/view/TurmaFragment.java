package com.example.rommel.pbl.view;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.TurmaDao;
import com.example.rommel.pbl.helper.MainActivityHelper;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.model.Turma;

import java.util.ArrayList;

public class TurmaFragment extends Fragment {

    private TurmaDao turmaDao;
    private ArrayList<Turma> turmas;
    private ArrayAdapter adapter;
    private MainActivityHelper helper;
    //Componentes Gráficos
    private Button btnNovaTurma;
    private ListView lstTurmas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turma, container, false);
        btnNovaTurma = (Button) view.findViewById(R.id.btnNovaTurma);
        lstTurmas = (ListView) view.findViewById(R.id.lstTurmas);
        acaoLista();
        if (getArguments() == null){
            btnNovaTurma.setEnabled(false);
            btnNovaTurma.setVisibility(View.INVISIBLE);
        }else {
            acaoBotao();
        }
        inicializarComponentes();
        listarTurmas();
        return view;
    }

    private void acaoBotao(){
        final Disciplina disciplina = (Disciplina) getArguments().getSerializable("disciplina");
        btnNovaTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlunoTurmaFragment fragment = new AlunoTurmaFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("id",turmaDao.getIdTurma());
                bundle.putSerializable("disciplina", disciplina);
                System.out.println("Nova turma para a disciplina " + disciplina.getNome());
                helper.setFragment(fragment);
            }
        });
    }

    private void listarTurmas(){
        if (getArguments() != null)
            turmas = turmaDao.listaTurmas((Disciplina) getArguments().getSerializable("disciplina"));
        turmas = turmaDao.listaTodasTurmas();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, turmas);
        lstTurmas.setAdapter(adapter);
        System.out.println("Quantidade de turmas cadastradas = " + turmas.size());
    }
    private void acaoLista(){
        lstTurmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Turma turma = (Turma) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), AlunoFragment.class);
                intent.putExtra("id", turma.getIdTurma());
                intent.putExtra("disciplina", turma.getDisciplina());
                getActivity().startActivity(intent);
            }
        });
    }

    private void inicializarComponentes(){
        turmaDao = new TurmaDao(getActivity().getApplicationContext());
        helper = ((MainActivity) getActivity()).getMainActivityHelper();
    }
}
