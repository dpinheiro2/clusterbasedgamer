package cbr.AtualizaConsultas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import ajudaCluster.converteFormatosCartasParaCartasJogadasClustering;
import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.PontuacaoModelo;
import cbr.AtualizaConsultas.AuxiliaConsultas.CartasModelo;
import cbr.AtualizaConsultas.AuxiliaConsultas.JogadasChamadasModelo;

public class AtualizarConsultas {


	public TrucoDescription atualizaConsultaEnvidoRobo(Integer quemEhMao, Integer pontosEnvido,
			List<CartasModelo> listaCartasJogadasOponente, int tentosAgente, int tentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		////System.out.println("atualiza consulta envido");
		cbr.setJogadorMao(quemEhMao);
		////System.out.println("quem mÃ£o: "+ quemEhMao);
		cbr.setPontosEnvidoRobo(pontosEnvido);
		////System.out.println("pontos envido robo "+ pontosEnvido);
		if (listaCartasJogadasOponente.size() >= 1) {
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
			////System.out.println("primeiraCarta humano: "+ cbr.getPrimeiraCartaHumano());
		
		}
//		cbr.setQuemGanhouEnvido(1);
		cbr.setTentosAnterioresRobo(tentosAgente);
		////System.out.println("tentos anteriores agente: "+ tentosAgente);
		
		cbr.setTentosAnterioresHumano(tentosOponente);
		////System.out.println("tentos anteriores humano: "+ tentosOponente);

		return cbr;
	}


	public TrucoDescription atualizaConsultaRealEnvidoRobo(Integer quemEhMao, Integer pontosEnvido,
			List<CartasModelo> listaCartasJogadasOponente, Integer quemPediuEnvido, int tentosAgente, int tentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		////System.out.println("atualiza consulta real envido");
		if(quemEhMao != 0 && quemEhMao != null )
		cbr.setJogadorMao(quemEhMao);
		////System.out.println("jogador mÃ£o: "+ quemEhMao);
		cbr.setPontosEnvidoRobo(pontosEnvido);
		////System.out.println("pontos envido robo: "+ pontosEnvido);
		if (listaCartasJogadasOponente.size() >= 1) {
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
			////System.out.println("primeira carta humano: "+ cbr.getPrimeiraCartaHumano());
		}
		if (quemPediuEnvido != 0 && quemPediuEnvido != null) {
			cbr.setQuemPediuEnvido(quemPediuEnvido);
			////System.out.println("quem pediu envido: "+ quemPediuEnvido);
		}
	
		cbr.setTentosAnterioresRobo(tentosAgente);
		////System.out.println("tentos agente: "+ tentosAgente);
		cbr.setTentosAnterioresHumano(tentosOponente);
		////System.out.println("tentos anteriores humano");
		return cbr;
	}

	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @param quemPediuEnvido
	 * @param quemPediuRealEnvido
	 * @return
	 */
	public TrucoDescription atualizaConsultaFaltaEnvidoRobo(Integer quemEhMao, Integer pontosEnvido,
			List<CartasModelo> listaCartasJogadasOponente, Integer quemPediuEnvido, Integer quemPediuRealEnvido,
			Integer Tentos, Integer TentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(quemEhMao);
		////System.out.println("=======================atualiza consulta falta envido robÃ´========================");
		////System.out.println("quem Ã© mÃ£o "+quemEhMao);
		////System.out.println("pontos envido robÃ´ "+pontosEnvido);
		
		
		cbr.setPontosEnvidoRobo(pontosEnvido);
		if (listaCartasJogadasOponente.size() >= 1) {
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
			////System.out.println("primeira carta oponente: "+  listaCartasJogadasOponente.get(0).getId());	
		}
		if (quemPediuEnvido != 0) {
			cbr.setQuemPediuEnvido(quemPediuEnvido);
			////System.out.println("quem pediu envido: "+ quemPediuEnvido);
		}
		if (quemPediuRealEnvido != 0)
			cbr.setQuemPediuRealEnvido(quemPediuRealEnvido);
            cbr.setQuemGanhouEnvido(1);
		    cbr.setTentosAnterioresRobo(Tentos);
	 	    cbr.setTentosAnterioresHumano(TentosOponente);

		 ////System.out.println("tentos anteriores robo: " + Tentos);
		 ////System.out.println("tentos humano: "+ TentosOponente);
		 ////System.out.println("=======================conclui consulta falta envido robÃ´========================");
		
		return cbr;
	}


