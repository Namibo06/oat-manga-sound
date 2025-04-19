package drive.rede.com.br.model;
import drive.rede.com.br.collections.ListaEncadeada;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class ListaReproducao {
    private String nome;
    private List<Musica> musicas;
    private int atual = 0;
    private ListaEncadeada lista;

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

    public boolean removerMusica(int posicao){
        if (!(musicas.isEmpty()) && posicao > musicas.size()){
            Scanner scan = new Scanner(System.in);
            posicao = scan.nextInt();
            musicas.remove(posicao);
            scan.close();
            return true;
        }
        else{ return false;}
    }

    public  boolean IsVazia(){
        if (musicas.isEmpty()){
            return false;
        }
        else {return true;}
    }

    public int tamanho(){
        return musicas.size();
    }

    public void criarListaApartirDe(ListaReproducao novaLista){
        Scanner scan = new Scanner(System.in);
        String nomeDanovaLista = scan.next();
        novaLista = new ListaReproducao(nomeDanovaLista);
        scan.close();
        System.out.println("A nova lista "+ nomeDanovaLista+ " foi criada com ssucesso!");
    }

    public int posicaoDa(Musica musica){
        return lista.indexOf(musica);
    }

    public boolean contemMusica(Musica musica){
        if (musicas.contains(musica)){
            return true;
        }
        else { return false;}
    }

    public boolean limparLista() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Você deseja limpar a lista de músicas? \n Digite: \n 1 - Sim \n 0 - Não ");
        int resposta = scan.nextInt();

        if (resposta == 1) {
            musicas.clear();
            return true;
        } else {
            return false;
        }
    }

    public Musica obterMusica(int posicao){
        System.out.println("Digite a posição da musica: ");
        Scanner scan = new Scanner(System.in);
        posicao = scan.nextInt();
        scan.close();
        return musicas.get(posicao);

    }

    public List<Musica> getMusicas () {
        return musicas;
    }

    public String getNome () {
        return nome;
    }
}