package com.classes.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.classes.BO.OnibusBO;
import com.classes.BO.ParadaBO;
import com.classes.BO.PassagemBO;
import com.classes.BO.UsuarioBO;
import com.classes.BO.ViagemBO;
import com.classes.DAO.OnibusDAO;
import com.classes.DAO.ViagemDAO;

import entidades.Admin;
import entidades.Cliente;
import entidades.Endereco;
import entidades.Onibus;
import entidades.Parada;
import entidades.Passagem;
import entidades.PontoParada;
import entidades.Usuario;
import entidades.Vaga;
import entidades.Viagem;

public class MainInsercao {
	public static void main(String[] args) {

//		UsuarioBO usuarioBO = new UsuarioBO();
//		Endereco endereco = new Endereco("89162-830", "Rio do Sul", "Vereadores", "Itoupava", "4400");
//		Cliente usuario = new Cliente("mussum", "mussolis", LocalDate.parse("1940-05-15"), "095-149-829-08", endereco);
//		
//		if (usuarioBO.inserir(usuario)) {
//			System.out.println("inserido");
//		}
//		else {
//			System.out.println("erro ao inserir");
//		}
//		
//		List<Usuario> lista = usuarioBO.pesquisarTodos();
//		for (Usuario u : lista) {
//			System.out.println(u.toString());
//		}
		
//		OnibusBO onibusBO = new OnibusBO();
//		Onibus onibus = new Onibus();
//		onibus.setCodigo("H1-23");
//		List<Vaga> vagas = new ArrayList<Vaga>(); 
//		for(int i = 1; i <= 20; i++) {
//			Vaga novaVaga = new Vaga();
//			novaVaga.setNum(i);
//			vagas.add(novaVaga);
//		}
//		onibus.setVagas(vagas);
//		
//		if (onibusBO.inserir(onibus)) {
//			System.out.println("deu boa");
//		}
//		else {
//			System.out.println("erro");
//		}
		
//		OnibusBO onibusBO = new OnibusBO();
//		for (Onibus o : onibusBO.pesquisarTodos()) {
//			System.out.println(o);
//		}
		
//		ParadaBO paradaBO = new ParadaBO();
//		String[] paradas = new String[] {"Rio do Sul", "Ibirama", "Ascurra", "Blumenau", "Ilhota", "Balneario Camboriu", "Itapema", "Palhoca", "Florianopolis"};
//		for (String cidade: paradas) {
//			Parada p = new Parada();
//			p.setCidade(cidade);
//			if (paradaBO.inserir(p)) {
//				System.out.println(cidade + " inserida.");
//			}
//			else {
//				System.err.println("erro ao inserir " + cidade);
//			}
//			
//		}
				
//		ParadaBO paradaBO = new ParadaBO();
//		for (Parada p : paradaBO.pesquisarTodos()) {
//			System.out.println(p);
//		}

		
		
		
////////////////////////////////TESTE INSERIR VIAGEM////////////////////////////////	
//		DateTimeFormatter formataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
//		String[] paradas = new String[] {"Rio do Sul", "Blumenau", "Balneario Camboriu", "Itapema"};
//		LocalDateTime horarios[] = new LocalDateTime[]{
//				LocalDateTime.parse("11/12/2024 - 13:30:00", formataBr),
//				LocalDateTime.parse("11/12/2024 - 15:00:00", formataBr),
//				LocalDateTime.parse("11/12/2024 - 16:45:00", formataBr), 
//				LocalDateTime.parse("11/12/2024 - 19:30:00", formataBr)};
//		
//		Viagem viagem = new Viagem();
//		viagem.setPontosDeParada(new ArrayList<PontoParada>());
//		for (int i = 0; i < 4; i++) {
//			PontoParada pp = new PontoParada();
//			pp.setHorario(horarios[i]);
//			pp.setParada(new Parada());
//			pp.getParada().setCidade(paradas[i]);
//			
//			viagem.getPontosDeParada().add(pp);
//		}
//		OnibusDAO onibusD = new OnibusDAO();
//		ViagemDAO viagemD = new ViagemDAO();
//		viagem.setOnibus(onibusD.procurarPorId(1));
//		viagem.atualizaHorariosOnibus();
//		
//		if (viagemD.inserir(viagem)) {
//			System.out.println("inserida com sucesso");
//		}
//		else {
//			System.out.println("erro ao inserir");
//		}
		
////////////////////////////////TESTE lISTAVIAGEM////////////////////////////////	
//		ViagemBO viagemB = new ViagemBO();
//		List<Viagem> vs = viagemB.pesquisarTodos();
//		for (Viagem v : vs) {
//			System.out.println(v.getId());
//			System.out.println(v.getDesc());
//			System.out.println(v.getPontosDeParada());
//			System.out.println(v.getOnibus());
//		}

////////////////////////////////TESTE EXCLUIR VIAGEM////////////////////////////////	
//		ViagemBO viagemB = new ViagemBO();
//		Viagem viagem = viagemB.pesquisarTodos().get(0);
//		
//		if (viagemB.excluir(viagem)) {
//			System.out.println("Excluido com sucesso");
//		}
//		else {
//			System.out.println("Erro ao excluir");
//		}
		
////////////////////////////////TESTE INSERIR PASSAGEM////////////////////////////////	
//		PassagemBO passagemBO = new PassagemBO();
//		UsuarioBO usuarioBO = new UsuarioBO();
//		ViagemBO viagemBO = new ViagemBO();
//		
//		Viagem viagem = viagemBO.pesquisarTodos().get(0);
//		Usuario usuario = usuarioBO.pesquisarTodos().get(0);
//		
//		Passagem passagem = new Passagem();
//		passagem.setCliente(usuario);
//		passagem.setNumeroPassagem("A23H1");
//		passagem.setEmbarque(viagem.getPontosDeParada().get(0));
//		passagem.setSaida(viagem.getPontosDeParada().get(2));
//		passagem.setVaga(viagem.getOnibus().getVagas().get(4));
//		passagem.setViagem(viagem);
//		
//		if (passagemBO.inserir(passagem)) {
//			System.out.println("inserido com sucesso");
//		}
//		else {
//			System.out.println("erro ao inserir");
//		}
		
////////////////////////////////TESTE PESQUISAR PASSAGEM////////////////////////////////	
//		PassagemBO passagemBO = new PassagemBO();
//		Passagem passagem = passagemBO.procurarPorId(1);
//		
//		System.out.println(passagem.getId());
//		System.out.println(passagem.getNumeroPassagem());
//		System.out.println(passagem.getCliente());
//		System.out.println(passagem.getEmbarque());
//		System.out.println(passagem.getSaida());
//		System.out.println(passagem.getViagem());
//		System.out.println(passagem.getVaga());
//
		
////////////////////////////////TESTE PESQUISAR LISTA DE PASSAGEM////////////////////////////////	
//		PassagemBO passagemBO = new PassagemBO();
//		Passagem passagem = passagemBO.pesquisarTodos().get(0);
//		
//		System.out.println(passagem.getId());
//		System.out.println(passagem.getNumeroPassagem());
//		System.out.println(passagem.getCliente());
//		System.out.println(passagem.getEmbarque());
//		System.out.println(passagem.getSaida());
//		System.out.println(passagem.getViagem());
//		System.out.println(passagem.getVaga());
//		
	}
	
}