package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Registro;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import java.util.Arrays;

public class SinistroDAO extends DAOGenerico<Sinistro> {
    
    @Override
    public Class<Sinistro> getClasseEntidade() {
        return Sinistro.class;
    }
    
    @Override
    public Sinistro[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        return Arrays.copyOf(registros, registros.length, Sinistro[].class);
    }
}