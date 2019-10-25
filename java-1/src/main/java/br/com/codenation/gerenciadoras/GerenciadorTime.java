package br.com.codenation.gerenciadoras;

import br.com.codenation.entidades.JogadorDeFutebol;
import br.com.codenation.entidades.TimeDeFutebol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciadorTime {
    public static List<TimeDeFutebol> timesDeFutebol = new ArrayList<>();

    public static boolean existeTime(Long id){
        boolean existeTime = false;

        for(int i=0; existeTime == false && i < timesDeFutebol.size(); i++){
            if(timesDeFutebol.get(i).getId().equals(id))
                existeTime = true;
        }

        return existeTime;
    }

    public static TimeDeFutebol buscarTime(Long idTime) {
        TimeDeFutebol timeDeFutebol = null;

        for(int i = 0; timeDeFutebol == null && i < timesDeFutebol.size(); i++){
            if(timesDeFutebol.get(i).getId().equals(idTime))
                timeDeFutebol = timesDeFutebol.get(i);
        }

        return timeDeFutebol;
    }

    public static Long buscarPosicaoTime(TimeDeFutebol timeDeFutebol){
        return new Long(timesDeFutebol.indexOf(timeDeFutebol));
    }

    public static List<Long> buscarTimes(){
        List<Long> idTimesDeFutebol = new ArrayList<>();

        for(TimeDeFutebol time : timesDeFutebol){
            idTimesDeFutebol.add(time.getId());
        }

        return idTimesDeFutebol;
    }

    public static List<JogadorDeFutebol> buscarTopJogadores(Integer top){
        List<JogadorDeFutebol> topJogadores = new ArrayList<>();

        for(TimeDeFutebol time: timesDeFutebol){
        //    Collections.sort(time.getJogadores());
            for(JogadorDeFutebol jogador : time.getJogadores()){
                topJogadores.add(jogador);
            }
        }

        Collections.sort(topJogadores);

        return topJogadores.subList(0,top);
    }
}
