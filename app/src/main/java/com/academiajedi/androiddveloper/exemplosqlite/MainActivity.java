package com.academiajedi.androiddveloper.exemplosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleViewClickHack{

    private Button btnNovoUsuario;
    private RecyclerView rvPessoa;

    private PessoaAdapter pessoaAdapter;
    private List<Pessoa> pessoaList;

    private PessoaDAO pessoaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoaList = new ArrayList<>();

        rvPessoa = (RecyclerView) findViewById(R.id.rvPessoa);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPessoa.setLayoutManager(llm);

        getPessoasFromDAO();

        pessoaAdapter = new PessoaAdapter(pessoaList);
        pessoaAdapter.setRecycleViewClick(this);
        rvPessoa.setAdapter(pessoaAdapter);

        btnNovoUsuario = (Button) findViewById(R.id.btnNovoUsuario);
        btnNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastroAcitivty.class);
                startActivity(i);
            }
        });
    }

    public void getPessoasFromDAO(){
        pessoaDAO = new PessoaDAO(MainActivity.this);
        pessoaList = pessoaDAO.selectAllPessoas();
    }

    @Override
    protected void onRestart() {
        super.onResume();
        getPessoasFromDAO();
        pessoaAdapter.mudarListaAdapter(pessoaList);
    }

    @Override
    public void onClickListener(int position) {
        pessoaDAO = new PessoaDAO(MainActivity.this);
        Pessoa pessoa = pessoaDAO.selectPessoa(pessoaList.get(position));

        Intent i = new Intent(MainActivity.this, CadastroAcitivty.class);
        i.putExtra("nome", pessoa.getNome());
        i.putExtra("idade", pessoa.getIdade());
        i.putExtra("sexo", pessoa.getSexo());
        i.putExtra("id", pessoa.getId());
        startActivity(i);
    }

    @Override
    public void onLongClickListener(int position) {
        pessoaDAO = new PessoaDAO(MainActivity.this);
        pessoaDAO.deletePessoa(pessoaList.get(position));
        pessoaAdapter.removerItem(position);
    }
}
