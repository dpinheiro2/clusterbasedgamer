package hashCluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import hash.HashCarta;

public class HashClusterCarta implements HashCarta {

	
	
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashPrimeiraCarta(List<TrucoDescription> listaCasos,
			int quemMao) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
       Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
     
		while(iteratorCasos.hasNext()) {
			TrucoDescription c = iteratorCasos.next();
		if (quemMao == 1){
				if (c.getClusterPrimeiraCartaAgenteMao() != null && !c.getClusterPrimeiraCartaAgenteMao().equals(0) ) {
					if (!c.getPrimeiraCartaRoboClustering().equals(-46)  && !c.getPrimeiraCartaRoboClustering().equals(-15) 
							&& !c.getPrimeiraCartaRoboClustering().equals(-10)  && !c.getPrimeiraCartaRoboClustering().equals(-1) ) {
						if (hashDeCasos.get(c.getClusterPrimeiraCartaAgenteMao()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterPrimeiraCartaAgenteMao(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterPrimeiraCartaAgenteMao()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterPrimeiraCartaAgenteMao());
							listaDeCasosExistentesPorGrupos.add(c);
							
							hashDeCasos.put(c.getClusterPrimeiraCartaAgenteMao(), listaDeCasosExistentesPorGrupos);
						}
					}
				}
			}

		else if (quemMao == 2 ){
				if (c.getClusterPrimeiraCartaAgentePe() != null && !c.getClusterPrimeiraCartaAgentePe().equals(0) ) {
					if (!c.getPrimeiraCartaRoboClustering().equals(-46)  && !c.getPrimeiraCartaRoboClustering().equals(-15) 
							&& !c.getPrimeiraCartaRoboClustering().equals(-10) && !c.getPrimeiraCartaRoboClustering().equals(-1)) {
						if (hashDeCasos.get(c.getClusterPrimeiraCartaAgentePe()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterPrimeiraCartaAgentePe(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterPrimeiraCartaAgentePe()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterPrimeiraCartaAgentePe());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterPrimeiraCartaAgentePe(), listaDeCasosExistentesPorGrupos);
						}
					}
				}
			}
		}
		return hashDeCasos;
	}

	
	public HashMap<Integer, List<TrucoDescription>> retornaHashSegundaCarta(List<TrucoDescription> listaCasos,
			int ganhadorPrimeira, TrucoDescription newCase) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
        Iterator<TrucoDescription> iteratorC = listaCasos.iterator();
        //System.out.println("tamanho da lista para retornar o hash da segunda carta: "+ listaCasos.size());
        //System.out.println("ganhador primeira: "+ganhadorPrimeira);
        
