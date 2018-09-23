package com.example.rtietjen2.trabalhoapp.Telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.R;

public class CadastroClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        Button salvarCadCliente = findViewById(R.id.btSalvarCliente);
        salvarCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroClienteActivity.this, "Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
