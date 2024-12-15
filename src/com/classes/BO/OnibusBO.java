package com.classes.BO;

import com.classes.DAO.OnibusDAO;

import entidades.Onibus;

import java.util.List;

public class OnibusBO {

    public boolean inserir(Onibus onibus){
        if (existe(onibus) != true) {
            OnibusDAO onibussDAO = new OnibusDAO();
            return onibussDAO.inserir(onibus);
        }
        return false;
    }
    
    public boolean excluir(Onibus onibus){
        OnibusDAO onibussDAO = new OnibusDAO();
        return onibussDAO.excluir(onibus);
    }
    public Onibus procurarPorCodigo(Onibus onibus){
        OnibusDAO onibussDAO = new OnibusDAO();
        return onibussDAO.procurarPorDescricao(onibus);
    }
    public Onibus procurarPorDescricao(Onibus onibus){
        OnibusDAO onibussDAO = new OnibusDAO();
        return onibussDAO.procurarPorDescricao(onibus);
    }
    public boolean existe(Onibus onibus){
        OnibusDAO onibussDAO = new OnibusDAO();
        return onibussDAO.existe(onibus);
    }
    
    public List<Onibus> pesquisarTodos(){
        OnibusDAO onibussDAO = new OnibusDAO();
        return onibussDAO.pesquisarTodos();
    }
}