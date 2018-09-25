package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProcedimentoDAO;


import java.util.List;

public class TelaProcedimento extends AppCompatActivity {

    private ListView listaProcedimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_procedimento);

        listaProcedimentos = findViewById(R.id.lista_procedimentos);
        carregarListaProcedimento();

        listaProcedimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Procedimento procedimento = (Procedimento) listaProcedimentos.getItemAtPosition(position);
                Intent intentCadastroProcedimento = new Intent(TelaProcedimento.this,CadastroProcedimentoActivity.class);
                intentCadastroProcedimento.putExtra("procedimento",procedimento);

                startActivity(intentCadastroProcedimento);
                finish();

            }
        });

        Button cadastarProcedimento = findViewById(R.id.btCadastrar_procedimento);
        cadastarProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaProcedimento.this,CadastroProcedimentoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerForContextMenu(listaProcedimentos);
    }

    private void carregarListaProcedimento() {
        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(this);
        List<Procedimento> procedimentos = procedimentoDAO.buscaProcedimentos();
        procedimentoDAO.close();


        ArrayAdapter<Procedimento> adapter = new ArrayAdapter<Procedimento>(this,android.R.layout.simple_list_item_1, procedimentos);
        listaProcedimentos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Procedimento procedimento = (Procedimento) listaProcedimentos.getItemAtPosition(info.position);

                ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(TelaProcedimento.this);
                procedimentoDAO.deletar(procedimento);
                procedimentoDAO.close();
                carregarListaProcedimento();
                return false;
            }
        });

    }
}
