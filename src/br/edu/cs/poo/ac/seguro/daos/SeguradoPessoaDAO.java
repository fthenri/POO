package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaDAO extends SeguradoDAO {

    public SeguradoPessoa buscar(String cpf) {
        return (SeguradoPessoa) super.buscar(cpf);
    }

    @Override
    public Class<SeguradoPessoa> getClasseEntidade() {
        return SeguradoPessoa.class;
    }
}