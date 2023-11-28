package com.example.crud_pim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private UsuarioDAO dao;
    private Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        telefone = findViewById(R.id.editTelefone);
        dao = new UsuarioDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("usuario")){
            usuario = (Usuario) it.getSerializableExtra("usuario");
            nome.setText(usuario.getNome());
            cpf.setText(usuario.getCpf());
            telefone.setText(usuario.getTelefone());
        }
    }
    public void salvar(View view){
        if(usuario == null) {
            usuario = new Usuario();
            usuario.setNome(nome.getText().toString());
            usuario.setCpf(cpf.getText().toString());
            usuario.setTelefone(telefone.getText().toString());
            long id = dao.inserir(usuario);
            Toast.makeText(this, "Usuario inserido com id: " + id, Toast.LENGTH_SHORT).show();
        }else{
            usuario.setNome(nome.getText().toString());
            usuario.setCpf(cpf.getText().toString());
            usuario.setTelefone(telefone.getText().toString());
            dao.atualizar(usuario);
            Toast.makeText(this, "Usuario foi atualizado", Toast.LENGTH_SHORT).show();
        }
        }

}