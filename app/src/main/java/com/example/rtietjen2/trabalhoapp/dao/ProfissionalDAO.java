package com.example.rtietjen2.trabalhoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rtietjen2.trabalhoapp.Entity.Profissional;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO extends SQLiteOpenHelper{


    public ProfissionalDAO(Context context) {
        super(context, "Projeto", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Profissionais (id integer primary key," +
                                                  "nome varchar(255)," +
                                                  "especialidade varchar(255)," +
                                                  "telefone varchar(255)," +
                                                  "login varchar(255)," +
                                                  "senha varchar(255))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Profissionais";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Profissional profissional) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome",profissional.getNome());
       // dados.put("especialidade",profissional.getEspecialidades());
        dados.put("telefone",profissional.getTelefone());
        dados.put("login",profissional.getLogin());
        dados.put("senha",profissional.getSenha());

        db.insert("Profissionais",null,dados);
    }

    public List<Profissional> buscaProfissionais() {
        String sql = "Select * from Profissionais;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Profissional> profissionais = new ArrayList<Profissional>();
        while (c.moveToNext()) {
            Profissional profissional = new Profissional();
            profissional.setNome(c.getString(c.getColumnIndex("nome")));
         //   profissional.setEspecialidades(c.getString(c.getColumnIndex("especialidade")));
            profissional.setTelefone(c.getString(c.getColumnIndex("telefone")));
            profissional.setLogin(c.getString(c.getColumnIndex("login")));
            profissional.setSenha(c.getString(c.getColumnIndex("senha")));
            profissionais.add(profissional);
        }
        c.close();

        return profissionais;
    }

    public void deletar(Profissional profissional) {
        SQLiteDatabase db = getReadableDatabase();

        String[] params = {profissional.getNome()};
        db.delete("Profissionais","nome = ? ",params);
    }
}
