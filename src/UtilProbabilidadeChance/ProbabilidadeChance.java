package UtilProbabilidadeChance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cbr.cbrDescriptions.TrucoDescription;

public class ProbabilidadeChance {
	public Integer retornaClusterComMaiorChanceDeVitoriaCarta(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {
	
		
		Double maiorPontuacaoAtual = 0.0;
		int grupoComMaiorPontuacao = 0;
		Double quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = 0.0;
        Iterator<Integer> iteratorKeysGrupos = listaSemRepetir.iterator();
        //System.out.println("tamanho da lista sem repetir dos casos com maior chance de vitória: "+ listaSemRepetir.size());
        for(Integer kAnalisado : listaSemRepetir) {
        	//System.out.println("k analisado: "+ kAnalisado);
        }
        int quantidadeIteracoesDoWhile =0;
		while (iteratorKeysGrupos.hasNext()) {
			int k = iteratorKeysGrupos.next();
			if ( k != 0) {
				quantidadeIteracoesDoWhile = quantidadeIteracoesDoWhile + 1;
				List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
				Double quantidadeDeVitoriasDoGrupo = 0.0;
				Iterator<TrucoDescription> iteratorTrucoDescription = casosDoGrupoAtual.iterator();
				while (iteratorTrucoDescription.hasNext()) {
					TrucoDescription d = iteratorTrucoDescription.next();
					if (d.getGanhadorMao().equals(1))
						quantidadeDeVitoriasDoGrupo = quantidadeDeVitoriasDoGrupo + 1.0;
				}
				Double quantidadeDeCasosNoGrupoAtual = (double) casosDoGrupoAtual.size();
				Double pontuacaoAtual;
				pontuacaoAtual = (double) quantidadeDeVitoriasDoGrupo / quantidadeDeCasosNoGrupoAtual;
				if(quantidadeIteracoesDoWhile == 0 ||(quantidadeIteracoesDoWhile != 0 &&(grupoComMaiorPontuacao == -10 || grupoComMaiorPontuacao == -15 ) )) {
					maiorPontuacaoAtual = pontuacaoAtual;
					grupoComMaiorPontuacao = k;
					quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = quantidadeDeCasosNoGrupoAtual;
				}
				
				else if (pontuacaoAtual > maiorPontuacaoAtual || (pontuacaoAtual.equals(maiorPontuacaoAtual)  
						&& quantidadeDeCasosNoGrupoAtual > quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao)) {
					maiorPontuacaoAtual = pontuacaoAtual;
					grupoComMaiorPontuacao = k;
					quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = quantidadeDeCasosNoGrupoAtual;
				}
			}
		}
		
		//System.out.println("grupo com maior pontuação retornado: "+ grupoComMaiorPontuacao);

		return grupoComMaiorPontuacao;
	}
	

	public Integer retornaClusterComMaiorChanceDeVitoriaEnvido(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {	
		Double maiorPontuacaoAtual = 0.0;
		int grupoComMaiorPontuacao = 0;
		Double quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = 0.0;
        Iterator<Integer> iteratorKeysGrupos = listaSemRepetir.iterator();
        int quantidadeIteracoesDoWhile =0;
        while (iteratorKeysGrupos.hasNext()) {
			int k = iteratorKeysGrupos.next();
			//////System.out.println("k analisado: "+k);
			if ( k != 0) {
				quantidadeIteracoesDoWhile = quantidadeIteracoesDoWhile + 1;
				List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
				
				
				Double quantidadeDeVitoriasDoGrupo = 0.0;
				Iterator<TrucoDescription> iteratorTrucoDescription = casosDoGrupoAtual.iterator();
				//////System.out.println("quantidade de casos do grupo analisado: "+casosDoGrupoAtual.size());
				while (iteratorTrucoDescription.hasNext()) {
					TrucoDescription d = iteratorTrucoDescription.next();
					if (d.getQuemGanhouEnvido().equals(1))
						quantidadeDeVitoriasDoGrupo = quantidadeDeVitoriasDoGrupo+1.0;
				}
				Double quantidadeDeCasosNoGrupoAtual = (double) casosDoGrupoAtual.size();
				Double pontuacaoAtual;
				pontuacaoAtual =(double) quantidadeDeVitoriasDoGrupo / quantidadeDeCasosNoGrupoAtual;
				//////System.out.println("quantidade de casos no grupo atual: "+ quantidadeDeCasosNoGrupoAtual);
				//////System.out.println("pontuação atual do grupo: "+ pontuacaoAtual);
				if(quantidadeIteracoesDoWhile == 0) {
					maiorPontuacaoAtual = pontuacaoAtual;
					grupoComMaiorPontuacao = k;
					quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = quantidadeDeCasosNoGrupoAtual;
				}
				else	if (pontuacaoAtual > maiorPontuacaoAtual || (pontuacaoAtual.equals(maiorPontuacaoAtual)  
						&& quantidadeDeCasosNoGrupoAtual > quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao)) {
					maiorPontuacaoAtual = pontuacaoAtual;
					grupoComMaiorPontuacao = k;
					quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = quantidadeDeCasosNoGrupoAtual;
				}
			}
		}
		
		
		
		//////System.out.println("maior pontuação atual: "+maiorPontuacaoAtual);
		//////System.out.println("quantidade de casos no grupo com maior pontuação: "+quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao);
		//////System.out.println("grupo com maior pontuação: "+ grupoComMaiorPontuacao);
		
		//////System.out.println("------------------------------Conclusão Escolha do grupo com maior probabilidade Envido extra cluster--------------");
		return grupoComMaiorPontuacao;
	}

	public Integer retornaClusterComMaiorChanceDeVitoriaTruco(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {	
		Double maiorPontuacaoAtual = 0.0;
		int grupoComMaiorPontuacao = 0;
		Double quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = 0.0;
        Iterator<Integer> iteratorKeysGrupos = listaSemRepetir.iterator();
		int quantidadeIteracoesDoWhile =0;
        while (iteratorKeysGrupos.hasNext()) {
			int k = iteratorKeysGrupos.next();
			if ( k != 0) {
				quantidadeIteracoesDoWhile = quantidadeIteracoesDoWhile + 1;
				List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
				Double quantidadeDeVitoriasDoGrupo = 0.0;
				Iterator<TrucoDescription> iteratorTrucoDescription = casosDoGrupoAtual.iterator();
				while (iteratorTrucoDescription.hasNext()) {
					TrucoDescription d = iteratorTrucoDescription.next();
					if (d.getQuemGanhouTruco().equals(1))
						quantidadeDeVitoriasDoGrupo = quantidadeDeVitoriasDoGrupo+ 1.0;
				}
				Double quantidadeDeCasosNoGrupoAtual = (double) casosDoGrupoAtual.size();
				Double pontuacaoAtual;
				pontuacaoAtual = (double) quantidadeDeVitoriasDoGrupo / quantidadeDeCasosNoGrupoAtual;
				if(quantidadeIteracoesDoWhile == 0) {
					maiorPontuacaoAtual = pontuacaoAtual;
					grupoComMaiorPontuacao = k;
					quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = quantidadeDeCasosNoGrupoAtual;
				}
				else	if (pontuacaoAtual > maiorPontuacaoAtual || (pontuacaoAtual.equals(maiorPontuacaoAtual)  
						&& quantidadeDeCasosNoGrupoAtual > quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao)) {
					maiorPontuacaoAtual = pontuacaoAtual;
					grupoComMaiorPontuacao = k;
					quantidadeDeCasosRecuperadosNoGrupoComMaiorPontuacao = quantidadeDeCasosNoGrupoAtual;
				}
			}
		}
		

		return grupoComMaiorPontuacao;
	}

}
