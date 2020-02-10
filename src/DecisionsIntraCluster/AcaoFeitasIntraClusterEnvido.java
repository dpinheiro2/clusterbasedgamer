package DecisionsIntraCluster;

import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;


public interface AcaoFeitasIntraClusterEnvido  {
	public boolean chamarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao);
	public boolean aceitarEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao);
	
	public boolean chamarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao);
	public boolean aceitarRealEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao);
	
	public boolean chamarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao);
	public boolean aceitarFaltaEnvidoIntraCluster(List<TrucoDescription> listaIntraClusterQuemEnvido, int jogadorMao);
	
	
	
	
	
}
