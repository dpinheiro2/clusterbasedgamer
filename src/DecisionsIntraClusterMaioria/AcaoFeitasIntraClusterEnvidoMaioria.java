package DecisionsIntraClusterMaioria;

import java.util.List;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import uteisRetornaQuantitativosSaldosOuProbabilidade.QuantitativosEnvido;

public class AcaoFeitasIntraClusterEnvidoMaioria implements AcaoFeitasIntraClusterEnvido {
	QuantitativosEnvido quantitativosEnvido = new QuantitativosEnvido();


	@Override
	public boolean chamarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaModeloSelecao maioriaModelo = quantitativosEnvido.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarEnvido(listaIntraClusterQuemEnvido);
		
		return selecaoJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean aceitarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaAceitarModelo maioriaModelo = quantitativosEnvido.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarEnvido(listaIntraClusterQuemEnvido);
		//////System.out.println("quantidade contador aceitar envido: "+ maioriaModelo.getContadorAceitouEganhou()+ maioriaModelo.getContadorAceitouEperdeu());
		//////System.out.println("quantidade negar envido: "+ maioriaModelo.getContadorNegou());
		return selecaoAceitarJogada(maioriaModelo);
		} catch (Exception e) {
			return false;
		}
		}

	@Override
	public boolean chamarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaModeloSelecao maioriaModelo = quantitativosEnvido.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaRealenvido(listaIntraClusterQuemEnvido);

		return selecaoJogada(maioriaModelo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aceitarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaAceitarModelo maioriaModelo =quantitativosEnvido.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarRealEnvido(
				listaIntraClusterQuemEnvido);

		return selecaoAceitarJogada(maioriaModelo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean chamarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaModeloSelecao maioriaModelo = quantitativosEnvido.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaFaltaEnvido(
				listaIntraClusterQuemEnvido);

		return selecaoJogada(maioriaModelo);
		} catch (Exception e) {
			e.printStackTrace();
		return false;
		}
	}

	@Override
	public boolean aceitarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao) {
		try {
		MaioriaAceitarModelo maioriaModelo = quantitativosEnvido.retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarFaltaEnvido(
				listaIntraClusterQuemEnvido);

		return selecaoAceitarJogada(maioriaModelo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean selecaoJogada(MaioriaModeloSelecao maioriaModelo) {
//		 ////////System.out.println("\t\t\t\t\tNao : " + Nao + "\t Gan : " + SimGanhou + "\t Perd : " + SimPerdeu);
		// se numero de vezes que nao chamou Ã© maior do numero que chamou, nao chama
		if (maioriaModelo.getNaoChamou() > maioriaModelo.getChamouEganhou() + maioriaModelo.getChamouEperdeu())
			return false;

		else if (maioriaModelo.getChamouEganhou() + maioriaModelo.getChamouEperdeu() > maioriaModelo.getNaoChamou()  )
			return true;
		else
			return false;
	}
	
	
	public boolean selecaoAceitarJogada(MaioriaAceitarModelo maioriaModelo) {

		if (maioriaModelo.getContadorNegou() > maioriaModelo.getContadorAceitouEganhou() + maioriaModelo.getContadorAceitouEperdeu())
			return false;

		else if (maioriaModelo.getContadorAceitouEganhou() + maioriaModelo.getContadorAceitouEperdeu()> maioriaModelo.getContadorNegou() )
			return true;
		else
			return false;
	}

	
}
