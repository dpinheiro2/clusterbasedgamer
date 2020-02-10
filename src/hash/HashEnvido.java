package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

public interface HashEnvido {
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarEnvido(List<TrucoDescription> listaCasos,
			int quemMao);
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarRealEnvido(List<TrucoDescription> listaCasos,
			int quemMao);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarFaltaEnvido(List<TrucoDescription> listaCasos,
			int quemMao);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarEnvido(List<TrucoDescription> listaCasos,
			int quemMao);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarRealEnvido(List<TrucoDescription> listaCasos,
			int quemMao);
	
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarFaltaEnvido(List<TrucoDescription> listaCasos,
			int quemMao);	
}
