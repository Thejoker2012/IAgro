package br.com.unisys.iagro.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.unisys.iagro.model.Fornecedor;

public class FornecedorDAO implements IFornecedorDAO {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public FornecedorDAO(Context context){
        DbHelper db = new DbHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();

    }

    /*private int id;
    private String nome;
    private String cnpj;
    private String email;
    private String endereco;
    private String numeroCelular;*/

    @Override
    public boolean salvar(Fornecedor fornecedor) {

        ContentValues cv = new ContentValues();
        cv.put("nome",fornecedor.getNome());
        cv.put("cnpj",fornecedor.getCnpj());
        cv.put("email",fornecedor.getEmail());
        cv.put("endereco",fornecedor.getEndereco());
        cv.put("numeroCelular",fornecedor.getNumeroCelular());

        try {
            Log.i("INFO","Fornecedor Salvo Com Sucesso!");
            escrever.insert(DbHelper.TABELA_FORNECEDOR,null,cv);
        }catch (Exception e){
            Log.i("INFO","Erro ao salvar Fornecedor" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Fornecedor fornecedor) {

        ContentValues cv = new ContentValues();
        cv.put("nome",fornecedor.getNome());
        cv.put("cnpj",fornecedor.getCnpj());
        cv.put("email",fornecedor.getEmail());
        cv.put("endereco",fornecedor.getEndereco());
        cv.put("numeroCelular",fornecedor.getNumeroCelular());

        try {
            String [] args = {fornecedor.getId().toString()};
            escrever.update(DbHelper.TABELA_FORNECEDOR,cv,"id=?",args );
            Log.i("INFO","Fornecedor Atualizado Com Sucesso!");
        }catch (Exception e){
            Log.i("INFO","Erro ao Atualizar Fornecedor" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Fornecedor fornecedor) {

        try {
            String [] args = {fornecedor.getId().toString()};
            escrever.delete(DbHelper.TABELA_FORNECEDOR,"id=?",args );
            Log.i("INFO","Fornecedor Removido Com Sucesso!");
        }catch (Exception e){
            Log.i("INFO","Erro ao Remover Fornecedor" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Fornecedor> listar() {
        List<Fornecedor> fornecedores = new ArrayList<>();

        String sql = "SELECT  * FROM " + DbHelper.TABELA_FORNECEDOR + " ;";
        Cursor c = ler.rawQuery(sql, null);

        while (c.moveToNext()){

            Long id = c.getLong(c.getColumnIndex("id") );
            String nomeFornec = c.getString(c.getColumnIndex("nome"));
            String cnpjFornec = c.getString(c.getColumnIndex("cnpj"));
            String emailFornec = c.getString(c.getColumnIndex("email"));
            String enderecoFornec = c.getString(c.getColumnIndex("endereco"));
            String numeroFornec = c.getString(c.getColumnIndex("numeroCelular"));

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(id);
            fornecedor.setNome(nomeFornec);
            fornecedor.setNome(cnpjFornec);
            fornecedor.setNome(emailFornec);
            fornecedor.setNome(enderecoFornec);
            fornecedor.setNome(numeroFornec);

            fornecedores.add(fornecedor);
        }
        return  fornecedores;
    }
}
