package utilProbabilidadeSorteio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cbr.cbrDescriptions.TrucoDescription;
import uteisRetornaQuantitativosSaldosOuProbabilidade.EspacoDePossibilidadesModelo;

public class SorteioProbabilidade {
	
	public Integer retornaSorteadoComProbabilidade(HashMap<Integer, List<TrucoDescription>> hash,
			Set<Integer> listaSemRepetir) {
 int kSelecionado =0;
		try {
			Iterator<Integer> iteratorK = listaSemRepetir.iterator();
			HashMap<Integer, Integer> hashQuantidadeDeCasosPorKey = new HashMap<Integer, Integer>();
			while (iteratorK.hasNext()) {
				int k = iteratorK.next();
				if (k != 0) {
					List<TrucoDescription> casosDoGrupoAtual = hash.get(k);
					hashQuantidadeDeCasosPorKey.put(k, casosDoGrupoAtual.size());
					
				}
			}
				Set<Integer> quantidadeDeCasosSet = hashQuantidadeDeCasosPorKey.keySet();
				Iterator iteratorKeys = quantidadeDeCasosSet.iterator();
				Double somaTotal = 0.0;
				while (iteratorKeys.hasNext()) {
					somaTotal += hashQuantidadeDeCasosPorKey.get(iteratorKeys.next());
				}
				
				Iterator iteratorKeysParaProbabilidade = quantidadeDeCasosSet.iterator();
				HashMap<Integer, Double> hashProbabilidadesPorKey = new HashMap<Integer, Double>();
				
				while (iteratorKeysParaProbabilidade.hasNext()) {
					int kAtual = (int) iteratorKeysParaProbabilidade.next();
					Double probabilidadeDoGrupoOcorrer = (double)  hashQuantidadeDeCasosPorKey.get(kAtual)/somaTotal;
					hashProbabilidadesPorKey.put(kAtual, probabilidadeDoGrupoOcorrer);
					
				}
				
				Iterator iteratorKeysParaAlocacaoDeValores = quantidadeDeCasosSet.iterator();
				HashMap<Integer, EspacoDePossibilidadesModelo> hashProporcoesPorKey = new HashMap<Integer, EspacoDePossibilidadesModelo>();
				Double espacoJaAlocado = 0.0;
				while(iteratorKeysParaAlocacaoDeValores.hasNext()) {
					int kAtual = (int) iteratorKeysParaAlocacaoDeValores.next();
					Double probabilidadeParaOgrupoAtual;
					probabilidadeParaOgrupoAtual = (double)hashProbabilidadesPorKey.get(kAtual);
					Double quantidadeDeCasasOcupadasPeloGrupoAtual;
					quantidadeDeCasasOcupadasPeloGrupoAtual= (double)probabilidadeParaOgrupoAtual * 100;
					
					EspacoDePossibilidadesModelo espacoDePossibilidades = new EspacoDePossibilidadesModelo();
					espacoDePossibilidades.setValorInicial(espacoJaAlocado);
					espacoDePossibilidades.setValorFinal(espacoJaAlocado+quantidadeDeCasasOcupadasPeloGrupoAtual);
					hashProporcoesPorKey.put(kAtual, espacoDePossibilidades);
					espacoJaAlocado = espacoDePossibilidades.getValorFinal();
					
				}
				
				
			Random random = new Random();
			double randomValue =(double)  0 + (99 - 0) * random.nextDouble();
			
			
			
			Iterator iteratorKeysParaEscolherOk = quantidadeDeCasosSet.iterator();
			
			while(iteratorKeysParaEscolherOk.hasNext()) {
				int kActual = (int) iteratorKeysParaEscolherOk.next();
				Double valorInicialKatual;
				valorInicialKatual = (double)hashProporcoesPorKey.get(kActual).getValorInicial();
				Double valorFinalKatual;
				valorFinalKatual = (double) hashProporcoesPorKey.get(kActual).getValorFinal();
				if(randomValue >= valorInicialKatual && randomValue<= valorFinalKatual)
					kSelecionado = kActual;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return kSelecionado;
	}

}
