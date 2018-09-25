package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rtietjen2.trabalhoapp.R;

public class TelaAgendamento extends AppCompatActivity {

    private ListView listaAgendamentos;

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
        registerForContextMenu(listaAgendamentos);
    }
}
