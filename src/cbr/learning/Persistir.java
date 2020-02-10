package cbr.learning;

import java.util.ArrayList;
import java.util.Collection;

import Ajudas.ValidaGanhadorMao;
import CentroidesModelo.CaseBasesModelo;
import ajudaCluster.CalcularCentroides;
import ajudaCluster.UtilSaldos;
import ajudaCluster.converteFormatosCartasParaCartasJogadasClustering;
import cbr.Adaptacoes.CbrModular;
import cbr.cbrDescriptions.TrucoDescription;
import jcolibri.cbrcore.CBRCase;

public interface Persistir {
	public void persistir(TrucoDescription newCase, CbrModular cbr);

	public void persistir(TrucoDescription newCase, CbrModular cbr, boolean compulsoryRetention);
	
 default void aprendeCasos(int ultimoId, TrucoDescription newCase, CbrModular cbr) {

		int lastId = ultimoId + 1;
	CBRCase aprenderCaso;
	CaseBasesModelo caseBases = cbr.preencheCaseBase();
		aprenderCaso = preparaAprendizado(newCase, lastId, caseBases);
		
		cbr.learnCasesMaos(aprenderCaso);
		CbrModular.casosEnviadosPersistencia ++;
		//System.out.println("casos enviados para persistencia "+ cbr.casosEnviadosPersistencia);
		/*
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//caseBases = cbr.preencheCaseBase();
		//faz perder muita performance e em uma partida muda muito pouco.
		//recalculaCentroidesDeAcordoComAsValidacoes(newCase, caseBases, cbr);

		
	}
	

	default CBRCase preparaAprendizado(TrucoDescription newCase, int lastId, CaseBasesModelo caseBases) {
		CBRCase tempCase = new CBRCase();
		String newIdPartida = "aprendizado_ativo";
		newCase.setIdPartida(newIdPartida);
		newCase.setIdMao(lastId);
		tempCase.setDescription(newCase);
		

		return tempCase;
	}


	/*
	 * estão sendo criados antes da validação
	default TrucoDescription setaGruposQueOcasoPertenceEadicionaInformacoesRelevantes(TrucoDescription newCase,
			CbrModular cbr) {

		if (newCase.getUtilTruco().equals(1))
			newCase = criaDescriptionComInformacoesRelevantesTruco(newCase, cbr);
		if (newCase.getUtilCarta().equals(1))
			newCase = criaDescriptionComInformacoesRelevantesCarta(newCase, cbr);
		if (newCase.getUtilEnvido().equals(1))
			newCase = criaDescriptionComInformacoesRelevantesEnvido(newCase, cbr);
		if (newCase.getUtilEnvido().equals(1) || newCase.getUtilFlor().equals(1))
			newCase = criaDescriptionComInformacoesRelevantesIndexacaoPontos(newCase, cbr);
		if (newCase.getUtilCarta().equals(1) || newCase.getUtilTruco().equals(1))
			newCase = criaDescriptionComInformacoesRelevantesIndexacaoJogadas(newCase, cbr);

	
		return newCase;
		
	}

*/
	// isso é depois que aprender para recalcular os centroides aprendidos
	//preciso chamar esse método
	default void recalculaCentroidesDeAcordoComAsValidacoes(TrucoDescription newCase, CaseBasesModelo caseBases,
			CbrModular cbr) {
		CalcularCentroides calcularCentroides = new CalcularCentroides(caseBases, cbr);
		if (newCase.getUtilCarta().equals(1)) {
			recalculaCentroidesPrimeiraCarta(newCase, calcularCentroides);
			recalculaCentroidesSegundaCarta(newCase, calcularCentroides);
			recalculaCentroidesTerceiraCarta(newCase, calcularCentroides);
		}

		if (newCase.getUtilTruco().equals(1))
			recalculaCentroidesTruco(newCase, calcularCentroides);
		
		

		if (newCase.getUtilEnvido().equals(1))
			recalculaCentroidesEnvido(newCase, calcularCentroides);
		if (newCase.getUtilEnvido().equals(1) || newCase.getUtilFlor().equals(1))
			recalculaCentroidesIndexacaoPontos(newCase, calcularCentroides);
		if (newCase.getUtilCarta().equals(1) || newCase.getUtilTruco().equals(1))
			recalculaCentroidesIndexacaoJogada(newCase, calcularCentroides);
		
	}
	

	default void recalculaCentroidesPrimeiraCarta(TrucoDescription newCase, CalcularCentroides calcularCentroides) {
		if (newCase.getJogadorMao().equals(1))
			calcularCentroides.recalculaCentroidesPrimeiraCartaRoboMao(newCase);
		else if (newCase.getJogadorMao().equals(2))
			calcularCentroides.recalculaCentroidesPrimeiraCartaRoboPe(newCase);

	}

	default TrucoDescription recalculaCentroidesSegundaCarta(TrucoDescription newCase,
			CalcularCentroides calcularCentroides) {
		if (newCase.getGanhadorPrimeiraRodada().equals(1))
			calcularCentroides.recalculaCentroidesSegundaCartaRoboGanhouAprimeira(newCase);
		else if (newCase.getGanhadorPrimeiraRodada().equals(2))
			calcularCentroides.recalculaCentroidesSegundaCartaRoboPerdeuAprimeira(newCase);

		if (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1))
			calcularCentroides.recalculaCentroidesSegundaCartaRoboGanhouAprimeira(newCase);
		else if (newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2))
			calcularCentroides.recalculaCentroidesSegundaCartaRoboPerdeuAprimeira(newCase);

