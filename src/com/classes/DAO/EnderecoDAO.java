package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classes.Conexao.Conexao;
import com.classes.DTO.Endereco;

public class EnderecoDAO {

    final String NOMEDATABELA = "Endereco";
    
    public boolean inserir(Endereco endereco) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (cep, cidade, rua, bairro, numero) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getRua());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getNumero());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean alterar(Endereco endereco) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " "
            		+ "SET "
            		+ "cep = ?, "
            		+ "cidade = ?, "
            		+ "rua = ?, "
            		+ "bairro = ?, "
            		+ "numero = ? "
            		+ "WHERE enderecoId = ?;";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getRua());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getNumero());
            ps.setInt(6, endereco.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
            
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    public boolean excluir(Endereco endereco) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE enderecoId = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, endereco.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    public Endereco procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idEndereco = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Endereco obj = new Endereco();
                obj.setId(rs.getInt(1));
                obj.setCep(rs.getString(2));
                obj.setCidade(rs.getString(3));
                obj.setRua(rs.getString(4));
                obj.setBairro(rs.getString(5));
                obj.setNumero(rs.getString(6));
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
    public Endereco procurarPorDescricao(Endereco endereco) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE bairro = ? AND numero = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, endereco.getBairro());
            ps.setString(2, endereco.getNumero());            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Endereco obj = new Endereco();
                obj.setId(rs.getInt(1));
                obj.setCep(rs.getString(2));
                obj.setCidade(rs.getString(3));
                obj.setRua(rs.getString(4));
                obj.setBairro(rs.getString(5));
                obj.setNumero(rs.getString(6));
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
    public boolean existe(Endereco endereco) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE "
            		+ "cep = ? AND "
            		+ "cidade = ? AND "
            		+ "rua = ? AND "
            		+ "bairro = ? AND "
            		+ "numero = ? ;";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getRua());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getNumero());
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
    public List<Endereco> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Endereco> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Endereco> montarLista(ResultSet rs) {
        List<Endereco> listObj = new ArrayList<Endereco>();
        try {
            while (rs.next()) {
                Endereco obj = new Endereco();
                obj.setId(rs.getInt(1));
                obj.setCep(rs.getString(2));
                obj.setCidade(rs.getString(3));
                obj.setRua(rs.getString(4));
                obj.setBairro(rs.getString(5));
                obj.setNumero(rs.getString(6));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
