package hashCluster;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import hash.HashEnvido;

public class HashClusterEnvido implements HashEnvido{
	
	
	
	private HashMap<Integer, List<TrucoDescription>> retornaHashEnvido(List<TrucoDescription> listaCasos, int quemMao) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();

		Iterator iteratorListaCasos = listaCasos.iterator();
		while(iteratorListaCasos.hasNext()) {
			TrucoDescription c = (TrucoDescription) iteratorListaCasos.next();
		
			if (quemMao == 1 ) {
				 
				if(hashDeCasos.get(c.getClusterQuemEnvidoAgenteMao()) == null &&  !c.getClusterQuemEnvidoAgenteMao().equals(0) ) {
					List<TrucoDescription> listaNova = new ArrayList<TrucoDescription>();
					listaNova.add(c);
					hashDeCasos.put(c.getClusterQuemEnvidoAgenteMao(), listaNova);
				}
				else if (hashDeCasos.get(c.getClusterQuemEnvidoAgenteMao()) != null &&  !c.getClusterQuemEnvidoAgenteMao().equals(0) ) {
					List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
							.get(c.getClusterQuemEnvidoAgenteMao());
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(c.getClusterQuemEnvidoAgenteMao(), listaDeCasosExistentesPorGrupos);
				} 
		}
			
	
		else if (quemMao == 2 ) {
			
				if (hashDeCasos.get(c.getClusterQuemEnvidoAgentePe()) != null && !c.getClusterQuemEnvidoAgentePe().equals(0) ) {
					List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos
							.get(c.getClusterQuemEnvidoAgentePe());
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(c.getClusterQuemEnvidoAgentePe(), listaDeCasosExistentesPorGrupos);
				}else if(hashDeCasos.get(c.getClusterQuemEnvidoAgenteMao()) == null &&  !c.getClusterQuemEnvidoAgentePe().equals(0) ) {
					List<TrucoDescription> listaNova = new ArrayList<TrucoDescription>();
					listaNova.add(c);
					hashDeCasos.put(c.getClusterQuemEnvidoAgentePe(), listaNova);
				}
		}
		}
		
		return hashDeCasos;
	}
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		// TODO Auto-generated method stub
		return retornaHashEnvido(listaCasos,  quemMao);
	}
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarRealEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		// TODO Auto-generated method stub
		return retornaHashEnvido(listaCasos,  quemMao);
	}
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarFaltaEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		// TODO Auto-generated method stub
		return retornaHashEnvido(listaCasos,  quemMao);
	}
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		// TODO Auto-generated method stub
		return retornaHashEnvido(listaCasos,  quemMao);
	}
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarRealEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		// TODO Auto-generated method stub
		return retornaHashEnvido(listaCasos,  quemMao);
	}
	@Override
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarFaltaEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		// TODO Auto-generated method stub
		return retornaHashEnvido(listaCasos,  quemMao);
	}


	
	
}
