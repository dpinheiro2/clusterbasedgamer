package DecisionsIntraClusterProbabilidadeChance;


import java.util.HashMap;
import java.util.List;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import UtilProbabilidadeChance.ProbabilidadeChance;
import UtilProbabilidadeChance.UtilProbabilidadeChanceIntraClusterEnvidoEtruco;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;

import clusterModelo.SaldoModelo;
import hashSolucao.HashSolucaoEnvido;

public class AcaoFeitasIntraClusterEnvidoProbabilidadeChance implements AcaoFeitasIntraClusterEnvido {
	HashSolucaoEnvido hashEnvido = new HashSolucaoEnvido();
	UtilProbabilidadeChanceIntraClusterEnvidoEtruco probabilidadeIntraCluster = new UtilProbabilidadeChanceIntraClusterEnvidoEtruco();
	ProbabilidadeChance probabilidadeChance = new ProbabilidadeChance(); 
	/*
	 * ========== POSSIBILIDADES CHAMAR ======================================= 12 -
	 * 12 - 1 CHAMA, 2 NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 20 - 2 CHAMA, 0 NINGUÉM
	 * NEGA 
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
	public boolean chamarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashChamarEnvido(listaIntraClusterQuemEnvido, jogadorMao);
			
			int idGrupoComMaiorProbabilidadeChamarEnvido = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			////////System.out.println("grupo retornado envido probabilidadeChance: "+ idGrupoComMaiorProbabilidadeChamarEnvido);
			boolean chamar = false;
			if(idGrupoComMaiorProbabilidadeChamarEnvido == 121020) {
				chamar = probabilidadeIntraCluster.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(listaIntraClusterQuemEnvido);
			}
			
			 

			return chamar;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean chamarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashChamarRealEnvido(listaIntraClusterQuemEnvido, jogadorMao);
			int idGrupoComMaiorProbabilidadeChamarEnvido = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			boolean chamar = false;
			if(idGrupoComMaiorProbabilidadeChamarEnvido == 121020) {
				chamar = probabilidadeIntraCluster.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(listaIntraClusterQuemEnvido);
			}
			
			

			return chamar;
			} catch (Exception e) {
				e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean chamarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashChamarFaltaEnvido(listaIntraClusterQuemEnvido, jogadorMao);

			int idGrupoComMaiorProbabilidadeChamarEnvido = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			//////System.out.println("okokokokokokokok@@@@@@ chamar falta envido okokokokokokokokookokokok@@@@@");
			//////System.out.println("grupo com maior probabilidade de vitoria intra cluster chamar falta envido: "+ idGrupoComMaiorProbabilidadeChamarEnvido);
			boolean chamar = false;
			if(idGrupoComMaiorProbabilidadeChamarEnvido == 121020) {
				chamar = probabilidadeIntraCluster.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(listaIntraClusterQuemEnvido);
			}
			
			//////System.out.println("okokokokokokokok@@@@@@ concluir chamar falta envido okokokokokokokokookokokok@@@@@");
			return chamar;
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * ========== COMBINAÇÕES ACEITAR  ============================================
	 * 20 - 2 CHAMA, 0 NINGUÉM NEGA 
	 * 10 - 1 CHAMA, 0 NINGUÉM NEGA 
	 * 12 - 1 CHAMA, 2 NEGA 
	 * 201012 <- REPRESENTA O CLUSTER ACEITAR TRUCO
	 * =============================================================================
	 * ========== COMBINAÇÕES NEGAR TRUCO=========================================== 
	 * 21 - 2 CHAMA, 1 NEGA 
	 * 30 - NINGUÉM CHAMA -> QUERO UMA OPINIÃO SOBRE ISSO, NÃO TENHO CERTEZA SE DEVO
	 * CONTABILIZAR PARA NEGAR TRUCO AQUELES QUE NÃO FOI CHAMADO 
	 * 2130 <- REPRESENTA NEGAR 
	 * =============================================================================
	 * ==
	 * 
	 */

	@Override
	public boolean aceitarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			////////System.out.println("jogador mão: "+ jogadorMao);
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashAceitarEnvido(listaIntraClusterQuemEnvido, jogadorMao);
			//////System.out.println("-----------aceitar envido --------------------");
			
			int idGrupoComMaiorProbabilidadeChamarEnvido = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			
			//////System.out.println("grupo com maior probabilidade de vitória aceitar envido intra cluster "+ idGrupoComMaiorProbabilidadeChamarEnvido);
			////////System.out.println("grupo retornado envido probabilidadeChance aceitar: "+ idGrupoComMaiorProbabilidadeChamarEnvido);
			boolean aceitar = false;
			
			if(idGrupoComMaiorProbabilidadeChamarEnvido == 201012) {
				aceitar = probabilidadeIntraCluster.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(listaIntraClusterQuemEnvido);
			}
			//////System.out.println("-----------conclusão aceitar envido --------------------");
			
			return aceitar;
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean aceitarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashAceitarRealEnvido(listaIntraClusterQuemEnvido, jogadorMao);
			//////System.out.println("------------ aceitar real envido --------");
			int idGrupoComMaiorProbabilidadeChamarEnvido = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			//////System.out.println("grupo retornado: "+ idGrupoComMaiorProbabilidadeChamarEnvido);
			boolean aceitar = false;
			if(idGrupoComMaiorProbabilidadeChamarEnvido == 201012) {
				aceitar = probabilidadeIntraCluster.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(listaIntraClusterQuemEnvido);
			}
			
			//////System.out.println("-------------- finaliza aceitar realEnvido ----------------");

			return aceitar;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashAceitarFaltaEnvido(listaIntraClusterQuemEnvido, jogadorMao);
			//////System.out.println("+++++++++++++++++++++++aceitar falta envido intra cluster++++++++++++++++++++++++++++");
			//////System.out.println("quantidade de casos analisados intra cluster: "+ listaIntraClusterQuemEnvido.size());
			int idGrupoComMaiorProbabilidadeChamarEnvido = probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			boolean aceitar = false;
			if(idGrupoComMaiorProbabilidadeChamarEnvido == 201012) {
				aceitar = probabilidadeIntraCluster.probabilidadeDeChamarEganharEmaiorDoQueChamarEperderEnvido(listaIntraClusterQuemEnvido);
			}
			

			
			 //////System.out.println("grupo com maior probabilidade envido: "+idGrupoComMaiorProbabilidadeChamarEnvido);
			 //////System.out.println("chamar retornado: "+ aceitar);
			 
			 //////System.out.println("++++++++++++++++++++++++fim  probabilidade envido cluster++++++++++++++++++++++");


			return aceitar;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	



}
