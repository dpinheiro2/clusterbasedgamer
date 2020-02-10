package hashSolucao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cbr.cbrDescriptions.TrucoDescription;
import hash.HashTruco;

public class HashSolucaoTruco implements HashTruco {
	/*
	 * ========== POSSIBILIDADES CHAMAR ======================================= 12 -
	 * 12 - 1 CHAMA, 2 NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 20 - 2 CHAMA, 0 NINGUÉM
	 * NEGA 121020 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA CHAMAR
	 * =============================================================================
	 * = ========= POSSIBILIDADES NÃO CHAMAR
	 * TRUCO==================================== 21 - 2 CHAMA, 1 NEGA 30 - NINGUÉM
	 * CHAMA 2130 <- VALOR QUE JUSTIFICA O GRUPO QUE CONTEM OS CASOS PARA NÃO CHAMAR
	 * 
	 * =============================================================================
	 * ==
	 * 
	 */
	public HashMap<Integer, List<TrucoDescription>> retornaHashChamarGenerico(List<TrucoDescription> listaCasos,
			String tipoDeChamada, int rodada) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		Iterator<TrucoDescription> iteratorListaCasos = listaCasos.iterator();
		// caso nenhum tenha chamado truco cria um grupo com o número 30
		while (iteratorListaCasos.hasNext()) {
			int quemChamou = 0;
			int quemChamouMaior1 = 0;
			int quemChamouMaior2 = 0;

			int quandoChamou = 0;
			int quandoChamouMaior1 = 0;
			int quandoChamouMaior2 = 0;

			int quemNegou = 0;
			TrucoDescription c = iteratorListaCasos.next();
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoDeChamada.equalsIgnoreCase("truco")) {
				quemChamou = c.getQuemTruco();
				quemChamouMaior1 = c.getQuemRetruco();
				quemChamouMaior2 = c.getQuemValeQuatro();

				quandoChamou = c.getQuandoTruco();
				quandoChamouMaior1 = c.getQuandoRetruco();
				quandoChamouMaior2 = c.getQuandoValeQuatro();

				quemNegou = c.getQuemNegouTruco();
				// colocar chamada maior 1 e chamada maior 2
			} else if (tipoDeChamada.equalsIgnoreCase("retruco")) {
				quemChamou = c.getQuemRetruco();
				quemChamouMaior1 = c.getQuemValeQuatro();
				quemChamouMaior2 = 0;

				quandoChamou = c.getQuandoRetruco();
				quandoChamouMaior1 = c.getQuandoValeQuatro();
				quandoChamouMaior2 = 0;

				quemNegou = c.getQuemNegouTruco();
				// colocar chamada maior 1
			} else if (tipoDeChamada.equalsIgnoreCase("valequatro")) {
				quemChamou = c.getQuemValeQuatro();
				quemChamouMaior1 = 0;
				quemChamouMaior2 =0;

				quandoChamou = c.getQuandoValeQuatro();
				quandoChamouMaior1 = 0;
				quandoChamouMaior2=0;
				quemNegou = c.getQuemNegouTruco();
			}

