package com.example.rommel.pbl.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.dao.DisciplinaDao;
import com.example.rommel.pbl.helper.MainActivityHelper;
import com.example.rommel.pbl.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 06/11/16.
 */

public class HomeFragment extends Fragment {

    private DisciplinaDao disciplinaDao;
    private ArrayList<Disciplina> disciplinas;
    private ArrayAdapter<Disciplina> adapter;
    private Disciplina disciplina;
    private ListView lista;
    private Button botaoAdicionar;
    private Intent intent;

    private MainActivityHelper mainActivityHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        lista = (ListView) view.findViewById(R.id.listaTurmas);
        botaoAdicionar = (Button) view.findViewById(R.id.btnAdicionar);
        inicializarComponentes();

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        contextMenu(menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void contextMenu (ContextMenu menu){
        MenuItem atualizar = menu.add("Atualizar");
        atualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                intent = new Intent(getActivity(), DisciplinaActivity.class);
                intent.putExtra("disciplina", disciplina);
                System.out.println("Disciplina Id Intent " + disciplina.getCodigo());
                getActivity().startActivity(intent);

                return false;
            }
        });
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                disciplinaDao.deletar(disciplina);
                carregarLista();
                return false;
            }
        });
    }
    public void carregarLista(){
        disciplinas = disciplinaDao.getDisciplinas();
        adapter = new ArrayAdapter<Disciplina>(getActivity(),android.R.layout.simple_list_item_1, disciplinas);
        lista.setAdapter(adapter);
    }

    private void inicializarComponentes(){
        disciplinaDao = new DisciplinaDao(getActivity().getApplicationContext());
        Activity activity = getActivity();
        if (activity instanceof MainActivity){
            System.out.println("É uma instância da atividade principal");
            mainActivityHelper = ((MainActivity) activity).getMainActivityHelper();
        }else{
            System.out.println(activity.getClass().getName());
        }
        acaoBotao();
        acaoLista();
        carregarLista();
        //setMenuEsquerdo();
        registerForContextMenu(lista);
    }




    private void acaoBotao(){
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"botao clicado", Toast.LENGTH_SHORT).show();
                System.out.println("Botão clicado mas não aparece porra nenhuma");

                intent = new Intent(getActivity(), DisciplinaActivity.class);
                getActivity().startActivity(intent);

            }
        });
    }

    private void acaoLista(){
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                disciplina = (Disciplina) adapterView.getItemAtPosition(i);

                intent = new Intent(getActivity(), DisciplinaActivity.class);
                intent.putExtra("disciplina", disciplina);

                return false;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                disciplina = (Disciplina) adapterView.getItemAtPosition(i);
                TurmaFragment fragment = new TurmaFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("disciplina", disciplina);
                fragment.setArguments(bundle);
                mainActivityHelper.setFragment(fragment);
            }
        });
    }


}
