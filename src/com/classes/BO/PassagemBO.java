package com.classes.BO;

import java.util.List;

import com.classes.DAO.PassagemDAO;
import com.classes.DTO.Passagem;

public class PassagemBO {

    public boolean inserir(Passagem passagem){
        if (existe(passagem) != true) {
            PassagemDAO passagemsDAO = new PassagemDAO();
            return passagemsDAO.inserir(passagem);
        }
        return false;
    }
    public boolean alterar(Passagem passagem){
        PassagemDAO passagemsDAO = new PassagemDAO();
        return passagemsDAO.alterar(passagem);
    }
    public boolean excluir(Passagem passagem){
        PassagemDAO passagemsDAO = new PassagemDAO();
        return passagemsDAO.excluir(passagem);
    }
    public Passagem procurarPorDescricao(Passagem passagem){
        PassagemDAO passagemsDAO = new PassagemDAO();
        return passagemsDAO.procurarPorDescricao(passagem);
    }
    public Passagem procurarPorId(int id){
    	PassagemDAO passagemsDAO = new PassagemDAO();
    	return passagemsDAO.procurarPorId(id);
    }
    public boolean existe(Passagem passagem){
        PassagemDAO passagemsDAO = new PassagemDAO();
        return passagemsDAO.existe(passagem);
    }
    public List<Passagem> pesquisarTodos(){
        PassagemDAO passagemsDAO = new PassagemDAO();
        return passagemsDAO.pesquisarTodos();
    }
}
