package br.edu.cs.poo.ac.seguro.daos;

import java.io.Serializable;
import java.util.Arrays;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Registro;

public abstract class DAOGenerico<T extends Registro> {

    private CadastroObjetos cadastro;

    public abstract Class<? extends T> getClasseEntidade();

    public DAOGenerico() {
        this.cadastro = new CadastroObjetos(getClasseEntidade());
    }

    public boolean incluir(T registro) {
        if (buscar(registro.getIdUnico()) != null) {
            return false;
        }
        cadastro.incluir(registro, registro.getIdUnico());
        return true;
    }

    public boolean alterar(T registro) {
        if (buscar(registro.getIdUnico()) == null) {
            return false;
        }
        cadastro.alterar(registro, registro.getIdUnico());
        return true;
    }

    public boolean excluir(String id) {
        if (buscar(id) == null) {
            return false;
        }
        cadastro.excluir(id);
        return true;
    }

    @SuppressWarnings("unchecked")
    public T buscar(String id) {
        return (T) cadastro.buscar(id);
    }

    public Registro[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos();
        return Arrays.copyOf(rets, rets.length, Registro[].class);
    }
}