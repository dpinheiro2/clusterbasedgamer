/**
 * Average.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 03/01/2007
 */
package euclidean;

import jcolibri.method.retrieve.NNretrieval.similarity.StandardGlobalSimilarityFunction;
import utils.debug.criaArquivosDebug;


/**
 * This function computes the average of the similarites of its subattributes.
 * @author Gustavo Paulus
 * @version 1.0
 */
public class GlobalEuclidean extends StandardGlobalSimilarityFunction {


	public double computeSimilarity(double[] values, double[] weigths, int ivalue)
	{
		double acum = 0;
		double weigthsAcum = 0;
		for(int i=0; i<ivalue; i++)
		{
	
			acum += values[i] * weigths[i];
			weigthsAcum += weigths[i];
		}
		
		

		
		
			 return (float) Math.sqrt((float) acum / (float) weigthsAcum);	
		
	}
	
}
