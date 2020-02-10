package DecisionsIntraClusterChanceSaldo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import UtilSaldo.Saldos;
import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.QuandoTrucoModelo;
import clusterModelo.SaldoModelo;


import hashSolucao.HashSolucaoTruco;

public class AcaoFeitasIntraClusterTrucoChanceSaldo implements AcaoFeitasIntraClusterTruco{
	HashSolucaoTruco hashTruco = new HashSolucaoTruco();
	Saldos saldo = new Saldos();
	/*
	 * ========== POSSIBILIDADES CHAMAR =======================================
	 * 12 - 1 CHAMA, 2 NEGA 
	 * 10 - 1 CHAMA, 0 NINGUÉM NEGA 
	 * 20 - 2 CHAMA, 0 NINGUÉM NEGA
	 * 121020 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA CHAMAR
	 * =============================================================================
	 * = ========= POSSIBILIDADES NÃO CHAMAR TRUCO==================================== 
	 * 21 - 2 CHAMA, 1 NEGA 
	 * 30 - NINGUÉM CHAMA
	 * 2130 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA NÃO CHAMAR
	 * 
	 * =============================================================================
	 * ==
	 * 
	 */
	@Override
	public boolean chamarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
		HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco.retornaHashQuemChamouTruco(listaIntraClusterQuemTruco, rodada);
		
		
		
		SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
		
		
		
		boolean chamar = saldoModeloQuemTruco.getClusterComMaiorSaldo()==121020 ? true : false;
				
		return  chamar ;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean chamarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
		HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco.retornaHashQuemChamouReTruco(listaIntraClusterQuemTruco, rodada);
		
		
		
		SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
		
		
		
		boolean chamar = saldoModeloQuemTruco.getClusterComMaiorSaldo()==121020 ? true : false;
		
		return  chamar ;		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	public boolean chamarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
		HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco.retornaHashQuemChamouValeQuatro(listaIntraClusterQuemTruco, rodada);
		
		
		
		SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
		
		
		
		
				
        boolean chamar = saldoModeloQuemTruco.getClusterComMaiorSaldo()==121020 ? true : false;
		
		return  chamar ;
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
	public boolean aceitarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
		HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco.retornaHashAceitarTruco(listaIntraClusterQuemTruco, rodada);
		
		SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
		
		boolean aceitar = saldoModeloQuemTruco.getClusterComMaiorSaldo()==201012 ? true : false;
		 
