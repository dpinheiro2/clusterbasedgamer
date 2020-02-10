package hash;

import java.util.HashMap;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

public interface HashTruco {

	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouTruco(List<TrucoDescription> listaCasos, int rodada);
	
	
	
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouReTruco(List<TrucoDescription> listaCasos, int rodada);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouValeQuatro(List<TrucoDescription> listaCasos, int rodada);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarTruco(List<TrucoDescription> listaCasos, int rodada);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarRetruco(List<TrucoDescription> listaCasos, int rodada);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarValeQuatro(List<TrucoDescription> listaCasos, int rodada);
	
	
	
		
	
}