		return newCase;
	}

	default void recalculaCentroidesTerceiraCarta(TrucoDescription newCase, CalcularCentroides calcularCentroides) {
		if (newCase.getGanhadorSegundaRodada().equals(1))
			calcularCentroides.recalculaCentroidesTerceiraCartaRoboGanhouAsegunda(newCase);
		else if (newCase.getGanhadorSegundaRodada().equals(2))
			calcularCentroides.recalculaCentroidesTerceiraCartaRoboPerdeuAsegunda(newCase);

		else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
			calcularCentroides.recalculaCentroidesTerceiraCartaRoboGanhouAsegunda(newCase);
		else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
			calcularCentroides.recalculaCentroidesTerceiraCartaRoboPerdeuAsegunda(newCase);

		else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(0)
				&& newCase.getJogadorMao().equals(1))
			calcularCentroides.recalculaCentroidesTerceiraCartaRoboGanhouAsegunda(newCase);
		else if (newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(0)
				&& newCase.getJogadorMao().equals(2))
			calcularCentroides.recalculaCentroidesTerceiraCartaRoboPerdeuAsegunda(newCase);

	}

	default void recalculaCentroidesEnvido(TrucoDescription newCase, CalcularCentroides calcularCentroides) {
		if (newCase.getJogadorMao().equals(1))
			calcularCentroides.recalculaCentroidesQuemEnvidoAgenteMao(newCase);
		if (newCase.getJogadorMao().equals(2))
			calcularCentroides.recalculaCentroidesQuemEnvidoAgentePe(newCase);

	}

	default void recalculaCentroidesTruco(TrucoDescription newCase, CalcularCentroides calcularCentroides) {

		if (newCase.getJogadorMao().equals(1))
			calcularCentroides.recalculaCentroidesQuemTrucoPrimeiraMao(newCase);
		else if (newCase.getJogadorMao().equals(1))
			calcularCentroides.recalculaCentroidesQuemTrucoPrimeiraPe(newCase);

		if (newCase.getGanhadorPrimeiraRodada().equals(1) || (newCase.equals(0) && newCase.getJogadorMao().equals(1)))
			calcularCentroides.recalculaCentroidesQuemTrucoSegundaGanhouAnterior(newCase);
		else if (newCase.getGanhadorPrimeiraRodada().equals(2)
				|| (newCase.equals(0) && newCase.getJogadorMao().equals(2)))
			calcularCentroides.recalculaCentroidesQuemTrucoSegundaPerdeuAnterior(newCase);

		if (newCase.getGanhadorSegundaRodada().equals(1)
				|| ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(1))
						|| (newCase.getGanhadorSegundaRodada().equals(0)
								&& newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(1))))
			calcularCentroides.recalculaCentroidesQuemTrucoTerceiraGanhouAnterior(newCase);

		else if (newCase.getGanhadorSegundaRodada().equals(2)
				|| ((newCase.getGanhadorSegundaRodada().equals(0) && newCase.getGanhadorPrimeiraRodada().equals(2))
						|| (newCase.getGanhadorSegundaRodada().equals(0)
								&& newCase.getGanhadorPrimeiraRodada().equals(0) && newCase.getJogadorMao().equals(2))))
			calcularCentroides.recalculaCentroidesQuemTrucoTerceiraCartaPerdeuAnterior(newCase);
	}

	default void recalculaCentroidesIndexacaoJogada(TrucoDescription newCase, CalcularCentroides calcularCentroides) {
		calcularCentroides.recalculaCentroidesIndexacao(newCase);
	}

	default void recalculaCentroidesIndexacaoPontos(TrucoDescription newCase, CalcularCentroides calcularCentroides) {
		calcularCentroides.recalculaCentroidesIndexacaoPontos(newCase);
	}
}
