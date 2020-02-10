package clusterModelo;

public class EnvidoModelo {
	private int quantidadeEnvidoChamadoAgente = 0;
	private int quantidadeRealEnvidoChamadoAgente = 0;
	private int quantidadeFaltaEnvidoChamadoAGente = 0;
	private int quantidadeEnvidoNegadoAgente = 0;

	
	public int getQuantidadeEnvidoChamadoAgente() {
		return quantidadeEnvidoChamadoAgente;
	}
	public void setQuantidadeEnvidoChamadoAgente(int quantidadeEnvidoChamadoAgente) {
		this.quantidadeEnvidoChamadoAgente = quantidadeEnvidoChamadoAgente;
	}
	public int getQuantidadeRealEnvidoChamadoAgente() {
		return quantidadeRealEnvidoChamadoAgente;
	}
	public void setQuantidadeRealEnvidoChamadoAgente(int quantidadeRealEnvidoChamadoAgente) {
		this.quantidadeRealEnvidoChamadoAgente = quantidadeRealEnvidoChamadoAgente;
	}
	public int getQuantidadeFaltaEnvidoChamadoAGente() {
		return quantidadeFaltaEnvidoChamadoAGente;
	}
	public void setQuantidadeFaltaEnvidoChamadoAGente(int quantidadeFaltaEnvidoChamadoAGente) {
		this.quantidadeFaltaEnvidoChamadoAGente = quantidadeFaltaEnvidoChamadoAGente;
	}
	public int getQuantidadeEnvidoNegadoAgente() {
		return quantidadeEnvidoNegadoAgente;
	}
	public void setQuantidadeEnvidoNegadoAgente(int quantidadeEnvidoNegadoAgente) {
		this.quantidadeEnvidoNegadoAgente = quantidadeEnvidoNegadoAgente;
	}
	public EnvidoModelo(int quantidadeEnvidoChamadoAgente, int quantidadeRealEnvidoChamadoAgente,
			int quantidadeFaltaEnvidoChamadoAGente, int quantidadeEnvidoNegadoAgente) {
		super();
		this.quantidadeEnvidoChamadoAgente = quantidadeEnvidoChamadoAgente;
		this.quantidadeRealEnvidoChamadoAgente = quantidadeRealEnvidoChamadoAgente;
		this.quantidadeFaltaEnvidoChamadoAGente = quantidadeFaltaEnvidoChamadoAGente;
		this.quantidadeEnvidoNegadoAgente = quantidadeEnvidoNegadoAgente;
	}
	
	
		
	
}
