package br.com.unisys.iagro.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;

    //Nome do banco de dados
    public static String NOME_DB = "IAGRO";

    //Nome das tabelas
    public static String TABELA_CLIENTE = "cliente";
    public static String TABELA_FORNECEDOR = "fornecedor";


    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_CLIENTE
                +" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL," +
                    "cnpj TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "endereco TEXT NOT NULL," +
                    "numeroCelular TEXT NOT NULL );";


        String sql1 = "CREATE TABLE IF NOT EXISTS " + TABELA_FORNECEDOR
                +" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nome TEXT NOT NULL," +
                    " cnpj TEXT NOT NULL," +
                    " email TEXT NOT NULL," +
                    " endereco TEXT NOT NULL," +
                    " numeroCelular TEXT NOT NULL ); ";

        try{
            db.execSQL( sql );
            db.execSQL(sql1);
            Log.i("INFO DB","Sucesso ao criar a tabela");

        }catch (Exception e){
            Log.i("INFO DB","Erro ao criar a tabela" + e.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_CLIENTE + " ; ";
        String sql1 = "DROP TABLE IF EXISTS " + TABELA_FORNECEDOR + " ; ";

        try{
            db.execSQL( sql );
            db.execSQL( sql1 );
            onCreate(db);

            Log.i("INFO DB","Sucesso ao atualizar App");

        }catch (Exception e){
            Log.i("INFO DB","Erro ao atualizar App" + e.getMessage());
        }
    }
}
