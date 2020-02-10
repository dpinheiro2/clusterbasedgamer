package DecisionsIntraClusterMaioria;

import java.util.Iterator;
import java.util.List;

import DecisionsIntraCluster.CartasJogadasIntraCluster;
import cbr.cbrDescriptions.TrucoDescription;

public class CartasJogadasIntraClusterMaioria implements CartasJogadasIntraCluster{
	public String verificaPrimeiraCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int jogadorMao) {
		try {
		int quantidadeCartaAlta = 0;
		int quantidadeMedia = 0;
		int quantidadeBaixa = 0;

		for (TrucoDescription d : jogador) {
			int cartaAlta = d.getCartaAltaRobo();
			int cartaMedia = d.getCartaMediaRobo();
			int cartaBaixa = d.getCartaBaixaRobo();
			if (d.getPrimeiraCartaRobo() == cartaAlta)
				quantidadeCartaAlta++;
			else if (d.getPrimeiraCartaRobo() == cartaMedia)
				quantidadeMedia++;
			else if (d.getPrimeiraCartaRobo() == cartaBaixa)
				quantidadeBaixa++;
		}

		if (quantidadeCartaAlta > quantidadeMedia && quantidadeCartaAlta > quantidadeBaixa)
			return "alta";
		else if (quantidadeMedia > quantidadeCartaAlta && quantidadeMedia > quantidadeBaixa)
			return "media";
		else
			return "baixa";
		}catch (Exception e) {
			return "alta";
		}
	}

	public String verificaSegundaCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int ganhadorPrimeiraRodada, TrucoDescription newCase) {
		try {
		int quantidadeCartaAlta = 0;
		int quantidadeMedia = 0;
		int quantidadeBaixa = 0;

		for (TrucoDescription d : jogador) {
			int cartaAlta = d.getCartaAltaRobo();
			int cartaMedia = d.getCartaMediaRobo();
			int cartaBaixa = d.getCartaBaixaRobo();

			// //////////////System.out.println("Segunda Carta Jogada" + d.getSegundaCartaRobo());
			if (d.getSegundaCartaRobo() == cartaAlta)
				quantidadeCartaAlta++;
			else if (d.getSegundaCartaRobo() == cartaMedia)
				quantidadeMedia++;
			else if (d.getSegundaCartaRobo() == cartaBaixa)
				quantidadeBaixa++;
		}

		if (quantidadeCartaAlta > quantidadeMedia && quantidadeCartaAlta > quantidadeBaixa)
			return "alta";
		else if (quantidadeMedia > quantidadeCartaAlta && quantidadeMedia > quantidadeBaixa)
			return "media";
		else
			return "baixa";
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "baixa";
}
	
	
	
	@Override
	public boolean irAoBaralhoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada, int quemMao,
			int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta) {
		return  selecaoIrAoBaralhoPorQuantitativos(listaIntraClusterQuemTruco, rodada);
	}

	

	// selecao ir ao baralho saldo
	public boolean selecaoIrAoBaralhoPorQuantitativos( List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldoQuemBaralho, int rodada) {
		if (aAcaoMaisFeitaEirAoBaralho(listaDeAcoesDoGrupoComMaiorSaldoQuemBaralho, rodada) ) {
			
			return true;
		}
		else
			return false;
	}

	public boolean aAcaoMaisFeitaEirAoBaralho(List<TrucoDescription> listaDeAcoesDoGrupoComMaiorSaldo, int rodada) {
		int quantidadeIrAoBaralho =0;
		int quantidadeNaoIrAoBaralho =0;
		for(TrucoDescription t: listaDeAcoesDoGrupoComMaiorSaldo) {
			if(rodada == 1 && (t.getPrimeiraCartaRoboClustering().equals(-15) || t.getPrimeiraCartaRoboClustering().equals(-10)) ) 
			   quantidadeIrAoBaralho ++;
			
			else if(rodada == 2 && (t.getSegundaCartaRoboClustering().equals(-15) || t.getSegundaCartaRoboClustering().equals(-10))) 
				 quantidadeIrAoBaralho ++;
			
			else if(rodada == 3 && (t.getTerceiraCartaRoboClustering().equals(-15) || t.getTerceiraCartaRoboClustering().equals(-10))) 
				 quantidadeIrAoBaralho ++;
			
			
			else quantidadeNaoIrAoBaralho ++;
		}
		return quantidadeIrAoBaralho > quantidadeNaoIrAoBaralho;
	}
	
	}
