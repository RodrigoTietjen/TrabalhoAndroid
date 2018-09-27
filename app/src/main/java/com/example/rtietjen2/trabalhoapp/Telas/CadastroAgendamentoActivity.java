package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Agendamento;
import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.AgendamentoDAO;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;

public class CadastroAgendamentoActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "shareData";
    public Cliente cliente = new Cliente();
    public Agendamento agendamento = new Agendamento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agendamento);

        Button btSalvarAgendamento = findViewById(R.id.btSalvarAgendamento);
        Intent intent = getIntent();
        Agendamento agendamento = (Agendamento) intent.getSerializableExtra("agendamento");
        if(agendamento == null){
            this.agendamento = new Agendamento();
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("nome", "");
            editor.putString("telefone", "");
            editor.putString("cliente", "");
            editor.putString("profissional", "");
            editor.putString("procedimento", "");
            editor.apply();
        }
        else{
            this.agendamento = agendamento;
        }
        if(agendamento != null) {
            populaCadastro(agendamento);
        }

        btSalvarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgendamentoDAO agendamentoDao = new AgendamentoDAO(CadastroAgendamentoActivity.this);
                EditText cliente = findViewById(R.id.agendamento_cliente_nome);
                CadastroAgendamentoActivity.this.agendamento.setProcedimento(cliente.getText().toString());

                EditText procedimento = findViewById(R.id.agendamento_procedimento_nome);
                CadastroAgendamentoActivity.this.agendamento.setProcedimento(procedimento.getText().toString());

                EditText profissional = findViewById(R.id.agendamento_profissional_nome);
                CadastroAgendamentoActivity.this.agendamento.setProfissional(profissional.getText().toString());

                if(CadastroAgendamentoActivity.this.cliente.getId() == 0){
                    agendamentoDao.insere(CadastroAgendamentoActivity.this.agendamento);
                }
                else{
                    agendamentoDao.alterar(CadastroAgendamentoActivity.this.agendamento);
                    agendamentoDao.close();
                    CadastroAgendamentoActivity.super.onResume();
                    finish();
                }
                agendamentoDao.buscaAgendamentos();
                agendamentoDao.close();
                finish();
            }
        });

        Button buscaCliente = findViewById(R.id.btBuscar_cliente);
        buscaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAgendamentoActivity.this,ClienteSelecionado.class);
                startActivity(intent);
            }
        });

        Button buscaProcedimento = findViewById(R.id.btBuscar_procedimento);
        buscaProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAgendamentoActivity.this,ProcedimentoSelecionado.class);
                startActivity(intent);
            }
        });

        Button buscaProfissional = findViewById(R.id.btBuscar_profissional);
        buscaProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAgendamentoActivity.this,ProfissionalSelecionado.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String cliente =  prefs.getString("nome", null);
        String procedimento = prefs.getString("procedimento", null);
        String profissional = prefs.getString("profissional", null);

        agendamento.setProcedimento(procedimento);

        agendamento.setCliente(cliente);

        agendamento.setProfissional(profissional);

        populaCadastro(agendamento);
    }

    public void populaCadastro(Agendamento agendamento) {

        EditText campoNomeCliente = findViewById(R.id.agendamento_cliente_nome);
        campoNomeCliente.setText(agendamento.getCliente());

        EditText campoProcedimento = findViewById(R.id.agendamento_procedimento_nome);
        campoProcedimento.setText(agendamento.getProcedimento());

        EditText profissional = findViewById(R.id.agendamento_profissional_nome);
        profissional.setText(agendamento.getProfissional());

    }
}
