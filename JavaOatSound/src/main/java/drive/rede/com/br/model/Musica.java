package br.com.mangarosa.model;

public class Musica {
    private String nome;
    private String artista;
    private String caminho;

    public Musica(String nome, String artista, String caminho) {
        this.nome = nome;
        this.artista = artista;
        this.caminho = caminho;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public String getCaminho() {
        return caminho;
    }

    @Override
    public String toString() {
        return nome + " - " + artista;
    }
}
