package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rtietjen2.trabalhoapp.Entity.Agendamento;
import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.AgendamentoDAO;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

import java.util.List;

public class TelaAgendamento extends AppCompatActivity {

    private ListView listaAgendamentos;
//    AgendamentoDAO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_agendamento);

        listaAgendamentos = findViewById(R.id.lista_agendamento);

        Button cadastarAgendamento = findViewById(R.id.btCadastrar_agendamento);
        cadastarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaAgendamento.this,CadastroAgendamentoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listaAgendamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Agendamento agendamento = (Agendamento) listaAgendamentos.getItemAtPosition(position);
                Intent intentCadastroProfissional = new Intent(TelaAgendamento.this,CadastroAgendamentoActivity.class);
                intentCadastroProfissional.putExtra("agendamento",agendamento);

                startActivity(intentCadastroProfissional);
                finish();

            }
        });
        registerForContextMenu(listaAgendamentos);
    }
    private void carregarListaAgendamento() {
        AgendamentoDAO procedimentoDAO = new AgendamentoDAO(this);
        List<Agendamento> agendamentos = procedimentoDAO.buscaAgendamentos();
        procedimentoDAO.close();

        ArrayAdapter<Agendamento> adapter = new ArrayAdapter<Agendamento>(this, android.R.layout.simple_list_item_1, agendamentos);
        listaAgendamentos.setAdapter(adapter);
    }
}



