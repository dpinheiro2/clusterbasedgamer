package DecisionsIntraClusterMaioria;

import java.util.Iterator;
import java.util.List;

import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import ajudaCluster.MaioriaAceitarModelo;

import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import uteisRetornaQuantitativosSaldosOuProbabilidade.QuantitativosTruco;

public class AcaoFeitasIntraClusterTrucoMaioria implements AcaoFeitasIntraClusterTruco{
	QuantitativosTruco quantitativosTruco = new QuantitativosTruco();
	
	
		
	@Override
	public boolean chamarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		
		MaioriaModeloSelecao maioriaModeloSelecaoQuemTruco = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarTruco(listaIntraClusterQuemTruco, rodada);
						
		
		
		
		return (selecaoJogadaMaioriaModeloSelecaoChamar(maioriaModeloSelecaoQuemTruco) ? true: false);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaAceitarModelo maioriaModeloSelecaoQuemTruco = 
				quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarTruco(listaIntraClusterQuemTruco, rodada);
		
		//int maioriaModeloSelecaoQuandoTruco = retornaArodadaQuandoTruco(hashQuandoTruco.get(clusterMaioriaQuandoTruco));
		
		
		return (selecaoJogadaMaioriaModeloSelecaoAceitar(maioriaModeloSelecaoQuemTruco) ? true: false);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean chamarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaModeloSelecao maioriaModeloSelecaoQuemRetruco = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarRetruco
				(listaIntraClusterQuemTruco, rodada);	
		
		return (selecaoJogadaMaioriaModeloSelecaoChamar(maioriaModeloSelecaoQuemRetruco) ? true: false);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaAceitarModelo maioriaModeloSelecaoQuemRetruco = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarRetruco
				(listaIntraClusterQuemTruco, rodada);
		
		return (selecaoJogadaMaioriaModeloSelecaoAceitar(maioriaModeloSelecaoQuemRetruco)? true: false);
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean chamarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaModeloSelecao maioriaModeloSelecaoQuemValeQuatro = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaValeQuatro
				(listaIntraClusterQuemTruco, rodada);
		
	
		
		
		return (selecaoJogadaMaioriaModeloSelecaoChamar(maioriaModeloSelecaoQuemValeQuatro)? true: false);
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaAceitarModelo maioriaModeloSelecaoQuemValeQuatro = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarValeQuatro
				(listaIntraClusterQuemTruco, rodada);
		
		
			
