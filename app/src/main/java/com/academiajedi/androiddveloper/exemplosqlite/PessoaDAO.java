package com.academiajedi.androiddveloper.exemplosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexsoaresdesiqueira on 08/02/17.
 */

public class PessoaDAO {

    private SQLiteDatabase db;

    public PessoaDAO(Context context) {
        BDHelper bdHelper = new BDHelper(context);
        db = bdHelper.getWritableDatabase();
    }

    public void insertPessoa(Pessoa pessoa){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", pessoa.getNome());
        contentValues.put("idade", pessoa.getIdade());
        contentValues.put("sexo", pessoa.getSexo());

        db.insert("usuario", null, contentValues);
    }

    public void updatePessoa(Pessoa pessoa){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", pessoa.getNome());
        contentValues.put("idade", pessoa.getIdade());
        contentValues.put("sexo", pessoa.getSexo());

        db.update("usuario", contentValues, "_id = ?", new String[]{""+pessoa.getId()});
    }

    public void deletePessoa(Pessoa pessoa){
        db.delete("usuario", "_id = ?", new String[]{""+pessoa.getId()});
    }

    public List<Pessoa> selectAllPessoas(){

        List<Pessoa> pessoaListReturn = new ArrayList<>();
        String[] colunas = new String[]{"_id","nome","idade","sexo"};

        Cursor cursor = db.query("usuario", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                Pessoa pessoa = new Pessoa();
                pessoa.setId(cursor.getInt(0));
                pessoa.setNome(cursor.getString(1));
                pessoa.setIdade(cursor.getInt(2));
                pessoa.setSexo(cursor.getString(3));

                pessoaListReturn.add(pessoa);

            } while (cursor.moveToNext());
        }

        return pessoaListReturn;
    }

    public Pessoa selectPessoa(Pessoa pessoa){

        Pessoa pessoa1 = new Pessoa();
        String[] colunas = new String[]{"_id","nome","idade","sexo"};

        Cursor cursor = db.query("usuario", colunas, "_id = ?", new String[]{""+pessoa.getId()}, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                pessoa1.setId(cursor.getInt(0));
                pessoa1.setNome(cursor.getString(1));
                pessoa1.setIdade(cursor.getInt(2));
                pessoa1.setSexo(cursor.getString(3));

            } while (cursor.moveToNext());
        }

        return pessoa1;
    }
}
