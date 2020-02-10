/**
 * CachedLinealCaseBase.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 03/05/2007
 */

package jcolibri.casebase;

import java.util.Collection;

import cbr.cbrDescriptions.CentroidesGrupoIndexacaoDescription;
import cbr.cbrDescriptions.CentroidesGrupoIndexacaoPontosDescription;
import cbr.cbrDescriptions.CentroidesPrimeiraCartaRoboMaoDescription;
import cbr.cbrDescriptions.CentroidesPrimeiraCartaRoboPeDescription;
import cbr.cbrDescriptions.CentroidesQuemGanhouEnvidoDescription;
import cbr.cbrDescriptions.CentroidesQuemTrucoDescription;
import cbr.cbrDescriptions.CentroidesSegundaCartaRoboGanhouAprimeiraDescription;
import cbr.cbrDescriptions.CentroidesSegundaCartaRoboPerdeuAprimeiraDescription;
import cbr.cbrDescriptions.CentroidesTerceiraCartaRoboGanhouAsegundaDescription;
import cbr.cbrDescriptions.CentroidesTerceiraCartaRoboPerdeuAsegundaDescription;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBaseGustavo;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.InitializingException;

/**
 * Cached case base that only persists cases when closing.
 * learn() and forget() are not synchronized with the persistence until close() is invoked.
 * <p>
 * This class presents better performance that LinelCaseBase as only access to the persistence once. This case base is used for evaluation.
 * 
 * @author Juan A. Recio-Garc�a
 * @see jcolibri.casebase.LinealCaseBase
 */
public class CachedLinealCaseBaseGustavoNew implements CBRCaseBaseGustavo {

	private jcolibri.cbrcore.Connector connector;
	private java.util.Collection<CBRCase> originalCases;
	private java.util.Collection<CBRCase> workingCases;
	
	
	private java.util.Collection<CBRCase> casesToLearn;
	private java.util.Collection<CBRCase> casesToRemove;
	
	/**
	 * Closes the case base saving or deleting the cases of the persistence media
	 */
	@Override
	public void close() {
		
		//casestoRemove.removeAll(workingCases);
		org.apache.commons.logging.LogFactory.getLog(this.getClass()).info("Deleting "+casesToRemove.size()+" cases from storage media");
		connector.deleteCases(casesToRemove);
		
		//java.util.ArrayList<CBRCase> casestoStore = new java.util.ArrayList<CBRCase>(workingCases);
		//casestoStore.removeAll(originalCases);
		org.apache.commons.logging.LogFactory.getLog(this.getClass()).info("Storing "+casesToLearn.size()+" cases into storage media");
		//System.out.println("quantidade de casos para persistir na lista: "+ casesToLearn.size());
		
		connector.storeCases(casesToLearn);
		
		connector.close();

	}



	/**
	 * Returns working cases.
	 */
	@Override
	public Collection<CBRCase> getCases() {
		return workingCases;
	}

	/**
	 * TODO.
	 */
	@Override
	public Collection<CBRCase> getCases(CaseBaseFilter filter) {
		// TODO
		return null;
	}

	/**
	 * Initializes the Case Base with the cases read from the given connector.
	 */
	@Override
	public void init(Connector connector) throws InitializingException {
		this.connector = connector;
		originalCases = this.connector.retrieveAllCases();	
		workingCases = new java.util.ArrayList<CBRCase>(originalCases);
		
		casesToRemove = new java.util.ArrayList<CBRCase>();
		 casesToLearn = new java.util.ArrayList<CBRCase>();
	}
	
	/**
	 * Forgets cases. It only removes the cases from the storage media when closing.
	 */
	@Override
	public void forgetCases(Collection<CBRCase> cases) {
		//System.out.println("entrou no forget");
		casesToRemove.addAll(cases);
		workingCases.removeAll(cases);

	}

	/**
	 * Learns cases that are only saved when closing the Case Base.
	 */
	@Override
	public void learnCases(Collection<CBRCase> cases) {
		workingCases.addAll(cases);
		casesToLearn.addAll(cases);

	}



	@Override
	public void forgetCase(CBRCase cases) {
		casesToRemove.add(cases);
		workingCases.remove(cases);
		
	}



