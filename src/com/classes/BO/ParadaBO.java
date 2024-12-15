package com.classes.BO;

import com.classes.DAO.ParadaDAO;
import entidades.Parada;

import java.util.List;

public class ParadaBO {

    public boolean inserir(Parada parada){
        if (existe(parada) != true) {
            ParadaDAO paradasDAO = new ParadaDAO();
            return paradasDAO.inserir(parada);
        }
        return false;
    }

    public boolean excluir(Parada parada){
        ParadaDAO paradasDAO = new ParadaDAO();
        return paradasDAO.excluir(parada);
    }
    public Parada procurarPorDescricao(Parada parada){
        ParadaDAO paradasDAO = new ParadaDAO();
        return paradasDAO.procurarPorDescricao(parada);
    }
    
    public boolean existe(Parada parada){
        ParadaDAO paradasDAO = new ParadaDAO();
        return paradasDAO.existe(parada);
    }
    public List<Parada> pesquisarTodos(){
        ParadaDAO paradasDAO = new ParadaDAO();
        return paradasDAO.pesquisarTodos();
    }
}