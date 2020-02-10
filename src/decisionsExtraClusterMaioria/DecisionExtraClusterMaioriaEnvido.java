package decisionsExtraClusterMaioria;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DecisionsIntraCluster.AcaoFeitasIntraClusterEnvido;
import ajudaCluster.MaioriaAceitarModelo;
import ajudaCluster.MaioriaModeloSelecao;
import cbr.cbrDescriptions.TrucoDescription;
import decisions.DecisionExtraClusterEnvido;
import uteisRetornaQuantitativosSaldosOuProbabilidade.QuantitativosEnvido;

public class DecisionExtraClusterMaioriaEnvido implements DecisionExtraClusterEnvido {

	private AcaoFeitasIntraClusterEnvido decisaoIntraCluster;

	public boolean chamarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {

		try {

			Integer clusterAcaoDaMaioria = retornaClusterComVotoDaMaioria(hashEnvido, hashEnvido.keySet());

			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();

			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);

			

			return decisaoIntraCluster.chamarEnvidoIntraCluster(listaDeCasosClusterMaioria, gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	public boolean aceitarEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = retornaClusterComVotoDaMaioria(hashEnvido, hashEnvido.keySet());
			hashEnvido.keySet().forEach(f ->{
				////////System.out.println("grupo atual: "+ f+" quantidade de casos: "+ hashEnvido.get(f).size());
			});
			
			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
				listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);
			
			return decisaoIntraCluster.aceitarEnvidoIntraCluster(listaDeCasosClusterMaioria, gamestate.getJogadorMao());

		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}

	public boolean chamarRealEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = retornaClusterComVotoDaMaioria(hashEnvido, hashEnvido.keySet());
			
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
			Integer clusterAcaoDaMaioria = retornaClusterComVotoDaMaioria(hashEnvido, hashEnvido.keySet());
			
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
			Integer clusterAcaoDaMaioriaMao = retornaClusterComVotoDaMaioria(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
							listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioriaMao);
			
			return decisaoIntraCluster.chamarFaltaEnvidoIntraCluster(listaDeCasosClusterMaioria,
					gamestate.getJogadorMao());

		} catch (Exception e) {
			return false;
		}

	}

	public boolean aceitarFaltaEnvido(TrucoDescription gamestate, int rodada,
			HashMap<Integer, List<TrucoDescription>> hashEnvido) {
		try {
			Integer clusterAcaoDaMaioria = retornaClusterComVotoDaMaioria(hashEnvido, hashEnvido.keySet());
			
			List<TrucoDescription> listaDeCasosClusterMaioria = new ArrayList<TrucoDescription>();
			listaDeCasosClusterMaioria = hashEnvido.get(clusterAcaoDaMaioria);
			
			return decisaoIntraCluster.chamarFaltaEnvidoIntraCluster(listaDeCasosClusterMaioria,
					gamestate.getJogadorMao());
		} catch (Exception e) {
			return false;
		}
	}

	private int retornaClusterComVotoDaMaioria(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {

		int grupoMaisVotado = 0;
		int quantidadeDeCasosNoGrupoMaisVotado = 0;
		Iterator<Integer> iteratorK =  listaSemRepetir.iterator();
		while (iteratorK.hasNext()) {
			int k = iteratorK.next();
			if (hash.get(k).size() > quantidadeDeCasosNoGrupoMaisVotado) {
				grupoMaisVotado = k;
				quantidadeDeCasosNoGrupoMaisVotado = hash.get(k).size();

			}

		}
		////////System.out.println("grupo mais votado: "+ grupoMaisVotado);
		return grupoMaisVotado;
	}

	@Override
	public void setDecisionIntraCluster(AcaoFeitasIntraClusterEnvido decisaoIntraCluster) {
		this.decisaoIntraCluster = decisaoIntraCluster;

	}

}
