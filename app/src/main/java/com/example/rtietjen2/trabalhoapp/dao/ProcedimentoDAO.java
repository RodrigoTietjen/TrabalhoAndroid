package com.example.rtietjen2.trabalhoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rtietjen2.trabalhoapp.Entity.Procedimento;
import com.example.rtietjen2.trabalhoapp.Entity.Profissional;

import java.util.ArrayList;
import java.util.List;

public class ProcedimentoDAO extends SQLiteOpenHelper {
    public Context context;

    public ProcedimentoDAO(Context context) {
        super(context, "Projeto", null, 13);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Procedimentos (id integer primary key AUTOINCREMENT," +
                     "nome varchar(255)," +
                     "valor real)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Procedimentos";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insere(Procedimento procedimento) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome",procedimento.getNome());
        dados.put("valor",procedimento.getValor());

        db.insert("Procedimentos",null,dados);
    }

    public List<Procedimento> buscaProcedimentos() {
        String sql = "Select * from Procedimentos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Procedimento> procedimentos = new ArrayList<Procedimento>();
        while (c.moveToNext()) {
            Procedimento procedimento = new Procedimento();
            procedimento.setId(c.getInt(c.getColumnIndex("id")));
            procedimento.setNome(c.getString(c.getColumnIndex("nome")));
            procedimento.setValor(c.getDouble(c.getColumnIndex("valor")));

            procedimentos.add(procedimento);
        }
        c.close();

        return procedimentos;
    }

    public void deletar(Procedimento procedimento) {
        SQLiteDatabase db = getReadableDatabase();

        String[] params = {Integer.toString(procedimento.getId())};
        db.delete("Procedimentos","id  = ?",params);
    }

    public void alterar(Procedimento procedimento) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {Integer.toString(procedimento.getId())};

        ContentValues dados = new ContentValues();
        dados.put("nome",procedimento.getNome());
        dados.put("valor",procedimento.getValor());


        db.update("Procedimentos",dados, "id = ?", params);
    }
}