        while (iteratorC.hasNext()) {
        
        TrucoDescription c = iteratorC.next();
       
     //System.out.println("Segunda Carta Robo CLustering: "+ c.getSegundaCartaRoboClustering());
		if (ganhadorPrimeira == 1 || ((ganhadorPrimeira == 0) && (newCase.getJogadorMao().equals(1)))) { 
				if (c.getClusterSegundaCartaAgenteGanhouAPrimeira() != null
						&& !c.getClusterSegundaCartaAgenteGanhouAPrimeira().equals(0) ) {
					if (!c.getSegundaCartaRoboClustering().equals(-46)  && !c.getSegundaCartaRoboClustering().equals(-15) 
							&& !c.getSegundaCartaRoboClustering().equals(-10)  && !c.getSegundaCartaRoboClustering().equals(-1) ) {
						if (hashDeCasos.get(c.getClusterSegundaCartaAgenteGanhouAprimeira()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgenteGanhouAPrimeira(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterSegundaCartaAgenteGanhouAPrimeira()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterSegundaCartaAgenteGanhouAprimeira());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgenteGanhouAPrimeira(),
									listaDeCasosExistentesPorGrupos);
						}
					}

				}
			
		} else if (ganhadorPrimeira == 2 || ((ganhadorPrimeira == 0) && (newCase.getJogadorMao().equals(2)))) {		
			
				if (c.getClusterSegundaCartaAgentePerdeuAPrimeira() != null
						&& !c.getClusterSegundaCartaAgentePerdeuAPrimeira().equals(0)) {
					if (!c.getSegundaCartaRoboClustering().equals(-46)  && !c.getSegundaCartaRoboClustering().equals(-15) 
							&& !c.getSegundaCartaRoboClustering().equals(-10)  && !c.getSegundaCartaRoboClustering().equals(-1) ) {
						if (hashDeCasos.get(c.getClusterSegundaCartaAgentePerdeuAprimeira()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgentePerdeuAprimeira(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterSegundaCartaAgentePerdeuAprimeira()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterSegundaCartaAgentePerdeuAprimeira());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgentePerdeuAprimeira(),
									listaDeCasosExistentesPorGrupos);
						}
					}
				}
			}
        }
		return hashDeCasos;
	}

	
	private HashMap<Integer, List<TrucoDescription>> retornaIrAoBaralhoHashPrimeiraCarta(List<TrucoDescription> listaCasos,
			int quemMao) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
       Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
       ////System.out.println("retorna hash ir ao baralho primeira carta cluster, listaCasos.size: "+ listaCasos.size());
		while(iteratorCasos.hasNext()) {
			TrucoDescription c = iteratorCasos.next();
		if (quemMao == 1){
				if (c.getClusterPrimeiraCartaAgenteMao() != null && !c.getClusterPrimeiraCartaAgenteMao().equals(0) ) {
					
						if (hashDeCasos.get(c.getClusterPrimeiraCartaAgenteMao()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterPrimeiraCartaAgenteMao(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterPrimeiraCartaAgenteMao()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterPrimeiraCartaAgenteMao());
							listaDeCasosExistentesPorGrupos.add(c);
							
							hashDeCasos.put(c.getClusterPrimeiraCartaAgenteMao(), listaDeCasosExistentesPorGrupos);
						}
					}
				}
			

		else if (quemMao == 2 ){
				if (c.getClusterPrimeiraCartaAgentePe() != null && !c.getClusterPrimeiraCartaAgentePe().equals(0) ) {
					
						if (hashDeCasos.get(c.getClusterPrimeiraCartaAgentePe()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterPrimeiraCartaAgentePe(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterPrimeiraCartaAgentePe()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterPrimeiraCartaAgentePe());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterPrimeiraCartaAgentePe(), listaDeCasosExistentesPorGrupos);
						}
					}
				}
			}
		
		////System.out.println("tamanho do hash cluster retornado: "+ hashDeCasos.size());
		return hashDeCasos;
	}

	private HashMap<Integer, List<TrucoDescription>> retornaIrAoBaralhoHashSegundaCarta(List<TrucoDescription> listaCasos,
			int ganhadorPrimeira, TrucoDescription newCase) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
        Iterator<TrucoDescription> iteratorC = listaCasos.iterator();
        
        while (iteratorC.hasNext()) {
        
        TrucoDescription c = iteratorC.next();
       
       // if(ganhadorPrimeira == 0) ganhadorPrimeira = c.getJogadorMao();
		if (ganhadorPrimeira == 1 || ((ganhadorPrimeira == 0) && (newCase.getJogadorMao().equals(1)))) { 
				if (c.getClusterSegundaCartaAgenteGanhouAPrimeira() != null
						&& !c.getClusterSegundaCartaAgenteGanhouAPrimeira().equals(0) ) {
					
						if (hashDeCasos.get(c.getClusterSegundaCartaAgenteGanhouAprimeira()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgenteGanhouAPrimeira(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterSegundaCartaAgenteGanhouAPrimeira()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterSegundaCartaAgenteGanhouAprimeira());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgenteGanhouAPrimeira(),
									listaDeCasosExistentesPorGrupos);
						}
					}

				
			
		} else if (ganhadorPrimeira == 2 || ((ganhadorPrimeira == 0) && (newCase.getJogadorMao().equals(2)))) {		
			
				if (c.getClusterSegundaCartaAgentePerdeuAPrimeira() != null
						&& !c.getClusterSegundaCartaAgentePerdeuAPrimeira().equals(0)) {
					
						if (hashDeCasos.get(c.getClusterSegundaCartaAgentePerdeuAprimeira()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgentePerdeuAprimeira(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterSegundaCartaAgentePerdeuAprimeira()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterSegundaCartaAgentePerdeuAprimeira());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterSegundaCartaAgentePerdeuAprimeira(),
									listaDeCasosExistentesPorGrupos);
						}
					}
				}
			}
        
		return hashDeCasos;
	}
	
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouFoiAoBaralho(List<TrucoDescription> listaCasos,
			int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta) {
	
		////System.out.println("rodada dentro do retorna hash quemChamou foi ao baralho");
		if(rodada == 1) return retornaIrAoBaralhoHashPrimeiraCarta(listaCasos, quemMao);
		 else if(rodada == 2) return retornaIrAoBaralhoHashSegundaCarta(listaCasos, ganhadorPrimeiraRodada, consulta);
		 else if(rodada  == 3) return retornaIrAoBaralhoHashTerceiraCarta(listaCasos, ganhadorPrimeiraRodada, ganhadorSegundaRodada, consulta);
		
		 else
		 return null;
		
	}

	private HashMap<Integer, List<TrucoDescription>> retornaIrAoBaralhoHashTerceiraCarta(List<TrucoDescription> listaCasos,
			int ganhadorPrimeiraRodada, int ganhadorSegundaRodada ,TrucoDescription newCase) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
        Iterator<TrucoDescription> iteratorC = listaCasos.iterator();
        
        while (iteratorC.hasNext()) {
        
        TrucoDescription c = iteratorC.next();
       
       // if(ganhadorAnterior == 0) ganhadorAnterior = c.getJogadorMao();
		if (ganhadorSegundaRodada == 1 || (  (ganhadorSegundaRodada == 0 && ganhadorPrimeiraRodada == 1)   || 
				(ganhadorSegundaRodada == 0 && ganhadorPrimeiraRodada == 0 && newCase.getJogadorMao().equals(1))) ) { 
				if (c.getClusterTerceiraCartaAgenteGanhouAsegunda() != null
						&& !c.getClusterTerceiraCartaAgenteGanhouAsegunda().equals(0) ) {
					
						if (hashDeCasos.get(c.getClusterTerceiraCartaAgenteGanhouAsegunda()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterTerceiraCartaAgenteGanhouAsegunda(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterTerceiraCartaAgenteGanhouAsegunda()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterTerceiraCartaAgenteGanhouAsegunda());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterTerceiraCartaAgenteGanhouAsegunda(),
									listaDeCasosExistentesPorGrupos);
						}
					}

				
			
		} else if (ganhadorSegundaRodada == 2 || (  (ganhadorSegundaRodada == 0 && ganhadorPrimeiraRodada == 2)   || 
				(ganhadorSegundaRodada == 0 && ganhadorPrimeiraRodada == 0 && newCase.getJogadorMao().equals(2))) ) {		
			
				if (c.getClusterTerceiraCartaAgentePerdeuAsegunda() != null
						&& !c.getClusterTerceiraCartaAgentePerdeuAsegunda().equals(0)) {
					
						if (hashDeCasos.get(c.getClusterTerceiraCartaAgentePerdeuAsegunda()) == null) {
							List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
							listaDeCasosPorCluster.add(c);
							hashDeCasos.put(c.getClusterTerceiraCartaAgentePerdeuAsegunda(), listaDeCasosPorCluster);
						} else if (hashDeCasos.get(c.getClusterTerceiraCartaAgentePerdeuAsegunda()) != null) {
							List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
									.get(c.getClusterTerceiraCartaAgentePerdeuAsegunda());
							listaDeCasosExistentesPorGrupos.add(c);
							hashDeCasos.put(c.getClusterTerceiraCartaAgentePerdeuAsegunda(),
									listaDeCasosExistentesPorGrupos);
						}
					}
				}
			}
        
		return hashDeCasos;
	}




	



	
}
