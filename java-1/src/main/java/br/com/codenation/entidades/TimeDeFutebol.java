package br.com.codenation.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;

public class TimeDeFutebol {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;

    private JogadorDeFutebol capitao;

    private List<JogadorDeFutebol> jogadores;

    public TimeDeFutebol(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;

        this.capitao = null;
        this.jogadores = new ArrayList<>();

        //fazer uma hash de jogadores
    }

    public void adicionarJogador(JogadorDeFutebol novoJogador){
        if(existeJogador(novoJogador.getId())){
            throw new IdentificadorUtilizadoException();
        }

        jogadores.add(novoJogador);
    }

    public boolean existeJogador(Long id){
        boolean existeJogador = false;

        for(int i=0; existeJogador == false && i < jogadores.size(); i++){
            if(jogadores.get(i).getId().equals(id))
                existeJogador = true;
        }

        return existeJogador;
    }

    public JogadorDeFutebol procuraJogador(Long idJogador){
        JogadorDeFutebol jogadorDeFutebol = null;

        //jogadorDeFutebol = jogadores.values().stream().filter(j -> j.getId().equals(idJogador)).findFirst().orElseThrow(() -> new RuntimeException());

        /*jogadores.values().forEach(jogador -> {
            if(jogador.getId() == idJogador)
                jogadorDeFutebol.set(jogador);
        });*/

        for(int i=0; jogadorDeFutebol == null && i < jogadores.size(); i++){
            if(jogadores.get(i).getId().equals(idJogador))
                jogadorDeFutebol = jogadores.get(i);
        }

        return jogadorDeFutebol;
    }

    public JogadorDeFutebol buscarCapitao(){
        if(capitao == null){
            throw new CapitaoNaoInformadoException();
        }

        return this.capitao;
    }

    public JogadorDeFutebol buscarMelhorJogador(){
        JogadorDeFutebol melhorJogador = null;

        if(!jogadores.isEmpty()){
            melhorJogador = jogadores.get(0);

            for(JogadorDeFutebol jogador : jogadores){
                if(jogador.getNivelHabilidade().compareTo(melhorJogador.getNivelHabilidade()) > 0)
                    melhorJogador = jogador;
            }
        }

        return melhorJogador;
    }

    public JogadorDeFutebol buscarJogadorMaisVelho(){
        JogadorDeFutebol jogadorMaisVelho = null;

        if(!jogadores.isEmpty()){
            LocalDate maiorDataNascimento = jogadores.get(0).getDataNascimento();

            for(JogadorDeFutebol jogador : jogadores){
                if(jogador.getDataNascimento().compareTo(maiorDataNascimento) > 0) {
                    maiorDataNascimento = jogador.getDataNascimento();
                    jogadorMaisVelho = jogador;
                }
            }
        }


        return jogadorMaisVelho;
    }

    public JogadorDeFutebol buscarJogadorMaiorSalario(){
        JogadorDeFutebol jogadorMaiorSalario = null;

        if(!jogadores.isEmpty()){
            jogadorMaiorSalario = jogadores.get(0);

            for(JogadorDeFutebol jogador: jogadores){
                if(jogador.getSalario().compareTo(jogadorMaiorSalario.getSalario()) > 0) {
                    jogadorMaiorSalario = jogador;
                }
                else if(jogador.getSalario().compareTo(jogadorMaiorSalario.getSalario()) == 0) {
                    if (jogador.getId().compareTo(jogadorMaiorSalario.getId()) < 0) {
                        jogadorMaiorSalario = jogador;
                    }
                }
            }
        }

        return jogadorMaiorSalario;
    }



    // region Getter an Setter
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public List<JogadorDeFutebol> getJogadores() {
        return jogadores;
    }

    public List<Long> getJogadoresWithId() {
        List<Long> idJogadores = new ArrayList<>();

        for(JogadorDeFutebol jogador : jogadores){
            idJogadores.add(jogador.getId());
        }

        return idJogadores;
    }

    public JogadorDeFutebol getJogador(Long idJogador){
        return jogadores.get(idJogador.intValue());
    }

    public void setJogadores(List<JogadorDeFutebol> jogadores) {
        this.jogadores = jogadores;
    }

    public JogadorDeFutebol getCapitao() {
        return capitao;
    }

    public void setCapitao(JogadorDeFutebol capitao) {
        this.capitao = capitao;
    }

    @Override
    public String toString() {
        return "TimeDeFutebol{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", corUniformePrincipal='" + corUniformePrincipal + '\'' +
                ", corUniformeSecundario='" + corUniformeSecundario + '\'' +
                ", capitao=" + capitao +
                ", jogadores=" + jogadores +
                '}';
    }
}
