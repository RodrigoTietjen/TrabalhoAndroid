package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
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

    public Profissional profissional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional);

        Intent intent = getIntent();
        Profissional profissional = (Profissional) intent.getSerializableExtra("profissional");
        if(profissional == null){
            this.profissional = new Profissional();
        }
        else{
            this.profissional = profissional;
        }
        if(profissional != null) {
            populaCadastro(profissional);
        }

        Button salvarCadProfissional = findViewById(R.id.btSalvarProfissional);
        salvarCadProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ProfissionalDAO profissionalDAO = new ProfissionalDAO(CadastroProfissionalActivity.this);

                EditText campoNomeProfissional = findViewById(R.id.profissional_nome);
                CadastroProfissionalActivity.this.profissional.setNome(campoNomeProfissional.getText().toString());

                EditText campoEspecialidadeProfissional = findViewById(R.id.profissional_especialidade);
                String especialidadeProfissional = campoEspecialidadeProfissional.getText().toString();

                EditText campoTelefoneProfissional = findViewById(R.id.profissional_telefone);
                CadastroProfissionalActivity.this.profissional.setTelefone(campoTelefoneProfissional.getText().toString());

                EditText campoLoginProfissional = findViewById(R.id.profissional_login);
                CadastroProfissionalActivity.this.profissional.setLogin(campoLoginProfissional.getText().toString());

                EditText campoSenhaProfissional = findViewById(R.id.profissional_senha);
                CadastroProfissionalActivity.this.profissional.setSenha(campoSenhaProfissional.getText().toString());

                if(CadastroProfissionalActivity.this.profissional.getId() == 0){
                    profissionalDAO.insere(CadastroProfissionalActivity.this.profissional);
                }
                else{
                    profissionalDAO.alterar(CadastroProfissionalActivity.this.profissional);
                    profissionalDAO.close();
                    finish();
                }
                profissionalDAO.buscaProfissionais();
                profissionalDAO.close();
                finish();

            }
        });
    }

    public void populaCadastro(Profissional profissional) {
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(CadastroProfissionalActivity.this);

        EditText campoNomeProfissional = findViewById(R.id.profissional_nome);
        campoNomeProfissional.setText(profissional.getNome());

//        EditText campoEspecialidadeProfissional = findViewById(R.id.profissional_especialidade);
//        campoEspecialidadeProfissional.setText(profissional.getEspecialidades());

        EditText campoTelefoneProfissional = findViewById(R.id.profissional_telefone);
        campoTelefoneProfissional.setText(profissional.getTelefone());

        EditText campoLoginProfissional = findViewById(R.id.profissional_login);
        campoLoginProfissional.setText(profissional.getLogin());

        EditText campoSenhaProfissional = findViewById(R.id.profissional_senha);
        campoSenhaProfissional.setText(profissional.getSenha());

    }
}
