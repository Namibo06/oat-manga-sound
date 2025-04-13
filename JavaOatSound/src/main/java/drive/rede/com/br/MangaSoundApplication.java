package drive.rede.com.br;


import drive.rede.com.br.model.ListaReproducao;
import drive.rede.com.br.model.Musica;
import drive.rede.com.br.player.Player;

import java.util.Scanner;

public class MangaSoundApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaReproducao lista = new ListaReproducao("Minhas Músicas");
        Player player = new Player(lista);

        while (true) {
            System.out.println("\n🎵 MangaSound - Menu Principal");
            System.out.println("1. Adicionar Música");
            System.out.println("2. Tocar Música");
            System.out.println("3. Pausar");
            System.out.println("4. Continuar");
            System.out.println("5. Próxima Música");
            System.out.println("6. Música Anterior");
            System.out.println("7. Reiniciar Lista");
            System.out.println("8. Parar");
            System.out.println("9. Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome da música: ");
                        String nome = sc.nextLine();
                        System.out.print("Artista: ");
                        String artista = sc.nextLine();
                        System.out.print("Caminho do arquivo .wav: ");
                        String caminho = sc.nextLine();
                        lista.adicionarMusica(new Musica(nome, artista, caminho));
                        System.out.println("✅ Música adicionada com sucesso!");
                        break;
                    case 2:
                        player.tocar();
                        break;
                    case 3:
                        player.pausar();
                        break;
                    case 4:
                        player.continuar();
                        break;
                    case 5:
                        player.proxima();
                        break;
                    case 6:
                        player.anterior();
                        break;
                    case 7:
                        player.reiniciar();
                        break;
                    case 8:
                        player.parar();
                        break;
                    case 9:
                        player.parar();
                        System.out.println("👋 Encerrando o MangaSound.");
                        return;
                    default:
                        System.out.println("❌ Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("⚠ Erro ao executar ação: " + e.getMessage());
            }
        }
    }
}
