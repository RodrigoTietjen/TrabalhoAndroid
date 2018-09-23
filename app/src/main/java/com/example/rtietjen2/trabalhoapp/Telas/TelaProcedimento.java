package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProcedimentoDAO;


import java.util.List;

public class TelaProcedimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_procedimento);

        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(this);
        List<Procedimento> procedimentos = procedimentoDAO.buscaProcedimentos();
        procedimentoDAO.close();

        ListView listaProcedimentos = findViewById(R.id.lista_procedimentos);
        ArrayAdapter<Procedimento> adapter = new ArrayAdapter<Procedimento>(this,android.R.layout.simple_list_item_1, procedimentos);
        listaProcedimentos.setAdapter(adapter);

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
