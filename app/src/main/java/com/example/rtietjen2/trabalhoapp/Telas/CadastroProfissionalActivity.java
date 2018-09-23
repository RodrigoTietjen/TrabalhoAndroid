package com.example.rtietjen2.trabalhoapp.Telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

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

                Profissional profissional = new Profissional();
                ProfissionalDAO profissionalDAO = new ProfissionalDAO(CadastroProfissionalActivity.this);

                EditText campoNomeProfissional = findViewById(R.id.profissional_nome);
                profissional.setNome(campoNomeProfissional.getText().toString());

                EditText campoEspecialidadeProfissional = findViewById(R.id.profissional_especialidade);
                String especialidadeProfissional = campoEspecialidadeProfissional.getText().toString();

                EditText campoTelefoneProfissional = findViewById(R.id.profissional_telefone);
                profissional.setTelefone(campoTelefoneProfissional.getText().toString());

                EditText campoLoginProfissional = findViewById(R.id.profissional_login);
                profissional.setLogin(campoLoginProfissional.getText().toString());

                EditText campoSenhaProfissional = findViewById(R.id.profissional_senha);
                profissional.setSenha(campoSenhaProfissional.getText().toString());

                profissionalDAO.insere(profissional);
                profissionalDAO.close();
                finish();

            }
        });
    }
}