			if (((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1)
					|| (quandoChamou != rodada && quandoChamouMaior1 != rodada && quandoChamouMaior2 != rodada)
							&& hashDeCasos.get(2130) == null)

					|| ((quandoChamou == rodada || quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada)
							&& ((quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

									|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 && quandoChamou == rodada
											|| quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada))
							&& hashDeCasos.get(121020) == null)) {
				List<TrucoDescription> listaDeCasosPor = new ArrayList<TrucoDescription>();
				listaDeCasosPor.add(c);
				if (((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1)
						|| (quandoChamou != rodada && quandoChamouMaior1 != rodada && quandoChamouMaior2 != rodada)
								&& hashDeCasos.get(2130) == null))
					hashDeCasos.put(2130, listaDeCasosPor);
				else if (((quandoChamou == rodada || quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada)
						&& ((quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

								|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 && quandoChamou == rodada
										|| quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada))
						&& hashDeCasos.get(121020) == null)) {
					hashDeCasos.put(121020, listaDeCasosPor);
				
			}
			} else if (((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1)
					|| (quandoChamou != rodada && quandoChamouMaior1 != rodada && quandoChamouMaior2 != rodada)
					&& hashDeCasos.get(2130) != null)

			|| ((quandoChamou == rodada || quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada)
					&& ((quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

							|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 && quandoChamou == rodada
									|| quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada))
					&& hashDeCasos.get(121020) != null)) {
				List<TrucoDescription> listaDeCasosExistentesPorGrupos = null;
				if (((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1)
						|| (quandoChamou != rodada && quandoChamouMaior1 != rodada && quandoChamouMaior2 != rodada)
						&& hashDeCasos.get(2130) == null)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(2130);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(2130, listaDeCasosExistentesPorGrupos);
				} else if (((quandoChamou == rodada || quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada)
						&& ((quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

								|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 && quandoChamou == rodada
										|| quandoChamouMaior1 == rodada || quandoChamouMaior2 == rodada))
						&& hashDeCasos.get(121020) != null)) {
					listaDeCasosExistentesPorGrupos = hashDeCasos.get(121020);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(121020, listaDeCasosExistentesPorGrupos);
				}
			}

		}

		return hashDeCasos;
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouTruco(List<TrucoDescription> listaCasos, int rodada) {

		return retornaHashChamarGenerico(listaCasos, "truco", rodada);
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouReTruco(List<TrucoDescription> listaCasos, int rodada) {

		return retornaHashChamarGenerico(listaCasos, "retruco", rodada);
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashQuemChamouValeQuatro(List<TrucoDescription> listaCasos, int rodada) {
		return retornaHashChamarGenerico(listaCasos, "valequatro", rodada);
	}

	/*
	 * ========== COMBINAÇÕES ACEITAR ============================================
	 * 20 - 2 CHAMA, 0 NINGUÉM NEGA 10 - 1 CHAMA, 0 NINGUÉM NEGA 12 - 1 CHAMA, 2
	 * NEGA 201012 <- REPRESENTA O CLUSTER ACEITAR TRUCO
	 * =============================================================================
	 * ========== COMBINAÇÕES NEGAR TRUCO===========================================
	 * 21 - 2 CHAMA, 1 NEGA 30 - NINGUÉM CHAMA -> QUERO UMA OPINIÃO SOBRE ISSO, NÃO
	 * TENHO CERTEZA SE DEVO CONTABILIZAR PARA NEGAR TRUCO AQUELES QUE NÃO FOI
	 * CHAMADO 2130 <- REPRESENTA NEGAR
	 * =============================================================================
	 * ==
	 * 
	 */
	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarGenerico(List<TrucoDescription> listaCasos,
			String tipoAceite, int rodada) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();
		Iterator<TrucoDescription> iteratorListaCasosIterator = listaCasos.iterator();
		while (iteratorListaCasosIterator.hasNext()) {
			TrucoDescription c = iteratorListaCasosIterator.next();
			int quemChamou = 0;
			int quemChamouMaior1 = 0;
			int quemChamouMaior2 = 0;

			int quandoChamou = 0;
			int quandoChamouMaior1 = 0;
			int quandoChamouMaior2 = 0;

			int quemNegou = 0;
			// aqui recebe o atributo objetivo de acordo com o tipo de chamada
			if (tipoAceite.equalsIgnoreCase("truco")) {
				quemChamou = c.getQuemTruco();
				quemChamouMaior1 = c.getQuemRetruco();
				quemChamouMaior2 = c.getQuemValeQuatro();

				quandoChamou = c.getQuandoTruco();
				quandoChamouMaior1 = c.getQuandoRetruco();
				quandoChamouMaior2 = c.getQuandoValeQuatro();

				quemNegou = c.getQuemNegouTruco();
			} else if (tipoAceite.equalsIgnoreCase("retruco")) {
				quemChamou = c.getQuemRetruco();
				quemChamouMaior1 = c.getQuemValeQuatro();

				quandoChamou = c.getQuandoRetruco();
				quandoChamouMaior1 = c.getQuandoValeQuatro();

				quemNegou = c.getQuemNegouTruco();
			} else if (tipoAceite.equalsIgnoreCase("valequatro")) {
				quemChamou = c.getQuemValeQuatro();

				quandoChamou = c.getQuandoValeQuatro();
				quemNegou = c.getQuemNegouTruco();
			}

			if ( ( ((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1) || (quemChamou == 0 && quemChamouMaior1 ==0 && quemChamouMaior2 == 0 )) 
					
							&& hashDeCasos.get(2130) == null)
					
					||  ( ( (quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

									|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 ))
							&& hashDeCasos.get(201012) == null)) {
				List<TrucoDescription> listaDeCasosPorCluster = new ArrayList<TrucoDescription>();
				if ( ( ((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1) || (quemChamou == 0 && quemChamouMaior1 ==0 && quemChamouMaior2 == 0 ) )
						
						&& hashDeCasos.get(2130) == null) ) {
					listaDeCasosPorCluster.add(c);
					hashDeCasos.put(2130, listaDeCasosPorCluster);
				} else if ( ( ( (quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

						|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 ))
				&& hashDeCasos.get(201012) == null)) {
					listaDeCasosPorCluster.add(c);

					hashDeCasos.put(201012, listaDeCasosPorCluster);
				}
			} else if ( ( ( (quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1) || (quemChamou == 0 && quemChamouMaior1 ==0 && quemChamouMaior2 == 0 ))
					
					&& hashDeCasos.get(2130) != null)
			
			||  ( ( (quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

							|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 ))
					&& hashDeCasos.get(201012) != null)) {
				if ( ( ((quemChamou == 2 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou == 1) || (quemChamou == 0 && quemChamouMaior1 ==0 && quemChamouMaior2 == 0 ))
						
						&& hashDeCasos.get(2130) != null) ) {
					List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(2130);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(2130, listaDeCasosExistentesPorGrupos);
				} else if( ( ( (quemChamou != 0 && quemChamouMaior1 == 0 && quemChamouMaior2 == 0 && quemNegou != 1)

						|| (quemChamouMaior1 != 0 || quemChamouMaior2 != 0 ))
				&& hashDeCasos.get(201012) == null)) {
					List<TrucoDescription> listaDeCasosExistentesPorGrupos = hashDeCasos.get(201012);
					listaDeCasosExistentesPorGrupos.add(c);
					hashDeCasos.put(201012, listaDeCasosExistentesPorGrupos);
				}

			}

		}

		return hashDeCasos;
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarTruco(List<TrucoDescription> listaCasos, int rodada) {
		return retornaHashAceitarGenerico(listaCasos, "truco", rodada);

	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarRetruco(List<TrucoDescription> listaCasos, int rodada) {
		return retornaHashAceitarGenerico(listaCasos, "retruco", rodada);
	}

	public HashMap<Integer, List<TrucoDescription>> retornaHashAceitarValeQuatro(List<TrucoDescription> listaCasos, int rodada) {
		HashMap<Integer, List<TrucoDescription>> hashDeCasos = new HashMap<Integer, List<TrucoDescription>>();

		return retornaHashAceitarGenerico(listaCasos, "valequatro", rodada);
	}

	
}
