package com.classes.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.classes.Conexao.Conexao;

import entidades.Admin;
import entidades.Cliente;
import entidades.Endereco;
import entidades.Parada;
import entidades.PontoParada;
import entidades.Viagem;

public class ViagemDAO {

	final String NOMEDATABELA = "Viagem";

	public boolean inserir(Viagem viagem) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "INSERT INTO " + NOMEDATABELA + " (onibus, descricao) VALUES (?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, viagem.getOnibus().getId());
			ps.setString(2, viagem.getDesc());
			ps.executeUpdate();
			ps.close();
			viagem.setId(procurarIdPorDescricao(viagem));
			System.out.println();
			sql = "INSERT INTO " + "viagem_has_parada" + " (viagem, parada, horario) VALUES (?, ?, ?);";
			DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
			for (PontoParada p : viagem.getPontosDeParada()) {
				PreparedStatement ps1 = conn.prepareStatement(sql);

				ps1.setInt(1, viagem.getId());
				ParadaDAO pd = new ParadaDAO();
				if (!pd.existe(p.getParada())) {
					pd.inserir(p.getParada());
				}
				if (p.getParada().getId() == 0) {
					p.setParada(pd.procurarPorDescricao(p.getParada())) ;
				}
				ps1.setInt(2, p.getParada().getId());
				ps1.setString(3, p.getHorario().format(formataBr));
				ps1.executeUpdate();
				ps1.close();
			}

			OnibusDAO onibusD = new OnibusDAO();
			onibusD.atualizar(viagem.getOnibus());
	
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Viagem viagem) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "UPDATE " + NOMEDATABELA + " " + "SET descricao = ?, onibus = ? WHERE idViagem = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, viagem.getDesc());
			ps.setInt(2, viagem.getOnibus().getId());
			ps.executeUpdate();
			ps.close();

			sql = "DELETE FROM" + "viagem_has_parada" + " WHERE viagem = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viagem.getId());
			ps.executeUpdate();
			ps.close();

			sql = "INSERT INTO " + "viagem_has_parada" + " (viagem, parada, horario) VALUES (?, ?, ?);";
			DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
			for (PontoParada p : viagem.getPontosDeParada()) {
				PreparedStatement ps1 = conn.prepareStatement(sql);

				ps1.setInt(1, viagem.getId());
				ParadaDAO pd = new ParadaDAO();
				if (!pd.existe(p.getParada())) {
					pd.inserir(p.getParada());
				}
				ps1.setInt(2, p.getParada().getId());
				ps1.setString(3, p.getHorario().format(formataBr));
				ps1.executeUpdate();
				ps1.close();
			}

			conn.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(Viagem viagem) {
		try {
			Connection conn = Conexao.conectar();

			String sql = "DELETE FROM " + " viagem_has_parada " + " WHERE viagem = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, viagem.getId());
			ps.executeUpdate();
			ps.close();
			
			sql = "DELETE FROM " + NOMEDATABELA + " WHERE idViagem = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viagem.getId());
			ps.executeUpdate();
			ps.close();
			
			sql = "DELETE FROM " + " horariodefuncionamento " + " WHERE onibus = ? AND inicio = ? AND fim = ?;";
			DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viagem.getOnibus().getId());
			ps.setString(2, viagem.getPontosDeParada().get(0).getHorario().format(formataBr));
			ps.setString(3, viagem.getPontosDeParada().get(viagem.getPontosDeParada().size() -1).getHorario().format(formataBr));
			ps.executeUpdate();
			
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Viagem procurarPorId(int id) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idViagem = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Viagem obj = new Viagem();
				OnibusDAO od = new OnibusDAO();
				obj.setId(rs.getInt(1));
				obj.setOnibus(od.procurarPorId(rs.getInt(2)));
				obj.setPontosDeParada(procuraPontosDeParada(obj));
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

	private List<PontoParada> procuraPontosDeParada(Viagem obj) {
		try {
			Connection conn = Conexao.conectar();
			String sql1 = "SELECT * FROM viagem_has_parada WHERE viagem = ?;";
			PreparedStatement ps1;
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, obj.getId());
			ResultSet rs = ps1.executeQuery();
			List<PontoParada> rList = montarListaDePontoParada(rs);
			return rList;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Viagem procurarPorDescricao(Viagem viagem) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE desc = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, viagem.getDesc());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Viagem obj = new Viagem();
				OnibusDAO od = new OnibusDAO();
				obj.setId(rs.getInt(1));
				obj.setOnibus(od.procurarPorId(rs.getInt(2)));
				obj.setPontosDeParada(procuraPontosDeParada(obj));

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

	public boolean existe(Viagem viagem) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE desc = ? AND onibus = ? ;";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, viagem.getDesc());
			ps.setInt(2, viagem.getOnibus().getId());
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

	public List<Viagem> pesquisarTodos() {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Viagem> listObj = montarLista(rs);
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<Viagem> montarLista(ResultSet rs) {
		List<Viagem> listObj = new ArrayList<Viagem>();
		try {
			while (rs.next()) {
				Viagem obj = new Viagem();
				OnibusDAO od = new OnibusDAO();
				
				obj.setId(rs.getInt(1));
				obj.setOnibus(od.procurarPorId(rs.getInt(2)));
				obj.setPontosDeParada(procuraPontosDeParada(obj));
				listObj.add(obj);
			}
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PontoParada> montarListaDePontoParada(ResultSet rs) {
		List<PontoParada> listObj = new ArrayList<PontoParada>();
		try {
			while (rs.next()) {
				DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
				ParadaDAO pd = new ParadaDAO();
				PontoParada obj = new PontoParada();
				obj.setParada(pd.procurarPorId(rs.getInt(2)));
				obj.setHorario(LocalDateTime.parse(rs.getString(3), formataBr));
				listObj.add(obj);
			}
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int procurarIdPorDescricao(Viagem viagem) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE descricao = ? AND onibus = ? ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, viagem.getDesc());
			ps.setInt(2, viagem.getOnibus().getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				ps.close();
				rs.close();
				conn.close();
				return id;
			} else {
				ps.close();
				rs.close();
				conn.close();
				return -1;
			}
		} catch (Exception e) {
			return -1;
		}
	}
}
