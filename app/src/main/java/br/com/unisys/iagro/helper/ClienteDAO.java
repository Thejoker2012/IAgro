package br.com.unisys.iagro.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.unisys.iagro.model.Cliente;

public class ClienteDAO implements IClienteDAO {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;


    public ClienteDAO(Context context){
        DbHelper db = new DbHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Cliente cliente) {

        ContentValues cv = new ContentValues();
        cv.put("nome",cliente.getNome());
        cv.put("cpf",cliente.getCpf());
        cv.put("email",cliente.getEmail());
        cv.put("endereco",cliente.getEndereco());
        cv.put("numeroCelular",cliente.getNumeroCelular());

        try {
            Log.i("INFO","Cliente Salvo Com Sucesso!");
            escrever.insert(DbHelper.TABELA_CLIENTE,null,cv);
        }catch (Exception e){
            Log.i("INFO","Erro ao salvar Cliente" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        ContentValues cv = new ContentValues();
        cv.put("nome",cliente.getNome());
        cv.put("cpf",cliente.getCpf());
        cv.put("email",cliente.getEmail());
        cv.put("endereco",cliente.getEndereco());
        cv.put("numeroCelular",cliente.getNumeroCelular());

        try {
            String [] args = {cliente.getId().toString()};
            escrever.update(DbHelper.TABELA_CLIENTE,cv,"id=?",args );
            Log.i("INFO","Cliente Atualizado Com Sucesso!");
        }catch (Exception e){
            Log.i("INFO","Erro ao Atualizar Cliente" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Cliente cliente) {
        try {
            String [] args = {cliente.getId().toString()};
            escrever.delete(DbHelper.TABELA_CLIENTE,"id=?",args );
            Log.i("INFO","Cliente Removido Com Sucesso!");
        }catch (Exception e){
            Log.i("INFO","Erro ao Remover Cliente" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT  * FROM " + DbHelper.TABELA_CLIENTE + " ;";
        Cursor c = ler.rawQuery(sql, null);

        while (c.moveToNext()){

            Long id = c.getLong(c.getColumnIndex("id") );
            String nomeClient = c.getString(c.getColumnIndex("nome"));
            String cpfClient = c.getString(c.getColumnIndex("cpf"));
            String emailClient = c.getString(c.getColumnIndex("email"));
            String enderecoClient = c.getString(c.getColumnIndex("endereco"));
            String numeroClient = c.getString(c.getColumnIndex("numeroCelular"));

            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente.setNome(nomeClient);
            cliente.setCpf(cpfClient);
            cliente.setEmail(emailClient);
            cliente.setEndereco(enderecoClient);
            cliente.setNumeroCelular(numeroClient);

            clientes.add(cliente);
        }
        return  clientes;
    }
}
