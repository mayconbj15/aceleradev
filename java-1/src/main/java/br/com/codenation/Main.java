import br.com.codenation.DesafioMeuTimeApplication;
import br.com.codenation.entidades.TimeDeFutebol;
import br.com.codenation.gerenciadoras.GerenciadorTime;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();

        TimeDeFutebol timeDeFutebol = GerenciadorTime.buscarTime(new Long(1));

        desafioMeuTimeApplication.incluirTime(new Long(1), "flamengo", LocalDate.now(), "vermelho", "branco");
        desafioMeuTimeApplication.incluirTime(new Long(2), "cruzeiro", LocalDate.now(), "azul", "vermelho");
        desafioMeuTimeApplication.incluirTime(new Long(3), "atletico", LocalDate.now(), "preto", "rosa");
        System.out.println(GerenciadorTime.buscarTime(new Long(1)));
        System.out.println(GerenciadorTime.buscarTime(new Long(2)));
        System.out.println(GerenciadorTime.buscarTime(new Long(3)));
        System.out.println(GerenciadorTime.buscarTime(new Long(5)));
        desafioMeuTimeApplication.incluirTime(new Long(3), "atletico", LocalDate.now(), "preto", "rosa");
    }
}
