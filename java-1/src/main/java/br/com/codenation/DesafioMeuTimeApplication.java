package br.com.codenation;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	List<club> clubs = new ArrayList<>();
	List<Player> players = new ArrayList<>();
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		club newClub = new club();
		for (club insideClub: clubs) {
			if (insideClub.id == id) {
				throw new IdentificadorUtilizadoException();
			}
		}
		newClub.id = id;
		newClub.nome = nome;
		newClub.corUniformePrincipal = corUniformePrincipal;
		newClub.dataCriacao = dataCriacao;
		newClub.corUniformeSecundario = corUniformeSecundario;
		clubs.add(newClub);
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (!clubs.stream().map(club::getID).collect(toList()).contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		for (Player player: players) {
			if (player.id == id) {
				throw new IdentificadorUtilizadoException();
			}
		}
		Player player = new Player();
		player.id = id;
		player.idTime = idTime;
		player.nome = nome;
		player.dataNascimento = dataNascimento;
		player.nivelHabilidade = nivelHabilidade;
		player.salario = salario;
		players.add(player);
	}

	public void definirCapitao(Long idJogador) {
		boolean theresIsPlayer = false;
		for (Player player: players) {
			if (player.id == idJogador) {
				player.captao = true;
				theresIsPlayer = true;
			}
		}
		if (theresIsPlayer) {
			for (Player player: players) {
				if (player.id != idJogador && player.captao) {
					player.captao = false;
				}
			}
		} else {
			throw new JogadorNaoEncontradoException();
		}
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		if (!clubs.stream().map(club::getID).collect(toList()).contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		for (Player player: players) {
			if (player.captao  && player.idTime == idTime) {
				return player.id;
			}
		}
		throw new CapitaoNaoInformadoException();
	}

	public String buscarNomeJogador(Long idJogador) {
		for (Player player: players) {
			if (player.id == idJogador) return player.nome;
		}
		throw  new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) {
		for (club club: clubs) {
			if (club.id == idTime) return club.nome;
		}
		throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		ArrayList<Long> returnList = new ArrayList<>();
		for (Player player: players) {
			if (player.idTime == idTime) {
				returnList.add(player.id);
			}
		}
		if (returnList.size() == 0) {
			throw new TimeNaoEncontradoException();
		}
		return returnList;
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		List<Player> filtered = new ArrayList<>();
		for (Player player: players) {
			if (player.idTime == idTime) {
				filtered.add(player);
			}
		}
		if (filtered.size() == 0) {
			throw new TimeNaoEncontradoException();
		}
		return Collections.max(filtered, Comparator.comparing(p -> p.nivelHabilidade)).id;
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		List<Player> filtered = new ArrayList<>();
		for (Player player: players) {
			if (player.idTime == idTime) {
				filtered.add(player);
			}
		}
		if (filtered.size() == 0) {
			throw  new TimeNaoEncontradoException();
		}
		return Collections.min(filtered, Comparator.comparing(p -> p.dataNascimento)).id;
	}

	public List<Long> buscarTimes() {
		List<club> ordered = clubs;
		ordered.sort(Comparator.comparing(c -> c.id));
		return ordered.stream().map(club::getID).collect(toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		List<Player> filtered = new ArrayList<>();
		for (Player player: players) {
			if (player.idTime == idTime) {
				filtered.add(player);
			}
		}
		if (filtered.size() == 0) {
			throw new TimeNaoEncontradoException();
		}
		return Collections.max(filtered, Comparator.comparing(p -> p.salario)).id;
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		for (Player player: players) {
			if (player.id == idJogador) return player.salario;
		}
		throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Player> newPlayers = players;
		newPlayers.sort(Collections.reverseOrder(Comparator.comparing(p -> p.nivelHabilidade)));
		List<Long> mapedPlayer = newPlayers.stream().map(Player::getId).collect(toList());
		if (mapedPlayer.size() == 0) return mapedPlayer;
		return mapedPlayer.subList(0, top);
	}

}
