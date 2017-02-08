package com.academiajedi.androiddveloper.exemplosqlite;

/**
 * Created by alexsoaresdesiqueira on 08/02/17.
 */

public class Pessoa {
    private String nome;
    private String sexo;
    private int idade;
    private int id;

    public Pessoa() {
    }

    public Pessoa(String nome, String sexo, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + ", Idade: " + this.getIdade() + ", Sexo: " + this.getSexo();
    }
}
