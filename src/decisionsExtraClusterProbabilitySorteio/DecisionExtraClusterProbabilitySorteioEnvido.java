package decisionsExtraClusterProbabilitySorteio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtraClusterEnvido;
import uteisRetornaQuantitativosSaldosOuProbabilidade.EspacoDePossibilidadesModelo;
import utilProbabilidadeSorteio.SorteioProbabilidade;

public class DecisionExtraClusterProbabilitySorteioEnvido implements DecisionExtraClusterEnvido {

	private AcaoFeitasIntraClusterEnvido decisaoIntraCluster;
	
	private SorteioProbabilidade sorteioProb = new SorteioProbabilidade();

	public boolean chamarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = null;
			clusterAcaoDaMaioria = retornaClusterComProbabilidade(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();

			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			return decisaoIntraCluster.chamarEnvidoIntraCluster(listaDeCasosClusterMaioria, gamestate.getJogadorMao());
		} catch (Exception e) {
			//////System.out.println("caiu no catch do chamar Envido");
			try {
				Thread.sleep((long) 100000000000.000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return false;
		}
	}

	public boolean aceitarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = null;
			clusterAcaoDaMaioria = retornaClusterComProbabilidade(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			return decisaoIntraCluster.aceitarEnvidoIntraCluster(listaDeCasosClusterMaioria, gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	public boolean chamarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = null;

			clusterAcaoDaMaioria = retornaClusterComProbabilidade(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			return decisaoIntraCluster.chamarRealEnvidoIntraCluster(listaDeCasosClusterMaioria,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	public boolean aceitarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = null;
			clusterAcaoDaMaioria = retornaClusterComProbabilidade(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			return decisaoIntraCluster.aceitarRealEnvidoIntraCluster(listaDeCasosClusterMaioria,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	public boolean chamarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = null;
			clusterAcaoDaMaioria = retornaClusterComProbabilidade(hashEnvido, hashEnvido.keySet());
			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			return decisaoIntraCluster.chamarFaltaEnvidoIntraCluster(listaDeCasosClusterMaioria,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	public boolean aceitarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = null;
			clusterAcaoDaMaioria = retornaClusterComProbabilidade(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();

			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			return decisaoIntraCluster.aceitarFaltaEnvidoIntraCluster(listaDeCasosClusterMaioria,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	public Integer retornaClusterComProbabilidade(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {
 		return sorteioProb.retornaSorteadoComProbabilidade(hash, listaSemRepetir);
	}

	@Override
	public void setDecisionIntraCluster(AcaoFeitasIntraClusterEnvido decisaoIntraCluster) {
		this.decisaoIntraCluster = decisaoIntraCluster;

	}

}
