package DecisionsIntraClusterProbabilidadeSorteio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import DecisionsIntraCluster.AcaoFeitasIntraClusterTruco;
import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaIrAoBaralhoModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import clusterModelo.EnvidoModelo;
import clusterModelo.QuandoTrucoModelo;
import clusterModelo.TrucoModelo;
import jdk.nashorn.internal.ir.CatchNode;
import uteisRetornaQuantitativosSaldosOuProbabilidade.QuantitativosEnvido;
import uteisRetornaQuantitativosSaldosOuProbabilidade.QuantitativosTruco;

public class AcaoFeitasIntraClusterTrucoProbabilidadeSorteio implements AcaoFeitasIntraClusterTruco{
	QuantitativosTruco quantitativosTruco = new QuantitativosTruco();
	
	
	@Override
	public boolean chamarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
			try {	
		MaioriaModeloSelecao maioriaModeloSelecaoQuemTruco = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarTruco(listaIntraClusterQuemTruco, rodada);
						
		
		
		
		return (selecaoJogadaMaioriaModeloSelecaoChamar(maioriaModeloSelecaoQuemTruco)? true: false);
			}catch (Exception e) {
				return false;
			}
	}

	@Override
	public boolean aceitarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaAceitarModelo maioriaModeloSelecaoQuemTruco = 
				quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarTruco(listaIntraClusterQuemTruco, rodada);
		
		
		
		
		return (selecaoJogadaMaioriaModeloSelecaoAceitar(maioriaModeloSelecaoQuemTruco) ? true: false);
		}catch (Exception e) {
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
		
		MaioriaAceitarModelo maioriaModeloSelecaoQuemRetruco = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarRetruco
				(listaIntraClusterQuemTruco, rodada);
		
		return (selecaoJogadaMaioriaModeloSelecaoAceitar(maioriaModeloSelecaoQuemRetruco) ? true: false);
	}

	@Override
	public boolean chamarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaModeloSelecao maioriaModeloSelecaoQuemValeQuatro = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaValeQuatro
				(listaIntraClusterQuemTruco, rodada);
		
	
		
		
		return (selecaoJogadaMaioriaModeloSelecaoChamar(maioriaModeloSelecaoQuemValeQuatro) ? true: false);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco,  int rodada) {
		try {
		MaioriaAceitarModelo maioriaModeloSelecaoQuemValeQuatro = quantitativosTruco.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarValeQuatro
				(listaIntraClusterQuemTruco, rodada);
		
		
			
		return (selecaoJogadaMaioriaModeloSelecaoAceitar(maioriaModeloSelecaoQuemValeQuatro) ? true: false);	
		} catch (Exception e) {
			return false;
		}
		}
	
	



	
	//seleção
	
		public boolean selecaoJogadaMaioriaModeloSelecaoChamar(MaioriaModeloSelecao maioriaSelecao) {
			 int quantidadeChamou = maioriaSelecao.getChamouEganhou() + maioriaSelecao.getChamouEperdeu();
			  int quantidadeNaoChamou = maioriaSelecao.getNaoChamou();
			  List<String> listaDeCartelasParaOsorteio = new ArrayList<String>();
			  for(int i=0; i< quantidadeChamou; i++) {
				  listaDeCartelasParaOsorteio.add("chamou");
			  }
			  for(int i=0; i< quantidadeNaoChamou; i++) {
				  listaDeCartelasParaOsorteio.add("naochamou");
			  }
			  
			  java.util.Collections.shuffle(listaDeCartelasParaOsorteio);
			  Random random = new Random();
			  int randomRetornado = random.nextInt((quantidadeNaoChamou+ quantidadeChamou) -1);
		      String retorno = listaDeCartelasParaOsorteio.get(randomRetornado);
			  
				if (retorno.equalsIgnoreCase("chamou") && maioriaSelecao.getChamouEganhou() > maioriaSelecao.getChamouEperdeu())
					return true;

					else
					return false;
		}
	

		public boolean selecaoJogadaMaioriaModeloSelecaoAceitar(MaioriaAceitarModelo maioriaSelecao) {
			int quantidadeAceitou = maioriaSelecao.getContadorAceitouEganhou() + maioriaSelecao.getContadorAceitouEperdeu();
			int quantidadeNegou = maioriaSelecao.getContadorNegou();
			
			List<String> listaDeCartelasParaOsorteio = new ArrayList<String>();
			  for(int i=0; i< quantidadeAceitou; i++) {
				  listaDeCartelasParaOsorteio.add("aceitou");
			  }
			  for(int i=0; i< quantidadeNegou; i++) {
				  listaDeCartelasParaOsorteio.add("negou");
			  }
			  
			  try {
			  java.util.Collections.shuffle(listaDeCartelasParaOsorteio);
			  Random random = new Random();
			  int randomRetornado = random.nextInt((quantidadeAceitou+ quantidadeNegou) -1);
		      String retorno = listaDeCartelasParaOsorteio.get(randomRetornado);
			  
				if (retorno.equalsIgnoreCase("aceitou") )
					return true;

					else
					return false;
		}catch (Exception e) {
			return false;
		}
		}
		

			        
}
