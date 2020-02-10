package cbr.learning;

import Ajudas.ValidaGanhadorMao;
import ajudaCluster.UtilSaldos;
import ajudaCluster.converteFormatosCartasParaCartasJogadasClustering;
import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.QuandoTrucoModelo;

public interface ValidaPersistir {
TrucoDescription criaDescriptionComOqueOcasoEutil(TrucoDescription trucoDescription, CbrModular cbr);
	
boolean validaCriteriosParaAprenderCarta(TrucoDescription trucoDescription,CbrModular cbr);
boolean validaCriteriosParaAprenderTruco(TrucoDescription trucoDescription, CbrModular cbr);
boolean validaCriteriosParaAprenderEnvido(TrucoDescription trucoDescription, CbrModular cbr);
boolean validaCriteriosParaAprenderFlor(TrucoDescription trucoDescription, CbrModular cbr);

default TrucoDescription criaDescriptionComInformacoesRelevantesTruco(TrucoDescription newCase, CbrModular cbr) {
	
	//seta ganhadores rodadas e cartas
	// setar os ganhadores das rodadas
	
	// adiciona as cartas nas três rodadas
			newCase = new converteFormatosCartasParaCartasJogadasClustering()
					.retornaCodificacaoPrimeiraCartaJogadaRoboClustering(newCase);
			newCase = new converteFormatosCartasParaCartasJogadasClustering()
					.retornaCodificacaoSegundaCartaJogadaRoboClustering(newCase);
			newCase = new converteFormatosCartasParaCartasJogadasClustering()
					.retornaCodificacaoTerceiraCartaJogadaRoboClustering(newCase);

			newCase.setGanhadorMao(ValidaGanhadorMao.retornaGanhadorDaMao(newCase));

			// verifica casos em que alguma carta não foi jogada
			// cartas não foram jogadas por que agente negou truco

			if (newCase.getQuemNegouTruco().equals(1) && newCase.getPrimeiraCartaRoboClustering() == null)
				newCase.setPrimeiraCartaRoboClustering(-15);
			if (newCase.getQuemNegouTruco().equals(1) && newCase.getSegundaCartaRoboClustering() == null)
				newCase.setSegundaCartaRoboClustering(-15);
			if (newCase.getQuemNegouTruco().equals(1) && newCase.getTerceiraCartaRoboClustering() == null)
				newCase.setTerceiraCartaRoboClustering(-15);

			// cartas não foram jogadas por que agente foi ao baralho
			if (newCase.getQuemBaralho().equals(1) && newCase.getPrimeiraCartaRoboClustering() == null)
				newCase.setPrimeiraCartaRoboClustering(-10);
			if (newCase.getQuemBaralho().equals(1) && newCase.getSegundaCartaRoboClustering() == null)
				newCase.setSegundaCartaRoboClustering(-10);
			if (newCase.getQuemBaralho().equals(1) && newCase.getTerceiraCartaRoboClustering() == null)
				newCase.setTerceiraCartaRoboClustering(-10);
			// cartas não foram jogadas por que o oponente correu ou foi ao baralho
			// seta cartas agente oponente correu no truco
			if (newCase.getQuemNegouTruco().equals(2) && newCase.getPrimeiraCartaRoboClustering() == null)
				newCase.setPrimeiraCartaRoboClustering(-46);
			if (newCase.getQuemNegouTruco().equals(2) && newCase.getSegundaCartaRoboClustering() == null)
				newCase.setSegundaCartaRoboClustering(-46);
			if (newCase.getQuemNegouTruco().equals(2) && newCase.getTerceiraCartaRoboClustering() == null)
				newCase.setTerceiraCartaRoboClustering(-46);
			// seta cartas agente quando oponente foi ao baralho
			if (newCase.getQuemBaralho().equals(2) && newCase.getPrimeiraCartaRoboClustering() == null)
				newCase.setPrimeiraCartaRoboClustering(-46);
			if (newCase.getQuemBaralho().equals(2) && newCase.getSegundaCartaRoboClustering() == null)
				newCase.setSegundaCartaRoboClustering(-46);
			if (newCase.getQuemBaralho().equals(2) && newCase.getTerceiraCartaRoboClustering() == null)
				newCase.setTerceiraCartaRoboClustering(-46);

			/*
			 * Seta as cartas do oponente
			 */

			// cartas não foram jogadas por que agente negou truco

			if (newCase.getQuemNegouTruco().equals(1) && newCase.getPrimeiraCartaHumanoClustering() == null)
				newCase.setPrimeiraCartaHumanoClustering(-15);
			if (newCase.getQuemNegouTruco().equals(1) && newCase.getSegundaCartaHumanoClustering() == null)
				newCase.setSegundaCartaHumanoClustering(-15);
			if (newCase.getQuemNegouTruco().equals(1) && newCase.getTerceiraCartaHumanoClustering() == null)
				newCase.setTerceiraCartaHumanoClustering(-15);
			// cartas não foram jogadas por que agente foi ao baralho
			if (newCase.getQuemBaralho().equals(1) && newCase.getPrimeiraCartaHumanoClustering() == null)
				newCase.setPrimeiraCartaHumanoClustering(-10);
			if (newCase.getQuemBaralho().equals(1) && newCase.getSegundaCartaHumanoClustering() == null)
				newCase.setSegundaCartaHumanoClustering(-10);
			if (newCase.getQuemBaralho().equals(1) && newCase.getTerceiraCartaHumanoClustering() == null)
				newCase.setTerceiraCartaHumanoClustering(-10);
			// cartas não foram jogadas por que o oponente correu ou foi ao baralho
			// seta cartas agente oponente correu no truco
			if (newCase.getQuemNegouTruco().equals(2) && newCase.getPrimeiraCartaHumanoClustering() == null)
				newCase.setPrimeiraCartaHumanoClustering(-46);
			if (newCase.getQuemNegouTruco().equals(2) && newCase.getSegundaCartaHumanoClustering() == null)
				newCase.setSegundaCartaHumanoClustering(-46);
			if (newCase.getQuemNegouTruco().equals(2) && newCase.getTerceiraCartaHumanoClustering() == null)
				newCase.setTerceiraCartaHumanoClustering(-46);
			// seta cartas agente quando oponente foi ao baralho
			if (newCase.getQuemBaralho().equals(2) && newCase.getPrimeiraCartaHumanoClustering() == null)
				newCase.setPrimeiraCartaHumanoClustering(-46);
			if (newCase.getQuemBaralho().equals(2) && newCase.getSegundaCartaHumanoClustering() == null)
				newCase.setSegundaCartaHumanoClustering(-46);
			if (newCase.getQuemBaralho().equals(2) && newCase.getTerceiraCartaHumanoClustering() == null)
				newCase.setTerceiraCartaHumanoClustering(-46);

			// seta ganhadores das rodadas
			if (!newCase.getPrimeiraCartaRoboClustering().equals(-46)
					&& !newCase.getPrimeiraCartaRoboClustering().equals(-15)
					&& !newCase.getPrimeiraCartaRoboClustering().equals(-10)
					&& !newCase.getPrimeiraCartaHumanoClustering().equals(-46)
					&& !newCase.getPrimeiraCartaHumanoClustering().equals(-15)
					&& !newCase.getPrimeiraCartaHumanoClustering().equals(-10)) {
				if (newCase.getPrimeiraCartaHumano().equals(newCase.getPrimeiraCartaRobo()))
					newCase.setGanhadorPrimeiraRodada(0);
				else if (newCase.getPrimeiraCartaRobo() > newCase.getPrimeiraCartaHumano())
					newCase.setGanhadorPrimeiraRodada(1);
				else if (newCase.getPrimeiraCartaRobo() < newCase.getPrimeiraCartaHumano())
					newCase.setGanhadorPrimeiraRodada(2);
			} else if (newCase.getPrimeiraCartaRoboClustering().equals(-46)
					|| newCase.getPrimeiraCartaHumanoClustering().equals(-46))
				newCase.setGanhadorPrimeiraRodada(1);
			else if (newCase.getPrimeiraCartaRoboClustering().equals(-15)
					|| newCase.getPrimeiraCartaHumanoClustering().equals(-15)
					|| newCase.getPrimeiraCartaRoboClustering().equals(-10)
					|| newCase.getPrimeiraCartaHumanoClustering().equals(-10))
				newCase.setGanhadorPrimeiraRodada(2);

			if (!newCase.getSegundaCartaRoboClustering().equals(-46) && !newCase.getSegundaCartaRoboClustering().equals(-15)
					&& !newCase.getSegundaCartaRoboClustering().equals(-10)
					&& !newCase.getSegundaCartaHumanoClustering().equals(-46)
					&& !newCase.getSegundaCartaHumanoClustering().equals(-15)
					&& !newCase.getSegundaCartaHumanoClustering().equals(-10)) {
				if (newCase.getSegundaCartaHumano().equals(newCase.getSegundaCartaRobo()))
					newCase.setGanhadorSegundaRodada(0);
				else if (newCase.getSegundaCartaRobo() > newCase.getSegundaCartaHumano())
					newCase.setGanhadorSegundaRodada(1);
				else if (newCase.getSegundaCartaRobo() < newCase.getSegundaCartaHumano())
					newCase.setGanhadorSegundaRodada(2);
			} else if (newCase.getSegundaCartaRoboClustering().equals(-46)
					|| newCase.getSegundaCartaHumanoClustering().equals(-46))
				newCase.setGanhadorSegundaRodada(1);
			else if (newCase.getSegundaCartaRoboClustering().equals(-15)
					|| newCase.getSegundaCartaHumanoClustering().equals(-15)
					|| newCase.getSegundaCartaRoboClustering().equals(-10)
					|| newCase.getSegundaCartaHumanoClustering().equals(-10))
				newCase.setGanhadorSegundaRodada(2);

			if (!newCase.getTerceiraCartaRoboClustering().equals(-46)
					&& !newCase.getTerceiraCartaRoboClustering().equals(-15)
					&& !newCase.getTerceiraCartaRoboClustering().equals(-10)
					&& !newCase.getTerceiraCartaHumanoClustering().equals(-46)
					&& !newCase.getTerceiraCartaHumanoClustering().equals(-15)
					&& !newCase.getTerceiraCartaHumanoClustering().equals(-10)) {
				if (newCase.getTerceiraCartaHumano().equals(newCase.getTerceiraCartaRobo()))
					newCase.setGanhadorTerceiraRodada(0);
				else if (newCase.getTerceiraCartaRobo() > newCase.getTerceiraCartaHumano())
					newCase.setGanhadorTerceiraRodada(1);
				else if (newCase.getTerceiraCartaRobo() < newCase.getTerceiraCartaHumano())
					newCase.setGanhadorTerceiraRodada(2);
			} else if (newCase.getTerceiraCartaRoboClustering().equals(-46)
					|| newCase.getTerceiraCartaHumanoClustering().equals(-46))
				newCase.setGanhadorTerceiraRodada(1);
			else if (newCase.getTerceiraCartaRoboClustering().equals(-15)
					|| newCase.getTerceiraCartaHumanoClustering().equals(-15)
					|| newCase.getTerceiraCartaRoboClustering().equals(-10)
					|| newCase.getTerceiraCartaHumanoClustering().equals(-10))
				newCase.setGanhadorTerceiraRodada(2);

			//evita que sejam setados como uteis para truco casos onde agente perdeu as duas primeiras e ainda assim jogou a ultima
			if(newCase.getGanhadorPrimeiraRodada().equals(2) && newCase.getGanhadorSegundaRodada().equals(2) && 
					(!newCase.getTerceiraCartaRobo().equals(0) || newCase.getTerceiraCartaRobo() != null)) {
			newCase.setUtilCarta(2);
			newCase.setUtilTruco(2);
			}
		//evita setar o mesmo que chamou truco chamar vale quatro
			if( (newCase.getQuemTruco() != null && newCase.getQuemRetruco() != null) && (!newCase.getQuemTruco().equals(0) && !newCase.getQuemRetruco().equals(0))
				&& (newCase.getQuemTruco().equals(newCase.getQuemRetruco()))	) {
			newCase.setUtilTruco(2);
			
			}
			//evita setar o mesmo que chamou truco chamar vale quatro
			if( (newCase.getQuemRetruco() != null && newCase.getQuemValeQuatro() != null) && (!newCase.getQuemRetruco().equals(0) && !newCase.getQuemValeQuatro().equals(0))
				&& (newCase.getQuemRetruco().equals(newCase.getQuemValeQuatro()))	) 
			newCase.setUtilTruco(2);
			
			
			
	newCase.setQuemTrucoCluster(retornaQuemCluster(newCase.getQuemTruco()));
	newCase.setQuemRetrucoCluster(retornaQuemCluster(newCase.getQuemRetruco()));
	newCase.setQuemValeQuatroCluster(retornaQuemCluster(newCase.getQuemValeQuatro()));

	//seta grupos e saldo
	newCase = cbr.preencheTodosOsGruposMaisSimilaresQuemTruco(newCase, 93);
	
	//seta saldo
	newCase.setSaldoTruco(UtilSaldos.retornaSaldoTruco(newCase));
	//seta truco

	newCase.setQuemGanhouTruco(newCase.getGanhadorMao());
	
	int saldoTruco = 0;
	if (newCase.getGanhadorMao().equals(1))
		saldoTruco = saldoTruco + newCase.getTentosTruco();
	else if (newCase.getGanhadorMao().equals(2))
		saldoTruco = saldoTruco - newCase.getTentosTruco();
	
	newCase.setSaldoTruco(saldoTruco);
	
	newCase =(criaDescriptionComInformacoesRelevantesIndexacaoJogadas(newCase, cbr));
	
	newCase = validaSeDeveIgnorarJogadasTruco(newCase);
	return newCase;

}

public default TrucoDescription validaSeDeveIgnorarJogadasTruco(TrucoDescription newCase) {
	if(newCase.getUtilTruco() == null ) {
	//o oponente jogou uma carta muito alta na ultima e o agente aumenta
	if(newCase.getGanhadorSegundaRodada().equals(2) && newCase.getTerceiraCartaHumano() > 40 
			&& newCase.getTerceiraCartaRobo() <40 && 
			( (newCase.getQuandoTruco() != null && newCase.getQuandoTruco().equals(3))|| (newCase.getQuandoRetruco() != null && 
					newCase.getQuandoRetruco().equals(3))|| (newCase.getQuandoValeQuatro() != null &&
					newCase.getQuandoValeQuatro().equals(3) )) && !newCase.getQuemNegouTruco().equals(1)) {
		newCase.setUtilTruco(2);	
		
	}
	//agente tem um jogo muito bom e negou truco
	if(newCase.getCartaAltaRobo() >=24 &&  newCase.getCartaMediaRobo() >=16 && (newCase.getQuemTruco() != null &&
	 newCase.getQuemNegouTruco()!= null  &&	newCase.getQuemNegouTruco().equals(1)))
		newCase.setUtilTruco(2);
	//agente tem um jogo muito ruim e não negou truco
		if(newCase.getCartaMediaRobo() < 6 &&  newCase.getCartaBaixaRobo() < 6 && 
				(newCase.getQuemTruco() != null && !newCase.getQuemTruco().equals(0) &&  !newCase.getQuemNegouTruco().equals(1)))
			newCase.setUtilTruco(2);
		//largou uma carta muito ruim na terceira rodada
		if(newCase.getGanhadorSegundaRodada().equals(1) && newCase.getTerceiraCartaRobo()<6 &&
				( (newCase.getQuandoTruco() != null && newCase.getQuandoTruco().equals(3)) || (newCase.getQuandoRetruco() != null && newCase.getQuandoRetruco().equals(3) ) 
		|| (newCase.getQuandoValeQuatro() != null && newCase.getQuandoValeQuatro().equals(3) )) && newCase.getQuemNegouTruco().equals(1)) {
			newCase.setUtilTruco(2);
		}
	}
	//agente jogou a ultima maior ou igual ao 7 de ouro
	if(newCase.getGanhadorSegundaRodada().equals(1) && newCase.getTerceiraCartaRobo() >= 40 && newCase.getQuemNegouTruco().equals(1))
		newCase.setUtilTruco(2);
	
	//chamou truco na primeira quando é mão e tem uma mão fraca
	if(newCase.getJogadorMao().equals(1) && newCase.getQuemTruco() != null && newCase.getCartaBaixaRobo() < 6 && newCase.getCartaMediaRobo() < 16 &&
	newCase.getQuemTruco().equals(1) && newCase.getQuandoTruco().equals(1))
			newCase.setUtilTruco(2);
	//chamou truco na primeira e perdeu a primeiraRodada
	if(newCase.getJogadorMao().equals(2) && newCase.getQuemTruco() != null &&  newCase.getGanhadorPrimeiraRodada().equals(2) &&
	newCase.getQuemTruco().equals(1) && newCase.getQuandoTruco().equals(1))
			newCase.setUtilTruco(2);
	//aceitou ou chamou retruco onde a carta alta é menor que um 1 normal
	if(newCase.getQuemRetruco() != null && newCase.getQuemRetruco().equals(0) && !newCase.getQuemNegouTruco().equals(1) && newCase.getCartaAltaRobo() < 12)
		newCase.setUtilTruco(2);
	//aceitou ou chamou vale quatro onde a carta alta é menor que um 1 normal
		if(newCase.getQuemValeQuatro() != null && newCase.getQuemValeQuatro().equals(0) && !newCase.getQuemNegouTruco().equals(1) && newCase.getCartaAltaRobo() < 16)
			newCase.setUtilTruco(2);
	//chamou truco onde a carta alta é menor que um 12
	if(newCase.getQuemTruco() != null  && !newCase.getQuemTruco().equals(0) && !newCase.getQuemNegouTruco().equals(1) && newCase.getCartaAltaRobo() < 8)	
		newCase.setUtilTruco(2);
		
	//CHAMOU TRUCO ONDE É MÃO E NÃO CHAMOU PONTOS
	if(newCase.getQuemTruco() != null && newCase.getQuemTruco().equals(1) && newCase.getQuandoTruco().equals(1) && newCase.getJogadorMao().equals(1) 
			&& ( (newCase.getQuemPediuEnvido() == null || !newCase.getQuemPediuEnvido().equals(1)) && 
					(newCase.getQuemPediuRealEnvido() == null || !newCase.getQuemPediuRealEnvido().equals(1)) &&
					(newCase.getQuemPediuFaltaEnvido() == null || !newCase.getQuemPediuFaltaEnvido().equals(1))) )
		newCase.setUtilTruco(2);
		
	return newCase;
}

public default int retornaQuemCluster(int quemChamou) {
	
	if(quemChamou == 1) return 1;
	else if(quemChamou == 2) return 3;
	else return 2;
	
}

default TrucoDescription criaDescriptionComInformacoesRelevantesCarta(TrucoDescription newCase, CbrModular cbr) {
	//System.out.println(newCase.toString());
	
	// adiciona as cartas nas três rodadas
	newCase = new converteFormatosCartasParaCartasJogadasClustering()
			.retornaCodificacaoPrimeiraCartaJogadaRoboClustering(newCase);
	newCase = new converteFormatosCartasParaCartasJogadasClustering()
			.retornaCodificacaoSegundaCartaJogadaRoboClustering(newCase);
	newCase = new converteFormatosCartasParaCartasJogadasClustering()
			.retornaCodificacaoTerceiraCartaJogadaRoboClustering(newCase);
//adiciona as cartas do humano
	converteFormatosCartasParaCartasJogadasClustering conversor = new converteFormatosCartasParaCartasJogadasClustering();
	if(newCase.getPrimeiraCartaHumano() != null)
	newCase.setPrimeiraCartaHumanoClustering(conversor.transformaCartasEmCartasClustering(newCase.getPrimeiraCartaHumano()));
	
	if(newCase.getSegundaCartaHumano() != null)
		newCase.setSegundaCartaHumanoClustering(conversor.transformaCartasEmCartasClustering(newCase.getSegundaCartaHumano()));
	
	if(newCase.getTerceiraCartaHumano() != null)
		newCase.setTerceiraCartaHumanoClustering(conversor.transformaCartasEmCartasClustering(newCase.getTerceiraCartaHumano()));
	
	
	newCase.setGanhadorMao(ValidaGanhadorMao.retornaGanhadorDaMao(newCase));

	// verifica casos em que alguma carta não foi jogada
	// cartas não foram jogadas por que agente negou truco

	if (newCase.getQuemNegouTruco().equals(1) && newCase.getPrimeiraCartaRoboClustering() == null) {
		newCase.setPrimeiraCartaRoboClustering(-15);
		newCase.setPrimeiraCartaRobo(-15);
	}
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getSegundaCartaRoboClustering() == null) {
		newCase.setSegundaCartaRoboClustering(-15);
		newCase.setSegundaCartaRobo(-15);
	}
		
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getTerceiraCartaRoboClustering() == null) {
		newCase.setTerceiraCartaRoboClustering(-15);
		newCase.setTerceiraCartaRobo(-15);
	}
	
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getPrimeiraCartaHumanoClustering() == null) {
		newCase.setPrimeiraCartaHumanoClustering(-15);
		newCase.setPrimeiraCartaHumano(-15);
	}
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getSegundaCartaHumanoClustering() == null) {
		newCase.setSegundaCartaHumanoClustering(-15);
		newCase.setSegundaCartaHumano(-15);
	}
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getTerceiraCartaHumanoClustering() == null) {
		newCase.setTerceiraCartaHumanoClustering(-15);
		newCase.setTerceiraCartaHumano(-15);
	}
	// cartas não foram jogadas por que agente foi ao baralho
	if (newCase.getQuemBaralho().equals(1) && newCase.getPrimeiraCartaRoboClustering() == null)
		newCase.setPrimeiraCartaRoboClustering(-10);
	if (newCase.getQuemBaralho().equals(1) && newCase.getSegundaCartaRoboClustering() == null)
		newCase.setSegundaCartaRoboClustering(-10);
	if (newCase.getQuemBaralho().equals(1) && newCase.getTerceiraCartaRoboClustering() == null)
		newCase.setTerceiraCartaRoboClustering(-10);
	// cartas não foram jogadas por que o oponente correu ou foi ao baralho
	// seta cartas agente oponente correu no truco
	if (newCase.getQuemNegouTruco().equals(2) && newCase.getPrimeiraCartaRoboClustering() == null)
		newCase.setPrimeiraCartaRoboClustering(-46);
	if (newCase.getQuemNegouTruco().equals(2) && newCase.getSegundaCartaRoboClustering() == null)
		newCase.setSegundaCartaRoboClustering(-46);
	if (newCase.getQuemNegouTruco().equals(2) && newCase.getTerceiraCartaRoboClustering() == null)
		newCase.setTerceiraCartaRoboClustering(-46);
	// seta cartas agente quando oponente foi ao baralho
	if (newCase.getQuemBaralho().equals(2) && newCase.getPrimeiraCartaRoboClustering() == null)
		newCase.setPrimeiraCartaRoboClustering(-46);
	if (newCase.getQuemBaralho().equals(2) && newCase.getSegundaCartaRoboClustering() == null)
		newCase.setSegundaCartaRoboClustering(-46);
	if (newCase.getQuemBaralho().equals(2) && newCase.getTerceiraCartaRoboClustering() == null)
		newCase.setTerceiraCartaRoboClustering(-46);
