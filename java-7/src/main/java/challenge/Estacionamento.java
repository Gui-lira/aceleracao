package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    List<Carro> parked = new ArrayList<>();

    private void colocarNoEstacionamento(Carro carro) {
        if (parked.size() < 10) {
            parked.add(carro);
        } else {
            boolean isThereSpace = false;
            for (Carro carro1: parked) {
                Motorista motorista = carro1.getMotorista();
                if (motorista.getIdade() < 55) {
                    parked.remove(carro1);
                    parked.add(carro);
                    isThereSpace = true;
                    break;
                }
            }
            if (!isThereSpace) {
                throw new EstacionamentoException("teste");
            }
        }
    }

    private boolean validateMotorista (Motorista motorista) {
        if (motorista.getHabilitacao() == null || motorista.getHabilitacao().isEmpty()) {
            throw new NullPointerException();
        }
        if (motorista.getNome() == null || motorista.getNome().isEmpty()) {
            throw new NullPointerException();
        }
        if (motorista.getIdade() < 18) {
            throw new EstacionamentoException("");
        }
        if (motorista.getPontos() > 20) {
            throw new EstacionamentoException("");
        }
        return true;
    }

    private void validateCar (Carro carro) {
        if (carro.getPlaca().isEmpty() || carro.getCor().name().isEmpty()) {
            throw new NullPointerException();
        }
    }

    public void estacionar(Carro carro) {
        if (carro.getMotorista() == null) {
            throw new EstacionamentoException("test");
        }
        Motorista motorista = carro.getMotorista();
          validateMotorista(motorista);
          validateCar(carro);
          colocarNoEstacionamento(carro);
    }

    public int carrosEstacionados() {
        return parked.size();
    }

    public boolean carroEstacionado(Carro carro) {
        for (Carro carro1: parked) {
            if (carro1.getMotorista().getNome() == carro.getMotorista().getNome()) return true;
        }
        return false;
    }
}
