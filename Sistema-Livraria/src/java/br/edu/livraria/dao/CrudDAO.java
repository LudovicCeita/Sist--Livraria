/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.livraria.dao;

import br.edu.livraria.util.exception.ErroSistema;
import java.util.List;

/**
 *
 * @author Notorius B.I.G
 */
public interface CrudDAO<E> {

    public void salvar(E entidade) throws ErroSistema;

    public void deletar(E entidade) throws ErroSistema;

    public List<E> buscar() throws ErroSistema;
}
