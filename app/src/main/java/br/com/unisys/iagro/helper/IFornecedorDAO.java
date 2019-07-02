package br.com.unisys.iagro.helper;

import java.util.List;

import br.com.unisys.iagro.model.Fornecedor;

public interface IFornecedorDAO {

    public boolean salvar(Fornecedor fornecedor);
    public boolean atualizar(Fornecedor fornecedor);
    public boolean deletar(Fornecedor fornecedor);
    public List<Fornecedor> listar();
}
