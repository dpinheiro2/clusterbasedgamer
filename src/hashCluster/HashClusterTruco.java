package hashCluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import hash.HashTruco;

public class HashClusterTruco implements HashTruco{
  
 
  
	private HashMap<Integer, List<TrucoDescription>> retornaHashQuemTruco(List<TrucoDescription> listaCasos, int rodada) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
         Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
		
         while(iteratorCasos.hasNext()) {
        	 TrucoDescription c = iteratorCasos.next();
        	if(rodada == 1) {
			int clusterQuemTruco = 0;
			if(c.getJogadorMao().equals(1) ) 
				clusterQuemTruco = c.getClusterQuemTrucoPrimeiraMao();
			else if(c.getJogadorMao().equals(2)) 
				clusterQuemTruco = c.getClusterQuemTrucoPrimeiraPe();
        	if (hashDeCasos.get(clusterQuemTruco) == null) {
				List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
				listaDeCasosPorCluster.add(c);
				hashDeCasos.put(clusterQuemTruco, listaDeCasosPorCluster);
			} else if (hashDeCasos.get(clusterQuemTruco) != null) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(clusterQuemTruco);
				listaDeCasosExistentesPorGrupos.add(c);
				hashDeCasos.put(clusterQuemTruco, listaDeCasosExistentesPorGrupos);
			}

		} else if(rodada == 2) {
			int clusterQuemTruco = 0;
			if(c.getGanhadorPrimeiraRodada().equals(1) ||(c.getGanhadorPrimeiraRodada().equals(0) && c.getJogadorMao().equals(1)) ) { 
				//System.out.println();
				clusterQuemTruco = c.getClusterQuemTrucoSegundaGanhouAnterior();
				
			}
			else if(c.getGanhadorPrimeiraRodada().equals(2) ||(c.getGanhadorPrimeiraRodada().equals(0) && c.getJogadorMao().equals(2)))
				clusterQuemTruco = c.getClusterQuemTrucoSegundaPerdeuAnterior();
        	
			if (hashDeCasos.get(clusterQuemTruco) == null) {
				List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
				listaDeCasosPorCluster.add(c);
				hashDeCasos.put(clusterQuemTruco, listaDeCasosPorCluster);
			} else if (hashDeCasos.get(clusterQuemTruco) != null) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(clusterQuemTruco);
				listaDeCasosExistentesPorGrupos.add(c);
				hashDeCasos.put(clusterQuemTruco, listaDeCasosExistentesPorGrupos);
			}
			
			
		} else if(rodada == 3) {
			
			int clusterQuemTruco = 0;
			if(c.getGanhadorSegundaRodada().equals(1) ||(c.getGanhadorSegundaRodada().equals(0) && c.getGanhadorPrimeiraRodada().equals(1)) ||
				(c.getGanhadorSegundaRodada().equals(0) && c.getGanhadorPrimeiraRodada().equals(0) && c.getJogadorMao().equals(1))) 
				clusterQuemTruco = c.getClusterQuemTrucoTerceiraGanhouAnterior();
			else if(c.getGanhadorSegundaRodada().equals(2) ||(c.getGanhadorSegundaRodada().equals(0) && c.getGanhadorPrimeiraRodada().equals(2)) ||
					(c.getGanhadorSegundaRodada().equals(0) && c.getGanhadorPrimeiraRodada().equals(0) && c.getJogadorMao().equals(2))) 
				clusterQuemTruco = c.getClusterQuemTrucoTerceiraPerdeuAnterior();
        	if (hashDeCasos.get(clusterQuemTruco) == null) {
				List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
				listaDeCasosPorCluster.add(c);
				hashDeCasos.put(clusterQuemTruco, listaDeCasosPorCluster);
			} else if (hashDeCasos.get(clusterQuemTruco) != null) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(clusterQuemTruco);
				listaDeCasosExistentesPorGrupos.add(c);
				hashDeCasos.put(clusterQuemTruco, listaDeCasosExistentesPorGrupos);
			}
			
			
		}
         }

		return hashDeCasos;
	}



	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouTruco(List<TrucoDescription> listaCasos, int rodada) {
		// TODO Auto-generated method stub
		return retornaHashQuemTruco(listaCasos, rodada);
	}



	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouReTruco(List<TrucoDescription> listaCasos, int rodada) {
		// TODO Auto-generated method stub
		return retornaHashQuemTruco(listaCasos, rodada);
	}

	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouValeQuatro(List<TrucoDescription> listaCasos, int rodada) {
		// TODO Auto-generated method stub
		return retornaHashQuemTruco(listaCasos, rodada);
	}

	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarTruco(List<TrucoDescription> listaCasos, int rodada) {
		// TODO Auto-generated method stub
		return retornaHashQuemTruco(listaCasos, rodada);
	}

	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarRetruco(List<TrucoDescription> listaCasos, int rodada) {
		// TODO Auto-generated method stub
		return retornaHashQuemTruco(listaCasos, rodada);
	}

	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarValeQuatro(List<TrucoDescription> listaCasos, int rodada) {
		// TODO Auto-generated method stub
		return retornaHashQuemTruco(listaCasos, rodada);
	}






	
}