//analisar o caso
	
	/*
	 * Seta as cartas do oponente
	 */

	// cartas não foram jogadas por que agente negou truco

	if (newCase.getQuemNegouTruco().equals(1) && newCase.getPrimeiraCartaHumanoClustering() == null)
		newCase.setPrimeiraCartaHumanoClustering(-15);
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getSegundaCartaHumanoClustering() == null)
		newCase.setSegundaCartaHumanoClustering(-15);
	if (newCase.getQuemNegouTruco().equals(1) && newCase.getTerceiraCartaHumanoClustering() == null)
		newCase.setTerceiraCartaHumanoClustering(-15);
	
	// cartas não foram jogadas por que agente foi ao baralho
	if (newCase.getQuemBaralho().equals(1) && newCase.getPrimeiraCartaHumanoClustering() == null)
		newCase.setPrimeiraCartaHumanoClustering(-10);
	if (newCase.getQuemBaralho().equals(1) && newCase.getSegundaCartaHumanoClustering() == null)
		newCase.setSegundaCartaHumanoClustering(-10);
	if (newCase.getQuemBaralho().equals(1) && newCase.getTerceiraCartaHumanoClustering() == null)
		newCase.setTerceiraCartaHumanoClustering(-10);
	//seta terceira carta quando agente perdeu as duas primeiras rodadas
	if(newCase.getTerceiraCartaRoboClustering() == null && 
	((newCase.getPrimeiraCartaHumano() > newCase.getPrimeiraCartaRobo() && newCase.getSegundaCartaHumano() > newCase.getSegundaCartaRobo()) 
	||(newCase.getPrimeiraCartaHumano().equals(newCase.getPrimeiraCartaRobo())&& newCase.getSegundaCartaHumano() > newCase.getSegundaCartaRobo()) 
	||(newCase.getPrimeiraCartaHumano()> newCase.getPrimeiraCartaRobo() && newCase.getSegundaCartaHumano().equals(newCase.getSegundaCartaRobo())) )) {
		newCase.setTerceiraCartaRobo(-15);
		newCase.setTerceiraCartaHumano(-15);
		newCase.setTerceiraCartaRoboClustering(-15);
		newCase.setTerceiraCartaHumanoClustering(-15);	
	}
	//tem um problema que não está sendo jogada a terceira carta as vezes quando as duas mãos são empardadas
	if(newCase.getPrimeiraCartaHumano().equals(newCase.getPrimeiraCartaRobo()) && newCase.getSegundaCartaHumano().equals(newCase.getSegundaCartaRobo())
			&& newCase.getJogadorMao().equals(2) && (newCase.getTerceiraCartaRobo().equals(0) || newCase.getTerceiraCartaRobo() == null)
&& !newCase.getQuemNegouTruco().equals(2) && (newCase.getTerceiraCartaHumano() == null || newCase.getTerceiraCartaRobo().equals(0) )) {
		newCase.setTerceiraCartaRobo(-15);
		newCase.setTerceiraCartaHumano(-15);
		newCase.setTerceiraCartaRoboClustering(-15);
		newCase.setTerceiraCartaHumanoClustering(-15);	
		//seta = 2 é desconsiderado
		newCase.setUtilCarta(2);
		newCase.setUtilTruco(2);
	}
	//alguma das cartas não foi jogada sem motivo
	if( (newCase.getQuemNegouTruco() == null ||  newCase.getQuemNegouTruco().equals(0)) &&
			(newCase.getQuemBaralho() == null || newCase.getQuemBaralho().equals(0)) ) {
		if(newCase.getPrimeiraCartaRobo() == null || newCase.getPrimeiraCartaRobo().equals(0)) {
			newCase.setPrimeiraCartaRobo(-15);
			newCase.setPrimeiraCartaRoboClustering(-15);
			newCase.setUtilCarta(2);
			newCase.setUtilTruco(2);
		}
		if(newCase.getSegundaCartaRobo()== null|| newCase.getSegundaCartaRobo().equals(0)) {
			newCase.setSegundaCartaRobo(-15);
			newCase.setSegundaCartaRoboClustering(-15);
			newCase.setUtilCarta(2);
			newCase.setUtilTruco(2);
		}
		
		if(newCase.getTerceiraCartaRobo()== null|| newCase.getTerceiraCartaRobo().equals(0)) {
			newCase.setTerceiraCartaRobo(-15);
			newCase.setTerceiraCartaRoboClustering(-15);
			newCase.setUtilCarta(2);
			newCase.setUtilTruco(2);
		}
	}
	
	// cartas não foram jogadas por que o oponente correu ou foi ao baralho
	// seta cartas agente oponente correu no truco
	if (newCase.getQuemNegouTruco().equals(2) && newCase.getPrimeiraCartaHumanoClustering().equals(null))
		newCase.setPrimeiraCartaHumanoClustering(-46);
	if (newCase.getQuemNegouTruco().equals(2) && newCase.getSegundaCartaHumanoClustering().equals(null))
		newCase.setSegundaCartaHumanoClustering(-46);
	if (newCase.getQuemNegouTruco().equals(2) && newCase.getTerceiraCartaHumanoClustering().equals(null))
		newCase.setTerceiraCartaHumanoClustering(-46);
	
	
	// seta cartas agente quando oponente foi ao baralho
	if (newCase.getQuemBaralho().equals(2) && newCase.getPrimeiraCartaHumanoClustering() == null)
		newCase.setPrimeiraCartaHumanoClustering(-46);
	if (newCase.getQuemBaralho().equals(2) && newCase.getSegundaCartaHumanoClustering() == null)
		newCase.setSegundaCartaHumanoClustering(-46);
	if (newCase.getQuemBaralho().equals(2) && newCase.getTerceiraCartaHumanoClustering() == null)
		newCase.setTerceiraCartaHumanoClustering(-46);
	//seta terceira carta quando OPONENTE perdeu as duas primeiras rodadas
	if(newCase.getTerceiraCartaRoboClustering() == null && 
	((newCase.getPrimeiraCartaHumano() < newCase.getPrimeiraCartaRobo() && newCase.getSegundaCartaHumano() < newCase.getSegundaCartaRobo()) 
	||(newCase.getPrimeiraCartaHumano().equals(newCase.getPrimeiraCartaRobo())&& newCase.getSegundaCartaHumano() < newCase.getSegundaCartaRobo()) 
	||(newCase.getPrimeiraCartaHumano()< newCase.getPrimeiraCartaRobo() && newCase.getSegundaCartaHumano().equals(newCase.getSegundaCartaRobo())) )) {
		newCase.setTerceiraCartaRobo(-46);
		newCase.setTerceiraCartaHumano(-46);
		newCase.setTerceiraCartaRoboClustering(-46);
		newCase.setTerceiraCartaHumanoClustering(-46);	
	}
	
	if(newCase.getPrimeiraCartaHumano().equals(newCase.getPrimeiraCartaRobo()) && newCase.getSegundaCartaHumano().equals(newCase.getSegundaCartaRobo())
			&& newCase.getJogadorMao().equals(1) && (newCase.getTerceiraCartaRobo().equals(0) || newCase.getTerceiraCartaRobo() == null)
&& !newCase.getQuemNegouTruco().equals(1) && (newCase.getTerceiraCartaHumano() == null || newCase.getTerceiraCartaRobo().equals(0) )) {
		newCase.setTerceiraCartaRobo(-46);
		newCase.setTerceiraCartaHumano(-46);
		newCase.setTerceiraCartaRoboClustering(-46);
		newCase.setTerceiraCartaHumanoClustering(-46);	
		//seta = 2 é desconsiderado
		newCase.setUtilCarta(2);
		newCase.setUtilTruco(2);
	}
	
	//alguma das cartas não foi jogada sem motivo
		if( (newCase.getQuemNegouTruco()== null||  newCase.getQuemNegouTruco().equals(0)) &&
				(newCase.getQuemBaralho()== null|| newCase.getQuemBaralho().equals(0)) ) {
			if(newCase.getPrimeiraCartaHumano()== null|| newCase.getPrimeiraCartaHumano().equals(0)) {
				newCase.setPrimeiraCartaHumano(-46);
				newCase.setPrimeiraCartaHumanoClustering(-46);
				newCase.setUtilCarta(2);
				newCase.setUtilTruco(2);
			}
			if(newCase.getSegundaCartaHumano()== null|| newCase.getSegundaCartaHumano().equals(0)) {
				newCase.setSegundaCartaHumano(-46);
				newCase.setSegundaCartaHumanoClustering(-46);
				newCase.setUtilCarta(2);
				newCase.setUtilTruco(2);
			}
			
			if(newCase.getTerceiraCartaHumano()== null|| newCase.getTerceiraCartaHumano().equals(0)) {
				newCase.setTerceiraCartaHumano(-46);
				newCase.setTerceiraCartaHumanoClustering(-46);
				newCase.setUtilCarta(2);
				newCase.setUtilTruco(2);
			}
		}
		
	//printa informações da decisão
	//System.out.println(newCase.toString());
	
		// seta ganhadores das rodadas
	if (!newCase.getPrimeiraCartaRoboClustering().equals(-46)
			&& !newCase.getPrimeiraCartaRoboClustering().equals(-15)
			&& !newCase.getPrimeiraCartaRoboClustering().equals(-10)
			&& !newCase.getPrimeiraCartaHumanoClustering().equals(-46)
			&& !newCase.getPrimeiraCartaHumanoClustering().equals(-15)
			&& !newCase.getPrimeiraCartaHumanoClustering().equals(-10)) {
		if (newCase.getPrimeiraCartaHumano().equals(newCase.getPrimeiraCartaRobo()))
			newCase.setGanhadorPrimeiraRodada(0);
		else if (newCase.getPrimeiraCartaRobo() > newCase.getPrimeiraCartaHumano())
			newCase.setGanhadorPrimeiraRodada(1);
		else if (newCase.getPrimeiraCartaRobo() < newCase.getPrimeiraCartaHumano())
			newCase.setGanhadorPrimeiraRodada(2);
	} else if (newCase.getPrimeiraCartaRoboClustering().equals(-46)
			|| newCase.getPrimeiraCartaHumanoClustering().equals(-46))
		newCase.setGanhadorPrimeiraRodada(1);
	else if (newCase.getPrimeiraCartaRoboClustering().equals(-15)
			|| newCase.getPrimeiraCartaHumanoClustering().equals(-15)
			|| newCase.getPrimeiraCartaRoboClustering().equals(-10)
			|| newCase.getPrimeiraCartaHumanoClustering().equals(-10))
		newCase.setGanhadorPrimeiraRodada(2);

	if (!newCase.getSegundaCartaRoboClustering().equals(-46) && !newCase.getSegundaCartaRoboClustering().equals(-15)
			&& !newCase.getSegundaCartaRoboClustering().equals(-10)
			&& !newCase.getSegundaCartaHumanoClustering().equals(-46)
			&& !newCase.getSegundaCartaHumanoClustering().equals(-15)
			&& !newCase.getSegundaCartaHumanoClustering().equals(-10)) {
		if (newCase.getSegundaCartaHumano().equals(newCase.getSegundaCartaRobo()))
			newCase.setGanhadorSegundaRodada(0);
		else if (newCase.getSegundaCartaRobo() > newCase.getSegundaCartaHumano())
			newCase.setGanhadorSegundaRodada(1);
		else if (newCase.getSegundaCartaRobo() < newCase.getSegundaCartaHumano())
			newCase.setGanhadorSegundaRodada(2);
	} else if (newCase.getSegundaCartaRoboClustering().equals(-46)
			|| newCase.getSegundaCartaHumanoClustering().equals(-46))
		newCase.setGanhadorSegundaRodada(1);
	else if (newCase.getSegundaCartaRoboClustering().equals(-15)
			|| newCase.getSegundaCartaHumanoClustering().equals(-15)
			|| newCase.getSegundaCartaRoboClustering().equals(-10)
			|| newCase.getSegundaCartaHumanoClustering().equals(-10))
		newCase.setGanhadorSegundaRodada(2);
	
	
