package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.classes.Conexao.Conexao;

import entidades.HorarioDeFuncionamento;
import entidades.Onibus;
import entidades.Vaga;

public class OnibusDAO {

    final String NOMEDATABELA = "Onibus";
    
    public boolean inserir(Onibus onibus) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (codigo) VALUES (?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, onibus.getCodigo());
            ps.executeUpdate();
            ps.close();
            onibus.setId(procurarPorDescricao(onibus).getId());
            
            sql = "INSERT INTO " + "vaga" + " (num, onibus) VALUES (?, ?);";
            for (int i = 0; i < onibus.getVagas().size(); i++) {
            	PreparedStatement ps2 = conn.prepareStatement(sql);
            	ps2.setInt(1, i+1);
            	ps2.setInt(2, onibus.getId());
            	ps2.executeUpdate();
            	ps2.close();
            }
            
            if (onibus.getHorarios() != null) {
            	sql = "INSERT INTO " + "horarioDeFuncionamento" + " (onibus, inicio, fim) VALUES (?, ?, ?);";
                for (int i = 0; i < onibus.getVagas().size(); i++) {
                	DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                	
                	PreparedStatement ps3 = conn.prepareStatement(sql);
                	ps3.setInt(1, onibus.getId());
                	ps3.setString(2, onibus.getHorarios().get(i).getInicio().format(formataBr));
                	ps3.setString(3, onibus.getHorarios().get(i).getFim().format(formataBr));
                	ps3.executeUpdate();
                	ps3.close();
                }
            }
            
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizar(Onibus onibus) {
    	 try {
             Connection conn = Conexao.conectar();
             String sql = "UPDATE " + NOMEDATABELA + " SET codigo = ? WHERE idOnibus = ?;";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, onibus.getCodigo());
             ps.setInt(2, onibus.getId());
             ps.executeUpdate();
             ps.close();
             
             sql = "DELETE FROM " + "horarioDeFuncionamento" + " WHERE onibus = ?";
             PreparedStatement ps1 = conn.prepareStatement(sql);
             ps1.setInt(1, onibus.getId());
             ps1.executeUpdate();
             
             if (onibus.getHorarios() != null) {
             	sql = "INSERT INTO " + "horarioDeFuncionamento" + " (onibus, inicio, fim) VALUES (?, ?, ?);";
                 for (int i = 0; i < onibus.getHorarios().size(); i++) {
                 	DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                 	
                 	PreparedStatement ps3 = conn.prepareStatement(sql);
                 	ps3.setInt(1, onibus.getId());
                 	ps3.setString(2, onibus.getHorarios().get(i).getInicio().format(formataBr));
                 	ps3.setString(3, onibus.getHorarios().get(i).getFim().format(formataBr));
                 	ps3.executeUpdate();
                 	ps3.close();
                 }
             }
             
             conn.close();
             return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
    }

    public boolean excluir(Onibus onibus) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idOnibus = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, onibus.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    public Onibus procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idOnibus = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Vaga> lista = procuraVagasDoOnibus(id);
            List<HorarioDeFuncionamento> lista2 = procuraHorariosDoOnibus(id);
            
            if (rs.next()) {
                Onibus obj = new Onibus();
                obj.setId(rs.getInt(1));
                obj.setCodigo(rs.getString(2));
                obj.setVagas(lista);
                obj.setHorarios(lista2);
                
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
    
    public Onibus procurarPorDescricao(Onibus onibus) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE codigo = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, onibus.getCodigo());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Onibus obj = new Onibus();
                obj.setId(rs.getInt(1));
                obj.setCodigo(rs.getString(2));
                obj.setVagas(procuraVagasDoOnibus(obj.getId()));
                obj.setHorarios(procuraHorariosDoOnibus(obj.getId()));
                
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
    public boolean existe(Onibus onibus) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE codigo = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, onibus.getCodigo());
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
    public List<Onibus> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Onibus> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Onibus> montarLista(ResultSet rs) {
        List<Onibus> listObj = new ArrayList<Onibus>();
        try {
            while (rs.next()) {
                Onibus obj = new Onibus();
                obj.setId(rs.getInt(1));
                obj.setCodigo(rs.getString(2));
                obj.setVagas(procuraVagasDoOnibus(obj.getId()));
                obj.setHorarios(procuraHorariosDoOnibus(obj.getId()));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Vaga> procuraVagasDoOnibus(int id){
    	try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + "vaga" + " WHERE onibus = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Vaga> listObj = montarListaVaga(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Vaga> montarListaVaga(ResultSet rs) {
        List<Vaga> listObj = new ArrayList<Vaga>();
        try {
            while (rs.next()) {
                Vaga obj = new Vaga();
                obj.setId(rs.getInt(1));
                obj.setNum(rs.getInt(2));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<HorarioDeFuncionamento> procuraHorariosDoOnibus(int id){
    	try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + "horariodefuncionamento" + " WHERE onibus = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<HorarioDeFuncionamento> listObj = montarListaHorarios(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<HorarioDeFuncionamento> montarListaHorarios(ResultSet rs) {
        List<HorarioDeFuncionamento> listObj = new ArrayList<HorarioDeFuncionamento>();
        try {
            while (rs.next()) {
                HorarioDeFuncionamento obj = new HorarioDeFuncionamento();
            	DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");

                obj.setId(rs.getInt(1));
                obj.setInicio(LocalDateTime.parse(rs.getString(3), formataBr));
                obj.setFim(LocalDateTime.parse(rs.getString(4), formataBr));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
