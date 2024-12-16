package com.classes.BO;

import java.util.List;

import com.classes.DAO.ParadaDAO;
import com.classes.DTO.Parada;

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