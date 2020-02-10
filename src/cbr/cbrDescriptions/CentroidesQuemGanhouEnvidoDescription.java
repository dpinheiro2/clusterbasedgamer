package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesQuemGanhouEnvidoDescription  implements CaseComponent{
    int id;
    
    
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int grupo;
    Double quemPediuEnvido;
    Double quemPediuRealEnvido;
    Double quemPediuFaltaEnvido;
    Double pontosEnvidoRobo;
    
    int numerok;
	
    public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public Double getQuemPediuEnvido() {
		return quemPediuEnvido;
	}
	public void setQuemPediuEnvido(Double quemPediuEnvido) {
		this.quemPediuEnvido = quemPediuEnvido;
	}
	public Double getQuemPediuRealEnvido() {
		return quemPediuRealEnvido;
	}
	public void setQuemPediuRealEnvido(Double quemPediuRealEnvido) {
		this.quemPediuRealEnvido = quemPediuRealEnvido;
	}
	public Double getQuemPediuFaltaEnvido() {
		return quemPediuFaltaEnvido;
	}
	public void setQuemPediuFaltaEnvido(Double quemPediuFaltaEnvido) {
		this.quemPediuFaltaEnvido = quemPediuFaltaEnvido;
	}
		public int getNumerok() {
		return numerok;
	}
	public void setNumerok(int numerok) {
		this.numerok = numerok;
	}

	
	
	
	
	public Double getPontosEnvidoRobo() {
		return pontosEnvidoRobo;
	}
	public void setPontosEnvidoRobo(Double pontosEnvidoRobo) {
		this.pontosEnvidoRobo = pontosEnvidoRobo;
	}
	public CentroidesQuemGanhouEnvidoDescription(int id, int grupo, Double quemPediuEnvido, Double quemPediuRealEnvido,
			Double quemPediuFaltaEnvido, Double quemNegouEnvido, int numerok) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.quemPediuEnvido = quemPediuEnvido;
		this.quemPediuRealEnvido = quemPediuRealEnvido;
		this.quemPediuFaltaEnvido = quemPediuFaltaEnvido;
		
		this.numerok = numerok;
	}
	public CentroidesQuemGanhouEnvidoDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
	
}
