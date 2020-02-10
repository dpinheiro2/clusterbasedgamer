package hashSet;

import java.util.HashMap;
import java.util.Set;

public class teste {

	public static void main(String[] args) {
		
		HashMap<Integer, String> teste = new HashMap<Integer, String>();
		teste.put(10, "primeiro");
		teste.put(1,"segundo");
		
		Set<Integer> setTeste = teste.keySet();
		setTeste.forEach(t ->{
			////System.out.println(t);
		});
	}

}
