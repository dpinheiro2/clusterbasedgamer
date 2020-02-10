package hashSolucao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import hash.HashCarta;

public class HashSolucaoCarta implements HashCarta{
	
	
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashPrimeiraCarta(List<TrucoDescription> listaCasos,
			int quemMao) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
           Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
				while(iteratorCasos.hasNext()) {
				TrucoDescription c = iteratorCasos.next();
					if (!c.getPrimeiraCartaRoboClustering().equals(-46)  && !c.getPrimeiraCartaRoboClustering().equals(-10)  
						&& c.getJogadorMao().equals(quemMao)
						&& !c.getPrimeiraCartaRoboClustering().equals(-15)  && !c.getPrimeiraCartaRobo().equals(-1)  
						&& !c.getPrimeiraCartaRobo().equals(0) ) {
					if (hashDeCasos.get(c.getPrimeiraCartaRoboClustering()) == null) {
						List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
						listaDeCasosPorCluster.add(c);
						hashDeCasos.put(c.getPrimeiraCartaRoboClustering(), listaDeCasosPorCluster);
					} else if (hashDeCasos.get(c.getPrimeiraCartaRoboClustering()) != null) {
						List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
								.get(c.getPrimeiraCartaRoboClustering());
						listaDeCasosExistentesPorGrupos.add(c);
						hashDeCasos.put(c.getPrimeiraCartaRoboClustering(), listaDeCasosExistentesPorGrupos);
					}
				}
			}
				return hashDeCasos;
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashSegundaCarta(List<TrucoDescription> listaCasos,
			int ganhadorPrimeira, TrucoDescription newCase) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		
		//if(ganhadorPrimeira == 0) ganhadorPrimeira = newCase.getJogadorMao();
		////System.out.println("dentro do retornaHashSegundaCarta: "+ ganhadorPrimeira +" tamanho lista de casos recebida: "+listaCasos.size());
		//System.out.println("ganhador da primeira: "+ ganhadorPrimeira);
		
		Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
		while(iteratorCasos.hasNext()) {
			TrucoDescription c = iteratorCasos.next();	
			int ganhadorPrimeiraRodadaCasoAtual = c.getGanhadorPrimeiraRodada();
			//if(ganhadorPrimeiraRodadaCasoAtual == 0) ganhadorPrimeiraRodadaCasoAtual = c.getJogadorMao();
			
			if (!c.getSegundaCartaRoboClustering().equals(-46)  && !c.getSegundaCartaRoboClustering().equals(-10) 
						&& !c.getSegundaCartaRoboClustering().equals(-15)  && !c.getSegundaCartaRoboClustering().equals(-1) 
						&& ganhadorPrimeira == ganhadorPrimeiraRodadaCasoAtual) {
					if (hashDeCasos.get(c.getSegundaCartaRoboClustering()) == null) {
						List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
						listaDeCasosPorCluster.add(c);
						hashDeCasos.put(c.getSegundaCartaRoboClustering(), listaDeCasosPorCluster);
					} else if (hashDeCasos.get(c.getSegundaCartaRoboClustering()) != null) {
						List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
								.get(c.getSegundaCartaRoboClustering());
						listaDeCasosExistentesPorGrupos.add(c);
						hashDeCasos.put(c.getSegundaCartaRoboClustering(), listaDeCasosExistentesPorGrupos);
					}
				}
			}
		 
		
		return hashDeCasos;
	}


	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouFoiAoBaralho(List<TrucoDescription> listaCasos,
			int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta) {
		
		if(rodada == 1) return retornaHashIrAoBaralhoPrimeiraCarta(listaCasos, quemMao);
		else if(rodada == 2) return retornaHashIrAoBaralhoSegundaCarta(listaCasos,  ganhadorPrimeiraRodada, consulta);
		else if(rodada == 3) return retornaHashIrAoBaralhoTerceiraCarta(listaCasos, consulta);
		else return null;
	}
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashIrAoBaralhoTerceiraCarta(List<TrucoDescription> listaCasos,
			 TrucoDescription newCase) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		
		//if(ganhadorPrimeira == 0) ganhadorPrimeira = newCase.getJogadorMao();
		////System.out.println("dentro do retornaHashTerceiraCarta: "+ ganhadorPrimeira +" tamanho lista de casos recebida: "+listaCasos.size());
		
		
		Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
		while(iteratorCasos.hasNext()) {
			TrucoDescription c = iteratorCasos.next();	
			int ganhadorSegundaRodadaCasoAtual = c.getGanhadorSegundaRodada();
			//if(ganhadorPrimeiraRodadaCasoAtual == 0) ganhadorPrimeiraRodadaCasoAtual = c.getJogadorMao();
			
			if ( newCase.getGanhadorSegundaRodada().equals(ganhadorSegundaRodadaCasoAtual)  ) {
					if (hashDeCasos.get(c.getTerceiraCartaRoboClustering()) == null) {
						List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
						listaDeCasosPorCluster.add(c);
						hashDeCasos.put(c.getTerceiraCartaRoboClustering(), listaDeCasosPorCluster);
					} else if (hashDeCasos.get(c.getTerceiraCartaRoboClustering()) != null) {
						List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
								.get(c.getTerceiraCartaRoboClustering());
						listaDeCasosExistentesPorGrupos.add(c);
						hashDeCasos.put(c.getTerceiraCartaRoboClustering(), listaDeCasosExistentesPorGrupos);
					}
				}
			}
		 
		
		return hashDeCasos;
	}
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashIrAoBaralhoSegundaCarta(List<TrucoDescription> listaCasos,
			int ganhadorPrimeira, TrucoDescription newCase) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		
		//if(ganhadorPrimeira == 0) ganhadorPrimeira = newCase.getJogadorMao();
		////System.out.println("dentro do retornaHashSegundaCarta: "+ ganhadorPrimeira +" tamanho lista de casos recebida: "+listaCasos.size());
		//System.out.println("ganhador da primeira: "+ ganhadorPrimeira);
		
		Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
		while(iteratorCasos.hasNext()) {
			TrucoDescription c = iteratorCasos.next();	
			int ganhadorPrimeiraRodadaCasoAtual = c.getGanhadorPrimeiraRodada();
			//if(ganhadorPrimeiraRodadaCasoAtual == 0) ganhadorPrimeiraRodadaCasoAtual = c.getJogadorMao();
			
			if ( ganhadorPrimeira == ganhadorPrimeiraRodadaCasoAtual) {
					if (hashDeCasos.get(c.getSegundaCartaRoboClustering()) == null) {
						List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
						listaDeCasosPorCluster.add(c);
						hashDeCasos.put(c.getSegundaCartaRoboClustering(), listaDeCasosPorCluster);
					} else if (hashDeCasos.get(c.getSegundaCartaRoboClustering()) != null) {
						List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
								.get(c.getSegundaCartaRoboClustering());
						listaDeCasosExistentesPorGrupos.add(c);
						hashDeCasos.put(c.getSegundaCartaRoboClustering(), listaDeCasosExistentesPorGrupos);
					}
				}
			}
		 
		
		return hashDeCasos;
	}
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashIrAoBaralhoPrimeiraCarta(List<TrucoDescription> listaCasos,
			int quemMao) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
           Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
				while(iteratorCasos.hasNext()) {
				TrucoDescription c = iteratorCasos.next();
					if ( c.getJogadorMao().equals(quemMao) ) {
					if (hashDeCasos.get(c.getPrimeiraCartaRoboClustering()) == null) {
						List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
						listaDeCasosPorCluster.add(c);
						hashDeCasos.put(c.getPrimeiraCartaRoboClustering(), listaDeCasosPorCluster);
					} else if (hashDeCasos.get(c.getPrimeiraCartaRoboClustering()) != null) {
						List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
								.get(c.getPrimeiraCartaRoboClustering());
						listaDeCasosExistentesPorGrupos.add(c);
						hashDeCasos.put(c.getPrimeiraCartaRoboClustering(), listaDeCasosExistentesPorGrupos);
					}
				}
			}
				return hashDeCasos;
	}

	/*
	 * Assim funcionava uma ideia antiga, mas não da certo
	 * ========== POSSIBILIDADES IR AO BARALHO
	 * ======================================= 12 - 11 - 1 VAI AO BARALHO *
	 * =============================================================================
	 * = ========= POSSIBILIDADES NÃO IR AO
	 * BARALHO==================================== 10 - 1 NÃO VAI AO BARALHO
	 * 
	 * =============================================================================
	 * ==
	 * 
	 */
	/*
			
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouFoiAoBaralhoPrimeiraCarta(List<TrucoDescription> listaCasos,
			int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta){
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		Iterator iteratorListaCasos = listaCasos.iterator();
		while (iteratorListaCasos.hasNext()) {
			TrucoDescription c = (TrucoDescription) iteratorListaCasos.next();
			if (((!c.getPrimeiraCartaRoboClustering().equals(-15) &&  !c.getPrimeiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(10) == null)
					|| ((c.getPrimeiraCartaRoboClustering().equals(-15) || c.getPrimeiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(11) == null)) {
				List<TrucoDescription> listaDeCasosPor = new ArrayList<TrucoDescription>();
				listaDeCasosPor.add(c);
				if (!c.getPrimeiraCartaRoboClustering().equals(-15) &&  !c.getPrimeiraCartaRoboClustering().equals(-10))
					hashDeCasos.put(10, listaDeCasosPor);
				else
					hashDeCasos.put(11, listaDeCasosPor);
			} else if (((!c.getPrimeiraCartaRoboClustering().equals(-15) &&  !c.getPrimeiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(10) != null)
					|| ((c.getPrimeiraCartaRoboClustering().equals(-15) || c.getPrimeiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(11) != null)) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = null;
				if (!c.getPrimeiraCartaRoboClustering().equals(-15) &&  !c.getPrimeiraCartaRoboClustering().equals(-10)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(10);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(10, listaDeCasosExistentesPorGrupos);
				} else if (c.getPrimeiraCartaRoboClustering().equals(-15) || c.getPrimeiraCartaRoboClustering().equals(-10)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(11);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(11, listaDeCasosExistentesPorGrupos);
				}
			}

		}
		////System.out.println("tamanho da lista recebida no hash solução: "+ listaCasos.size());
		////System.out.println("hash de casos solução: "+ hashDeCasos.size());
		return hashDeCasos;
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouFoiAoBaralhoSegundaCarta(List<TrucoDescription> listaCasos,
			int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta){
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		Iterator iteratorListaCasos = listaCasos.iterator();
		while (iteratorListaCasos.hasNext()) {
			TrucoDescription c = (TrucoDescription) iteratorListaCasos.next();
			if (((!c.getSegundaCartaRoboClustering().equals(-15) &&  !c.getSegundaCartaRoboClustering().equals(-10)) && hashDeCasos.get(10) == null)
					|| ((c.getSegundaCartaRoboClustering().equals(-15) || c.getSegundaCartaRoboClustering().equals(-10)) && hashDeCasos.get(11) == null)) {
				List<TrucoDescription> listaDeCasosPor = new ArrayList<TrucoDescription>();
				listaDeCasosPor.add(c);
				if (!c.getSegundaCartaRoboClustering().equals(-15) &&  !c.getSegundaCartaRoboClustering().equals(-10))
					hashDeCasos.put(10, listaDeCasosPor);
				else
					hashDeCasos.put(11, listaDeCasosPor);
			} else if (((!c.getSegundaCartaRoboClustering().equals(-15) &&  !c.getSegundaCartaRoboClustering().equals(-10)) && hashDeCasos.get(10) != null)
					|| ((c.getSegundaCartaRoboClustering().equals(-15) || c.getSegundaCartaRoboClustering().equals(-10)) && hashDeCasos.get(11) != null)) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = null;
				if (!c.getSegundaCartaRoboClustering().equals(-15) &&  !c.getSegundaCartaRoboClustering().equals(-10)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(10);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(10, listaDeCasosExistentesPorGrupos);
				} else if (c.getSegundaCartaRoboClustering().equals(-15) || c.getSegundaCartaRoboClustering().equals(-10)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(11);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(11, listaDeCasosExistentesPorGrupos);
				}
			}

		}
		////System.out.println("tamanho da lista recebida no hash solução: "+ listaCasos.size());
		////System.out.println("hash de casos solução: "+ hashDeCasos.size());
		return hashDeCasos;
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouFoiAoBaralhoTerceiraCarta(List<TrucoDescription> listaCasos,
			int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorTerceiraRodada, TrucoDescription consulta){
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		Iterator iteratorListaCasos = listaCasos.iterator();
		while (iteratorListaCasos.hasNext()) {
			TrucoDescription c = (TrucoDescription) iteratorListaCasos.next();
			if (((!c.getTerceiraCartaRoboClustering().equals(-15) &&  !c.getTerceiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(10) == null)
					|| ((c.getTerceiraCartaRoboClustering().equals(-15) || c.getTerceiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(11) == null)) {
				List<TrucoDescription> listaDeCasosPor = new ArrayList<TrucoDescription>();
				listaDeCasosPor.add(c);
				if (!c.getTerceiraCartaRoboClustering().equals(-15) &&  !c.getTerceiraCartaRoboClustering().equals(-10))
					hashDeCasos.put(10, listaDeCasosPor);
				else
					hashDeCasos.put(11, listaDeCasosPor);
			} else if (((!c.getTerceiraCartaRoboClustering().equals(-15) &&  !c.getTerceiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(10) != null)
					|| ((c.getTerceiraCartaRoboClustering().equals(-15) || c.getTerceiraCartaRoboClustering().equals(-10)) && hashDeCasos.get(11) != null)) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = null;
				if (!c.getTerceiraCartaRoboClustering().equals(-15) &&  !c.getTerceiraCartaRoboClustering().equals(-10)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(10);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(10, listaDeCasosExistentesPorGrupos);
				} else if (c.getTerceiraCartaRoboClustering().equals(-15) || c.getTerceiraCartaRoboClustering().equals(-10)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(11);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(11, listaDeCasosExistentesPorGrupos);
				}
			}

		}
		////System.out.println("tamanho da lista recebida no hash solução: "+ listaCasos.size());
		////System.out.println("hash de casos solução: "+ hashDeCasos.size());
		return hashDeCasos;
	}
	*/
}
