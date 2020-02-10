package DecisionsIntraClusterProbabilidadeChance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import UtilProbabilidadeChance.ProbabilidadeChance;
import UtilProbabilidadeChance.UtilProbabilidadeChanceIntraClusterEnvidoEtruco;

import cbr.cbrDescriptions.TrucoDescription;

import hashSolucao.HashSolucaoTruco;

public class AcaoFeitasIntraClusterTrucoProbabilidadeChance implements AcaoFeitasIntraClusterTruco {
	HashSolucaoTruco hashTruco = new HashSolucaoTruco();
	ProbabilidadeChance probabilidadeChanceExtra = new ProbabilidadeChance();

	UtilProbabilidadeChanceIntraClusterEnvidoEtruco chanceIntraClusterTruco = new UtilProbabilidadeChanceIntraClusterEnvidoEtruco();

	/*
	 * ========== POSSIBILIDADES CHAMAR ======================================= 12 -
	 * 1 CHAMA, 2 NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 20 - 2 CHAMA, 0 NINGUÉM NEGA
	 * 121020 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA CHAMAR
	 * =============================================================================
	 * = ========= POSSIBILIDADES NÃO CHAMAR
	 * TRUCO==================================== 21 - 2 CHAMA, 1 NEGA 30 - NINGUÉM
	 * CHAMA 2130 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA NÃO CHAMAR
	 * 
	 * =============================================================================
	 * ==
	 * 
	 */
	@Override
	public boolean chamarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco
					.retornaHashQuemChamouTruco(listaIntraClusterQuemTruco, rodada);
			

			
			int grupoComMaiorProbabilidadeQuemTruco = probabilidadeChanceExtra
					.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
////////System.out.println("grupo com maior probabilidade chance intra cluster quandoTruco "+grupoComMaiorProbabilidadeQuandoTruco);
////////System.out.println("grupo com maior probabilidade chance intra cluster quem Truco "+grupoComMaiorProbabilidadeQuemTruco);
boolean chamar = false;
////////System.out.println("rodada atual: "+ rodada);
			if ( grupoComMaiorProbabilidadeQuemTruco == 121020) {
			 chamar = chanceIntraClusterTruco
						.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(listaIntraClusterQuemTruco);
			 	}

