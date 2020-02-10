package similarityTests;

public class testesRetornos {

	public static void main(String[] args) {
		double valorAvaliado =16.0 - 5.882353;
		double valorAvaliadoNormalizado = Math.abs(valorAvaliado);
		double valorElevadoAoQuadrado = (double) Math.pow(valorAvaliadoNormalizado,2);
		////System.out.println("resultado elevado ao quadrado: "+ valorElevadoAoQuadrado);
		double retorno =  valorElevadoAoQuadrado/100;
		////System.out.println("valor do retorno: "+ retorno +" valor absoluto: "+valorAvaliadoNormalizado);
	
		double retornoInterval = 1 - ((double) Math.abs(valorAvaliado)/16);
		////System.out.println("retorno interval sem dividir pelo intervalo "+ retornoInterval);
	}

}
