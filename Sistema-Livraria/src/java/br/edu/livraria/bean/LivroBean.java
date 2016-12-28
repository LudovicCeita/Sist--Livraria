/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.livraria.bean;

import br.edu.livraria.dao.LivroDAO;
import br.edu.livraria.entidade.Livro;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Notorius B.I.G
 */

@ManagedBean
@SessionScoped
public class LivroBean  extends CrudBean<Livro, LivroDAO> {
    
    private LivroDAO livroDAO;

    @Override
    public LivroDAO getDao() {
       if(livroDAO == null){
                livroDAO = new LivroDAO();
        }
        return livroDAO;
    }

    @Override
    public Livro criarNovaEntidade() {
         return new Livro();
    }
    
}
