package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;

import java.util.List;

public class ClienteSelecionado extends AppCompatActivity {

    private ListView selecionaCliente;
    public static final String PREFS_NAME = "shareData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_selecionado);

        selecionaCliente = findViewById(R.id.seleciona_cliente);
        carregarListaCliente();


        selecionaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Cliente cliente = (Cliente) selecionaCliente.getItemAtPosition(position);

                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("nome", cliente.getNome());
                editor.putString("telefone", cliente.getTelefone());
                editor.apply();
                finish();

            }
        });

    }
    private void carregarListaCliente() {
        ClienteDAO clienteDAO = new ClienteDAO(this);
        List<Cliente> clientes = clienteDAO.buscaClientes();
        clienteDAO.close();

        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        selecionaCliente.setAdapter(adapter);
    }


}