//System.out.println("terceira carta robo: "+ newCase.getTerceiraCartaRobo() + " id mão: "+newCase.getIdMao());
//System.out.println("carta alta robo: "+ newCase.getCartaAltaRobo()+" jogador mão: "+ newCase.getJogadorMao());
	if (!newCase.getTerceiraCartaRoboClustering().equals(-46)
			&& !newCase.getTerceiraCartaRoboClustering().equals(-15)
			&& !newCase.getTerceiraCartaRoboClustering().equals(-10)
			&& !newCase.getTerceiraCartaHumanoClustering().equals(-46)
			&& !newCase.getTerceiraCartaHumanoClustering().equals(-15)
			&& !newCase.getTerceiraCartaHumanoClustering().equals(-10)) {
		if (newCase.getTerceiraCartaHumano().equals(newCase.getTerceiraCartaRobo()))
			newCase.setGanhadorTerceiraRodada(0);
		else if (newCase.getTerceiraCartaRobo() > newCase.getTerceiraCartaHumano())
			newCase.setGanhadorTerceiraRodada(1);
		else if (newCase.getTerceiraCartaRobo() < newCase.getTerceiraCartaHumano())
			newCase.setGanhadorTerceiraRodada(2);
	} else if (newCase.getTerceiraCartaRoboClustering().equals(-46)
			|| newCase.getTerceiraCartaHumanoClustering().equals(-46))
		newCase.setGanhadorTerceiraRodada(1);
	else if (newCase.getTerceiraCartaRoboClustering().equals(-15)
			|| newCase.getTerceiraCartaHumanoClustering().equals(-15)
			|| newCase.getTerceiraCartaRoboClustering().equals(-10)
			|| newCase.getTerceiraCartaHumanoClustering().equals(-10))
		newCase.setGanhadorTerceiraRodada(2);
	
	
	//evita que sejam setados como uteis para truco casos onde agente perdeu as duas primeiras e ainda assim jogou a ultima
	if(newCase.getGanhadorPrimeiraRodada().equals(2) && newCase.getGanhadorSegundaRodada().equals(2) && 
			(!newCase.getTerceiraCartaRobo().equals(0) || newCase.getTerceiraCartaRobo() != null)) {
	newCase.setUtilCarta(2);
	newCase.setUtilTruco(2);
	}
	
	//centroides
	newCase.setClusteringindexacao(cbr.retornaGrupoMaisSimilarIndexadoJogada(newCase));
	if (newCase.getJogadorMao().equals(1))
		newCase.setClusterPrimeiraCartaAgenteMao(cbr.retornaCentroideMaisSimilarPrimeiraCartaRoboMao(newCase));

	else if (newCase.getJogadorMao().equals(2))
		newCase.setClusterPrimeiraCartaAgentePe(cbr.retornaCentroideMaisSimilarPrimeiraCartaRoboPe((newCase)));

	newCase = new converteFormatosCartasParaCartasJogadasClustering()
			.retornaCodificacaoPrimeiraCartaJogadaRoboClustering(newCase);
	newCase = new converteFormatosCartasParaCartasJogadasClustering().retornaCartasJogadasHumanoClustering(newCase);

	if (newCase.getGanhadorPrimeiraRodada().equals(1)
			|| (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1)))
		newCase.setClusterSegundaCartaAgenteGanhouAPrimeira(
				cbr.retornaCentroideSegundaCartaRoboGanhouAprimeira(newCase));
	else if (newCase.getGanhadorPrimeiraRodada().equals(2)
			|| (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2)))
		newCase.setClusterSegundaCartaAgentePerdeuAPrimeira(
				cbr.retornaCentroideSegundaCartaRoboPerdeuAprimeira(newCase));


	if (newCase.getGanhadorSegundaRodada().equals(1))
		newCase.setClusterTerceiraCartaAgenteGanhouAsegunda(
				cbr.retornaCentroideTerceiraCartaRoboGanhouAsegunda(newCase));
	else if (newCase.getGanhadorSegundaRodada().equals(2))
		newCase.setClusterTerceiraCartaAgentePerdeuAsegunda(
				cbr.retornaCentroideTerceiraCartaRoboPerdeuAsegunda(newCase));

	else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
		newCase.setClusterTerceiraCartaAgenteGanhouAsegunda(
				cbr.retornaCentroideTerceiraCartaRoboGanhouAsegunda(newCase));
	else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
		newCase.setClusterTerceiraCartaAgentePerdeuAsegunda(
				cbr.retornaCentroideTerceiraCartaRoboPerdeuAsegunda(newCase));

	else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(0)
			&& newCase.getJogadorMao().equals(1))
		newCase.setClusterTerceiraCartaAgenteGanhouAsegunda(
				cbr.retornaCentroideTerceiraCartaRoboGanhouAsegunda(newCase));

	else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(0)
			&& newCase.getJogadorMao().equals(2))
		newCase.setClusterTerceiraCartaAgentePerdeuAsegunda(
				cbr.retornaCentroideTerceiraCartaRoboPerdeuAsegunda(newCase));
	
	newCase =(criaDescriptionComInformacoesRelevantesIndexacaoJogadas(newCase, cbr));

	newCase = validaSeDeveIgnorarCarta(newCase);
	return newCase;

}
public default TrucoDescription validaSeDeveIgnorarCarta(TrucoDescription newCase) {
	if(newCase.getUtilCarta() == null ) {
	//empardou a primeira e jogou a baixa na segunda
	if(newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getSegundaCartaRoboClustering().equals(4))
		newCase.setUtilCarta(2);
	//perdeu a primeira e jogou a baixa na segunda
	else if(newCase.getGanhadorPrimeiraRodada().equals(2) && newCase.getSegundaCartaRoboClustering().equals(4))
		newCase.setUtilCarta(2);
	/*
	 * desperdicios na segunda carta quando perdeu a primeira
	 */
	//jogou a carta alta para fazer uma carta que poderia ter sido feita com a carta media
	else if(newCase.getGanhadorPrimeiraRodada().equals(2) && 
	newCase.getSegundaCartaRobo().equals(newCase.getCartaAltaRobo()) && newCase.getSegundaCartaHumano() < newCase.getCartaMediaRobo() &&
	!newCase.getCartaMediaRobo().equals(newCase.getPrimeiraCartaRobo())) 
		newCase.setUtilCarta(2);
	// jogou a carta alta para fazer uma carta que poderia ter sido feita com a carta baixa
	else if(newCase.getGanhadorPrimeiraRodada().equals(2) && 
			newCase.getSegundaCartaRobo().equals(newCase.getCartaAltaRobo()) && newCase.getSegundaCartaHumano() < newCase.getCartaBaixaRobo() &&
			!newCase.getCartaBaixaRobo().equals(newCase.getPrimeiraCartaRobo())) 
				newCase.setUtilCarta(2);
	// jogou a carta media para fazer uma carta que poderia ter sido feita com a carta baixa
		else if(newCase.getGanhadorPrimeiraRodada().equals(2) && 
				newCase.getSegundaCartaRobo().equals(newCase.getCartaMediaRobo()) && newCase.getSegundaCartaHumano() < newCase.getCartaBaixaRobo() &&
				!newCase.getCartaBaixaRobo().equals(newCase.getPrimeiraCartaRobo())) 
					newCase.setUtilCarta(2);
		
	
	/*
	 * desperdicios na segunda primeira carta quando é mão
	 */
	//jogou a carta alta para fazer uma carta que poderia ter sido feita com a carta media
	else if(newCase.getJogadorMao().equals(2) && 
	newCase.getPrimeiraCartaRobo().equals(newCase.getCartaAltaRobo()) && newCase.getPrimeiraCartaHumano() < newCase.getCartaMediaRobo() ) 
		newCase.setUtilCarta(2);
	// jogou a carta alta para fazer uma carta que poderia ter sido feita com a carta baixa
	else if(newCase.getJogadorMao().equals(2) && 
			newCase.getPrimeiraCartaRobo().equals(newCase.getCartaAltaRobo()) && newCase.getPrimeiraCartaHumano() < newCase.getCartaBaixaRobo() ) 
				newCase.setUtilCarta(2);
	// jogou a carta media para fazer uma carta que poderia ter sido feita com a carta baixa
		else if(newCase.getJogadorMao().equals(2) && 
				newCase.getPrimeiraCartaRobo().equals(newCase.getCartaMediaRobo()) && newCase.getPrimeiraCartaHumano() < newCase.getCartaBaixaRobo() ) 
					newCase.setUtilCarta(2);
		
	
	
	
	}
	return  newCase;
}

