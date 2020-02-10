package DecisionsIntraClusterProbabilidadeChance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.CartasJogadasIntraCluster;
import UtilProbabilidadeChance.ProbabilidadeChance;
import UtilProbabilidadeChance.UtilProbabilidadeChanceIntraClusterEnvidoEtruco;
import cbr.cbrDescriptions.TrucoDescription;

import hashSolucao.HashSolucaoCarta;

public class CartasJogadasIntraClusterProbabilidadeChance implements CartasJogadasIntraCluster {

	HashSolucaoCarta hashCarta = new HashSolucaoCarta();
	ProbabilidadeChance probabilidadeChance = new ProbabilidadeChance();
	
	UtilProbabilidadeChanceIntraClusterEnvidoEtruco chanceIntraClusterTruco = new UtilProbabilidadeChanceIntraClusterEnvidoEtruco();
	
	@Override
	public String verificaPrimeiraCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int jogadorMao) {
		//////////System.out.println("lita de casos retornados para a primeira carta: "+ jogador.size());
		try {
		HashMap<Integer, List<TrucoDescription>> hashAcoesEncontradas =  hashCarta.retornaHashPrimeiraCarta(jogador, jogadorMao);
		//////////System.out.println("hash de casos retornados para a primeira carta: "+ hashAcoesEncontradas.size());
		
		int cartaParaSerJogada =probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashAcoesEncontradas, hashAcoesEncontradas.keySet());
	 
		if(cartaParaSerJogada  == 46) return "alta";
		else if(cartaParaSerJogada == 16) return "media";
		else if(cartaParaSerJogada == 4) return "baixa";
		else {
			//////////System.out.println("carta para ser jogada retornada: "+ cartaParaSerJogada);
			return null;
		}
		
	}catch (Exception e) {
		e.printStackTrace();
		 return "erro cartasJogadas probabilidade intracluster";
		}
		
	}

	@Override
	public String verificaSegundaCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int ganhadorPrimeiraRodada, TrucoDescription newCase) {
      ////System.out.println("ganhador Primeira rodada: "+ ganhadorPrimeiraRodada );
		
		
		HashMap<Integer, List<TrucoDescription>> hashAcoesEncontradas =  hashCarta.retornaHashSegundaCarta(jogador, ganhadorPrimeiraRodada, newCase);
		   
		int cartaParaSerJogada =probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashAcoesEncontradas, hashAcoesEncontradas.keySet());
	 try {
		if(cartaParaSerJogada  == 46) return "alta";
		else if(cartaParaSerJogada == 16) return "media";
		else if(cartaParaSerJogada == 4) return "baixa";
	 
	 }catch (Exception e) {
		e.printStackTrace();
		
		return "erro segundaCartapROBABILIDADEINTRACLUSTER";	
	}
	return null;	
	}
			
	


	@Override
	public boolean irAoBaralhoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada, int quemMao,
			int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta) {
		
		HashMap< Integer, List<TrucoDescription>> hashIrAoBaralho = hashCarta.retornaHashQuemChamouFoiAoBaralho(listaIntraClusterQuemTruco, rodada, quemMao, ganhadorPrimeiraRodada, ganhadorSegundaRodada, consulta);
        int cartaParaSerJogada =probabilidadeChance.retornaClusterComMaiorChanceDeVitoriaCarta(hashIrAoBaralho, hashIrAoBaralho.keySet());
		
			//System.out.println("carta para ser jogada: "+ cartaParaSerJogada);
			
			////System.out.println("retorno: "+ retorno);
			return cartaParaSerJogada == -15 || cartaParaSerJogada == -10;
			
	}
	
}



