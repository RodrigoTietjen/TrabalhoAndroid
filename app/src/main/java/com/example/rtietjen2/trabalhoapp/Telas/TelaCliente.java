package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.Entity.Profissional;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;
import com.example.rtietjen2.trabalhoapp.dao.ProfissionalDAO;

import java.util.List;

public class TelaCliente extends AppCompatActivity {

    private ListView listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);
        
        listaClientes = findViewById(R.id.lista_cliente);
        carregarListaCliente();

        Button cadastrarCliente = findViewById(R.id.btCadastrar_cliente);
        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaCliente.this,CadastroClienteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerForContextMenu(listaClientes);
    }

    private void carregarListaCliente() {
        ClienteDAO clienteDAO = new ClienteDAO(this);
        List<Cliente> clientes = clienteDAO.buscaClientes();
        clienteDAO.close();

        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this,android.R.layout.simple_list_item_1, clientes);
        listaClientes.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Cliente cliente = (Cliente) listaClientes.getItemAtPosition(info.position);

                ClienteDAO clienteDao = new ClienteDAO(TelaCliente.this);
                clienteDao.deletar(cliente);
                clienteDao.close();
                carregarListaCliente();
                return false;
            }
        });

    }
}
