package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.entidades.JogadorDeFutebol;
import br.com.codenation.entidades.TimeDeFutebol;
import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.gerenciadoras.GerenciadorJogadores;
import br.com.codenation.gerenciadoras.GerenciadorTime;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	//aqui ser uma list de long que vai ser a indexação da classe que irá manter a lista de jogadores

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		if(GerenciadorTime.existeTime(id)){
			throw new IdentificadorUtilizadoException();
		}else{
			GerenciadorTime.timesDeFutebol.add(new TimeDeFutebol(
					id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario
			));
		}

	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Long posicaoDoTime;

		if(!GerenciadorTime.existeTime(idTime)){
			throw new TimeNaoEncontradoException();
		} else if(GerenciadorJogadores.buscarJogador(id) != null){
			throw new IdentificadorUtilizadoException();
		}

		posicaoDoTime = GerenciadorTime.buscarPosicaoTime(GerenciadorTime.buscarTime(idTime));

		GerenciadorTime.timesDeFutebol.get(posicaoDoTime.intValue()).adicionarJogador(new JogadorDeFutebol(
				id, idTime, nome, dataNascimento, nivelHabilidade, salario
		));

	}


	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		/*timesDeFutebol.values().forEach(timeDeFutebol -> {
			if(timeDeFutebol.getJogador(idJogador) != null){
				timeDeFutebol.setCapitao(timeDeFutebol.getJogador(idJogador));
			}
		});*/
		
		
		//timesDeFutebol.values().stream().filter(times -> times.procuraJogador(idJogador).equals(idJogador)).findAny().orElseThrow(() -> new JogadorNaoEncontradoException());
		
		JogadorDeFutebol jogador = null;
		TimeDeFutebol timeDeFutebol = null;

		jogador = GerenciadorJogadores.buscarJogador(idJogador);

		if(jogador == null){
			throw new JogadorNaoEncontradoException();

		}else{
			timeDeFutebol = GerenciadorTime.buscarTime(jogador.getIdTime());
			timeDeFutebol.setCapitao(jogador);
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		TimeDeFutebol timeDeFutebol = GerenciadorTime.buscarTime(idTime);
		JogadorDeFutebol jogador = null;
		Long idCapitao = null;

		if(timeDeFutebol == null){
			throw new TimeNaoEncontradoException();
		}else{
			jogador = timeDeFutebol.buscarCapitao();
			idCapitao = jogador.getId();
		}

		return idCapitao;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		JogadorDeFutebol jogadorDeFutebol = null;
		String nome = null;

		jogadorDeFutebol = GerenciadorJogadores.buscarJogador(idJogador);

		if(jogadorDeFutebol == null)
			throw new JogadorNaoEncontradoException();
		else
			nome = jogadorDeFutebol.getNome();

		return nome;
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		TimeDeFutebol timeDeFutebol = null;
		String nome = null;
		
		timeDeFutebol = GerenciadorTime.buscarTime(idTime);
		
		if(timeDeFutebol == null)
			throw new TimeNaoEncontradoException();
		else
			nome = timeDeFutebol.getNome();

		return nome;
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadores = null;
		TimeDeFutebol timeDeFutebol = null;

		timeDeFutebol = GerenciadorTime.buscarTime(idTime);
		
		if(jogadores == null)
			throw new TimeNaoEncontradoException();
		else
			jogadores = timeDeFutebol.getJogadoresWithId();

		return  jogadores;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Long idMelhorJogador = null;
		TimeDeFutebol timeDeFutebol = null;

		timeDeFutebol = GerenciadorTime.buscarTime(idTime);

		if(timeDeFutebol == null)
			throw new TimeNaoEncontradoException();
		else
			idMelhorJogador = timeDeFutebol.buscarMelhorJogador().getId();

		return  idMelhorJogador;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Long idJogadorMaisVelho = null;
		TimeDeFutebol timeDeFutebol = null;

		GerenciadorTime.buscarTime(idTime);

		if(timeDeFutebol == null)
			throw new TimeNaoEncontradoException();
		else
			idJogadorMaisVelho = timeDeFutebol.buscarJogadorMaisVelho().getId();

		return idJogadorMaisVelho;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return GerenciadorTime.buscarTimes();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		TimeDeFutebol timeDeFutebol = GerenciadorTime.buscarTime(idTime);
		JogadorDeFutebol jogador = null;
		Long idJogadorMaiorSalario = null;

		if(timeDeFutebol == null)
			throw new TimeNaoEncontradoException();
		else{
			jogador = timeDeFutebol.buscarJogadorMaiorSalario();

			idJogadorMaiorSalario = jogador.getId();
		}

		return idJogadorMaiorSalario;
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		JogadorDeFutebol jogador = GerenciadorJogadores.buscarJogador(idJogador);

		if(jogador == null)
			throw new JogadorNaoEncontradoException();

		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<JogadorDeFutebol> topJogadores;
		List<Long> idTopJogadores = new ArrayList<>();

		topJogadores = GerenciadorTime.buscarTopJogadores(top);

		if(topJogadores != null){
			for(JogadorDeFutebol jogador : topJogadores){
				idTopJogadores.add(jogador.getId());
			}
		}

		return idTopJogadores;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		String corUniformeTimeDeFora;

		TimeDeFutebol timeCasa = GerenciadorTime.buscarTime(timeDaCasa);
		TimeDeFutebol timeFora = GerenciadorTime.buscarTime(timeDeFora);

		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
			corUniformeTimeDeFora = timeFora.getCorUniformeSecundario();
		else
			corUniformeTimeDeFora = timeFora.getCorUniformePrincipal();

		return corUniformeTimeDeFora;
	}

}
