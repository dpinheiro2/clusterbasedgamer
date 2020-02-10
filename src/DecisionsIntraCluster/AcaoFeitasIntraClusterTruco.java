package DecisionsIntraCluster;

import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

public interface AcaoFeitasIntraClusterTruco {
	public boolean chamarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada );
	public boolean aceitarTrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada);
	
	public boolean chamarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada );
	public boolean aceitarRetrucoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada);
	
	public boolean chamarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada );
	public boolean aceitarValeQuatroIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada);
	
	
	
}
