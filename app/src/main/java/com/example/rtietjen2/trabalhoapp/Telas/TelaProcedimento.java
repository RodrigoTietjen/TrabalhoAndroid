package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.R;

public class TelaProcedimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_procedimento);

        Button cadastarProcedimento = findViewById(R.id.btCadastrar_procedimento);
        cadastarProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaProcedimento.this,CadastroProcedimentoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
