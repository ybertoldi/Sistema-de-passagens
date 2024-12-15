package com.classes.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classes.Conexao.Conexao;

import entidades.Admin;
import entidades.Cliente;
import entidades.Endereco;
import entidades.Usuario;

public class UsuarioDAO {

	final String NOMEDATABELA = "Usuario";

	public boolean inserir(Usuario usuario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "INSERT INTO " + NOMEDATABELA
					+ " (nome, sobrenome, dataDeNascimento, cpf, tipo, endereco) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSobrenome());
			ps.setString(3, usuario.getDataDeNascimentoFormatada());
			ps.setString(4, usuario.getCpf());

			int tipo = (usuario.getClass() == Admin.class) ? 1 : 0;
			ps.setInt(5, tipo);

			if (usuario.getEndereco().getId() != -1) {
				ps.setInt(6, usuario.getEndereco().getId());
			} else {
				EnderecoDAO ed = new EnderecoDAO();
				Endereco endereco = null;
				if (ed.existe(usuario.getEndereco())) {
					endereco = ed.procurarPorDescricao(usuario.getEndereco());
				} else {
					ed.inserir(usuario.getEndereco());
					endereco = ed.procurarPorDescricao(usuario.getEndereco());
					usuario.setEndereco(endereco);
				}
				ps.setInt(6, endereco.getId());
			}

			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Usuario usuario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "UPDATE " + NOMEDATABELA + " " + "SET " + "nome = ?, " + "sobrenome = ?, "
					+ "dataDeNascimento = ?, " + "cpf = ?, " + "tipo = ?, " + "endereco = ? " + "WHERE idUsuario = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSobrenome());
			ps.setString(3, usuario.getDataDeNascimentoFormatada());
			ps.setString(4, usuario.getCpf());

			int tipo = (usuario.getClass() == Admin.class) ? 1 : 0;
			ps.setInt(5, tipo);

			if (usuario.getEndereco().getId() != -1) {
				ps.setInt(6, usuario.getEndereco().getId());
			} else {
				EnderecoDAO ed = new EnderecoDAO();
				Endereco endereco;
				if (ed.existe(usuario.getEndereco())) {
					endereco = ed.procurarPorDescricao(usuario.getEndereco());
				} else {
					ed.inserir(usuario.getEndereco());
					endereco = ed.procurarPorDescricao(usuario.getEndereco());
					usuario.setEndereco(endereco);
				}
				ps.setInt(6, endereco.getId());
			}
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(Usuario usuario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idUsuario = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, usuario.getId());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// (nome, sobrenome, dataDeNascimento, cpf, tipo, endereco)
	public Usuario procurarPorId(int id) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idUsuario = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				EnderecoDAO ed = new EnderecoDAO();
				Usuario obj = new Usuario();
				obj.setId(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setSobrenome(rs.getString(3));
				obj.setDataDeNascimento(rs.getString(4));
				obj.setCpf(rs.getString(5));
				obj.setEndereco(ed.procurarPorId(rs.getInt(7)));

				Usuario rObj = (rs.getInt(6) == 1) ? new Admin(obj) : new Cliente(obj);

				ps.close();
				rs.close();
				conn.close();
				return rObj;
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

	public Usuario procurarPorDescricao(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE "
            		+ "nome = ? AND "
            		+ "sobrenome = ? AND "
            		+ "dataDeNascimento = ? AND "
            		+ "cpf = ? AND "
            		+ "tipo = ? AND "
            		+ "endereco = ? ;";
            PreparedStatement ps = conn.prepareStatement(sql);
            EnderecoDAO ed = new EnderecoDAO();
            if (!ed.existe(usuario.getEndereco())) {
            	return null;
            }
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getDataDeNascimentoFormatada());
            ps.setString(4, usuario.getCpf());
            
            int tipo = (usuario.getClass() == Admin.class)? 1 : 0;
            ps.setInt(5, tipo);
            ps.setInt(6, ed.procurarPorDescricao(usuario.getEndereco()).getId());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setId(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setSobrenome(rs.getString(3));
				obj.setDataDeNascimento(rs.getString(4));
				obj.setCpf(rs.getString(5));
				obj.setEndereco(ed.procurarPorId(rs.getInt(7)));

				Usuario rObj = (rs.getInt(6) == 1) ? new Admin(obj) : new Cliente(obj);

                ps.close();
                rs.close();
                conn.close();
                return rObj;
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
    //(nome, sobrenome, dataDeNascimento, cpf, tipo, endereco)

	public boolean existe(Usuario usuario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE " 
					+ "nome = ? AND " 
					+ "sobrenome = ? AND "
					+ "dataDeNascimento = ? AND " 
					+ "cpf = ? AND " 
					+ "tipo = ? AND "
					+ "endereco = ? ;";

			PreparedStatement ps = conn.prepareStatement(sql);
            EnderecoDAO ed = new EnderecoDAO();
            if (!ed.existe(usuario.getEndereco())) {
            	return false;
            }
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getDataDeNascimentoFormatada());
            ps.setString(4, usuario.getCpf());
            
            int tipo = (usuario.getClass() == Admin.class)? 1 : 0;
            ps.setInt(5, tipo);
            ps.setInt(6, ed.procurarPorDescricao(usuario.getEndereco()).getId());
            
			
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

	public List<Usuario> pesquisarTodos() {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Usuario> listObj = montarLista(rs);
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Usuario> pesquisarTodosAdmins() {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE tipo = 1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Usuario> listObj = montarLista(rs);
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Usuario> pesquisarTodosClientes() {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE tipo = 0;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Usuario> listObj = montarLista(rs);
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//(nome, sobrenome, dataDeNascimento, cpf, tipo, endereco) 
	public List<Usuario> montarLista(ResultSet rs) {
		List<Usuario> listObj = new ArrayList<Usuario>();
		try {
			while (rs.next()) {
				EnderecoDAO ed = new EnderecoDAO();
				
				Usuario obj = new Usuario();
				obj.setId(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setSobrenome(rs.getString(3));
				obj.setDataDeNascimento(rs.getString(4));
				obj.setCpf(rs.getString(5));
				obj.setEndereco( ed.procurarPorId(rs.getInt(7)));
				Usuario rObj = (rs.getInt(6) == 1) ? new Admin(obj) : new Cliente(obj);
				listObj.add(rObj);
			}
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
