package clusterModelo;

public class QuandoTrucoModelo {
	int quandoTrucoMaisPontuado;
	int quandoRetrucoMaisPontuado;
	int quandoValeQuatroMaisPontuado;
	
	
	public int getQuandoTrucoMaisPontuado() {
		return quandoTrucoMaisPontuado;
	}
	public void setQuandoTrucoMaisPontuado(int quandoTrucoMaisPontuado) {
		this.quandoTrucoMaisPontuado = quandoTrucoMaisPontuado;
	}
	public int getQuandoRetrucoMaisPontuado() {
		return quandoRetrucoMaisPontuado;
	}
	public void setQuandoRetrucoMaisPontuado(int quandoRetrucoMaisPontuado) {
		this.quandoRetrucoMaisPontuado = quandoRetrucoMaisPontuado;
	}
	public int getQuandoValeQuatroMaisPontuado() {
		return quandoValeQuatroMaisPontuado;
	}
	public void setQuandoValeQuatroMaisPontuado(int quandoValeQuatroMaisPontuado) {
		this.quandoValeQuatroMaisPontuado = quandoValeQuatroMaisPontuado;
	}
	
	
	public QuandoTrucoModelo(int quandoTrucoMaisPontuado, int quandoRetrucoMaisPontuado,
			int quandoValeQuatroMaisPontuado) {
		super();
		this.quandoTrucoMaisPontuado = quandoTrucoMaisPontuado;
		this.quandoRetrucoMaisPontuado = quandoRetrucoMaisPontuado;
		this.quandoValeQuatroMaisPontuado = quandoValeQuatroMaisPontuado;
	}
	
	public QuandoTrucoModelo() {
		
	}
	
	

}
