package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

import java.util.List;

public class TelaProfissional  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_profissional);

        ProfissionalDAO profissionalDAO = new ProfissionalDAO(this);
        List<Profissional> profissionais = profissionalDAO.buscaProfissionais();
        profissionalDAO.close();

        ListView listaProfissionais = findViewById(R.id.lista_profissionais);
        ArrayAdapter<Profissional> adapter = new ArrayAdapter<Profissional>(this,android.R.layout.simple_list_item_1, profissionais);
        listaProfissionais.setAdapter(adapter);

        Button cadastrarProfissional = findViewById(R.id.btCadastrar_profissional);
        cadastrarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaProfissional.this,CadastroProfissionalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
