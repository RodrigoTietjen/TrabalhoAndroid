package com.example.rtietjen2.trabalhoapp.Telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.R;

public class CadastroProfissionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional);

        Button salvarCadProfissional = findViewById(R.id.btSalvarProfissional);
        salvarCadProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroProfissionalActivity.this, "Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
