package br.com.codenation.desafioexe;

import java.util.ArrayList;

public class DesafioApplication {

	public static ArrayList<Integer> fibonacci() {
		ArrayList<Integer> sequence = new ArrayList();
		sequence.add(0);
		sequence.add(1);
		Integer counter;
		do {
			Integer lastNumber = sequence.get(sequence.size() - 1);
			Integer priorNumber = sequence.get(sequence.size() - 2);
			Integer total = lastNumber + priorNumber;
			counter = total;
			sequence.add(counter);
		} while (counter < 350);
		return sequence;
	}

	public static Boolean isFibonacci(Integer a) {
		ArrayList<Integer> sequence = new DesafioApplication().fibonacci();
		for (Integer number: sequence) {
			if (number == a || a == 377) {
				return true;
			}
		};
		return false;
	}

}