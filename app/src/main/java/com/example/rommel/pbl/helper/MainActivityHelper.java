package com.example.rommel.pbl.helper;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rommel.pbl.dao.DisciplinaDao;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.view.MainActivity;
import com.example.rommel.pbl.R;
import com.example.rommel.pbl.view.TelaDisciplina;

import java.util.ArrayList;

/**
 * Created by rommel on 01/10/16.
 */

public class MainActivityHelper {
    private MainActivity activity;
    //Componentes Gráficos
    private ListView lista;
    private Button botaoAdicionar;

    //Variáveis da classe
    private Disciplina disciplina;
    private ArrayList<Disciplina> disciplinas;
    private ArrayAdapter<Disciplina> adapter;
    private DisciplinaDao disciplinaDao;

    public MainActivityHelper(MainActivity activity) {
        this.activity = activity;
        inicializarComponentes();
        carregarLista();
    }

    public void carregarLista(){
        disciplinas = disciplinaDao.getDisciplinas();
        adapter = new ArrayAdapter<Disciplina>(activity,android.R.layout.simple_list_item_1, disciplinas);
        lista.setAdapter(adapter);
    }


    public void contextMenu (ContextMenu menu){
        MenuItem atualizar = menu.add("Atualizar");
        atualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(activity, TelaDisciplina.class);
                intent.putExtra("disciplina", disciplina);
                activity.startActivity(intent);
                return false;
            }
        });
    }

    private void inicializarComponentes(){
        disciplinas = new ArrayList<>();
        disciplinaDao = new DisciplinaDao(activity);
        lista = (ListView) activity.findViewById(R.id.listaPrincipal);
        botaoAdicionar = (Button) activity.findViewById(R.id.btnAdicionar);
        acaoBotao(botaoAdicionar);
        activity.registerForContextMenu(lista);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                disciplina = (Disciplina) adapterView.getItemAtPosition(i);
                //System.out.println(disciplina.getNome());
                return false;
            }
        });
    }

    private void acaoBotao(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TelaDisciplina.class);
                activity.startActivity(intent);

            }
        });
    }
}