		return (selecaoJogadaMaioriaModeloSelecaoAceitar(maioriaModeloSelecaoQuemValeQuatro)? true: false);	
		}catch (Exception e) {
			return false;
		}
		}
	
	
	
	
	//seleção
	
		public boolean selecaoJogadaMaioriaModeloSelecaoChamar(MaioriaModeloSelecao maioriaSelecao) {
			if (maioriaSelecao.getNaoChamou() > maioriaSelecao.getChamouEganhou() + maioriaSelecao.getChamouEperdeu())
				return false;

			else if (maioriaSelecao.getChamouEganhou() + maioriaSelecao.getChamouEperdeu()> maioriaSelecao.getNaoChamou() )
				return true;
			else
				return false;
		}
	
			

		public boolean selecaoJogadaMaioriaModeloSelecaoAceitar(MaioriaAceitarModelo maioriaSelecao) {
			if (maioriaSelecao.getContadorNegou() > maioriaSelecao.getContadorAceitouEganhou() + maioriaSelecao.getContadorAceitouEperdeu())
				return false;

			else if (maioriaSelecao.getContadorAceitouEganhou() + maioriaSelecao.getContadorAceitouEperdeu() > maioriaSelecao.getContadorNegou())
				return true;
			else
				return false;
		}
		
		    //maioria quando Truco
			public int retornaArodadaMaisPontuadaQuandoTruco(List<TrucoDescription> listaDeCasos) {
					int contadorTrucoChamadoPrimeiraRodada = 0;
					int contadorTrucoChamadoSegundaRodada = 0;
					int contadorTrucoChamadoTerceiraRodada = 0;
					
					for(TrucoDescription actual : listaDeCasos) {
						if(actual.getQuandoTruco() == 1) contadorTrucoChamadoPrimeiraRodada ++;
						else if(actual.getQuandoTruco() == 2) contadorTrucoChamadoSegundaRodada ++;
						else if(actual.getQuandoTruco() == 3) contadorTrucoChamadoTerceiraRodada ++;
					}
					
				if(contadorTrucoChamadoPrimeiraRodada > contadorTrucoChamadoSegundaRodada && 
						contadorTrucoChamadoPrimeiraRodada > contadorTrucoChamadoTerceiraRodada)
					return 1;
				else if(contadorTrucoChamadoSegundaRodada > contadorTrucoChamadoPrimeiraRodada && 
						contadorTrucoChamadoSegundaRodada > contadorTrucoChamadoTerceiraRodada)
					return 2;
				else 
					return 3;
				
				
				
			}
			        //maioria quando truco
					public int retornaArodadaQuandoTruco(List<TrucoDescription> listaDeCasos) {
							int contadorTrucoChamadoPrimeiraRodada = 0;
							int contadorTrucoChamadoSegundaRodada = 0;
							int contadorTrucoChamadoTerceiraRodada = 0;
							
							for(TrucoDescription actual : listaDeCasos) {
								if(actual.getQuandoTruco() == 1) contadorTrucoChamadoPrimeiraRodada ++;
								else if(actual.getQuandoTruco() == 2) contadorTrucoChamadoSegundaRodada ++;
								else if(actual.getQuandoTruco() == 3) contadorTrucoChamadoTerceiraRodada ++;
							}
							
						if(contadorTrucoChamadoPrimeiraRodada > contadorTrucoChamadoSegundaRodada && 
								contadorTrucoChamadoPrimeiraRodada > contadorTrucoChamadoTerceiraRodada)
							return 1;
						else if(contadorTrucoChamadoSegundaRodada > contadorTrucoChamadoPrimeiraRodada && 
								contadorTrucoChamadoSegundaRodada > contadorTrucoChamadoTerceiraRodada)
							return 2;
						else 
							return 3;	
						
					}
					
					//maioria quando Retruco
					public int retornaArodadaQuandoRetruco(List<TrucoDescription> listaDeCasos) {
							int contadorRetrucoChamadoPrimeiraRodada = 0;
							int contadorRetrucoChamadoSegundaRodada = 0;
							int contadorRetrucoChamadoTerceiraRodada = 0;
							
							for(TrucoDescription actual : listaDeCasos) {
								if(actual.getQuandoRetruco() == 1) contadorRetrucoChamadoPrimeiraRodada++;
								else if(actual.getQuandoRetruco() == 2) contadorRetrucoChamadoSegundaRodada ++;
								else if(actual.getQuandoRetruco() == 3) contadorRetrucoChamadoTerceiraRodada ++;
							}
							
						if(contadorRetrucoChamadoPrimeiraRodada > contadorRetrucoChamadoSegundaRodada && 
								contadorRetrucoChamadoPrimeiraRodada > contadorRetrucoChamadoTerceiraRodada)
							return 1;
						else if(contadorRetrucoChamadoSegundaRodada > contadorRetrucoChamadoPrimeiraRodada && 
								contadorRetrucoChamadoSegundaRodada > contadorRetrucoChamadoTerceiraRodada)
							return 2;
						else 
							return 3;	
						
					}
			
					//maioria quando ValeQuatro
					public int retornaArodadaQuandoValeQuatro(List<TrucoDescription> listaDeCasos) {
							int contadorValeQuatroChamadoPrimeiraRodada = 0;
							int contadorValeQuatroChamadoSegundaRodada = 0;
							int contadorValeQuatroChamadoTerceiraRodada = 0;
							
							for(TrucoDescription actual : listaDeCasos) {
								if(actual.getQuandoValeQuatro() == 1) contadorValeQuatroChamadoPrimeiraRodada++;
								else if(actual.getQuandoValeQuatro() == 2) contadorValeQuatroChamadoSegundaRodada ++;
								else if(actual.getQuandoValeQuatro() == 3) contadorValeQuatroChamadoTerceiraRodada ++;
							}
							
						if(contadorValeQuatroChamadoPrimeiraRodada > contadorValeQuatroChamadoSegundaRodada && 
								contadorValeQuatroChamadoPrimeiraRodada > contadorValeQuatroChamadoTerceiraRodada)
							return 1;
						else if(contadorValeQuatroChamadoSegundaRodada > contadorValeQuatroChamadoPrimeiraRodada && 
								contadorValeQuatroChamadoSegundaRodada > contadorValeQuatroChamadoTerceiraRodada)
							return 2;
						else 
							return 3;	
						
					}
				

}
