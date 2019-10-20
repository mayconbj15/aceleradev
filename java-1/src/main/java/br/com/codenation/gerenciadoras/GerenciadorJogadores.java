package br.com.codenation.gerenciadoras;

import br.com.codenation.entidades.JogadorDeFutebol;
import br.com.codenation.entidades.TimeDeFutebol;

import java.sql.Time;
import java.util.List;

public class GerenciadorJogadores {

    public static JogadorDeFutebol buscarJogador(Long idJogador){
        List<TimeDeFutebol> timesDeFutebol = GerenciadorTime.timesDeFutebol;
        JogadorDeFutebol jogador = null;
        TimeDeFutebol timeDeFutebol = null;

        for(int i = 0; jogador == null && i < timesDeFutebol.size() ; i++){
            timeDeFutebol = timesDeFutebol.get(i);

            jogador = timeDeFutebol.procuraJogador(idJogador);
        }

        return jogador;
    }
}
