package cbr.cbrDescriptions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CentroidesGrupoIndexacaoPontosDescription implements CaseComponent {

int id;



double centroidepontosenvidorobo;


int grupo;
int numerok;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getGrupo() {
	return grupo;
}
public void setGrupo(int grupo) {
	this.grupo = grupo;
}
public int getNumerok() {
	return numerok;
}
public void setNumerok(int numerok) {
	this.numerok = numerok;
}

public double getCentroidepontosenvidorobo() {
	return centroidepontosenvidorobo;
}
public void setCentroidepontosenvidorobo(double centroidepontosenvidorobo) {
	this.centroidepontosenvidorobo = centroidepontosenvidorobo;
}




@Override
public Attribute getIdAttribute() {
	// TODO Auto-generated method stub
	return null;
}



	
}
