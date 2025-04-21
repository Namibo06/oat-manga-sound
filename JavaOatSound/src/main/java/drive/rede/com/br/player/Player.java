// Player.java
package drive.rede.com.br.player;

import drive.rede.com.br.model.ListaReproducao;
import drive.rede.com.br.model.Musica;

import javax.sound.sampled.*;
import java.io.File;

public class Player {
    private ListaReproducao lista;
    private Clip clip;
    private AudioInputStream stream;
    private Long frameAtual;
    private String status = "stop";

    public Player(ListaReproducao lista) {
        this.lista = lista;
    }

    public void tocar() throws Exception {
        Musica m = lista.getMusicaAtual();
        if (m == null) {
            System.out.println("Nenhuma música disponível na lista.");
            return;
        }
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

        File f = new File(m.getCaminho());
        stream = AudioSystem.getAudioInputStream(f);
        clip = AudioSystem.getClip();
        clip.open(stream);
        clip.start();
        status = "play";
        System.out.println(" Tocando: " + m);
    }

    public void pausar() {
        if (clip != null && status.equals("play")) {
            frameAtual = clip.getMicrosecondPosition();
            clip.stop();
            status = "pause";
            System.out.println(" Música pausada.");
        }
    }

    public void continuar() throws Exception {
        if (clip != null && status.equals("pause")) {
            clip.setMicrosecondPosition(frameAtual);
            clip.start();
            status = "play";
            System.out.println(" Continuando música.");
        }
    }

    public void parar() {
        if (clip != null) {
            clip.stop();
            clip.close();
            status = "stop";
            System.out.println(" Música parada.");
        }
    }

    public void proxima() throws Exception {
        parar();
        lista.proximaMusica();
        tocar();
    }

    public void anterior() throws Exception {
        if (clip != null) {
            long posUS = clip.getMicrosecondPosition();
            if (posUS > 10_000_000L) {
                clip.setMicrosecondPosition(0);
                clip.start();
                status = "play";
                System.out.println(" Música reiniciada.");
                return;
            }
        }
        parar();
        lista.musicaAnterior();
        tocar();
    }

    public void reiniciar() throws Exception {
        parar();
        lista.reiniciar();
        tocar();
    }
}
