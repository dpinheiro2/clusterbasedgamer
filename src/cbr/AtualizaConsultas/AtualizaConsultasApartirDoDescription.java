package cbr.AtualizaConsultas;

import java.util.List;

import cbr.AtualizaConsultas.AuxiliaConsultas.CartasModelo;
import cbr.AtualizaConsultas.AuxiliaConsultas.JogadasChamadasModelo;
import cbr.cbrDescriptions.TrucoDescription;

public class AtualizaConsultasApartirDoDescription {


	
	
	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @return
	 */
	public TrucoDescription atualizaConsultaEnvidoRoboMao(TrucoDescription newTrucoDescription) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setPontosEnvidoRobo(newTrucoDescription.getPontosEnvidoRobo());
		
//		cbr.setQuemGanhouEnvido(1);
		salvaLog("\natualiza consulta envido robo mao para cluster\n" + cbr.toString());
		return cbr;
	}

	public TrucoDescription atualizaConsultaEnvidoRoboPe(TrucoDescription newTrucoDescription) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setPontosEnvidoRobo(newTrucoDescription.getPontosEnvidoRobo());
		
		cbr.setPrimeiraCartaHumano(newTrucoDescription.getPrimeiraCartaHumano());
//		cbr.setQuemGanhouEnvido(1);
		salvaLog("\natualiza consulta envido robo pé para cluster\n" + cbr.toString());
		return cbr;
	}
	
	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @param quemPediuEnvido
	 * @return
	 */
	
	public TrucoDescription atualizaConsultaRealEnvidoRoboMao(TrucoDescription newTrucoDescription) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setPontosEnvidoRobo(newTrucoDescription.getPontosEnvidoRobo());
		
		
		cbr.setQuemPediuEnvido(newTrucoDescription.getQuemPediuEnvido());
//		cbr.setQuemGanhouEnvido(1);
		salvaLog("\natualiza consulta real envido robo pé\n" + cbr.toString());
		return cbr;
	}
	
	public TrucoDescription atualizaConsultaRealEnvidoRoboPe(TrucoDescription newTrucoDescription) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setPontosEnvidoRobo(newTrucoDescription.getPontosEnvidoRobo());
		cbr.setPrimeiraCartaHumano(newTrucoDescription.getPrimeiraCartaHumano());
		
		cbr.setQuemPediuEnvido(newTrucoDescription.getQuemPediuEnvido());
//		cbr.setQuemGanhouEnvido(1);
		salvaLog("\natualiza consulta real envido robo pé\n" + cbr.toString());
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
	
	public TrucoDescription atualizaConsultaRoboMao(TrucoDescription newTrucoDescription) {
		TrucoDescription cbr = new TrucoDescription();
		
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setPontosEnvidoRobo(newTrucoDescription.getPontosEnvidoRobo());

		if (newTrucoDescription.getQuemPediuEnvido() != 0)
			cbr.setQuemPediuEnvido(newTrucoDescription.getQuemPediuEnvido());
		if (newTrucoDescription.getQuemPediuRealEnvido() != 0)
			cbr.setQuemPediuRealEnvido(newTrucoDescription.getQuemPediuRealEnvido());
//		cbr.setQuemGanhouEnvido(1);
		cbr.setTentosAnterioresRobo(newTrucoDescription.getTentosAnterioresRobo());
		cbr.setTentosAnterioresHumano(newTrucoDescription.getTentosAnterioresHumano());

		salvaLog("\natualiza consulta falta envido robo \n" + cbr.toString());
		return cbr;
	}
	
	public TrucoDescription atualizaConsultaFaltaEnvidoRoboPe(TrucoDescription newTrucoDescription) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setPontosEnvidoRobo(newTrucoDescription.getPontosEnvidoRobo());
		
		cbr.setPrimeiraCartaHumano(newTrucoDescription.getPrimeiraCartaHumano());
		if (newTrucoDescription.getQuemPediuEnvido() != 0)
			cbr.setQuemPediuEnvido(newTrucoDescription.getQuemPediuEnvido());
		if (newTrucoDescription.getQuemPediuRealEnvido() != 0)
			cbr.setQuemPediuRealEnvido(newTrucoDescription.getQuemPediuRealEnvido());
