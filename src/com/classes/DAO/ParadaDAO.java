package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classes.Conexao.Conexao;
import com.classes.DTO.Parada;

public class ParadaDAO {
	//253.000
	//||| |||
	//012 345
	//3
	//substring(0, 3+1);
	
	
    final String NOMEDATABELA = "Parada";
    
    public boolean inserir(Parada parada) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (cidade) VALUES (?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, parada.getCidade());
            ps.executeUpdate();
            parada.setId(procurarPorDescricao(parada).getId());
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
 
    public boolean excluir(Parada parada) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idParada = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, parada.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    public Parada procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idParada = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Parada obj = new Parada();
                obj.setId(rs.getInt(1));
                obj.setCidade(rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
        	 e.printStackTrace();
             return null;
        }
    }
    public Parada procurarPorDescricao(Parada parada) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE cidade = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, parada.getCidade());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Parada obj = new Parada();
                obj.setId(rs.getInt(1));
                obj.setCidade(rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public boolean existe(Parada parada) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE cidade = ? ;";

            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, parada.getCidade());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
        return false;
    }
    public List<Parada> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Parada> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Parada> montarLista(ResultSet rs) {
        List<Parada> listObj = new ArrayList<Parada>();
        try {
            while (rs.next()) {
                Parada obj = new Parada();
                obj.setId(rs.getInt(1));
                obj.setCidade(rs.getString(2));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
