package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.rtietjen2.trabalhoapp.R;

public class TelaCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);



        Button cadastrarCliente = findViewById(R.id.btCadastrar_cliente);
        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaCliente.this,CadastroClienteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
