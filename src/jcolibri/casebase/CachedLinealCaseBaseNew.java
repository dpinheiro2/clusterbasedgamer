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

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
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
public class CachedLinealCaseBaseNew implements CBRCaseBase {

	private jcolibri.cbrcore.Connector connector;
	private java.util.Collection<CBRCase> originalCases;
	private java.util.Collection<CBRCase> workingCases;
	
	
	private java.util.Collection<CBRCase> casesToLearn;
	private java.util.Collection<CBRCase> casesToRemove;
	
	/**
	 * Closes the case base saving or deleting the cases of the persistence media
	 */
	public void close() {
		
		//casestoRemove.removeAll(workingCases);
		org.apache.commons.logging.LogFactory.getLog(this.getClass()).info("Deleting "+casesToRemove.size()+" cases from storage media");
		connector.deleteCases(casesToRemove);
		
		//java.util.ArrayList<CBRCase> casestoStore = new java.util.ArrayList<CBRCase>(workingCases);
		//casestoStore.removeAll(originalCases);
		org.apache.commons.logging.LogFactory.getLog(this.getClass()).info("Storing "+casesToLearn.size()+" cases into storage media");
		
		connector.storeCases(casesToLearn);
		
		connector.close();

	}



	/**
	 * Returns working cases.
	 */
	public Collection<CBRCase> getCases() {
		return workingCases;
	}

	/**
	 * TODO.
	 */
	public Collection<CBRCase> getCases(CaseBaseFilter filter) {
		// TODO
		return null;
	}

	/**
	 * Initializes the Case Base with the cases read from the given connector.
	 */
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
	public void forgetCases(Collection<CBRCase> cases) {
		//System.out.println("entrou no forget");
		casesToRemove.addAll(cases);
		workingCases.removeAll(cases);

	}

	/**
	 * Learns cases that are only saved when closing the Case Base.
	 */
	public void learnCases(Collection<CBRCase> cases) {
		workingCases.addAll(cases);
		casesToLearn.addAll(cases);

	}

	@Override
	public void learnCase(CBRCase cases) {
		workingCases.add(cases);
		casesToLearn.add(cases);
		
	}

	@Override
	public void forgetCase(CBRCase cases) {
		casesToRemove.add(cases);
		workingCases.remove(cases);
		
	}

}
