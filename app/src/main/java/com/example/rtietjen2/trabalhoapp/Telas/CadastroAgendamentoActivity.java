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
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;

public class CadastroAgendamentoActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "shareData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agendamento);



        Button buscaCliente = findViewById(R.id.btBuscar_cliente);
        buscaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAgendamentoActivity.this,ClienteSelecionado.class);
                startActivity(intent);
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String ola =  prefs.getString("nome", null);

                Cliente cliente = new Cliente();
                cliente.setNome(ola);

                Agendamento agendamento = new Agendamento();
                agendamento.setCliente(cliente);

                populaCadastro(agendamento);

            }
        });
    }

    public void populaCadastro(Agendamento agendamento) {

        EditText campoNomeCliente = findViewById(R.id.agendamento_cliente_nome);
        campoNomeCliente.setText(agendamento.getCliente().getNome());

//        EditText campoNomeProfissional = findViewById(R.id.agendamento_proprofissional_nome);
//        campoNomeCliente.setText(agendamento.getProfissional().getNome());
//
//        EditText campoNomeProcedimento = findViewById(R.id.agendamento_procedimento_nome);
//        campoNomeCliente.setText(agendamento.getProcedimentos().getNome());

    }
}
