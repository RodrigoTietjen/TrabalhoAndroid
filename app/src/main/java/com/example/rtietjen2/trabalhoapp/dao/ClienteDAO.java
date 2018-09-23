package com.example.rtietjen2.trabalhoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rtietjen2.trabalhoapp.Entity.Cliente;
import com.example.rtietjen2.trabalhoapp.Entity.Profissional;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends SQLiteOpenHelper {
    public ClienteDAO(Context context) {
        super(context, "Projeto", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Clientes (id integer primary key," +
                     "nome varchar(255)," +
                     "telefone varchar(255)," +
                     "endereco varchar(255)," +
                     "email varchar(255))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Clientes";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome",cliente.getNome());
        dados.put("telefone",cliente.getTelefone());
        dados.put("endereco",cliente.getEndereco());
        dados.put("email",cliente.getEmail());

        db.insert("Clientes",null,dados);
    }

    public List<Cliente> buscaClientes() {
        String sql = "Select * from Clientes;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Cliente> clientes = new ArrayList<Cliente>();
        while (c.moveToNext()) {
            Cliente cliente = new Cliente();
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
            cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cliente.setEmail(c.getString(c.getColumnIndex("email")));
            clientes.add(cliente);
        }
        c.close();

        return clientes;
    }
}
