package clusterModelo;

public class PontuacaoModelo {
	int clusterComMaiorPontuacao;
	Double maiorPontuacao;
	int quantidadeDeCasosNoMelhorCluster;
	
	public int getClusterComMaiorPontuacao() {
		return clusterComMaiorPontuacao;
	}
	public void setClusterComMaiorPontuacao(int clusterComMaiorPontuacao) {
		this.clusterComMaiorPontuacao = clusterComMaiorPontuacao;
	}
	public Double getMaiorPontuacao() {
		return maiorPontuacao;
	}
	public void setMaiorPontuacao(Double maiorPontuacao) {
		this.maiorPontuacao = maiorPontuacao;
	}
	public int getQuantidadeDeCasosNoMelhorCluster() {
		return quantidadeDeCasosNoMelhorCluster;
	}
	public void setQuantidadeDeCasosNoMelhorCluster(int quantidadeDeCasosNoMelhorCluster) {
		this.quantidadeDeCasosNoMelhorCluster = quantidadeDeCasosNoMelhorCluster;
	}
	
	public PontuacaoModelo(int clusterComMaiorPontuacao, Double maiorPontuacao, int quantidadeDeCasosNoMelhorCluster) {
		super();
		this.clusterComMaiorPontuacao = clusterComMaiorPontuacao;
		this.maiorPontuacao = maiorPontuacao;
		this.quantidadeDeCasosNoMelhorCluster = quantidadeDeCasosNoMelhorCluster;
	}
	
	
	

}
