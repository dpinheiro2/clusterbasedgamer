package ajudaCluster;

import cbr.cbrDescriptions.CentroidesGrupoIndexacaoDescription;
import cbr.cbrDescriptions.TrucoDescription;

public class converteTrucoDescriptionParaCentroidesGrupoIndexacaoDescription {

	
	public CentroidesGrupoIndexacaoDescription converte(TrucoDescription td) {
		converteFormatosCartasParaCartasJogadasClustering conversorDeCartas = new converteFormatosCartasParaCartasJogadasClustering();
		
		CentroidesGrupoIndexacaoDescription convertido = new CentroidesGrupoIndexacaoDescription();
		
		double cartaAltaClustering = conversorDeCartas.retornaCartaAlta(td);
		double cartaMediaClustering = conversorDeCartas.retornaCartaMedia(td);
		double cartaBaixaClustering = conversorDeCartas.retornaCartaBaixa(td);
		
		convertido.setCentroidecartaaltarobomao(cartaAltaClustering);
		convertido.setCentroidecartamediarobomao(cartaMediaClustering);
		convertido.setCentroidecartabaixarobomao(cartaBaixaClustering);
		
		
		return convertido;
	}
}