//		cbr.setQuemGanhouEnvido(1);
		cbr.setTentosAnterioresRobo(newTrucoDescription.getTentosAnterioresRobo());
		cbr.setTentosAnterioresHumano(newTrucoDescription.getTentosAnterioresHumano());

		salvaLog("\natualiza consulta falta envido robo \n" + cbr.toString());
		return cbr;
	}


	/**
	 * @param JogadorMao
	 * @param CartaAlta
	 * @param CartaMedia
	 * @param CartaBaixa
	 * @param GanhadorPrimeiraRodada
	 * @param GanhadorSegundaRodada
	 * @param GanhadorTerceiraRodada
	 * @param QuemTruco
	 * @param QuemRetruco
	 * @param QuemValeQuatro
	 * @param listaCartasJogadas
	 * @param listaCartasJogadasOponente
	 * @param listaJogadasChamadas
	 * @param contador
	 * @return
	 */
	public TrucoDescription atualizaConsultaTrucoRobo(TrucoDescription newTrucoDescription) {

		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setCartaAltaRobo(newTrucoDescription.getCartaAltaRobo());
		cbr.setCartaMediaRobo(newTrucoDescription.getCartaMediaRobo());
		cbr.setCartaBaixaRobo(newTrucoDescription.getCartaBaixaRobo());

		if (newTrucoDescription.getPrimeiraCartaRobo() != null && newTrucoDescription.getPrimeiraCartaRobo() !=0)
			cbr.setPrimeiraCartaRobo(newTrucoDescription.getPrimeiraCartaRobo());
		if (newTrucoDescription.getSegundaCartaRobo() != null && newTrucoDescription.getSegundaCartaRobo() != 0)
			cbr.setSegundaCartaRobo(newTrucoDescription.getSegundaCartaRobo());
		if (newTrucoDescription.getTerceiraCartaRobo() != null && newTrucoDescription.getTerceiraCartaRobo() != 0)
			cbr.setTerceiraCartaRobo(newTrucoDescription.getTerceiraCartaRobo());

		if (newTrucoDescription.getPrimeiraCartaHumano() != null  && newTrucoDescription.getPrimeiraCartaHumano() != 0)
			cbr.setPrimeiraCartaHumano(newTrucoDescription.getPrimeiraCartaHumano());
		
		if (newTrucoDescription.getSegundaCartaHumano() != null && newTrucoDescription.getSegundaCartaHumano() != 0)
			cbr.setSegundaCartaHumano(newTrucoDescription.getSegundaCartaHumano());
		
		if (newTrucoDescription.getTerceiraCartaHumano() != null && newTrucoDescription.getTerceiraCartaHumano() !=0 )
			cbr.setTerceiraCartaHumano(newTrucoDescription.getTerceiraCartaHumano());

		if (newTrucoDescription.getGanhadorPrimeiraRodada() != null && newTrucoDescription.getGanhadorPrimeiraRodada() != 0)
			cbr.setGanhadorPrimeiraRodada(newTrucoDescription.getGanhadorPrimeiraRodada());
		
		if (newTrucoDescription.getGanhadorSegundaRodada() != null && newTrucoDescription.getGanhadorSegundaRodada() != 0)
			cbr.setGanhadorSegundaRodada(newTrucoDescription.getGanhadorSegundaRodada());
		
		if (newTrucoDescription.getGanhadorTerceiraRodada() != null && newTrucoDescription.getGanhadorTerceiraRodada() != 0)
			cbr.setGanhadorTerceiraRodada(newTrucoDescription.getGanhadorTerceiraRodada());

		if (newTrucoDescription.getQuemTruco() != null && newTrucoDescription.getQuemTruco() != 0)
			cbr.setQuemTruco(newTrucoDescription.getQuemTruco());

		if (newTrucoDescription.getQuemRetruco() != null && newTrucoDescription.getQuemRetruco() != 0)
			cbr.setQuemRetruco(newTrucoDescription.getQuemRetruco());

		if (newTrucoDescription.getQuemValeQuatro() !=null && newTrucoDescription.getQuemValeQuatro() != 0)
			cbr.setQuemValeQuatro(newTrucoDescription.getQuemValeQuatro());
        if(newTrucoDescription.getQuandoTruco() != null && newTrucoDescription.getQuandoTruco() != 0 )
		cbr.setQuandoTruco(newTrucoDescription.getQuandoTruco());
        
        if(newTrucoDescription.getQuandoRetruco() != null && newTrucoDescription.getQuandoRetruco() != 0)
        cbr.setQuandoRetruco(newTrucoDescription.getQuandoRetruco());
        if(newTrucoDescription.getQuandoValeQuatro() != null && newTrucoDescription.getQuandoValeQuatro() != 0)
		cbr.setQuandoValeQuatro(newTrucoDescription.getQuandoValeQuatro());

//		//////System.out.println(cbr);
		salvaLog("\natualiza consulta truco robo \n" + cbr.toString());

		return cbr;
	}

	
	/**
	 * @param JogadorMao
	 * @param CartaAlta
	 * @param CartaMedia
	 * @param CartaBaixa
	 * @param listaCartasJogadas
	 * @param listaCartasJogadasOponente
	 * @return
	 */
	public TrucoDescription atualizaConsultaTerceiraCartaRobo(TrucoDescription newTrucoDescription) {

		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(newTrucoDescription.getJogadorMao());
		cbr.setCartaAltaRobo(newTrucoDescription.getCartaAltaRobo());
		cbr.setCartaMediaRobo(newTrucoDescription.getCartaMediaRobo());
		cbr.setCartaBaixaRobo(newTrucoDescription.getCartaBaixaRobo());

		if (newTrucoDescription.getPrimeiraCartaRobo() !=null && newTrucoDescription.getPrimeiraCartaRobo() != 0)
			cbr.setPrimeiraCartaRobo(newTrucoDescription.getPrimeiraCartaRobo());
		
		if (newTrucoDescription.getSegundaCartaRobo() != null  && newTrucoDescription.getSegundaCartaRobo() !=0)
			cbr.setSegundaCartaRobo(newTrucoDescription.getSegundaCartaRobo());

		if (newTrucoDescription.getPrimeiraCartaHumano() != null && newTrucoDescription.getPrimeiraCartaHumano() != 0)
			
			cbr.setPrimeiraCartaHumano(newTrucoDescription.getTerceiraCartaRobo());
		
		if (newTrucoDescription.getSegundaCartaHumano() != null && newTrucoDescription.getTerceiraCartaHumano() != 0)
			cbr.setSegundaCartaHumano(newTrucoDescription.getSegundaCartaHumano());
		
		// valida ganhador da primeira rodada
		if (newTrucoDescription.getGanhadorPrimeiraRodada() !=  null && newTrucoDescription.getGanhadorPrimeiraRodada() !=0)
				cbr.setGanhadorPrimeiraRodada(newTrucoDescription.getGanhadorPrimeiraRodada());
	
		if (newTrucoDescription.getGanhadorSegundaRodada() !=  null && newTrucoDescription.getGanhadorSegundaRodada() !=0)
		cbr.setGanhadorSegundaRodada(newTrucoDescription.getGanhadorSegundaRodada());
	
		//FAZER PARA PRIMEIRA ROBO MAO, PRIMEIRA ROBO PE..getClass().
		
		
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

	/**
	 * @param JogadorMao
	 * @param CartaAlta
	 * @param CartaMedia
	 * @param CartaBaixa
	 * @param PrimeiraCarta
	 * @param SegundaCarta
	 * @param TerceiraCarta
	 * @param PrimeiraCartaOponente
	 * @param SegundaCartaOponente
	 * @param TerceiraCartaOponente
	 * @param GanhadorPrimeiraRodada
	 * @param GanhadorSegundaRodada
	 * @param GanhadorTerceiraRodada
	 * @param listaCartasJogadas
	 * @param listaCartasJogadasOponente
	 * @param listaJogadasChamadas
	 * @param contador
	 * @return
	 */
	public TrucoDescription atualizaConsultaBaralhoRobo(Integer JogadorMao, Integer CartaAlta, Integer CartaMedia,
			Integer CartaBaixa, Integer GanhadorPrimeiraRodada, Integer GanhadorSegundaRodada,
			Integer GanhadorTerceiraRodada, List<CartasModelo> listaCartasJogadas,
			List<CartasModelo> listaCartasJogadasOponente, List<JogadasChamadasModelo> listaJogadasChamadas,
			Integer contador) {
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
