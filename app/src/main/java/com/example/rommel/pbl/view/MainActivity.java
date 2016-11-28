package com.example.rommel.pbl.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    }


    public  MainActivityHelper getMainActivityHelper(){
        return this.helper;
    }
}
