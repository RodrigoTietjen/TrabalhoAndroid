package com.example.rtietjen2.trabalhoapp.Entity;

import java.io.Serializable;

public class Procedimento implements Serializable{

    public int id;

    private String nome;

    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getValor(){
        return this.valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    @Override
    public String toString() {
        return getNome() + " " + getValor();
    }
}