	public TrucoDescription atualizaConsultaTrucoRobo(Integer JogadorMao, List<CartasModelo> listaCartasRecebidas,
			Integer GanhadorPrimeiraRodada, Integer GanhadorSegundaRodada, Integer GanhadorTerceiraRodada,
			Integer QuemTruco, Integer QuemRetruco, Integer QuemValeQuatro, List<CartasModelo> listaCartasJogadas,
			List<CartasModelo> listaCartasJogadasOponente, List<JogadasChamadasModelo> listaJogadasChamadas,
			Integer contador, Integer QuandoTruco, Integer QuandoRetruco, Integer QuandoValeQuatro, int tentosRobo, int tentosOponente) {

		TrucoDescription cbr = new TrucoDescription();
		//////System.out.println("jogador mÃ£o: "+ JogadorMao);
		if(!JogadorMao.equals(0)  && JogadorMao != null)
		cbr.setJogadorMao(JogadorMao);
		
		cbr.setCartaAltaRobo(listaCartasRecebidas.get(0).getId());
		cbr.setCartaMediaRobo(listaCartasRecebidas.get(1).getId());
		cbr.setCartaBaixaRobo(listaCartasRecebidas.get(2).getId());

		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaRobo(listaCartasJogadas.get(0).getId());
		if (listaCartasJogadas.size() >= 2)
			cbr.setSegundaCartaRobo(listaCartasJogadas.get(1).getId());
		if (listaCartasJogadas.size() >= 3)
			cbr.setTerceiraCartaRobo(listaCartasJogadas.get(2).getId());

		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaHumano(listaCartasJogadasOponente.get(1).getId());
		if (listaCartasJogadasOponente.size() >= 3)
			cbr.setTerceiraCartaHumano(listaCartasJogadasOponente.get(2).getId());

		if (contador > 1)
			cbr.setGanhadorPrimeiraRodada(GanhadorPrimeiraRodada);
		if (contador > 2)
			cbr.setGanhadorSegundaRodada(GanhadorSegundaRodada);
		if (contador > 3)
			cbr.setGanhadorTerceiraRodada(GanhadorTerceiraRodada);

		if (listaJogadasChamadas.size() >= 1)
			cbr.setQuemTruco(QuemTruco);

		if (listaJogadasChamadas.size() >= 2)
			cbr.setQuemRetruco(QuemRetruco);

		if (listaJogadasChamadas.size() >= 3)
			cbr.setQuemValeQuatro(QuemValeQuatro);
        if(QuandoTruco != null && !QuandoTruco.equals(0))
		cbr.setQuandoTruco(QuandoTruco);
		if(QuandoRetruco != null && !QuandoRetruco.equals(0))
        cbr.setQuandoRetruco(QuandoRetruco);
		if(QuandoValeQuatro != null && !QuandoValeQuatro.equals(0))
		cbr.setQuandoValeQuatro(QuandoValeQuatro);
        if(tentosRobo != 0)
		cbr.setTentosAnterioresRobo(tentosRobo);
        if(tentosOponente != 0)
        cbr.setTentosAnterioresHumano(tentosOponente);
//		//////System.out.println(cbr);
		salvaLog("\natualiza consulta truco robo \n" + cbr.toString());

		return cbr;
	}

		public TrucoDescription atualizaConsultaCartaRobo(Integer JogadorMao, Integer CartaAlta, Integer CartaMedia,
			Integer CartaBaixa, List<CartasModelo> listaCartasJogadas, List<CartasModelo> listaCartasJogadasOponente) {

		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(JogadorMao);
		//////System.out.println("jogador mÃ£o vindo do atualizaWeb: "+ JogadorMao);
		cbr.setCartaAltaRobo(CartaAlta);
		cbr.setCartaMediaRobo(CartaMedia);
		cbr.setCartaBaixaRobo(CartaBaixa);

		if (listaCartasJogadas.size() >= 1) {
			cbr.setPrimeiraCartaRobo(listaCartasJogadas.get(0).getId());
			int primeiraCartaRoboClustering = new converteFormatosCartasParaCartasJogadasClustering().retornaCodificacaoPrimeiraCartaJogadaRoboClustering(cbr).getPrimeiraCartaRoboClustering();
			cbr.setPrimeiraCartaRoboClustering(primeiraCartaRoboClustering);
		}
		if (listaCartasJogadas.size() >= 2) {
			cbr.setSegundaCartaRobo(listaCartasJogadas.get(1).getId());
			int segundaCartaRoboClustering = new converteFormatosCartasParaCartasJogadasClustering().retornaCodificacaoSegundaCartaJogadaRoboClustering(cbr).getSegundaCartaRoboClustering();
			cbr.setSegundaCartaRoboClustering(segundaCartaRoboClustering);
		}

		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaHumano(listaCartasJogadasOponente.get(1).getId());
		// valida ganhador da primeira rodada
		if (listaCartasJogadasOponente.size() >= 1 && listaCartasJogadas.size() >= 1) {
			if (listaCartasJogadas.get(0).getId() > listaCartasJogadasOponente.get(0).getId()
					|| (listaCartasJogadas.get(0).getId() == listaCartasJogadasOponente.get(0).getId()
							&& JogadorMao == 1))
				cbr.setGanhadorPrimeiraRodada(1);
			else if (listaCartasJogadas.get(0).getId() < listaCartasJogadasOponente.get(0).getId()
					|| (listaCartasJogadas.get(0).getId() == listaCartasJogadasOponente.get(0).getId()
							&& JogadorMao == 2))
				cbr.setGanhadorPrimeiraRodada(2);

		}

		if (listaCartasJogadasOponente.size() >= 2 && listaCartasJogadas.size() >= 2) {
			if (listaCartasJogadas.get(1).getId() > listaCartasJogadasOponente.get(1).getId()
					|| (listaCartasJogadas.get(1).getId() == listaCartasJogadasOponente.get(1).getId()
							&& cbr.getGanhadorPrimeiraRodada() == 1))
				cbr.setGanhadorSegundaRodada(1);
			else if (listaCartasJogadas.get(1).getId() < listaCartasJogadasOponente.get(1).getId()
					|| (listaCartasJogadas.get(1).getId() == listaCartasJogadasOponente.get(1).getId()
							&& cbr.getGanhadorPrimeiraRodada() == 2))
				cbr.setGanhadorSegundaRodada(2);

		}

		salvaLog("\natualiza consulta carta robo \n" + cbr.toString());
		return cbr;
	}

