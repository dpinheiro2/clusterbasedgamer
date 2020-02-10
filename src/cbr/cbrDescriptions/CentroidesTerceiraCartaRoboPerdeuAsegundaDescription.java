package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesTerceiraCartaRoboPerdeuAsegundaDescription implements CaseComponent {
    int id;
    
    
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int grupo;
	double terceiraCartaHumanoClustering;
	double terceiraCartaRoboClustering;
	int numerok;
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public double getTerceiraCartaHumanoClustering() {
		return terceiraCartaHumanoClustering;
	}
	public void setTerceiraCartaHumanoClustering(double terceiraCartaHumanoClustering) {
		this.terceiraCartaHumanoClustering = terceiraCartaHumanoClustering;
	}
	public double getTerceiraCartaRoboClustering() {
		return terceiraCartaRoboClustering;
	}
	public void setTerceiraCartaRoboClustering(double terceiraCartaRoboClustering) {
		this.terceiraCartaRoboClustering = terceiraCartaRoboClustering;
	}
	public int getNumerok() {
		return numerok;
	}
	public void setNumerok(int numerok) {
		this.numerok = numerok;
	}

	
	
	
	public CentroidesTerceiraCartaRoboPerdeuAsegundaDescription(int id, int grupo, double terceiraCartaHumanoClustering,
			double terceiraCartaRoboClustering, int numerok) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.terceiraCartaHumanoClustering = terceiraCartaHumanoClustering;
		this.terceiraCartaRoboClustering = terceiraCartaRoboClustering;
		this.numerok = numerok;
	}
	public CentroidesTerceiraCartaRoboPerdeuAsegundaDescription(){
		
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
