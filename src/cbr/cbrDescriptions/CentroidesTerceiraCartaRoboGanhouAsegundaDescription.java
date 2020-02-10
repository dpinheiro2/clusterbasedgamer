package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesTerceiraCartaRoboGanhouAsegundaDescription  implements CaseComponent{
	int id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int grupo;
	Double terceiraCartaRoboClustering;
	int numerok;
	
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public Double getTerceiraCartaRoboClustering() {
		return terceiraCartaRoboClustering;
	}
	public void setTerceiraCartaRoboClustering(Double terceiraCartaRoboClustering) {
		this.terceiraCartaRoboClustering = terceiraCartaRoboClustering;
	}
	public int getNumerok() {
		return numerok;
	}
	public void setNumerok(int numerok) {
		this.numerok = numerok;
	}

	
	
public CentroidesTerceiraCartaRoboGanhouAsegundaDescription(int id, int grupo, Double terceiraCartaRoboClustering,
			int numerok) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.terceiraCartaRoboClustering = terceiraCartaRoboClustering;
		this.numerok = numerok;
	}
public	CentroidesTerceiraCartaRoboGanhouAsegundaDescription(){
		
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
