package com.example.rtietjen2.trabalhoapp.Telas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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


        /**
         * TODO
         * Click e abre o cadastro para editar(não popula com o cliente clicado)
         */
        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Cliente cliente = (Cliente) listaClientes.getItemAtPosition(position);
                Intent intentCadastroCliente = new Intent(TelaCliente.this,CadastroClienteActivity.class);
                intentCadastroCliente.putExtra("cliente",cliente);

                startActivity(intentCadastroCliente);
                Toast.makeText(TelaCliente.this,cliente.getNome(),Toast.LENGTH_SHORT).show();
            }
        });




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

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Cliente cliente = (Cliente) listaClientes.getItemAtPosition(info.position);


        /**
         * TODO
         * Ligação não funciona.
         */
        MenuItem ligar = menu.add("Ligar");
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ActivityCompat.checkSelfPermission(TelaCliente.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TelaCliente.this, new String[]{Manifest.permission.CALL_PHONE},122 );
                }else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + "99998585"));
                    startActivity(intentLigar);
                }
                return false;
            }
        });

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
