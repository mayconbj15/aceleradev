package br.com.codenation.Entidades;

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

    public TimeDeFutebol(long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
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

    public Long getMelhorJogador(){
        Integer maiorHabilidade = 0;
        Long melhorJogador = null;

        for(int i=0; i < jogadores.size(); i++){
            JogadorDeFutebol jogadorDeFutebol = jogadores.get(i);
            if(jogadorDeFutebol.getNivelHabilidade() > maiorHabilidade){
                maiorHabilidade = jogadorDeFutebol.getNivelHabilidade();
                melhorJogador = jogadorDeFutebol.getId();
            }
                
        }

        return melhorJogador;
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
        List<JogadorDeFutebol> jogadores = getJogadores();
        List<Long> idJogadores = new ArrayList<>();

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
}