default TrucoDescription criaDescriptionComInformacoesRelevantesIndexacaoJogadas(TrucoDescription newCase,
		CbrModular cbr) {
	newCase.setClusteringindexacao(cbr.retornaGrupoMaisSimilarIndexadoJogada(newCase));

	return newCase;
}

default TrucoDescription criaDescriptionComInformacoesRelevantesIndexacaoEnvido(TrucoDescription newCase,
		CbrModular cbr) {
	newCase.setClusteringindexacao(cbr.retornaGrupoMaisSimilarIndexadoPontos(newCase));

	return newCase;
}


default TrucoDescription criaDescriptionComInformacoesRelevantesEnvido(TrucoDescription newCase, CbrModular cbr) {

	
	if (newCase.getJogadorMao().equals(1))
		newCase.setClusterQuemEnvidoAgenteMao(cbr.retornaCentroideMaisSimilarEnvido(newCase));

	else if (newCase.getJogadorMao().equals(2))
		newCase.setClusterQuemEnvidoAgentePe(cbr.retornaCentroideMaisSimilarEnvido(newCase));
	
	/*
	 * Preenche quem ganhou envido
	 */
	int quemGanhou =0;
	if( (newCase.getQuemPediuEnvido() !=null && !newCase.getQuemPediuEnvido().equals(0) ) || 
	(newCase.getQuemPediuRealEnvido() != null && !newCase.getQuemPediuRealEnvido().equals(0)  )
	||	(newCase.getQuemPediuFaltaEnvido() != null && !newCase.getQuemPediuFaltaEnvido().equals(0)  )) {
		if(newCase.getQuemNegouEnvido().equals(1)) quemGanhou = 2;
		else if(newCase.getQuemNegouEnvido().equals(2)) quemGanhou = 1;
		
		else if(newCase.getPontosEnvidoHumano() > newCase.getPontosEnvidoRobo() 
		||(newCase.getPontosEnvidoHumano().equals(newCase.getPontosEnvidoRobo()) && newCase.getJogadorMao().equals(2) ))
			quemGanhou =2;
		else if(newCase.getPontosEnvidoHumano() < newCase.getPontosEnvidoRobo() 
				||(newCase.getPontosEnvidoHumano().equals(newCase.getPontosEnvidoRobo()) && newCase.getJogadorMao().equals(1) ))
					quemGanhou =1;
		
		
	}
	newCase.setQuemGanhouEnvido(quemGanhou);
	
	
	int saldoEnvido = 0;

	if (newCase.getQuemGanhouEnvido().equals(1)) {
		saldoEnvido = saldoEnvido + newCase.getTentosEnvido();
	} else if (newCase.getQuemGanhouEnvido().equals(2)) {
		saldoEnvido = saldoEnvido - newCase.getTentosEnvido();
	}

	newCase.setQuemPediuEnvidoCluster(retornaQuemCluster(newCase.getQuemPediuEnvido()));
	newCase.setQuemPediuRealEnvidoCluster(retornaQuemCluster(newCase.getQuemPediuRealEnvido()));
	newCase.setQuemPediuFaltaEnvidoCluster(retornaQuemCluster (newCase.getQuemPediuFaltaEnvido()));
	
	//newCase.setSaldoEnvido(UtilSaldos.retornaSaldoEnvido(newCase));
	
	newCase.setSaldoEnvido(saldoEnvido);
	newCase.setClusteringIndexacaoPontos(cbr.retornaGrupoMaisSimilarIndexadoPontos(newCase));
	
	newCase = validaSedeveIgnorarEnvidoEretornaDescription(newCase);
	newCase = criaDescriptionComInformacoesRelevantesIndexacaoEnvido(newCase, cbr);
	
	//val
	
	return newCase;
}

