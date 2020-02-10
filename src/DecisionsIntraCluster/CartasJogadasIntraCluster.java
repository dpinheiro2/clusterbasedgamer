package DecisionsIntraCluster;

import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;

public interface CartasJogadasIntraCluster {
	public String verificaPrimeiraCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int jogadorMao);
	public String verificaSegundaCartaMaisJogadaNoGrupoMaisProvavel(List<TrucoDescription> jogador, int ganhadorPrimeira, TrucoDescription newCase);
	public boolean irAoBaralhoIntraCluster(List<TrucoDescription> listaIntraClusterQuemTruco, int rodada, int quemMao, int ganhadorPrimeiraRodada, int ganhadorSegundaRodada, TrucoDescription consulta);
}
