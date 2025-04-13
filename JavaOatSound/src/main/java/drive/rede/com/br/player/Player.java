package br.com.mangarosa.player;

import br.com.mangarosa.model.Musica;
import br.com.mangarosa.model.ListaReproducao;

import javax.sound.sampled.*;
import java.io.File;

public class Player {
    private ListaReproducao lista;
    private Clip clip;
    private AudioInputStream audioInputStream;
    private Long frameAtual;
    private String status = "stop";

    public Player(ListaReproducao lista) {
        this.lista = lista;
    }

    public void tocar() throws Exception {
        Musica musica = lista.getMusicaAtual();
        if (musica == null) {
            System.out.println("Nenhuma música disponível na lista.");
            return;
        }

        if (clip != null && clip.isRunning()) clip.stop();

        File file = new File(musica.getCaminho());
        audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = "play";
        System.out.println("🎶 Tocando: " + musica);
    }

    public void pausar() {
        if (clip != null && status.equals("play")) {
            frameAtual = clip.getMicrosecondPosition();
            clip.stop();
            status = "pause";
            System.out.println("⏸ Música pausada.");
        }
    }

    public void continuar() throws Exception {
        if (clip != null && status.equals("pause")) {
            clip.setMicrosecondPosition(frameAtual);
            clip.start();
            status = "play";
            System.out.println("▶ Continuando música.");
        }
    }

    public void parar() {
        if (clip != null) {
            clip.stop();
            clip.close();
            status = "stop";
            System.out.println("⏹ Música parada.");
        }
    }

    public void proxima() throws Exception {
        parar();
        lista.proximaMusica();
        tocar();
    }

    public void anterior() throws Exception {
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
