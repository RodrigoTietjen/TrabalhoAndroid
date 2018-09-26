package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProcedimentoDAO;

public class CadastroProcedimentoActivity extends AppCompatActivity {

    public  Procedimento procedimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_procedimento);

        Intent intent = getIntent();
        Procedimento procedimento = (Procedimento) intent.getSerializableExtra("procedimento");
        if(procedimento == null){
            this.procedimento = new Procedimento();
        }
        else{
            this.procedimento = procedimento;
        }
        if(procedimento != null) {
            populaCadastro(procedimento);
        }

        Button salvarCadProcedimento = findViewById(R.id.btSalvarProcedimento);
        salvarCadProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroProcedimentoActivity.this, "Clicked",Toast.LENGTH_SHORT).show();

                ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(CadastroProcedimentoActivity.this);

                EditText campoNomeProcedimento = findViewById(R.id.procedimento_nome);
                CadastroProcedimentoActivity.this.procedimento.setNome(campoNomeProcedimento.getText().toString());

                EditText campoValorProcedimento = findViewById(R.id.procedimento_valor);
                CadastroProcedimentoActivity.this.procedimento.setValor(Double.parseDouble(campoValorProcedimento.getText().toString()));

                if(CadastroProcedimentoActivity.this.procedimento.getId() == 0){
                    procedimentoDAO.insere(CadastroProcedimentoActivity.this.procedimento);
                }
                else{
                    procedimentoDAO.alterar(CadastroProcedimentoActivity.this.procedimento);
                    procedimentoDAO.close();
                    finish();
                }
                procedimentoDAO.buscaProcedimentos();
                procedimentoDAO.close();
                finish();

            }
        });
    }

    public void populaCadastro(Procedimento procedimento) {
//        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(CadastroProcedimentoActivity.this);

        EditText campoNomeProcedimento = findViewById(R.id.procedimento_nome);
        campoNomeProcedimento.setText(procedimento.getNome());

        EditText campoValorProcedimento = findViewById(R.id.procedimento_valor);
        campoValorProcedimento.setText(Double.toString(procedimento.getValor()));

    }
}
