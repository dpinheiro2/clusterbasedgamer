package hashSolucao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import hash.HashEnvido;

public class HashSolucaoEnvido implements HashEnvido {
	/*
	 * equalsequalsequalsequalsequals POSSIBILIDADES CHAMAR equalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals= 
	 * 12 - 1 CHAMA, 2 NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 20 - 2 CHAMA, 0 NINGUÉM
	 * NEGA 121020 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA CHAMAR
	 * equalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals=
	 * = equalsequalsequalsequals= POSSIBILIDADES NÃO CHAMAR
	 * TRUCOequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals 
	 * 21 - 2 CHAMA, 1 NEGA 
	 * 30 - NINGUÉM CHAMA 
	 * 2130 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA NÃO CHAMAR
	 * 
	 * equalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals=
	 * equals
	 * 
	 */
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarGenerico(List<TrucoDescription> listaCasos,
			String tipoDeChamada, int quemEmao) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		Iterator<TrucoDescription>  listaIterator = listaCasos.iterator();
		
		// caso nenhum tenha chamado truco cria um grupo com o número 30
		while(listaIterator.hasNext()){
		TrucoDescription c =  listaIterator.next();
		if(c.getJogadorMao().equals(quemEmao)) {
		if(c.getQuemPediuFaltaEnvido() != null && !c.getQuemPediuFaltaEnvido().equals(0)) {
			//System.out.println("quem pediu falta envido: "+ c.getQuemPediuFaltaEnvido() + " quem negou envido: "+ c.getQuemNegouEnvido());
			//System.out.println("pontos do vivente: "+ c.getPontosEnvidoRobo());
			//System.out.println("grupo indexação: "+ c.getClusteringIndexacaoPontos());
			
			//if(quemEmao == 1)  System.out.println("cluster envido jogador mão: "+c.getClusterQuemEnvidoAgenteMao() );
			//else if(quemEmao == 2) System.out.println("cluster envido jogador mão: "+c.getClusterQuemEnvidoAgentePe() );
			
		}
		if(c.getQuemPediuRealEnvido() != null && !c.getQuemPediuRealEnvido().equals(0)) {
			//System.out.println("quem pediu real envido: "+ c.getQuemPediuRealEnvido() + " quem negou envido: "+ c.getQuemNegouEnvido());
			//System.out.println("pontos do vivente: "+ c.getPontosEnvidoRobo());
			//System.out.println("grupo indexação: "+ c.getClusteringIndexacaoPontos());
			
			//if(quemEmao == 1)  System.out.println("cluster envido jogador mão: "+c.getClusterQuemEnvidoAgenteMao() );
			//else if(quemEmao == 2) System.out.println("cluster envido jogador mão: "+c.getClusterQuemEnvidoAgentePe() );
			
		}
			
			int quemChamou = 0;
			int quemChamouMaior1 =0;
			int quemChamouMaior2 = 0;
			int quemNegou = 0;
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoDeChamada.equalsIgnoreCase("envido")) {
				quemChamou = c.getQuemPediuEnvido();
				quemChamouMaior1 = c.getQuemPediuRealEnvido();
				quemChamouMaior2 = c.getQuemPediuFaltaEnvido();
				quemNegou = c.getQuemNegouEnvido();
			} else if (tipoDeChamada.equalsIgnoreCase("realenvido")) {
				quemChamou = c.getQuemPediuRealEnvido();
				quemChamouMaior1 = c.getQuemPediuFaltaEnvido();
				quemChamouMaior2 =0;
				quemNegou = c.getQuemNegouEnvido();
			} else if (tipoDeChamada.equalsIgnoreCase("faltaenvido")) {
				quemChamou = c.getQuemPediuFaltaEnvido();
				quemChamouMaior1 = 0;
				quemChamouMaior2 =0;
				quemNegou = c.getQuemNegouEnvido();
			}

			if ((((quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0) || (quemChamou == 2 && quemNegou == 1))
					&& hashDeCasos.get(2130) == null)
					|| ((((quemChamou !=0 || quemChamouMaior1 !=0 || quemChamouMaior2 != 0 ) || (quemChamou == 2  && quemNegou != 1)) )
							&& hashDeCasos.get(121020) == null)) {
				List<TrucoDescription> listaDeCasosPor = new ArrayList<TrucoDescription>();
				listaDeCasosPor.add(c);
				if (((quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0) || (quemChamou == 2 && quemNegou == 1))
						&& hashDeCasos.get(2130) == null)
					hashDeCasos.put(2130, listaDeCasosPor);
				else if(((quemChamou !=0 || quemChamouMaior1 !=0 || quemChamouMaior2 != 0 ) || (quemChamou == 2  && quemNegou != 1)) )
					hashDeCasos.put(121020, listaDeCasosPor);
			} else if ((((quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 ) || (quemChamou == 2 && quemNegou == 1))
					&& hashDeCasos.get(2130) != null)
					|| ((((quemChamou !=0 || quemChamouMaior1 !=0 || quemChamouMaior2 != 0 )|| (quemChamou == 2  && quemNegou != 1)) )
							&& hashDeCasos.get(121020) != null)) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = null;
				if (((quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0) || (quemChamou == 2 && quemNegou == 1))) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(2130);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(2130, listaDeCasosExistentesPorGrupos);
				} else   if(((quemChamou !=0 || quemChamouMaior1 !=0 || quemChamouMaior2 != 0 ) || (quemChamou == 2  && quemNegou != 1)) ) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(121020);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(121020, listaDeCasosExistentesPorGrupos);
				}
			}

		}
		
		
		}
		
		return hashDeCasos;
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		return retornaHashChamarGenerico(listaCasos, "envido", quemMao);

	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarRealEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		return retornaHashChamarGenerico(listaCasos, "realenvido", quemMao);
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarFaltaEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		return retornaHashChamarGenerico(listaCasos, "faltaenvido", quemMao);
	}

	/*
	 * equalsequalsequalsequalsequals COMBINAÇÕES ACEITAR equalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals
	 * 20 - 2 CHAMA, 0 NINGUÉM NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 12 - 1 CHAMA, 2
	 * NEGA 201012 <- REPRESENTA O CLUSTER ACEITAR TRUCO
	 * equalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals=
	 * equalsequalsequalsequalsequals COMBINAÇÕES NEGAR TRUCOequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals=
	 * 21 - 2 CHAMA, 1 NEGA 
	 * 30 - NINGUÉM CHAMA -> não vou contabilizar ninguém chama para aceitar
	 * CHAMADO 2130 <- REPRESENTA NEGAR
	 * equalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequalsequals=
	 * equals
	 * 
	 */
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarGenerico(List<TrucoDescription> listaCasos,
			String tipoAceite, int quemEmao) {
		//////System.out.println("Lista de casos para aceitar: " +listaCasos.size());
		//////System.out.println("jogador mÃo: "+ quemEmao);
		//////System.out.println("tipo aceite: "+ tipoAceite);
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
        Iterator<TrucoDescription> iteratorCasos = listaCasos.iterator();
		while(iteratorCasos.hasNext()) {
			 TrucoDescription c = iteratorCasos.next();
			 int jogadorMaoCasoAtual = c.getJogadorMao();
		if(quemEmao == jogadorMaoCasoAtual) {	
			int quemChamou = 0;
			int quemChamouMaior1 =0;
			int quemChamouMaior2 = 0;
			int quemNegou = 0;
			
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoAceite.equalsIgnoreCase("envido")) {
				quemChamou = c.getQuemPediuEnvido();
				quemChamouMaior1 = c.getQuemPediuRealEnvido();
				quemChamouMaior2 = c.getQuemPediuFaltaEnvido();
				
				quemNegou = c.getQuemNegouEnvido();
				
			} else if (tipoAceite.equalsIgnoreCase("realenvido")) {
				quemChamou = c.getQuemPediuRealEnvido();
				quemChamouMaior1 = c.getQuemPediuFaltaEnvido();
				quemChamouMaior1 = 0;
				quemNegou = c.getQuemNegouEnvido();
				
			} else if (tipoAceite.equalsIgnoreCase("faltaenvido")) {
				quemChamou = c.getQuemPediuFaltaEnvido();
				quemChamouMaior1 = 0;
				quemChamouMaior2 = 0;
				quemNegou = c.getQuemNegouEnvido();
			}

			if ((((quemChamou == 2 && quemNegou == 1) || (quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0)) && hashDeCasos.get(2130) == null)
					|| ((quemChamou != 0 && quemNegou != 1) || (tipoAceite.equalsIgnoreCase("envido") && !c.getQuemPediuRealEnvido().equals(0) &&
							quemNegou !=1) || ((tipoAceite.equalsIgnoreCase("envido") || tipoAceite.equalsIgnoreCase("realenvido") )&& (!c.getQuemPediuFaltaEnvido().equals(0) &&
									quemNegou !=1)) ||(quemChamou != 0 || quemChamouMaior1 !=0 || quemChamouMaior2 !=0 )
							&& hashDeCasos.get(201012) == null)) {
				List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
				if (((quemChamou == 2 && quemNegou == 1)||(quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0))
						&& hashDeCasos.get(2130) == null) {
					listaDeCasosPorCluster.add(c);
					hashDeCasos.put(2130, listaDeCasosPorCluster);
				} else if ((quemChamou != 0 && quemNegou != 1) || (tipoAceite.equalsIgnoreCase("envido") && !c.getQuemPediuRealEnvido().equals(0) &&
						quemNegou !=1) || ((tipoAceite.equalsIgnoreCase("envido") || tipoAceite.equalsIgnoreCase("realenvido") )&& (!c.getQuemPediuFaltaEnvido().equals(0) &&
								quemNegou !=1) ||  (quemChamou != 0 || quemChamouMaior1 !=0 || quemChamouMaior2 !=0 ))
						 && hashDeCasos.get(201012) == null) {
					listaDeCasosPorCluster.add(c);

					hashDeCasos.put(201012, listaDeCasosPorCluster);
				}
				                  
			} else if (((quemChamou == 2 && quemNegou == 1) ||(quemChamou == 0)
					&& hashDeCasos.get(2130) != null)
					|| ((quemChamou !=0 && quemNegou != 1) || (tipoAceite.equalsIgnoreCase("envido") && !c.getQuemPediuRealEnvido().equals(0) &&
							quemNegou !=1) || ((tipoAceite.equalsIgnoreCase("envido") || tipoAceite.equalsIgnoreCase("realenvido") )&& (!c.getQuemPediuFaltaEnvido().equals(0) &&
									quemNegou !=1))  ||(quemChamou != 0 || quemChamouMaior1 !=0 || quemChamouMaior2 !=0 )
					
							 && hashDeCasos.get(201012) != null)) {
				if ((quemChamou == 2 && quemNegou == 1) || (quemChamou == 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0)) {
					List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(2130);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(2130, listaDeCasosExistentesPorGrupos);
				} else if ((quemChamou !=0 && quemNegou != 1) || (tipoAceite.equalsIgnoreCase("envido") && !c.getQuemPediuRealEnvido().equals(0) &&
						quemNegou !=1) || ((tipoAceite.equalsIgnoreCase("envido") || tipoAceite.equalsIgnoreCase("realenvido") )&& !c.getQuemPediuFaltaEnvido().equals(0) &&
								quemNegou !=1)  ||(quemChamou != 0 || quemChamouMaior1 !=0 || quemChamouMaior2 !=0 )) {
					List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(201012);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(201012, listaDeCasosExistentesPorGrupos);
				}

			}
		}
		
			
		
		}
		
		return hashDeCasos;
	}
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		return retornaHashAceitarGenerico(listaCasos, "envido", quemMao);
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarRealEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		return retornaHashAceitarGenerico(listaCasos, "realenvido", quemMao);	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarFaltaEnvido(List<TrucoDescription> listaCasos,
			int quemMao) {
		return retornaHashAceitarGenerico(listaCasos, "faltaenvido", quemMao);
	}
}
