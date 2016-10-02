package com.example.rommel.pbl.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.model.Disciplina;
import com.example.rommel.pbl.helper.MainActivityHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainActivityHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MainActivityHelper(this);
        helper.carregarLista();
    }

    @Override
    protected void onResume(){
        super.onResume();
        helper = new MainActivityHelper(this);

        helper.carregarLista();
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        helper = new MainActivityHelper(this);
        helper.contextMenu(menu);
        super.onCreateContextMenu(menu, view, menuInfo);
    }

}