			return chamar;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean chamarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco
					.retornaHashQuemChamouReTruco(listaIntraClusterQuemTruco, rodada);
		

			
			int grupoComMaiorProbabilidadeQuemTruco = probabilidadeChanceExtra
					.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());

			boolean chamar = false;
			if (grupoComMaiorProbabilidadeQuemTruco == 121020) {
				chamar = chanceIntraClusterTruco
						.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(listaIntraClusterQuemTruco);
			}

			return chamar;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean chamarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco
					.retornaHashQuemChamouValeQuatro(listaIntraClusterQuemTruco, rodada);
			
			
			int grupoComMaiorProbabilidadeQuemTruco = probabilidadeChanceExtra
					.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());

			boolean chamar = false;
			if ( grupoComMaiorProbabilidadeQuemTruco == 121020) {
				chamar = chanceIntraClusterTruco
						.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(listaIntraClusterQuemTruco);
			}

			return chamar;

		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * ========== COMBINAÇÕES ACEITAR ============================================
	 * 20 - 2 CHAMA, 0 NINGUÉM NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 12 - 1 CHAMA, 2
	 * NEGA 201012 <- REPRESENTA O CLUSTER ACEITAR TRUCO
	 * =============================================================================
	 * ========== COMBINAÇÕES NEGAR TRUCO===========================================
	 * 21 - 2 CHAMA, 1 NEGA 30 - NINGUÉM CHAMA -> QUERO UMA OPINIÃO SOBRE ISSO, NÃO
	 * TENHO CERTEZA SE DEVO CONTABILIZAR PARA NEGAR TRUCO AQUELES QUE NÃO FOI
	 * CHAMADO 2130 <- REPRESENTA NEGAR
	 * =============================================================================
	 * ==
	 * 
	 */

	@Override
	public boolean aceitarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco
					.retornaHashAceitarTruco(listaIntraClusterQuemTruco, rodada);

			int grupoComMaiorProbabilidadeQuemTruco = probabilidadeChanceExtra
					.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());

			boolean aceitar = false;
			////////System.out.println("grupo com maior probabilidade de vitória quem truco: "+ grupoComMaiorProbabilidadeQuemTruco);
			if (grupoComMaiorProbabilidadeQuemTruco == 201012) {
				aceitar = chanceIntraClusterTruco
						.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(listaIntraClusterQuemTruco);
			}

			return aceitar;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean aceitarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco
					.retornaHashAceitarRetruco(listaIntraClusterQuemTruco, rodada);
			int grupoComMaiorProbabilidadeQuemTruco = probabilidadeChanceExtra
					.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());

			boolean aceitar = false;
			if (grupoComMaiorProbabilidadeQuemTruco == 201012) {
				aceitar = chanceIntraClusterTruco
						.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(listaIntraClusterQuemTruco);
			}

			return aceitar;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean aceitarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
			HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco
					.retornaHashAceitarValeQuatro(listaIntraClusterQuemTruco, rodada);

			int grupoComMaiorProbabilidadeQuemTruco = probabilidadeChanceExtra
					.retornaClusterComMaiorChanceDeVitoriaTruco(hashQuemTruco, hashQuemTruco.keySet());
			////////System.out.println("grupo com maior probabilidade de vitória quem truco: "+ grupoComMaiorProbabilidadeQuemTruco);
			boolean aceitar = false;
			if (grupoComMaiorProbabilidadeQuemTruco == 201012) {
				aceitar = chanceIntraClusterTruco
						.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderTruco(listaIntraClusterQuemTruco);
			}

			return aceitar;

		} catch (Exception e) {
			return false;
		}
	}



	// selecao ir ao baralho saldo
	public boolean selecaoIrAoBaralhoPorSaldo(List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldoQuemBaralho,
			int rodada) {
		if (aAcaoMaisFeitaEirAoBaralho(listaDeAcoesDoGrupoComMaiorSaldoQuemBaralho)
				&& quandoIrAoBaralho(listaDeAcoesDoGrupoComMaiorSaldoQuemBaralho) == rodada) {

			return true;
		} else
			return false;
	}

	public boolean aAcaoMaisFeitaEirAoBaralho(List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldo) {
		int quantidadeIrAoBaralho = 0;
		int quantidadeNaoIrAoBaralho = 0;
		for (TrucoDescription t : listaDeAcoesDoGrupoComMaiorSaldo) {
			if (t.getQuemBaralho() == 1)
				quantidadeIrAoBaralho++;
			else
				quantidadeNaoIrAoBaralho++;
		}
		return quantidadeIrAoBaralho > quantidadeNaoIrAoBaralho;
	}

	public int quandoIrAoBaralho(List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldo) {
		int quantidadeIrAoBaralhoPrimeiraRodada = 0;
		int quantidadeIrAoBaralhoSegundaRodada = 0;
		int quantidadeIrAoBaralhoTerceiraRodada = 0;
		Iterator<TrucoDescription> iteratorCasosDoGrupo = listaDeAcoesDoGrupoComMaiorSaldo.iterator();
		while (iteratorCasosDoGrupo.hasNext()) {
			TrucoDescription t = iteratorCasosDoGrupo.next();
			if (t.getQuemBaralho() == 1) {
				if (t.getQuandoBaralho() == 1)
					quantidadeIrAoBaralhoPrimeiraRodada++;
				else if (t.getQuandoBaralho() == 2)
					quantidadeIrAoBaralhoSegundaRodada++;
				else if (t.getQuandoBaralho() == 3)
					quantidadeIrAoBaralhoTerceiraRodada++;
			}

		}
		int localQueMaisVaiAoBaralho = 0;
		if (quantidadeIrAoBaralhoPrimeiraRodada > quantidadeIrAoBaralhoSegundaRodada
				&& quantidadeIrAoBaralhoPrimeiraRodada > quantidadeIrAoBaralhoTerceiraRodada)
			localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoPrimeiraRodada;
		else if (quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoPrimeiraRodada
				&& quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoTerceiraRodada)
			localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoSegundaRodada;
		else if (quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoPrimeiraRodada
				&& quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoTerceiraRodada)
			localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoSegundaRodada;
		else if (quantidadeIrAoBaralhoTerceiraRodada > quantidadeIrAoBaralhoPrimeiraRodada
				&& quantidadeIrAoBaralhoTerceiraRodada > quantidadeIrAoBaralhoSegundaRodada)
			localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoTerceiraRodada;

		return localQueMaisVaiAoBaralho;
	}

}
