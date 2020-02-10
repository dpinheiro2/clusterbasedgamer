package cbr.AtualizaConsultas.AuxiliaConsultas;

public class TentosGanhos {
	
	
	
	Integer tentosEnvido;
	Integer tentosFlor;
	Integer tentosTruco;
	Integer quemGanhouTruco;
	Integer quemGanhouEnvido;
	Integer quemGanhouFlor;
	public Integer getTentosEnvido() {
		return tentosEnvido;
	}
	public void setTentosEnvido(Integer tentosEnvido) {
		this.tentosEnvido = tentosEnvido;
	}
	public Integer getTentosFlor() {
		return tentosFlor;
	}
	public void setTentosFlor(Integer tentosFlor) {
		this.tentosFlor = tentosFlor;
	}
	public Integer getTentosTruco() {
		return tentosTruco;
	}
	public void setTentosTruco(Integer tentosTruco) {
		this.tentosTruco = tentosTruco;
	}
	public Integer getQuemGanhouTruco() {
		return quemGanhouTruco;
	}
	public void setQuemGanhouTruco(Integer quemGanhouTruco) {
		this.quemGanhouTruco = quemGanhouTruco;
	}
	public Integer getQuemGanhouEnvido() {
		return quemGanhouEnvido;
	}
	public void setQuemGanhouEnvido(Integer quemGanhouEnvido) {
		this.quemGanhouEnvido = quemGanhouEnvido;
	}
	public Integer getQuemGanhouFlor() {
		return quemGanhouFlor;
	}
	public void setQuemGanhouFlor(Integer quemGanhouFlor) {
		this.quemGanhouFlor = quemGanhouFlor;
	}
	public TentosGanhos() {
		super();
		this.tentosEnvido = 0;
		this.tentosFlor = 0;
		this.tentosTruco = 0;
		this.quemGanhouTruco = 0;
		this.quemGanhouEnvido = 0;
		this.quemGanhouFlor = 0;
	}

	
	
	public void setEnvido(int Quem, int tentos) {
		this.quemGanhouEnvido = Quem;
		this.tentosEnvido = tentos;

	}
	
	
	
	public void setTruco(int Quem, int tentos) {
		this.quemGanhouTruco= Quem;
		this.tentosTruco = tentos;

	}
	
	
}