		 return aceitar;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}


	@Override
	public boolean aceitarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
		HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco.retornaHashAceitarRetruco(listaIntraClusterQuemTruco, rodada);
				SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
				
				boolean aceitar = saldoModeloQuemTruco.getClusterComMaiorSaldo()==201012 ? true : false;
				 
				 return aceitar;
					} catch (Exception e) {
						e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean aceitarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,
			 int rodada) {
		try {
		HashMap<Integer, List<TrucoDescription>> hashQuemTruco = hashTruco.retornaHashAceitarValeQuatro(listaIntraClusterQuemTruco, rodada);
		
		
		SaldoModelo saldoModeloQuemTruco = saldo.retornaAcaoOuGrupoComMaiorSaldoTruco(hashQuemTruco, hashQuemTruco.keySet());
      
				
				
		boolean aceitar = saldoModeloQuemTruco.getClusterComMaiorSaldo()==201012 ? true : false;
		 
		 return aceitar;
	
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}

	

			public boolean aAcaoMaisFeitaEirAoBaralho(List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldo) {
				int quantidadeIrAoBaralho =0;
				int quantidadeNaoIrAoBaralho =0;
				for(TrucoDescription t: listaDeAcoesDoGrupoComMaiorSaldo) {
					if(t.getQuemBaralho() == 1)  quantidadeIrAoBaralho ++;
					else quantidadeNaoIrAoBaralho ++;
				}
				return quantidadeIrAoBaralho > quantidadeNaoIrAoBaralho;
			}
			
			public int quandoIrAoBaralho(List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldo) {
				int quantidadeIrAoBaralhoPrimeiraRodada =0;
				int quantidadeIrAoBaralhoSegundaRodada =0;
				int quantidadeIrAoBaralhoTerceiraRodada =0;
				Iterator<TrucoDescription> iteratorCasosDoGrupo = listaDeAcoesDoGrupoComMaiorSaldo.iterator();
				while(iteratorCasosDoGrupo.hasNext()) {
					TrucoDescription t= iteratorCasosDoGrupo.next();
					if(t.getQuemBaralho() == 1) {
						if(t.getQuandoBaralho() == 1) quantidadeIrAoBaralhoPrimeiraRodada ++;
						else if(t.getQuandoBaralho() == 2) quantidadeIrAoBaralhoSegundaRodada ++;
						else if(t.getQuandoBaralho() == 3) quantidadeIrAoBaralhoTerceiraRodada ++;
					}
					
				}
				int localQueMaisVaiAoBaralho = 0;
				if(quantidadeIrAoBaralhoPrimeiraRodada > quantidadeIrAoBaralhoSegundaRodada 
				&& quantidadeIrAoBaralhoPrimeiraRodada > quantidadeIrAoBaralhoTerceiraRodada) localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoPrimeiraRodada;
				else if(quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoPrimeiraRodada
						&& quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoTerceiraRodada) localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoSegundaRodada;
				else if(quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoPrimeiraRodada
						&& quantidadeIrAoBaralhoSegundaRodada > quantidadeIrAoBaralhoTerceiraRodada) localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoSegundaRodada;
				else if(quantidadeIrAoBaralhoTerceiraRodada > quantidadeIrAoBaralhoPrimeiraRodada
						&&quantidadeIrAoBaralhoTerceiraRodada > quantidadeIrAoBaralhoSegundaRodada ) localQueMaisVaiAoBaralho = quantidadeIrAoBaralhoTerceiraRodada;
				
				return localQueMaisVaiAoBaralho;
			}
			
			
			public QuandoTrucoModelo verificaQuandoOTrucoEchamado(List<TrucoDescription> jogador) {
				int quantidadeTrucoChamadoAgentePrimeiraRodada = 0;
				int quantidadeRetrucoChamadoAgentePrimeiraRodada = 0;
				int quantidadeValeQuatroChamadoAgentePrimeiraRodada = 0;

				int quantidadeTrucoChamadoAgenteSegundaRodada = 0;
				int quantidadeRetrucoChamadoAgenteSegundaRodada = 0;
				int quantidadeValeQuatroChamadoAgenteSegundaRodada = 0;

				int quantidadeTrucoChamadoAgenteTerceiraRodada = 0;
				int quantidadeRetrucoChamadoAgenteTerceiraRodada = 0;
				int quantidadeValeQuatroChamadoAgenteTerceiraRodada = 0;
				Iterator<TrucoDescription> iteratorTc = jogador.iterator();
				while (iteratorTc.hasNext()) {
					TrucoDescription tc = iteratorTc.next();
					if (tc.getId() != null) {
						if (tc.getQuandoTruco() == 1 && tc.getQuemTruco() == 1)
							quantidadeTrucoChamadoAgentePrimeiraRodada++;
						if (tc.getQuandoRetruco() == 1 && tc.getQuemRetruco() == 1)
							quantidadeRetrucoChamadoAgentePrimeiraRodada++;
						if (tc.getQuandoValeQuatro() == 1 && tc.getQuemValeQuatro() == 1)
							quantidadeValeQuatroChamadoAgentePrimeiraRodada++;

						if (tc.getQuandoTruco() == 2 && tc.getQuemTruco() == 1)
							quantidadeTrucoChamadoAgenteSegundaRodada++;
						if (tc.getQuandoRetruco() == 2 && tc.getQuemRetruco() == 1)
							quantidadeRetrucoChamadoAgenteSegundaRodada++;
						if (tc.getQuandoValeQuatro() == 2 && tc.getQuemValeQuatro() == 1)
							quantidadeValeQuatroChamadoAgenteSegundaRodada++;

						if (tc.getQuandoTruco() == 3 && tc.getQuemTruco() == 1)
							quantidadeTrucoChamadoAgenteTerceiraRodada++;
						if (tc.getQuandoRetruco() == 3 && tc.getQuemRetruco() == 1)
							quantidadeRetrucoChamadoAgenteTerceiraRodada++;
						if (tc.getQuandoValeQuatro() == 3 && tc.getQuemValeQuatro() == 1)
							quantidadeValeQuatroChamadoAgenteTerceiraRodada++;
					}
				}
				QuandoTrucoModelo quandoTrucoModelo = new QuandoTrucoModelo();
				// truco
				if (quantidadeTrucoChamadoAgentePrimeiraRodada > quantidadeTrucoChamadoAgenteSegundaRodada
						&& quantidadeTrucoChamadoAgentePrimeiraRodada > quantidadeTrucoChamadoAgenteTerceiraRodada)
					quandoTrucoModelo.setQuandoTrucoMaisPontuado(1);
				else if (quantidadeTrucoChamadoAgenteSegundaRodada > quantidadeTrucoChamadoAgentePrimeiraRodada
						&& quantidadeTrucoChamadoAgenteSegundaRodada > quantidadeTrucoChamadoAgenteTerceiraRodada)
					quandoTrucoModelo.setQuandoTrucoMaisPontuado(2);

				else if (quantidadeTrucoChamadoAgenteTerceiraRodada > quantidadeTrucoChamadoAgentePrimeiraRodada
						&& quantidadeTrucoChamadoAgenteTerceiraRodada > quantidadeTrucoChamadoAgenteSegundaRodada)
					quandoTrucoModelo.setQuandoTrucoMaisPontuado(3);
				// retruco
				if (quantidadeRetrucoChamadoAgentePrimeiraRodada > quantidadeRetrucoChamadoAgenteSegundaRodada
						&& quantidadeRetrucoChamadoAgentePrimeiraRodada > quantidadeRetrucoChamadoAgenteTerceiraRodada)
					quandoTrucoModelo.setQuandoRetrucoMaisPontuado(1);
				else if (quantidadeRetrucoChamadoAgenteSegundaRodada > quantidadeRetrucoChamadoAgentePrimeiraRodada
						&& quantidadeRetrucoChamadoAgenteSegundaRodada > quantidadeRetrucoChamadoAgenteTerceiraRodada)
					quandoTrucoModelo.setQuandoRetrucoMaisPontuado(2);

				else if (quantidadeRetrucoChamadoAgenteTerceiraRodada > quantidadeRetrucoChamadoAgentePrimeiraRodada
						&& quantidadeRetrucoChamadoAgenteTerceiraRodada > quantidadeRetrucoChamadoAgenteSegundaRodada)
					quandoTrucoModelo.setQuandoRetrucoMaisPontuado(3);
				// valeQuatro

				if (quantidadeValeQuatroChamadoAgentePrimeiraRodada > quantidadeValeQuatroChamadoAgenteSegundaRodada
						&& quantidadeValeQuatroChamadoAgentePrimeiraRodada > quantidadeValeQuatroChamadoAgenteTerceiraRodada)
					quandoTrucoModelo.setQuandoValeQuatroMaisPontuado(1);
				else if (quantidadeValeQuatroChamadoAgenteSegundaRodada > quantidadeValeQuatroChamadoAgentePrimeiraRodada
						&& quantidadeValeQuatroChamadoAgenteSegundaRodada > quantidadeValeQuatroChamadoAgenteTerceiraRodada)
					quandoTrucoModelo.setQuandoValeQuatroMaisPontuado(2);

				else if (quantidadeValeQuatroChamadoAgenteTerceiraRodada > quantidadeValeQuatroChamadoAgentePrimeiraRodada
						&& quantidadeValeQuatroChamadoAgenteTerceiraRodada > quantidadeValeQuatroChamadoAgenteSegundaRodada)
					quandoTrucoModelo.setQuandoValeQuatroMaisPontuado(3);

				return quandoTrucoModelo;

			}
			

		


}
