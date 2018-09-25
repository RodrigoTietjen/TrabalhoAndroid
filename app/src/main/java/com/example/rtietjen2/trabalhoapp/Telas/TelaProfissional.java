package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

import java.util.List;

public class TelaProfissional  extends AppCompatActivity {

    private ListView listaProfissionais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_profissional);

        listaProfissionais = findViewById(R.id.lista_profissionais);
        carregarListaProfissionais();

        listaProfissionais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Profissional profissional = (Profissional) listaProfissionais.getItemAtPosition(position);
                Intent intentCadastroProfissional = new Intent(TelaProfissional.this,CadastroProfissionalActivity.class);
                intentCadastroProfissional.putExtra("profissional",profissional);

                startActivity(intentCadastroProfissional);
                finish();

            }
        });

        Button cadastrarProfissional = findViewById(R.id.btCadastrar_profissional);
        cadastrarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaProfissional.this,CadastroProfissionalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerForContextMenu(listaProfissionais);
    }

    private void carregarListaProfissionais() {
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(this);
        List<Profissional> profissionais = profissionalDAO.buscaProfissionais();
        profissionalDAO.close();
       
        ArrayAdapter<Profissional> adapter = new ArrayAdapter<Profissional>(this,android.R.layout.simple_list_item_1, profissionais);
        listaProfissionais.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Profissional profissional = (Profissional) listaProfissionais.getItemAtPosition(info.position);

                ProfissionalDAO profissionalDAO = new ProfissionalDAO(TelaProfissional.this);
                profissionalDAO.deletar(profissional);
                profissionalDAO.close();
                carregarListaProfissionais();
                return false;
            }
        });

    }

}