public default TrucoDescription validaSedeveIgnorarEnvidoEretornaDescription(TrucoDescription newCase) {
/*
 * descomentar isso para descobrir o porque alguns falta envido estão sendo chamados errado.
	try {
		Thread.currentThread().sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	//System.out.println("utilEnvido = " + newCase.getUtilEnvido());
	if(newCase.getUtilEnvido() == null ){	
		//é mão e pediu envido sem pontos
	
	if(newCase.getJogadorMao().equals(1) && ( (newCase.getQuemPediuEnvido() != null || !newCase.getQuemPediuEnvido().equals(0)) ||
	(newCase.getQuemPediuRealEnvido() != null || !newCase.getQuemPediuRealEnvido().equals(0)) || 
	(newCase.getQuemPediuFaltaEnvido() != null || !newCase.getQuemPediuFaltaEnvido().equals(0)) ) 
	&& newCase.getPontosEnvidoRobo()< 20 && !newCase.getQuemNegouEnvido().equals(1)) 
		newCase.setUtilEnvido(2);
	//tem menos que 28 e chamou ou aceitou falta envido
	if( (newCase.getQuemPediuFaltaEnvido() != null && !newCase.getQuemPediuFaltaEnvido().equals(0)) && !newCase.getQuemNegouEnvido().equals(1) 
			&& newCase.getPontosEnvidoRobo() < 28)
		newCase.setUtilEnvido(2);
	//tem menos que 25 e chamou ou aceitou real envido
	if( (newCase.getQuemPediuRealEnvido() != null && !newCase.getQuemPediuRealEnvido().equals(0)) && !newCase.getQuemNegouEnvido().equals(1) 
			&& newCase.getPontosEnvidoRobo() < 25)
		newCase.setUtilEnvido(2);
	
	//tem mais de 27 e negou envido
	 if( (newCase.getQuemPediuFaltaEnvido() != null && !newCase.getQuemPediuFaltaEnvido().equals(0)) && newCase.getQuemNegouEnvido().equals(1) 
			&& newCase.getPontosEnvidoRobo() > 27)
		newCase.setUtilEnvido(2);
	 
	 //tem mais ou igual a 27 e ninguém chamou Envido
	 if( ( (newCase.getQuemPediuEnvido() == null || newCase.getQuemPediuEnvido().equals(0)) &&
		(newCase.getQuemPediuRealEnvido() == null || newCase.getQuemPediuRealEnvido().equals(0)) &&
		(newCase.getQuemPediuFaltaEnvido() == null || newCase.getQuemPediuFaltaEnvido().equals(0)) ) &&
	   newCase.getPontosEnvidoRobo() >= 27 ) {
		 newCase.setUtilEnvido(2);
	 }
	 
	 
	}
	// não tem pontos é mão e chamou pontos
	if( newCase.getJogadorMao().equals(1)  && ( (newCase.getQuemPediuEnvido()!= null && !newCase.getQuemPediuEnvido().equals(0)) || 
	(newCase.getQuemPediuRealEnvido() != null && !newCase.getQuemPediuRealEnvido().equals(0)) ||
	(newCase.getQuemPediuFaltaEnvido() != null && !newCase.getQuemPediuFaltaEnvido().equals(0)) ) && (newCase.getQuemNegouEnvido() == null ||
			!newCase.getQuemNegouEnvido().equals(1)))	
		newCase.setUtilEnvido(0);


	
	return newCase;
	
}
 

}
