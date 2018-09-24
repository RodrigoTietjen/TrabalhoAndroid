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
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);


        /**
         * Recebe o intent da tela de cliente com o cliente selecionado.
         */
        Intent intent = getIntent();
        Cliente cliente = (Cliente) intent.getSerializableExtra("cliente");
        if(cliente != null) {
            Toast.makeText(CadastroClienteActivity.this,cliente.getTelefone(),Toast.LENGTH_SHORT).show();
            populaCadastro(cliente);
        }


        if(cliente != null){


        }else {
            /**
             * Ao clicar em salvar (seta o objeto e slava no banco.
             */
            Button salvarCadCliente = findViewById(R.id.btSalvarCliente);
            salvarCadCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CadastroClienteActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

                    Cliente cliente = new Cliente();
                    ClienteDAO clienteDAO = new ClienteDAO(CadastroClienteActivity.this);
                    EditText campoNomeCliente = findViewById(R.id.cliente_nome);
                    cliente.setNome(campoNomeCliente.getText().toString());

                    EditText campoTelefoneCliente = findViewById(R.id.cliente_telefone);
                    cliente.setTelefone(campoTelefoneCliente.getText().toString());

                    EditText campoEnderecoCliente = findViewById(R.id.cliente_endereco);
                    cliente.setEndereco(campoEnderecoCliente.getText().toString());

                    EditText campoEmailCliente = findViewById(R.id.cliente_email);
                    cliente.setEmail(campoEmailCliente.getText().toString());

                    clienteDAO.insere(cliente);
                    clienteDAO.close();
                    finish();
                }
            });
        }
    }

    public void populaCadastro(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO(CadastroClienteActivity.this);

        EditText campoNomeCliente = findViewById(R.id.cliente_nome);
        campoNomeCliente.setText(cliente.getNome());

        EditText campoTelefoneCliente = findViewById(R.id.cliente_telefone);
        campoTelefoneCliente.setText(cliente.getTelefone());

        EditText campoEnderecoCliente = findViewById(R.id.cliente_endereco);
        campoEnderecoCliente.setText(cliente.getEndereco());

        EditText campoEmailCliente = findViewById(R.id.cliente_email);
        campoEmailCliente.setText(cliente.getEmail());

        this.cliente = cliente;
    }
}
