package com.example.rommel.pbl.helper;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rommel.pbl.dao.Banco;
import com.example.rommel.pbl.dao.DisciplinaDao;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.view.AlunoCadastro;
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

    //Variáivateveis da classe
    protected static Disciplina disciplina;
    private ArrayList<Disciplina> disciplinas;
    private ArrayAdapter<Disciplina> adapter;
    private DisciplinaDao disciplinaDao;
    private Intent intent;

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

                intent = new Intent(activity, TelaDisciplina.class);
                intent.putExtra("disciplina", disciplina);
                System.out.println("Disciplina Id Intent " + disciplina.getCodigo());
                activity.startActivity(intent);
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

    private void inicializarComponentes(){
        disciplinas = new ArrayList<>();
        disciplinaDao = new DisciplinaDao(activity.getApplicationContext());
        lista = (ListView) activity.findViewById(R.id.listaPrincipal);
        botaoAdicionar = (Button) activity.findViewById(R.id.btnAdicionar);
        acaoBotao(botaoAdicionar);
        activity.registerForContextMenu(lista);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                disciplina = (Disciplina) adapterView.getItemAtPosition(i);
                intent = new Intent(activity, TelaDisciplina.class);
                intent.putExtra("disciplina", disciplina);
                System.out.println(disciplina.getNome());
                return false;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(activity, AlunoCadastro.class);
                activity.startActivity(intent);
            }
        });
    }

    private void acaoBotao(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, TelaDisciplina.class);
                activity.startActivity(intent);

            }
        });
    }
}
