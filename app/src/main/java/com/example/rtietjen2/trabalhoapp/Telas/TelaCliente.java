package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

import java.util.List;

public class TelaCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);

        ClienteDAO clienteDAO = new ClienteDAO(this);
        List<Cliente> clientes = clienteDAO.buscaClientes();
        clienteDAO.close();

        ListView listaClientes = findViewById(R.id.lista_cliente);
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this,android.R.layout.simple_list_item_1, clientes);
        listaClientes.setAdapter(adapter);

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
