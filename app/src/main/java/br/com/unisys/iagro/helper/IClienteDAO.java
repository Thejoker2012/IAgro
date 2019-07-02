package br.com.unisys.iagro.helper;

import java.util.List;

import br.com.unisys.iagro.model.Cliente;

public interface IClienteDAO {

    public boolean salvar(Cliente cliente);
    public boolean atualizar(Cliente cliente);
    public boolean deletar(Cliente cliente);
    public List<Cliente> listar();
}
