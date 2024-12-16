package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.classes.Conexao.Conexao;
import com.classes.DTO.Vaga;

public class VagaDAO {

    final String NOMEDATABELA = "Vaga";
    
    public Vaga procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idVaga = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vaga obj = new Vaga();
                obj.setId(rs.getInt(1));
                obj.setNum(rs.getInt(2));
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
    
}
