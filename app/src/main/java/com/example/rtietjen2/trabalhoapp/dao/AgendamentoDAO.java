package com.example.rtietjen2.trabalhoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.rtietjen2.trabalhoapp.Entity.Agendamento;
import com.example.rtietjen2.trabalhoapp.Entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO extends SQLiteOpenHelper {

    public Context context;
    public AgendamentoDAO(Context context) {

        super(context, "Projeto", null, 10);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Agendamentos (id integer primary key AUTOINCREMENT," +
                "cliente varchar(255)," +
                "profissional varchar(255)," +
                "procedimento varchar(255));";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Agendamentos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Agendamento agendamento) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("cliente",agendamento.getCliente());
        dados.put("profissional",agendamento.getProfissional());
        dados.put("procedimento",agendamento.getProcedimento());

        db.insert("Agendamentos",null,dados);
    }

    public List<Agendamento> buscaAgendamentos() {
        String sql = "Select * from Agendamentos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Agendamento> agendamentos = new ArrayList<Agendamento>();
        while (c.moveToNext()) {
            Agendamento agendamento = new Agendamento();
            agendamento.setId(c.getInt(c.getColumnIndex("id")));
            agendamento.setCliente(c.getString(c.getColumnIndex("cliente")));
            agendamento.setProfissional(c.getString(c.getColumnIndex("profissional")));
            agendamento.setProcedimento(c.getString(c.getColumnIndex("procedimento")));
            agendamentos.add(agendamento);
        }
        c.close();

        return agendamentos;
    }

    public void deletar(Agendamento agendamento) {
        SQLiteDatabase db = getReadableDatabase();

        String[] params = {Integer.toString(agendamento.getId())};
        Toast.makeText(this.context, Integer.toString(agendamento.getId()), Toast.LENGTH_LONG).show();
        db.delete("Agendamentos","id  = ?",params);

    }

    public void alterar(Agendamento agendamento) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {Integer.toString(agendamento.getId())};

        ContentValues dados = new ContentValues();
        dados.put("cliente",agendamento.getCliente());
        dados.put("profissional",agendamento.getProfissional());
        dados.put("procedimento",agendamento.getProcedimento());

        db.update("Agendamentos",dados, "id = ?", params);

    }
}
