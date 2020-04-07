package markov;

import java.util.Collection;
import java.util.List;

import CbrQuerys.CBR;
import cbr.cbrDescriptions.TrucoDescription;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;

public class RespondeMarkovEngine implements CBR{

	@Override
	public boolean aceitarEnvido(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarEnvido(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarRealEnvido(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarFaltaEnvido(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarRealEnvido(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarFaltaEnvido(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarTruco(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarReTruco(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarValeQuatro(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarTruco(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarReTruco(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarValeQuatro(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Cartas retorna o id
	 */
	@Override
	public int primeiraCarta(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int segundaCarta(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int terceiraCarta(TrucoDescription gameState, int rodada) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cartaVirada(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarContraFlor(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aceitarContraFlorResto(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarContraFlor(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chamarContraFlorResto(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean irAoBaralho(TrucoDescription gameStateRobo, int rodada) {
		// TODO Auto-generated method stub
		return false;
	}
	
/*
 * aqui você recebe as informações com o que vc precisa
 * quem ganhou, pontuação ....Esse método passa o agente que você quiser de forma invertida, me pergunta que eu te explico.
 */
	@Override
	public void retain(TrucoDescription newCase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<RetrievalResult> retornaRecuperadosFiltradoPontos(TrucoDescription gamestate, double threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<RetrievalResult> retornaRecuperadosFiltradosTruco(TrucoDescription gamestate, double threshold,
			int rodada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<RetrievalResult> retornaRecuperadosFiltradosPrimeiraCarta(TrucoDescription gamestate,
			double threshold, int jogadorMao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrucoDescription> retornaRecuperadosFiltradosSegundaCarta(TrucoDescription gamestate,
			double threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<RetrievalResult> retornaRecuperadosFiltradosTerceiraCarta(TrucoDescription gamestate,
			double threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selecaoJogada(int Nao, int SimGanhou, int SimPerdeu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean selecaoJogadaVitoria(int Ganhou, int Perdeu) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setAprendizagem(String tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRetencao(String tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fechaBase() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizaConfiguracoesIniciais2(String cartaAlta, String cartaMedia, String cartaBaixa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizaConfiguracoesIniciais() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setThreshold(double threshold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean faltaConhecimentoParaAdecisao(TrucoDescription query, String tipoDaConsulta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void zeraGruposInformacoesRodadaFinalizada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTipoDecisao(String Tipo1, String tipoReusoIntraCluster1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReusoComCluster(boolean cluster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setaGrupoMaisSimilarIndexadoJogada(TrucoDescription stateAgent1Jogada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setaGrupoMaisSimilarIndexadoPontos(TrucoDescription stateAgent1Envido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAjusteAutomaticoDoK(boolean ajusteAutomaticoDoK) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void autoAjustarK() {
		// TODO Auto-generated method stub
		
	}

}
