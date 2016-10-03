package com.example.rommel.pbl.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rommel.pbl.R;
import com.example.rommel.pbl.helper.AlunoCadastroHelper;

public class AlunoCadastro extends AppCompatActivity {
    private AlunoCadastroHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_cadastro);
        helper = new AlunoCadastroHelper(this);
    }
}
