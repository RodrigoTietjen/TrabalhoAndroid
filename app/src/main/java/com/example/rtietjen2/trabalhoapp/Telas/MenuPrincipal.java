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
    private TextInputEditText etLogin ,etSenha ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        this.btAgendamento = this.findViewById(R.id.btAgendamento);

        this.btAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();


                    Intent intent = new Intent(MenuPrincipal.this,TelaAgendamento.class);
                    startActivity(intent);


                // finish();
                // Toast.makeText(getApplicationContext(),
                //            "Entrar", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
