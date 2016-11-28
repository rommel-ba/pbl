package com.example.rommel.pbl.helper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rommel.pbl.view.AlunoFragment;
import com.example.rommel.pbl.view.HomeFragment;
import com.example.rommel.pbl.view.MainActivity;
import com.example.rommel.pbl.R;
import com.example.rommel.pbl.view.TurmaFragment;

/**
 * Created by rommel on 01/10/16.
 */

public class MainActivityHelper {
    private MainActivity activity;
    private ListView menuEsquerdo;

    public MainActivityHelper(MainActivity activity) {
        this.activity = activity;

        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentos, new HomeFragment());
        transaction.commit();

        inicializarComponentes();
    }

    private void inicializarComponentes(){
        menuEsquerdo = (ListView) activity.findViewById(R.id.menu_lateral_esquerdo);
        setMenuEsquerdo();

    }

    public void setFragment(Fragment fragment){
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentos, fragment);
        transaction.addToBackStack("pilha");
        transaction.commit();
    }
    private void setMenuEsquerdo(){
        String  menu[] ={"Alunos", "Disciplinas", "Turmas"};
        ArrayAdapter<String> esquerdo = new ArrayAdapter<String>(activity.getApplication(), android.R.layout.simple_list_item_1, menu);
        menuEsquerdo.setAdapter(esquerdo);
        setAcaoMenuEsquerdo();
    }

    private void setFragmentInterno(Fragment fragment){
        FragmentManager manager = activity.getFragmentManager();
        manager.popBackStack();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentos, fragment);
        transaction.addToBackStack("pilha");
        transaction.commit();
    }

    private void setAcaoMenuEsquerdo (){
        menuEsquerdo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(activity.getApplicationContext(), "Alunos", Toast.LENGTH_SHORT).show();
                        AlunoFragment alunoFragment = new AlunoFragment();
                        setFragmentInterno(alunoFragment);
                        break;
                    case 1:
                        Toast.makeText(activity.getApplicationContext(), "Disciplinas", Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        Toast.makeText(activity.getApplicationContext(), "Turmas", Toast.LENGTH_SHORT).show();
                        TurmaFragment turmaFragment = new TurmaFragment();
                        setFragmentInterno(turmaFragment);
                        break;
                }
            }
        });
    }
}
