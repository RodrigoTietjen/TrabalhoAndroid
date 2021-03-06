package com.example.rtietjen2.trabalhoapp.Telas;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.dao.ProcedimentoDAO;

public class LoginActivity extends AppCompatActivity {

    private Button btEntrar;
    private TextInputEditText etLogin, etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.btEntrar = this.findViewById(R.id.btEntrar);
        this.etLogin  = this.findViewById(R.id.etLogin);
        this.etSenha  = this.findViewById(R.id.etSenha);

        this.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();

                if (login.equals("1") && senha.equals("1")){
                    Intent intent = new Intent(LoginActivity.this,MenuPrincipal.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Bloqueado", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}