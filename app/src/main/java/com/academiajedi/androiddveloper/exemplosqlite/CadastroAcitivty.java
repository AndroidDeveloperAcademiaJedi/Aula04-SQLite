package com.academiajedi.androiddveloper.exemplosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by alexsoaresdesiqueira on 08/02/17.
 */

public class CadastroAcitivty extends AppCompatActivity {

    private EditText etNome;
    private EditText etIdade;
    private EditText etSexo;
    private Button btnCadastrar;

    private PessoaDAO pessoaDAO;
    private Pessoa pessoa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = (EditText) findViewById(R.id.etNome);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etSexo = (EditText) findViewById(R.id.etSexo);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pessoaDAO = new PessoaDAO(CadastroAcitivty.this);

                if (btnCadastrar.getText().equals("ATUALIZAR")){

                    pessoa.setIdade(Integer.parseInt(etIdade.getText().toString()));
                    pessoa.setNome(etNome.getText().toString());
                    pessoa.setSexo(etSexo.getText().toString());

                    pessoaDAO.updatePessoa(pessoa);

                } else {

                    Pessoa pessoa = new Pessoa(
                            etNome.getText().toString(),
                            etSexo.getText().toString(),
                            Integer.parseInt(etIdade.getText().toString())
                    );

                    pessoaDAO.insertPessoa(pessoa);
                }

                onBackPressed();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pessoa = new Pessoa();
            pessoa.setIdade(extras.getInt("idade"));
            pessoa.setId(extras.getInt("id"));
            pessoa.setNome(extras.getString("nome"));
            pessoa.setSexo(extras.getString("sexo"));

            etNome.setText(pessoa.getNome());
            etIdade.setText(Integer.toString(pessoa.getIdade()));
            etSexo.setText(pessoa.getSexo());

            btnCadastrar.setText("ATUALIZAR");
        }
    }
}