	/**
	 * @param listaCartas
	 * @return
	 */
	public TrucoDescription atualizaConsultaFlorHumano(List<CartasModelo> listaCartas) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setNaipeCartaAltaHumano(listaCartas.get(0).getNaipe());
		cbr.setNaipeCartaMediaHumano(listaCartas.get(1).getNaipe());
		cbr.setNaipeCartaBaixaHumano(listaCartas.get(2).getNaipe());

		salvaLog("\natualiza consulta flor humano\n" + cbr.toString());

		return cbr;
	}

	/**
	 * @param listaCartas
	 * @return
	 */
	public TrucoDescription atualizaConsultaFlorRobo(List<CartasModelo> listaCartas) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setNaipeCartaAltaRobo(listaCartas.get(0).getNaipe());
		cbr.setNaipeCartaMediaRobo(listaCartas.get(1).getNaipe());
		cbr.setNaipeCartaBaixaRobo(listaCartas.get(2).getNaipe());

		salvaLog("\natualiza consulta flor robo \n" + cbr.toString());

		return cbr;
	}



	/**
	 * @param pontosFlor
	 * @return
	 */
	public TrucoDescription atualizaConsultaContraFlorRobo(Integer pontosFlor) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setPontosFlorRobo(pontosFlor);

		salvaLog("\natualiza consulta contra flor robo \n" + cbr.toString());

		return cbr;
	}

	
	/**
	 * @param pontosFlor
	 * @return
	 */
	public TrucoDescription atualizaConsultaContraFlorRestoRobo(Integer pontosFlor, Integer Tentos,
			Integer TentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setPontosFlorRobo(pontosFlor);
		cbr.setTentosAnterioresRobo(Tentos);
		cbr.setTentosAnterioresHumano(TentosOponente);

		salvaLog("\natualiza consulta contra flor resto robo \n" + cbr.toString());

		return cbr;
	}


	public TrucoDescription atualizaConsultaBaralhoRobo(Integer JogadorMao, Integer CartaAlta, Integer CartaMedia,
			Integer CartaBaixa, Integer GanhadorPrimeiraRodada, Integer GanhadorSegundaRodada,
			Integer GanhadorTerceiraRodada, List<CartasModelo> listaCartasJogadas,
			List<CartasModelo> listaCartasJogadasOponente, List<JogadasChamadasModelo> listaJogadasChamadas,
			Integer contador, int tentosAgente, int tentosOponente) {
		TrucoDescription cbr = new TrucoDescription();

		cbr.setJogadorMao(JogadorMao);

		cbr.setCartaAltaRobo(CartaAlta);
		cbr.setCartaMediaRobo(CartaMedia);
		cbr.setCartaBaixaRobo(CartaBaixa);

		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaRobo(listaCartasJogadas.get(0).getId());
		if (listaCartasJogadas.size() >= 2)
			cbr.setSegundaCartaRobo(listaCartasJogadas.get(1).getId());
		if (listaCartasJogadas.size() >= 3)
			cbr.setTerceiraCartaRobo(listaCartasJogadas.get(2).getId());

		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaHumano(listaCartasJogadasOponente.get(1).getId());
		if (listaCartasJogadasOponente.size() >= 3)
			cbr.setTerceiraCartaHumano(listaCartasJogadasOponente.get(2).getId());

		if (contador > 1)
			cbr.setGanhadorPrimeiraRodada(GanhadorPrimeiraRodada);
		if (contador > 2)
			cbr.setGanhadorSegundaRodada(GanhadorSegundaRodada);
		if (contador > 3)
			cbr.setGanhadorTerceiraRodada(GanhadorTerceiraRodada);
		salvaLog("\natualiza consulta Baralho robo \n" + cbr.toString());
		return cbr;

	}

	/**
	 * @param number
	 * @return
	 */
	private Integer complementa(Integer number) {
		if (number == 1)
			return 2;
		else
			return 1;
	}

	public void salvaLog(String Dados) {
//		try {
//			File arquivo = new File("Logs_Consultas.txt");
//			FileWriter fw = new FileWriter(arquivo, true);
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.append(Dados);
//			bw.flush();
//			bw.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}