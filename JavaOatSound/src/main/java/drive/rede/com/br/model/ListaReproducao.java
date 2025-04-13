package drive.rede.com.br.model;

import java.util.ArrayList;
import java.util.List;

public class ListaReproducao {
    private String nome;
    private List<Musica> musicas;
    private int atual = 0;

    public ListaReproducao(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public Musica getMusicaAtual() {
        if (musicas.isEmpty()) return null;
        return musicas.get(atual);
    }

    public Musica proximaMusica() {
        if (atual < musicas.size() - 1) atual++;
        return getMusicaAtual();
    }

    public Musica musicaAnterior() {
        if (atual > 0) atual--;
        return getMusicaAtual();
    }

    public void reiniciar() {
        atual = 0;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }
}