	@Override
	public void learnCase(CBRCase cases, String tipo) {
		/*
		 * primeiro verifica se não existe o caso nas listas tem que verificar para todos os tipos de centroides
		 */
		 Collection<CBRCase>  workingCasesToRemove = new java.util.ArrayList<CBRCase>();
		 Collection<CBRCase>  learningCasesToRemove = new java.util.ArrayList<CBRCase>();
		
		if(!tipo.equals("maos")) {
			if(tipo.equals("indexacaoJogada")) {
				CentroidesGrupoIndexacaoDescription atualParametro = (CentroidesGrupoIndexacaoDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesGrupoIndexacaoDescription armazenadoDescription = (CentroidesGrupoIndexacaoDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesGrupoIndexacaoDescription armazenadoDescription = (CentroidesGrupoIndexacaoDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
			}else if(tipo.equals("indexacaoPontos")) {
				CentroidesGrupoIndexacaoPontosDescription atualParametro = (CentroidesGrupoIndexacaoPontosDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesGrupoIndexacaoPontosDescription armazenadoDescription = (CentroidesGrupoIndexacaoPontosDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesGrupoIndexacaoPontosDescription armazenadoDescription = (CentroidesGrupoIndexacaoPontosDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
			}else if(tipo.equals("PrimeiraCartaRoboMao")) {
				CentroidesPrimeiraCartaRoboMaoDescription atualParametro = (CentroidesPrimeiraCartaRoboMaoDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesPrimeiraCartaRoboMaoDescription armazenadoDescription = (CentroidesPrimeiraCartaRoboMaoDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesPrimeiraCartaRoboMaoDescription armazenadoDescription = (CentroidesPrimeiraCartaRoboMaoDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
				
			}else if(tipo.equals("PrimeiraCartaRoboPe")) {
				
				CentroidesPrimeiraCartaRoboPeDescription atualParametro = (CentroidesPrimeiraCartaRoboPeDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesPrimeiraCartaRoboPeDescription armazenadoDescription = (CentroidesPrimeiraCartaRoboPeDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesPrimeiraCartaRoboPeDescription armazenadoDescription = (CentroidesPrimeiraCartaRoboPeDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
			}else if(tipo.equals("QuemGanhouEnvido")) {
				CentroidesQuemGanhouEnvidoDescription atualParametro = (CentroidesQuemGanhouEnvidoDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesQuemGanhouEnvidoDescription armazenadoDescription = (CentroidesQuemGanhouEnvidoDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesQuemGanhouEnvidoDescription armazenadoDescription = (CentroidesQuemGanhouEnvidoDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
			}else if(tipo.equals("QuemTruco")) {
				CentroidesQuemTrucoDescription atualParametro = (CentroidesQuemTrucoDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesQuemTrucoDescription armazenadoDescription = (CentroidesQuemTrucoDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesQuemTrucoDescription armazenadoDescription = (CentroidesQuemTrucoDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
				
			}else if(tipo.equals("SegundaCartaRoboGanhouAprimeira")) {
				CentroidesSegundaCartaRoboGanhouAprimeiraDescription atualParametro = (CentroidesSegundaCartaRoboGanhouAprimeiraDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesSegundaCartaRoboGanhouAprimeiraDescription armazenadoDescription = (CentroidesSegundaCartaRoboGanhouAprimeiraDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesSegundaCartaRoboGanhouAprimeiraDescription armazenadoDescription = (CentroidesSegundaCartaRoboGanhouAprimeiraDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
			}else if(tipo.equals("SegundaCartaRoboPerdeuAprimeira")) {
				CentroidesSegundaCartaRoboPerdeuAprimeiraDescription atualParametro = (CentroidesSegundaCartaRoboPerdeuAprimeiraDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesSegundaCartaRoboPerdeuAprimeiraDescription armazenadoDescription = (CentroidesSegundaCartaRoboPerdeuAprimeiraDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesSegundaCartaRoboPerdeuAprimeiraDescription armazenadoDescription = (CentroidesSegundaCartaRoboPerdeuAprimeiraDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
			}else if(tipo.equals("TerceiraCartaRoboGanhouAsegunda")) {
				CentroidesTerceiraCartaRoboGanhouAsegundaDescription atualParametro = (CentroidesTerceiraCartaRoboGanhouAsegundaDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesTerceiraCartaRoboGanhouAsegundaDescription armazenadoDescription = (CentroidesTerceiraCartaRoboGanhouAsegundaDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesTerceiraCartaRoboGanhouAsegundaDescription armazenadoDescription = (CentroidesTerceiraCartaRoboGanhouAsegundaDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
			}else if(tipo.equals("TerceiraCartaRoboPerdeuAsegunda")) {
				CentroidesTerceiraCartaRoboPerdeuAsegundaDescription atualParametro = (CentroidesTerceiraCartaRoboPerdeuAsegundaDescription) cases.getDescription();
				for(CBRCase armazenadoWorking : workingCases ) {
					CentroidesTerceiraCartaRoboPerdeuAsegundaDescription armazenadoDescription = (CentroidesTerceiraCartaRoboPerdeuAsegundaDescription) armazenadoWorking.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) workingCasesToRemove.add(armazenadoWorking);
				}
				for(CBRCase armazenadoToLearn : casesToLearn ) {
					CentroidesTerceiraCartaRoboPerdeuAsegundaDescription armazenadoDescription = (CentroidesTerceiraCartaRoboPerdeuAsegundaDescription) armazenadoToLearn.getDescription();
					// se der erro pode ser aqui, já que está alterando a lista enquanto percorre.
					if(armazenadoDescription.getGrupo() == atualParametro.getGrupo()) learningCasesToRemove.add(armazenadoToLearn);
				}
				
				
			}
			
			
		}
		casesToLearn.removeAll(learningCasesToRemove);
		workingCasesToRemove.removeAll(workingCasesToRemove);
		
		casesToLearn.add(cases);
		workingCases.add(cases);
		
		
	}

}
