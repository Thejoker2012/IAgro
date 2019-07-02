package br.com.unisys.iagro.helper;

import java.util.List;

import br.com.unisys.iagro.model.Cliente;

public class ClienteDAO implements IClienteDAO {
    @Override
    public boolean salvar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean deletar(Cliente cliente) {
        return false;
    }

    @Override
    public List<Cliente> listar() {
        return null;
    }
}
