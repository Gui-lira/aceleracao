package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player {
    public long id;
    public long idTime;
    public String nome;
    public LocalDate dataNascimento;
    public int nivelHabilidade;
    public BigDecimal salario;
    public boolean captao = false;
    public long getId() {
        return id;
    }
    public BigDecimal getSalario() {
        return salario;
    }
}
