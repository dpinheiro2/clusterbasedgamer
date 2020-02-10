package crazzyTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTests {

	public static void main(String[] args) {
		Random random = new Random();
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(12);
		lista.add(15);
		////System.out.println("tamanho da lista: "+ (lista.size()-1));
		int valor = random.nextInt(2);
		////System.out.println(valor);
	}

}
