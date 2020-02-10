package cbr.AtualizaConsultas;

import java.util.List;

import cbr.AtualizaConsultas.AuxiliaConsultas.CartasModelo;
import cbr.AtualizaConsultas.AuxiliaConsultas.JogadasChamadasModelo;
import cbr.cbrDescriptions.TrucoDescription;

public class AtualizarConsultasServer {

	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @return
	 */
	public TrucoDescription atualizaConsultaEnvidoRobo(
			int quemEhMao,
			int pontosEnvido,
			int CartaJogadasOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(quemEhMao);
		cbr.setPontosEnvidoRobo(pontosEnvido);
		if (CartaJogadasOponente != 0 )
			cbr.setPrimeiraCartaHumano(CartaJogadasOponente);
		cbr.setQuemGanhouEnvido(1);
		
		return cbr;
	}
	
	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @return
	 */
	public TrucoDescription atualizaConsultaEnvidoHumano(
			int quemEhMao,
			int pontosEnvido, 
			int CartaJogadasOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(complementa(quemEhMao));
		cbr.setPontosEnvidoHumano(pontosEnvido);
		if ( CartaJogadasOponente!=0)
			cbr.setPrimeiraCartaRobo(CartaJogadasOponente);
		cbr.setQuemGanhouEnvido(2);
		return cbr;
	}
	
	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @param quemPediuEnvido
	 * @return
	 */
	public TrucoDescription atualizaConsultaRealEnvidoRobo(
			int quemEhMao,
			int pontosEnvido, 
			int CartaJogadasOponente,
			int quemPediuEnvido) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(quemEhMao);
		cbr.setPontosEnvidoRobo(pontosEnvido);
		if (CartaJogadasOponente != 0 )
			cbr.setPrimeiraCartaHumano(CartaJogadasOponente);
		cbr.setQuemPediuEnvido(quemPediuEnvido);
		cbr.setQuemGanhouEnvido(1);
		return cbr;
	}

	/**
	 * @param quemEhMao
	 * @param pontosEnvido
	 * @param listaCartasJogadasOponente
	 * @param quemPediuEnvido
	 * @return
	 */
	public TrucoDescription atualizaConsultaRealEnvidoHumano(
			int quemEhMao,
			int pontosEnvido, 
			int CartaJogadasOponente,
			int quemPediuEnvido) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(complementa(quemEhMao));
		cbr.setPontosEnvidoHumano(pontosEnvido);
		if (CartaJogadasOponente != 0 )
			cbr.setPrimeiraCartaRobo(CartaJogadasOponente);
		cbr.setQuemPediuEnvido(complementa(quemPediuEnvido));
		cbr.setQuemGanhouEnvido(2);
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
	public TrucoDescription atualizaConsultaFaltaEnvidoRobo(
			int quemEhMao, 
			int pontosEnvido,
			int CartaJogadasOponente,
			int quemPediuEnvido, 
			int quemPediuRealEnvido,
			
			int Tentos,
			int TentosOponente
			
			) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(quemEhMao);
		cbr.setPontosEnvidoRobo(pontosEnvido);
		if (CartaJogadasOponente != 0 )
			cbr.setPrimeiraCartaHumano(CartaJogadasOponente);
		cbr.setQuemPediuEnvido(quemPediuEnvido);
		cbr.setQuemPediuRealEnvido(quemPediuRealEnvido);
		cbr.setQuemGanhouEnvido(1);
		
		
		cbr.setTentosAnterioresRobo(Tentos);
		cbr.setTentosAnterioresHumano(TentosOponente);
		
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
	public TrucoDescription atualizaConsultaFaltaEnvidoHumano(
			int quemEhMao, 
			int pontosEnvido,
			int CartaJogadasOponente,
			int quemPediuEnvido,
			int quemPediuRealEnvido,
			
			int Tentos,
			int TentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(complementa(quemEhMao));
		cbr.setPontosEnvidoHumano(pontosEnvido);
		if (CartaJogadasOponente != 0 )
			cbr.setPrimeiraCartaRobo(CartaJogadasOponente);
		cbr.setQuemPediuEnvido(complementa(quemPediuEnvido));
		cbr.setQuemPediuRealEnvido(complementa(quemPediuRealEnvido));
		cbr.setQuemGanhouEnvido(2);
		

		cbr.setTentosAnterioresRobo(TentosOponente);
		cbr.setTentosAnterioresHumano(Tentos);
		
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
	public TrucoDescription atualizaConsultaTrucoRobo(
			int JogadorMao, 
			
			int CartaAlta, 
			int CartaMedia,
			int CartaBaixa, 
			
			int GanhadorPrimeiraRodada, 
			int GanhadorSegundaRodada,
			int GanhadorTerceiraRodada,
			
			int QuemTruco,
			int QuemRetruco, 
			int QuemValeQuatro, 
			
			List<CartasModelo> listaCartasJogadas, 
			List<CartasModelo> listaCartasJogadasOponente,
			
			List<JogadasChamadasModelo> listaJogadasChamadas,
			int contador) {
		
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
		
		if(listaJogadasChamadas.size() >=1 )
			cbr.setQuemTruco(QuemTruco);
		
		if(listaJogadasChamadas.size() >=2 )
			cbr.setQuemRetruco(QuemRetruco);
		
		if(listaJogadasChamadas.size() >=3 )
			cbr.setQuemValeQuatro(QuemValeQuatro);
		
		cbr.setQuandoTruco(contador);
		
		cbr.setQuemGanhouTruco(1);

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
	public TrucoDescription atualizaConsultaTrucoHumano(
			int JogadorMao, 
			
			int CartaAlta, 
			int CartaMedia,
			int CartaBaixa, 
			
			int GanhadorPrimeiraRodada, 
			int GanhadorSegundaRodada,
			int GanhadorTerceiraRodada,
			
			int QuemTruco,
			int QuemRetruco, 
			int QuemValeQuatro, 
			
			List<CartasModelo> listaCartasJogadas, 
			List<CartasModelo> listaCartasJogadasOponente,
			
			List<JogadasChamadasModelo> listaJogadasChamadas,
			int contador) {
		
		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(complementa(JogadorMao));
		cbr.setCartaAltaHumano(CartaAlta);
		cbr.setCartaMediaHumano(CartaMedia);
		cbr.setCartaBaixaHumano(CartaBaixa);

		
		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaHumano(listaCartasJogadas.get(0).getId());
		if (listaCartasJogadas.size() >= 2)
		cbr.setSegundaCartaHumano(listaCartasJogadas.get(1).getId());
		if (listaCartasJogadas.size() >= 3)
		cbr.setTerceiraCartaHumano(listaCartasJogadas.get(2).getId());

		
		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaRobo(listaCartasJogadasOponente.get(0).getId());
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaRobo(listaCartasJogadasOponente.get(1).getId());
		if (listaCartasJogadasOponente.size() >= 3)
			cbr.setTerceiraCartaRobo(listaCartasJogadasOponente.get(2).getId());

		if (contador > 1)
			cbr.setGanhadorPrimeiraRodada(complementa(GanhadorPrimeiraRodada));
		if (contador > 2)
			cbr.setGanhadorSegundaRodada(complementa(GanhadorSegundaRodada));
		if (contador > 3)
			cbr.setGanhadorTerceiraRodada(complementa(GanhadorTerceiraRodada));
		
		if(listaJogadasChamadas.size() >=1 )
			cbr.setQuemTruco(complementa(QuemTruco));
		
		if(listaJogadasChamadas.size() >=2 )
			cbr.setQuemRetruco(complementa(QuemRetruco));
		
		if(listaJogadasChamadas.size() >=3 )
			cbr.setQuemValeQuatro(complementa(QuemValeQuatro));
		
		cbr.setQuandoTruco(contador);
		cbr.setQuemGanhouTruco(2);

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
	public TrucoDescription atualizaConsultaCartaHumano(
			int JogadorMao,
			
			int CartaAlta,
			int CartaMedia, 
			int CartaBaixa,
			
			List<CartasModelo> listaCartasJogadas, 
			List<CartasModelo> listaCartasJogadasOponente
			) {
		

		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(complementa(JogadorMao));
		cbr.setCartaAltaHumano(CartaAlta);
		cbr.setCartaMediaHumano(CartaMedia);
		cbr.setCartaBaixaHumano(CartaBaixa);
		
		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaHumano(listaCartasJogadas.get(0).getId());
		if (listaCartasJogadas.size() >= 2)
			cbr.setSegundaCartaHumano(listaCartasJogadas.get(1).getId());
		
		
		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaRobo(listaCartasJogadasOponente.get(0).getId());
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaRobo(listaCartasJogadasOponente.get(1).getId());

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
	public TrucoDescription atualizaConsultaCartaRobo(
			int JogadorMao,
			
			int CartaAlta,
			int CartaMedia, 
			int CartaBaixa,
			
			List<CartasModelo> listaCartasJogadas, 
			List<CartasModelo> listaCartasJogadasOponente
			) {
		

		TrucoDescription cbr = new TrucoDescription();
		cbr.setJogadorMao(JogadorMao);
		cbr.setCartaAltaRobo(CartaAlta);
		cbr.setCartaMediaRobo(CartaMedia);
		cbr.setCartaBaixaRobo(CartaBaixa);
		
		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaRobo(listaCartasJogadas.get(0).getId());
		if (listaCartasJogadas.size() >= 2)
			cbr.setSegundaCartaRobo(listaCartasJogadas.get(1).getId());
		
		
		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaHumano(listaCartasJogadasOponente.get(0).getId());
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaHumano(listaCartasJogadasOponente.get(1).getId());

			return cbr;
	}

	/**
	 * @param listaCartas
	 * @return
	 */
	public TrucoDescription atualizaConsultaFlorHumano(
			List<CartasModelo> listaCartas) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setNaipeCartaAltaHumano(listaCartas.get(0).getNaipe());
		cbr.setNaipeCartaMediaHumano(listaCartas.get(1).getNaipe());
		cbr.setNaipeCartaBaixaHumano(listaCartas.get(2).getNaipe());
			return cbr;
	}

	/**
	 * @param listaCartas
	 * @return
	 */
	public TrucoDescription atualizaConsultaFlorRobo(
			List<CartasModelo> listaCartas) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setNaipeCartaAltaRobo(listaCartas.get(0).getNaipe());
		cbr.setNaipeCartaMediaRobo(listaCartas.get(1).getNaipe());
		cbr.setNaipeCartaBaixaRobo(listaCartas.get(2).getNaipe());
			return cbr;
	}
		
	/**
	 * @param pontosFlor
	 * @return
	 */
	public TrucoDescription atualizaConsultaContraFlorHumano(
			int pontosFlor) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setPontosFlorHumano(pontosFlor);
		return cbr;
	}

	/**
	 * @param pontosFlor
	 * @return
	 */
	public TrucoDescription atualizaConsultaContraFlorRobo(
			int pontosFlor) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setPontosFlorRobo(pontosFlor);
		
		return cbr;
	}
	
	/**
	 * @param pontosFlor
	 * @return
	 */
	public TrucoDescription atualizaConsultaContraFlorRestoHumano(
			int pontosFlor,
			int Tentos,
			int TentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setPontosFlorHumano(pontosFlor);
		cbr.setTentosAnterioresRobo(TentosOponente);
		cbr.setTentosAnterioresHumano(Tentos);

		return cbr;
	}

	/**
	 * @param pontosFlor
	 * @return
	 */
	public TrucoDescription atualizaConsultaContraFlorRestoRobo(
			int pontosFlor,
			int Tentos,
			int TentosOponente) {
		TrucoDescription cbr = new TrucoDescription();
		cbr.setPontosFlorRobo(pontosFlor);
		cbr.setTentosAnterioresRobo(Tentos);
		cbr.setTentosAnterioresHumano(TentosOponente);
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
	public TrucoDescription atualizaConsultaBaralhoHumano(
			int JogadorMao, 
			
			int CartaAlta, 
			int CartaMedia,
			int CartaBaixa, 
			
			int PrimeiraCarta,
			int SegundaCarta, 
			int TerceiraCarta,

			int PrimeiraCartaOponente,
			int SegundaCartaOponente,
			int TerceiraCartaOponente, 
			
			int GanhadorPrimeiraRodada, 
			int GanhadorSegundaRodada,
			int GanhadorTerceiraRodada,
			
			
			List<CartasModelo> listaCartasJogadas, 
			List<CartasModelo> listaCartasJogadasOponente,
			
			List<JogadasChamadasModelo> listaJogadasChamadas,
			int contador) {
		TrucoDescription cbr = new TrucoDescription();
		
		cbr.setJogadorMao(complementa(JogadorMao));

		cbr.setCartaAltaHumano(CartaAlta);
		cbr.setCartaMediaHumano(CartaMedia);
		cbr.setCartaBaixaHumano(CartaBaixa);
		
		
		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaHumano(PrimeiraCarta);
		if (listaCartasJogadas.size() >= 2)
			cbr.setSegundaCartaHumano(SegundaCarta);
		if (listaCartasJogadas.size() >= 3)
			cbr.setTerceiraCartaHumano(TerceiraCarta);
		
		
		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaRobo(PrimeiraCartaOponente);	
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaRobo(SegundaCartaOponente);
		if (listaCartasJogadasOponente.size() >= 3)
			cbr.setTerceiraCartaRobo(TerceiraCartaOponente);
		
		if (contador > 1)
			cbr.setGanhadorPrimeiraRodada(complementa(GanhadorPrimeiraRodada));
		if (contador > 2)
			cbr.setGanhadorSegundaRodada(complementa(GanhadorSegundaRodada));
		if (contador > 3)
			cbr.setGanhadorTerceiraRodada(complementa(GanhadorTerceiraRodada));

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
	public TrucoDescription atualizaConsultaBaralhoRobo(
			int JogadorMao, 
			int CartaAlta, 
			int CartaMedia,
			int CartaBaixa, 
			
			int PrimeiraCarta,
			int SegundaCarta, 
			int TerceiraCarta,

			int PrimeiraCartaOponente,
			int SegundaCartaOponente,
			int TerceiraCartaOponente, 
			
			int GanhadorPrimeiraRodada, 
			int GanhadorSegundaRodada,
			int GanhadorTerceiraRodada,
			
			List<CartasModelo> listaCartasJogadas, 
			List<CartasModelo> listaCartasJogadasOponente,
			
			List<JogadasChamadasModelo> listaJogadasChamadas,
			int contador) {
		TrucoDescription cbr = new TrucoDescription();
		
		cbr.setJogadorMao(JogadorMao);

		cbr.setCartaAltaRobo(CartaAlta);
		cbr.setCartaMediaRobo(CartaMedia);
		cbr.setCartaBaixaRobo(CartaBaixa);
		
		
		if (listaCartasJogadas.size() >= 1)
			cbr.setPrimeiraCartaRobo(PrimeiraCarta);
		if (listaCartasJogadas.size() >= 2)
			cbr.setSegundaCartaRobo(SegundaCarta);
		if (listaCartasJogadas.size() >= 3)
			cbr.setTerceiraCartaRobo(TerceiraCarta);
		
		
		if (listaCartasJogadasOponente.size() >= 1)
			cbr.setPrimeiraCartaHumano(PrimeiraCartaOponente);	
		if (listaCartasJogadasOponente.size() >= 2)
			cbr.setSegundaCartaHumano(SegundaCartaOponente);
		if (listaCartasJogadasOponente.size() >= 3)
			cbr.setTerceiraCartaHumano(TerceiraCartaOponente);
		
		if (contador > 1)
			cbr.setGanhadorPrimeiraRodada(GanhadorPrimeiraRodada);
		if (contador > 2)
			cbr.setGanhadorSegundaRodada(GanhadorSegundaRodada);
		if (contador > 3)
			cbr.setGanhadorTerceiraRodada(GanhadorTerceiraRodada);

		return cbr;

	}

	/**
	 * @param number
	 * @return
	 */
	private int complementa(int number) {
		if(number ==1)
			return 2;
		else 
			return 1;
	}
	
	
}
