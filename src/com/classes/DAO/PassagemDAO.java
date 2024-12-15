package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classes.Conexao.Conexao;

import entidades.Passagem;
import entidades.PontoParada;
import entidades.Viagem;

public class PassagemDAO {

    final String NOMEDATABELA = "Passagem";
    
    public boolean inserir(Passagem passagem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (cliente, viagem, entrada, saida, vaga, numeroPassagem) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, passagem.getCliente().getId());
            ps.setInt(2, passagem.getViagem().getId());
            ps.setInt(3, passagem.getEmbarque().getParada().getId());
            ps.setInt(4, passagem.getSaida().getParada().getId());
            ps.setInt(5, passagem.getVaga().getId());
            ps.setString(6, passagem.getNumeroPassagem());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean alterar(Passagem passagem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " "
            		+ "SET "
            		+ "cliente = ?, "
            		+ "viagem = ?, "
            		+ "entrada = ?, "
            		+ "saida = ?, "
            		+ "vaga = ?, "
            		+ "numeroPassagem = ? "
            		+ "WHERE idPassagem = ?;";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, passagem.getCliente().getId());
            ps.setInt(2, passagem.getViagem().getId());
            ps.setInt(3, passagem.getEmbarque().getParada().getId());
            ps.setInt(4, passagem.getSaida().getParada().getId());
            ps.setString(5, passagem.getNumeroPassagem());
            ps.setInt(6, passagem.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
            
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    public boolean excluir(Passagem passagem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idPassagem = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, passagem.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    public Passagem procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idPassagem = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Passagem obj = new Passagem();
                UsuarioDAO usuariod = new UsuarioDAO();
                ViagemDAO viagemd = new ViagemDAO();
                VagaDAO vagad = new VagaDAO();
                
                obj.setId(rs.getInt(1));
                obj.setCliente(usuariod.procurarPorId(rs.getInt(2)));
                obj.setViagem(viagemd.procurarPorId(rs.getInt(3)));
                
                Viagem v = obj.getViagem();
                int idEntrada = rs.getInt(4);
                int idSaida = rs.getInt(5);
                for (PontoParada pp: v.getPontosDeParada()) {
                	if (pp.getParada().getId() == idEntrada) {
                		obj.setEmbarque(pp);
                	}
                	else if (pp.getParada().getId() == idSaida) {
                		obj.setSaida(pp);
                	}
                }

                obj.setVaga(vagad.procurarPorId(rs.getInt(6)));
                obj.setNumeroPassagem(rs.getString(7));
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
	//(Id, cliente, viagem, entrada, saida, vaga, numeroPassagem)          

    public Passagem procurarPorDescricao(Passagem passagem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE viagem = ? AND numeroPassagem = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, passagem.getViagem().getId());
            ps.setString(2, passagem.getNumeroPassagem());            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Passagem obj = new Passagem();
                UsuarioDAO usuariod = new UsuarioDAO();
                ViagemDAO viagemd = new ViagemDAO();
                VagaDAO vagad = new VagaDAO();
                
                obj.setId(rs.getInt(1));
                obj.setCliente(usuariod.procurarPorId(rs.getInt(2)));
                obj.setViagem(viagemd.procurarPorId(rs.getInt(3)));
                
                Viagem v = obj.getViagem();
                int idEntrada = rs.getInt(4);
                int idSaida = rs.getInt(5);
                for (PontoParada pp: v.getPontosDeParada()) {
                	if (pp.getParada().getId() == idEntrada) {
                		obj.setEmbarque(pp);
                	}
                	else if (pp.getParada().getId() == idSaida) {
                		obj.setSaida(pp);
                	}
                }

                obj.setVaga(vagad.procurarPorId(rs.getInt(6)));
                obj.setNumeroPassagem(rs.getString(7));
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
    public boolean existe(Passagem passagem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE viagem = ? AND (numeroPassagem = ? OR vaga = ?) ;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, passagem.getViagem().getId());
            ps.setString(2, passagem.getNumeroPassagem());
            ps.setInt(3, passagem.getVaga().getId());
            
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
    public List<Passagem> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Passagem> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Passagem> pesquisarTodosPorViagem(Viagem viagem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE viagem = ? ;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, viagem.getId());
            ResultSet rs = ps.executeQuery();
            List<Passagem> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Passagem> montarLista(ResultSet rs) {
        List<Passagem> listObj = new ArrayList<Passagem>();
        try {
            while (rs.next()) {
            	//(idPassagem, idcliente, idviagem, entrada, saida, vaga, numeroPassagem)
            	
                Passagem obj = new Passagem();
                UsuarioDAO usuariod = new UsuarioDAO();
                ViagemDAO viagemd = new ViagemDAO();
                VagaDAO vagad = new VagaDAO();
                
                obj.setId(rs.getInt(1));
                obj.setCliente(usuariod.procurarPorId(rs.getInt(2)));
                obj.setViagem(viagemd.procurarPorId(rs.getInt(3)));
                
                Viagem v = obj.getViagem();
                int idEntrada = rs.getInt(4);
                int idSaida = rs.getInt(5);
                for (PontoParada pp: v.getPontosDeParada()) {
                	if (pp.getParada().getId() == idEntrada) {
                		obj.setEmbarque(pp);
                	}
                	else if (pp.getParada().getId() == idSaida) {
                		obj.setSaida(pp);
                	}
                }

                obj.setVaga(vagad.procurarPorId(rs.getInt(6)));
                obj.setNumeroPassagem(rs.getString(7));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
