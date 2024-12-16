package com.classes.BO;

import java.util.List;

import com.classes.DAO.UsuarioDAO;
import com.classes.DTO.Usuario;

public class UsuarioBO {

    public boolean inserir(Usuario usuario){
        if (existe(usuario) != true) {
            UsuarioDAO usuariosDAO = new UsuarioDAO();
            return usuariosDAO.inserir(usuario);
        }
        return false;
    }
    public boolean alterar(Usuario usuario){
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        return usuariosDAO.alterar(usuario);
    }
    public boolean excluir(Usuario usuario){
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        return usuariosDAO.excluir(usuario);
    }

    public Usuario procurarPorDescricao(Usuario usuario){
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        return usuariosDAO.procurarPorDescricao(usuario);
    }
    public boolean existe(Usuario usuario){
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        return usuariosDAO.existe(usuario);
    }
    public List<Usuario> pesquisarTodos(){
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        return usuariosDAO.pesquisarTodos();
    }
}