package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

public interface HashCarta {
	public HashMap<Integer, List<TrucoDescription>> retornaHashPrimeiraCarta(List<TrucoDescription> listaCasos,
			int quemMao);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashSegundaCarta(List<TrucoDescription> listaCasos,
			int ganhadorPrimeira, TrucoDescription newCase);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouFoiAoBaralho(List<TrucoDescription> listaCasos, int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta);

}