package cbr.AtualizaConsultas.AuxiliaConsultas;

public class QuemGanhouRodadaModelo {

	private int quemGanhou;
	private int quantosPontosGanhou;
	private int quemNaoAceitouPontos;
	
	public int getQuemGanhou() {
		return quemGanhou;
	}

	public void setQuemGanhou(int quemGanhou) {
		this.quemGanhou = quemGanhou;
	}

	public int getQuantosPontosGanhou() {
		return quantosPontosGanhou;
	}

	public void setQuantosPontosGanhou(int quantosPontosGanhou) {
		this.quantosPontosGanhou = quantosPontosGanhou;
	}
	
	public int getQuemNaoAceitouPontos() {
		return quemNaoAceitouPontos;
	}

	public void setQuemNaoAceitouPontos(int quemNaoAceitouPontos) {
		this.quemNaoAceitouPontos = quemNaoAceitouPontos;
	}
	
	public QuemGanhouRodadaModelo(int quemGanhou, int quantosPontosGanhou, int quemNaoAceitouPontos) {
		super();
		this.quemGanhou = quemGanhou;
		this.quantosPontosGanhou = quantosPontosGanhou;
		this.quemNaoAceitouPontos = quemNaoAceitouPontos;
	}
	
}
