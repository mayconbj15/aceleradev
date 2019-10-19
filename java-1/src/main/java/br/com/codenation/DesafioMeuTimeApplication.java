package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.Entidades.JogadorDeFutebol;
import br.com.codenation.Entidades.TimeDeFutebol;
import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<TimeDeFutebol> timesDeFutebol = new ArrayList<>();
	//aqui ser uma list de long que vai ser a indexação da classe que irá manter a lista de jogadores
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(!existeTime(id)){
			throw new IdentificadorUtilizadoException();
		}else{
			timesDeFutebol.add(new TimeDeFutebol(
					id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario
			));
		}

	}

	private boolean existeTime(Long id){
		boolean existeTime = false;
		for(int i=0; i < timesDeFutebol.size() && existeTime == false; i++){
			if(timesDeFutebol.get(i).getId().equals(id));
				existeTime = true;
		}

		return existeTime;
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		int idTimeInt = idTime.intValue();

		if(!existeTime(idTime)){
			throw new TimeNaoEncontradoException();
		}

		timesDeFutebol.get(idTimeInt).adicionarJogador(new JogadorDeFutebol(
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

		for(int i = 0; i < timesDeFutebol.size() && jogador == null; i++){
			List<JogadorDeFutebol> jogadores = timesDeFutebol.get(i).getJogadores();
			
			for(int j=0; j < jogadores.size(); j++){
				if(jogadores.get(i).getId().equals(idJogador)){
					jogador = jogadores.get(i);
					timeDeFutebol = timesDeFutebol.get(i);
				}
			}
		}

		if(jogador != null){
			timeDeFutebol.setCapitao(jogador);
		}else{
			throw new JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		TimeDeFutebol timeDeFutebol = buscarTime(idTime);
		
		if(timeDeFutebol == null){
			throw new TimeNaoEncontradoException();
		}

		JogadorDeFutebol jogador = timeDeFutebol.buscarCapitao();

		return jogador.getId();
	}

	public TimeDeFutebol buscarTime(Long idTime) {
		TimeDeFutebol timeDeFutebol = null;

		for(int i = 0; i < timesDeFutebol.size() && timeDeFutebol == null; i++){
			if(timesDeFutebol.get(i).getId().equals(idTime))
				timeDeFutebol = timesDeFutebol.get(i);
		}

		return timeDeFutebol;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		JogadorDeFutebol jogadorDeFutebol = null;
		String nome = null;

		for(int i=0 ; jogadorDeFutebol == null && i < timesDeFutebol.size(); i++){
			jogadorDeFutebol = timesDeFutebol.get(i).procuraJogador(idJogador);
		}

		if(jogadorDeFutebol != null)
			nome = jogadorDeFutebol.getNome();
		else
			throw new JogadorNaoEncontradoException();

		return nome;
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		String nome = null;
		
		for(int i=0; i < timesDeFutebol.size(); i++){
			if(timesDeFutebol.get(i).getId().equals(idTime))
				nome = timesDeFutebol.get(i).getNome();

		}
		
		if(nome == null)
			throw new TimeNaoEncontradoException();
		else
			return nome;
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadores = null;

		for(int i=0; i < timesDeFutebol.size(); i++){
			if(timesDeFutebol.get(i).getId().equals(idTime))
				jogadores = timesDeFutebol.get(i).getJogadoresWithId();
		}
		
		if(jogadores == null)
			throw new TimeNaoEncontradoException();
		else
			return jogadores;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Long idMelhorJogador = null;
		Integer melhorJogador = 0;

		for(int i=0; i < timesDeFutebol.size(); i++){

		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}

}
