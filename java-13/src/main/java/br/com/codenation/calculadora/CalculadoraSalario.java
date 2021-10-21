package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		System.out.print(salarioBase);
		double inssDiscount = calcularInss(salarioBase);
		double withInss = salarioBase - inssDiscount;
		double taxDiscount = calculateIRFS(withInss);
		double newSalary = withInss - taxDiscount;
		if (newSalary < 1039) return 0;
		return Math.round(newSalary);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if (salarioBase <= 1500) {
			return salarioBase * 0.08;
		}
		if (salarioBase <= 4000) {
			return salarioBase * 0.09;
		}
		return salarioBase * 0.11;
	}

	private double calculateIRFS(double baseSalary) {
		if (baseSalary <= 3000) return 0.0;
		if (baseSalary <= 6000) return baseSalary * 0.075;
		return baseSalary * 0.15;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/