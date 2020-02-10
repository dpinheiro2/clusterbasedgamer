package DecisionsIntraClusterProbabilidadeSorteio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionsIntraCluster.CartasJogadasIntraCluster;
import ajudaCluster.MaioriaIrAoBaralhoModelo;
import cbr.cbrDescriptions.TrucoDescription;

public class CartasJogadasIntraClusterProbabilidadeSorteio implements CartasJogadasIntraCluster{
	public String verificaPrimeiraCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int jogadorMao) {
try {
		List<String> listaDeCartasJogadas = new ArrayList<String>();

		for (TrucoDescription d : jogador) {
			int cartaAlta = d.getCartaAltaRobo();
			int cartaMedia = d.getCartaMediaRobo();
			int cartaBaixa = d.getCartaBaixaRobo();
			if (d.getPrimeiraCartaRobo() == cartaAlta)
				listaDeCartasJogadas.add("alta");
			else if (d.getPrimeiraCartaRobo() == cartaMedia)
				listaDeCartasJogadas.add("media");
			else if (d.getPrimeiraCartaRobo() == cartaBaixa)
				listaDeCartasJogadas.add("baixa");
		}

		java.util.Collections.shuffle(listaDeCartasJogadas);

		Random random = new Random();
		int randomRetornado = random.nextInt(listaDeCartasJogadas.size() - 1);

		return listaDeCartasJogadas.get(randomRetornado);
}catch (Exception e) {
	return "alta";
}
	}

	public String verificaSegundaCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int ganhadorPrimeiraRodada, TrucoDescription newCase) {
		try {
		List<String> listaDeCartasJogadas = new ArrayList<String>();

		for (TrucoDescription d : jogador) {
			int cartaAlta = d.getCartaAltaRobo();
			int cartaMedia = d.getCartaMediaRobo();
			int cartaBaixa = d.getCartaBaixaRobo();

			// //////////////System.out.println("Segunda Carta Jogada" + d.getSegundaCartaRobo());
			if (d.getSegundaCartaRobo() == cartaAlta)
				listaDeCartasJogadas.add("alta");
			else if (d.getSegundaCartaRobo() == cartaMedia)
				listaDeCartasJogadas.add("media");
			else if (d.getSegundaCartaRobo() == cartaBaixa)
				listaDeCartasJogadas.add("baixa");
		}

		java.util.Collections.shuffle(listaDeCartasJogadas);

		Random random = new Random();
		int randomRetornado = random.nextInt(listaDeCartasJogadas.size() - 1);

		return listaDeCartasJogadas.get(randomRetornado);
	}catch (Exception e) {
		return "baixa";
	}

	}
	

	@Override
	public boolean irAoBaralhoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada, int quemMao,
			int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta) {
		try {
			MaioriaIrAoBaralhoModelo maioriaFoiAoBaralho = retornaArodadaContadoresIrAoBaralho(listaIntraClusterQuemTruco, rodada, consulta);
			
			return  retornaIrAoBaralho(maioriaFoiAoBaralho);
			} catch (Exception e) {
				return false;
			}
	}
	
	//seleção ir ao baralho	
	public boolean retornaIrAoBaralho(MaioriaIrAoBaralhoModelo maioriaBaralho) {
		 int quantidadeFoiAoBaralho = maioriaBaralho.getContadorFoiAoBaralho();
		  int quantidadeContinuou=  maioriaBaralho.getContadorNaoFoiAoBaralho();
		  List<String> listaDeCartelasParaOsorteio = new ArrayList<String>();
		  for(int i=0; i< quantidadeFoiAoBaralho; i++) {
			  listaDeCartelasParaOsorteio.add("baralho");
		  }
		  for(int i=0; i< quantidadeContinuou; i++) {
			  listaDeCartelasParaOsorteio.add("continuou");
		  }
		  
		  java.util.Collections.shuffle(listaDeCartelasParaOsorteio);
		  Random random = new Random();
		  int randomRetornado = random.nextInt((quantidadeFoiAoBaralho+ quantidadeContinuou) -1);
	      String retorno = listaDeCartelasParaOsorteio.get(randomRetornado);
		
		
		
		if(retorno.equalsIgnoreCase("baralho")) return true;
		else return false;
	}
		

	//contador ir ao baralho
	public MaioriaIrAoBaralhoModelo retornaArodadaContadoresIrAoBaralho(List<TrucoDescription> listaDeCasos, int rodada, TrucoDescription t) {
	
		MaioriaIrAoBaralhoModelo maioriaIrAoBaralhoModelo = new MaioriaIrAoBaralhoModelo();
		
		for(TrucoDescription actual : listaDeCasos) {
			if(rodada == 1 && (t.getPrimeiraCartaRoboClustering().equals(-15) || t.getPrimeiraCartaRoboClustering().equals(-10)) ) 
				maioriaIrAoBaralhoModelo.setContadorFoiAoBaralho(maioriaIrAoBaralhoModelo.getContadorFoiAoBaralho() +1);   
				
				
				else if(rodada == 2 && (t.getSegundaCartaRoboClustering().equals(-15) || t.getSegundaCartaRoboClustering().equals(-10))) 
					maioriaIrAoBaralhoModelo.setContadorFoiAoBaralho(maioriaIrAoBaralhoModelo.getContadorFoiAoBaralho() +1);
				
				else if(rodada == 3 && (t.getTerceiraCartaRoboClustering().equals(-15) || t.getTerceiraCartaRoboClustering().equals(-10))) 
					maioriaIrAoBaralhoModelo.setContadorFoiAoBaralho(maioriaIrAoBaralhoModelo.getContadorFoiAoBaralho() +1);
				
				
				else maioriaIrAoBaralhoModelo.setContadorNaoFoiAoBaralho(maioriaIrAoBaralhoModelo.getContadorNaoFoiAoBaralho() +1);
			
		}
		
     return maioriaIrAoBaralhoModelo;
	
	}

}
