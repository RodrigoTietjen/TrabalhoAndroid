package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rtietjen2.trabalhoapp.R;

public class MenuPrincipal extends AppCompatActivity {

    private Button btAgendamento;
    private Button btCliente;
    private Button btProcedimento;
    private Button btProfissional;
    private TextInputEditText etLogin ,etSenha ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        this.btAgendamento = this.findViewById(R.id.btAgendamento);
        this.btCliente = this.findViewById(R.id.btCliente);
        this.btProcedimento = this.findViewById(R.id.btProcedimento);
        this.btProfissional = this.findViewById(R.id.btProfissional);

        this.btAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MenuPrincipal.this,TelaAgendamento.class);
                    startActivity(intent);


                // finish();
                // Toast.makeText(getApplicationContext(),
                //            "Entrar", Toast.LENGTH_SHORT).show();

            }
        });

        this.btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this,TelaCliente.class);
                startActivity(intent);


                // finish();
                // Toast.makeText(getApplicationContext(),
                //            "Entrar", Toast.LENGTH_SHORT).show();

            }
        });

        this.btProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this,TelaProcedimento.class);
                startActivity(intent);


                // finish();
                // Toast.makeText(getApplicationContext(),
                //            "Entrar", Toast.LENGTH_SHORT).show();

            }
        });

        this.btProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this,TelaProfissional.class);
                startActivity(intent);


                // finish();
                // Toast.makeText(getApplicationContext(),
                //            "Entrar", Toast.LENGTH_SHORT).show();

            }
        });

        this.btAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this,TelaAgendamento.class);
                startActivity(intent);


                // finish();
                // Toast.makeText(getApplicationContext(),
                //            "Entrar", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
