package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProcedimentoDAO;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

import java.util.List;

public class ProfissionalSelecionado extends AppCompatActivity {

    private ListView selecionaProfissional;
    public static final String PREFS_NAME = "shareData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional_selecionado);

        selecionaProfissional = findViewById(R.id.seleciona_profissional);
        carregarListaProcedimentos();


        selecionaProfissional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Profissional profissional = (Profissional) selecionaProfissional.getItemAtPosition(position);

                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("profissional", profissional.getNome());
                editor.apply();
                finish();

            }
        });
    }

    private void carregarListaProcedimentos() {
        ProfissionalDAO procedimentoDAO = new ProfissionalDAO(this);
        List<Profissional> profissionais = procedimentoDAO.buscaProfissionais();
        procedimentoDAO.close();

        ArrayAdapter<Profissional> adapter = new ArrayAdapter<Profissional>(this, android.R.layout.simple_list_item_1, profissionais);
        selecionaProfissional.setAdapter(adapter);
    }

}
