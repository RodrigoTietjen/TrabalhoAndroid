package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;
import com.example.rtietjen2.trabalhoapp.dao.ProcedimentoDAO;

import java.util.List;

public class ProcedimentoSelecionado extends AppCompatActivity {

    private ListView selecionaProcedimento;
    public static final String PREFS_NAME = "shareData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimento_selecionado);

        selecionaProcedimento = findViewById(R.id.seleciona_procedimento);
        carregarListaProcedimentos();


        selecionaProcedimento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Procedimento cliente = (Procedimento) selecionaProcedimento.getItemAtPosition(position);

                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("procedimento", cliente.getNome());
                editor.apply();
                finish();

            }
        });
    }

    private void carregarListaProcedimentos() {
        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(this);
        List<Procedimento> procedimentos = procedimentoDAO.buscaProcedimentos();
        procedimentoDAO.close();

        ArrayAdapter<Procedimento> adapter = new ArrayAdapter<Procedimento>(this, android.R.layout.simple_list_item_1, procedimentos);
        selecionaProcedimento.setAdapter(adapter);
    }
}
