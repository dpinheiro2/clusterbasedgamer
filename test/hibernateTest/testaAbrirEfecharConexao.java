package hibernateTest;

import jcolibri.casebase.CachedLinealCaseBaseNew;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnectorNew;
import jcolibri.exception.InitializingException;

public class testaAbrirEfecharConexao {

	static Connector _connectorMaos = null;
    static CBRCaseBase _caseBaseMaos = null;

	
	public static void main(String[] args) {
		_connectorMaos  = new DataBaseConnectorNew(); 
		try {
			_connectorMaos.initFromXMLfile(jcolibri.util.FileIO.findFile( "cbr/Hibernate/" + "databases/databaseconfig.xml"));
			
			_caseBaseMaos = new CachedLinealCaseBaseNew(); 
			_caseBaseMaos.init(_connectorMaos);
			
			//System.out.println("quantidade de casos recuperados: "+ _caseBaseMaos.getCases().size());
			
			_connectorMaos.close();
			_caseBaseMaos.close();
			
			//System.out.println(_connectorMaos);
			try {
				java.util.concurrent.TimeUnit.SECONDS.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InitializingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
}
