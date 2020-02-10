package clusterModelo;

public class SaldoModelo {
	int clusterComMaiorSaldo;
	Double maiorSaldo;
	Double quantidadeCasosGrupoComMaiorSaldo;
	
	public int getClusterComMaiorSaldo() {
		return clusterComMaiorSaldo;
	}
	public void setClusterComMaiorSaldo(int clusterComMaiorSaldo) {
		this.clusterComMaiorSaldo = clusterComMaiorSaldo;
	}
	public Double getMaiorSaldo() {
		return maiorSaldo;
	}
	public void setMaiorSaldo(Double maiorSaldo) {
		this.maiorSaldo = maiorSaldo;
	}
	public Double getQuantidadeCasosGrupoComMaiorSaldo() {
		return quantidadeCasosGrupoComMaiorSaldo;
	}
	public void setQuantidadeCasosGrupoComMaiorSaldo(Double quantidadeCasosGrupoComMaiorSaldo) {
		this.quantidadeCasosGrupoComMaiorSaldo = quantidadeCasosGrupoComMaiorSaldo;
	}
	public SaldoModelo(int clusterComMaiorSaldo, Double maiorSaldo, Double quantidadeCasosGrupoComMaiorSaldo) {
		super();
		this.clusterComMaiorSaldo = clusterComMaiorSaldo;
		this.maiorSaldo = maiorSaldo;
		this.quantidadeCasosGrupoComMaiorSaldo = quantidadeCasosGrupoComMaiorSaldo;
	}
	
	public SaldoModelo() {
		
	}


	
	
	
	

}
