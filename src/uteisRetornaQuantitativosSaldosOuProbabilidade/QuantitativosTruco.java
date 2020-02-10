package uteisRetornaQuantitativosSaldosOuProbabilidade;


import java.util.Iterator;
import java.util.List;

import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;

public class QuantitativosTruco {

	/*
	 * ========== POSSIBILIDADES CHAMAR ======================================= 12 -
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
	public MaioriaModeloSelecao retornaMaioriaModeloChamarGenerico(List<TrucoDescription> listaCasos,
			String tipoDeChamada, int rodada) {
		MaioriaModeloSelecao maioriaModeloSelecao = new MaioriaModeloSelecao();
		Iterator<TrucoDescription> iteratorListaCasos = listaCasos.iterator();
		// caso nenhum tenha chamado truco cria um grupo com o número 30
		while(iteratorListaCasos.hasNext()){
			int quemChamou = 0;
			int quemChamouMaior1 = 0;
			int quemChamouMaior2 = 0;
			
			int quandoChamou =0 ;
			int quandoChamouMaior1 =0;
			int quandoChamouMaior2 = 0;
			
			int quemNegou = 0;
			
			TrucoDescription c = iteratorListaCasos.next();
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoDeChamada.equalsIgnoreCase("truco")) {
				quemChamou = c.getQuemTruco();
				quemChamouMaior1 = c.getQuemRetruco();
				quemChamouMaior2 = c.getQuemValeQuatro();
				
				quandoChamou = c.getQuandoTruco();
				quandoChamouMaior1 = c.getQuandoRetruco();
				quandoChamouMaior2 = c.getQuandoValeQuatro();
				
				quemNegou = c.getQuemNegouTruco();
			}
			else if (tipoDeChamada.equalsIgnoreCase("retruco")) {
				quemChamou = c.getQuemRetruco();
				quemChamouMaior1 = c.getQuemValeQuatro();
				
				quandoChamou = c.getQuandoRetruco();
				quandoChamouMaior1 = c.getQuandoValeQuatro();
				
				quemNegou = c.getQuemNegouTruco();
			}
			else if (tipoDeChamada.equalsIgnoreCase("valequatro")) {
				quemChamou = c.getQuemValeQuatro();
				quemNegou = c.getQuemNegouTruco();
			}

			if ((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1)
					|| (quandoChamou != rodada && quandoChamouMaior1 != rodada && quandoChamouMaior2 != rodada)) {
				maioriaModeloSelecao.setNaoChamou(maioriaModeloSelecao.getNaoChamou() + 1);
				} else if ((quandoChamou == rodada || quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada)
						&& (  (quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 &&  quemNegou != 1)
								
						|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 && quandoChamou == rodada || quandoChamouMaior1 == rodada 
						|| quandoChamouMaior2 == rodada)   )) {
					if(c.getQuemGanhouTruco().equals(1)) maioriaModeloSelecao.setChamouEganhou(maioriaModeloSelecao.getChamouEganhou()+1);
					else if(c.getQuemGanhouTruco().equals(2)) maioriaModeloSelecao.setChamouEperdeu(maioriaModeloSelecao.getChamouEperdeu()+1);				}
			}

		return maioriaModeloSelecao;
	}


	
	public MaioriaModeloSelecao retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarTruco(List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar, int rodada) {
        MaioriaModeloSelecao maioriaModeloSelecao = retornaMaioriaModeloChamarGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "truco", rodada);
         return maioriaModeloSelecao;
	}
	
	
		public MaioriaModeloSelecao retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaChamarRetruco(List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar, int rodada) {
        MaioriaModeloSelecao maioriaModeloSelecao = retornaMaioriaModeloChamarGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "retruco", rodada);
         return maioriaModeloSelecao;
	}
	
	public MaioriaModeloSelecao retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaValeQuatro(List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar, int rodada) {
		MaioriaModeloSelecao maioriaModeloSelecao = retornaMaioriaModeloChamarGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "valequatro", rodada);
        return maioriaModeloSelecao;	
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
	public MaioriaAceitarModelo retornaMaioriaModeloAceitarGenerico(List<TrucoDescription> listaCasos, String tipoAceite, int rodada) {
		MaioriaAceitarModelo maioriaModeloSelecao = new MaioriaAceitarModelo();
         Iterator<TrucoDescription> iteratorListaCasosIterator = listaCasos.iterator();
		while(iteratorListaCasosIterator.hasNext()) {
			TrucoDescription c =  iteratorListaCasosIterator.next();
			int quemChamou = 0;
			int quemChamouMaior1 = 0;
			int quemChamouMaior2 = 0;
			
			int quandoChamou =0 ;
			int quandoChamouMaior1 =0;
			int quandoChamouMaior2 = 0;
			
			int quemNegou = 0;
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoAceite.equalsIgnoreCase("truco")) {
				quemChamou = c.getQuemTruco();
				quemChamouMaior1 = c.getQuemRetruco();
				quemChamouMaior2 = c.getQuemValeQuatro();
				
				quandoChamou = c.getQuandoTruco();
				quandoChamouMaior1 = c.getQuandoRetruco();
				quandoChamouMaior2 = c.getQuandoValeQuatro();
				
				quemNegou = c.getQuemNegouTruco();
				
			}
			else if (tipoAceite.equalsIgnoreCase("retruco")) {
				quemChamou = c.getQuemRetruco();
				
				quemChamouMaior1 = c.getQuemValeQuatro();
				
				quandoChamou = c.getQuandoRetruco();
				quandoChamouMaior1 = c.getQuandoValeQuatro();
				
				quemNegou = c.getQuemNegouTruco();
			}
			else if (tipoAceite.equalsIgnoreCase("valequatro")) {
				quemChamou = c.getQuemValeQuatro();
				quemNegou = c.getQuemNegouTruco();
			}
			

			
				if( (quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1)
						|| (quandoChamou != rodada && quandoChamouMaior1 != rodada && quandoChamouMaior2 != rodada) ) {
					maioriaModeloSelecao.setContadorNegou(maioriaModeloSelecao.getContadorNegou()+1);
				} else if  ( ( (quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

						|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 ))) {
					if(c.getQuemGanhouTruco().equals(1) ) maioriaModeloSelecao.setContadorAceitouEganhou(maioriaModeloSelecao.getContadorAceitouEganhou()+1);
					else if(c.getQuemGanhouTruco().equals(2) ) maioriaModeloSelecao.setContadorAceitouEperdeu(maioriaModeloSelecao.getContadorAceitouEperdeu()+1);
				}

			

		}

		return maioriaModeloSelecao;
	}

	
	
	public MaioriaAceitarModelo retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarTruco(List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar, int rodada) {
        MaioriaAceitarModelo maioriaModeloSelecao = retornaMaioriaModeloAceitarGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "truco", rodada);
        
		 return maioriaModeloSelecao;
	}
	
	
	public MaioriaAceitarModelo retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarRetruco(List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar, int rodada) {
		MaioriaAceitarModelo maioriaModeloSelecao = retornaMaioriaModeloAceitarGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "retruco", rodada);
        
		 return maioriaModeloSelecao;
	
	}
	

	public MaioriaAceitarModelo retornaQuantitativosDeSucessoEstabelecidoPelaMaioriaParaAceitarValeQuatro(List<TrucoDescription> listaMaioriaCasosDoGrupoMaisSimilar, int rodada) {
		MaioriaAceitarModelo maioriaModeloSelecao = retornaMaioriaModeloAceitarGenerico(listaMaioriaCasosDoGrupoMaisSimilar, "valequatro", rodada);
        
		 return maioriaModeloSelecao;

	}	

}
