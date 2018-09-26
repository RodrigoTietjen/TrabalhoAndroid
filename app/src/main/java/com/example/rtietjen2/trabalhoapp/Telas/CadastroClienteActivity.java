package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ClienteDAO;

public class CadastroClienteActivity extends AppCompatActivity {
    public Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);


        /**
         * Recebe o intent da tela de cliente com o cliente selecionado.
         */
        Intent intent = getIntent();
        Cliente cliente = (Cliente) intent.getSerializableExtra("cliente");
        if(cliente == null){
            this.cliente = new Cliente();
        }
        else{
            this.cliente = cliente;
        }
        if(cliente != null) {
            Toast.makeText(CadastroClienteActivity.this,cliente.getTelefone(),Toast.LENGTH_SHORT).show();
            populaCadastro(cliente);
        }
            /**
             * Ao clicar em salvar (seta o objeto e slava no banco.
             */
            Button salvarCadCliente = findViewById(R.id.btSalvarCliente);
            salvarCadCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(CadastroClienteActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

                    ClienteDAO clienteDAO = new ClienteDAO(CadastroClienteActivity.this);
                    EditText campoNomeCliente = findViewById(R.id.cliente_nome);
                    CadastroClienteActivity.this.cliente.setNome(campoNomeCliente.getText().toString());

                    EditText campoTelefoneCliente = findViewById(R.id.cliente_telefone);
                    CadastroClienteActivity.this.cliente.setTelefone(campoTelefoneCliente.getText().toString());

                    EditText campoEnderecoCliente = findViewById(R.id.cliente_endereco);
                    CadastroClienteActivity.this.cliente.setEndereco(campoEnderecoCliente.getText().toString());

                    EditText campoEmailCliente = findViewById(R.id.cliente_email);
                    CadastroClienteActivity.this.cliente.setEmail(campoEmailCliente.getText().toString());
                    Toast.makeText(CadastroClienteActivity.this, Integer.toString(CadastroClienteActivity.this.cliente.getId()), Toast.LENGTH_LONG).show();
                    if(CadastroClienteActivity.this.cliente.getId() == 0){
                        clienteDAO.insere(CadastroClienteActivity.this.cliente);
                    }
                    else{
                        clienteDAO.alterar(CadastroClienteActivity.this.cliente);
                        clienteDAO.close();
                        CadastroClienteActivity.super.onResume();
                        finish();
                    }
                    clienteDAO.buscaClientes();
                    clienteDAO.close();
                    finish();

                }
            });
    }

    public void populaCadastro(Cliente cliente) {
//        ClienteDAO clienteDAO = new ClienteDAO(CadastroClienteActivity.this);
//        Toast.makeText(CadastroClienteActivity.this, Integer.toString(cliente.getId()), Toast.LENGTH_LONG).show();

        EditText campoNomeCliente = findViewById(R.id.cliente_nome);
        campoNomeCliente.setText(cliente.getNome());

        EditText campoTelefoneCliente = findViewById(R.id.cliente_telefone);
        campoTelefoneCliente.setText(cliente.getTelefone());

        EditText campoEnderecoCliente = findViewById(R.id.cliente_endereco);
        campoEnderecoCliente.setText(cliente.getEndereco());

        EditText campoEmailCliente = findViewById(R.id.cliente_email);
        campoEmailCliente.setText(cliente.getEmail());

//        this.cliente = cliente;
    }
}
