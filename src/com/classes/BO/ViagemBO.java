package com.classes.BO;

import java.util.List;

import com.classes.DAO.ViagemDAO;
import com.classes.DTO.Viagem;

public class ViagemBO {

    public boolean inserir(Viagem viagem){
        if (existe(viagem) != true) {
            ViagemDAO viagemsDAO = new ViagemDAO();
            return viagemsDAO.inserir(viagem);
        }
        return false;
    }
    public boolean alterar(Viagem viagem){
        ViagemDAO viagemsDAO = new ViagemDAO();
        return viagemsDAO.alterar(viagem);
    }
    public boolean excluir(Viagem viagem){
        ViagemDAO viagemsDAO = new ViagemDAO();
        return viagemsDAO.excluir(viagem);
    }
    public Viagem procurarPorDescricao(Viagem viagem){
        ViagemDAO viagemsDAO = new ViagemDAO();
        return viagemsDAO.procurarPorDescricao(viagem);
    }
    public Viagem procurarPorId(int id){
    	ViagemDAO viagemsDAO = new ViagemDAO();
    	return viagemsDAO.procurarPorId(id);
    }
    public boolean existe(Viagem viagem){
        ViagemDAO viagemsDAO = new ViagemDAO();
        return viagemsDAO.existe(viagem);
    }
    public List<Viagem> pesquisarTodos(){
        ViagemDAO viagemsDAO = new ViagemDAO();
        return viagemsDAO.pesquisarTodos();
    }
}
