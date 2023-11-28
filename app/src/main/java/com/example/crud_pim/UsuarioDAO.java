package com.example.crud_pim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
    public long inserir(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("Nome", usuario.getNome());
        values.put("CPF", usuario.getCpf());
        values.put("Telefone", usuario.getTelefone());
        return banco.insert("Usuario", null, values);
    }
    public List<Usuario> obterTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = banco.query("Usuario", new String[]{"id", "nome", "cpf", "telefone"},
                null,null, null, null, null);
        while (cursor.moveToNext()){
            Usuario u = new Usuario();
            u.setId(cursor.getInt(0));
            u.setNome(cursor.getString(1));
            u.setCpf(cursor.getString(2));
            u.setTelefone(cursor.getString(3));
            usuarios.add(u);
        }
        return usuarios;
    }
    public void excluir(Usuario u){
        banco.delete("usuario", "id = ?", new String[]{u.getId().toString()});
    }
    public void atualizar(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("Nome", usuario.getNome());
        values.put("CPF", usuario.getCpf());
        values.put("Telefone", usuario.getTelefone());
        banco.update("usuario", values,"id=?", new String[]{usuario.getId().toString()});
    }
}
