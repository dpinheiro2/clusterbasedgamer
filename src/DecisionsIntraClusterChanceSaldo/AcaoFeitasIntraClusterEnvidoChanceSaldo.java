package DecisionsIntraClusterChanceSaldo;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;

import clusterModelo.SaldoModelo;
import hashSolucao.HashSolucaoEnvido;

public class AcaoFeitasIntraClusterEnvidoChanceSaldo implements AcaoFeitasIntraClusterEnvido {
	HashSolucaoEnvido hashEnvido = new HashSolucaoEnvido();
	Saldos retornaSaldo = new Saldos();
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
			////////System.out.println("tamanho da lista para seleção intra cluster: "+ listaIntraClusterQuemEnvido.size() );
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashChamarEnvido(listaIntraClusterQuemEnvido, jogadorMao);
			listaJogadasRealizadas.keySet().forEach(f ->{
				////////System.out.println("Key: "+ f);
			});
			SaldoModelo saldoEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			
			////////System.out.println("ação intra cluster sugerida: "+ saldoEnvido.getClusterComMaiorSaldo());

			boolean chamar = saldoEnvido.getClusterComMaiorSaldo() == 121020 ? true : false;

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

			SaldoModelo saldoEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());

			boolean chamar = saldoEnvido.getClusterComMaiorSaldo() == 121020 ? true : false;

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

			SaldoModelo saldoEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());

			boolean chamar = saldoEnvido.getClusterComMaiorSaldo() == 121020 ? true : false;

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
			
			
			SaldoModelo saldoEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
			////////System.out.println("");
			boolean aceitar = saldoEnvido.getClusterComMaiorSaldo()==201012 ? true : false;
			 
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
			SaldoModelo saldoEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());

			
			boolean aceitar = saldoEnvido.getClusterComMaiorSaldo()==201012 ? true : false;
			 
			 return aceitar;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aceitarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
			HashMap<Integer, List<TrucoDescription>> listaJogadasRealizadas = hashEnvido
					.retornaHashAceitarFaltaEnvido(listaIntraClusterQuemEnvido, jogadorMao);
		    SaldoModelo saldoEnvido = retornaSaldo.retornaAcaoOuGrupoComMaiorSaldoEnvido(listaJogadasRealizadas,
					listaJogadasRealizadas.keySet());
		    boolean aceitar = saldoEnvido.getClusterComMaiorSaldo()==201012 ? true : false;
			 
			 return aceitar;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	



}
