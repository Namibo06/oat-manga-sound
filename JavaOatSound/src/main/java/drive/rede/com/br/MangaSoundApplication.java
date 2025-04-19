package drive.rede.com.br;


import drive.rede.com.br.model.ListaReproducao;
import drive.rede.com.br.model.Musica;
import drive.rede.com.br.player.Player;

import java.util.Scanner;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class MangaSoundApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Inicialmente, cria uma lista padrão
        ListaReproducao lista = new ListaReproducao("Minhas Músicas");
        Player player = new Player(lista);

        while (true) {
            System.out.println("\n🎵 MangaSound - Menu Principal");
            System.out.println("1. Adicionar Música ao Repositório");
            System.out.println("2. Criar Lista de Reprodução");
            System.out.println("3. Editar Lista de Reprodução");
            System.out.println("4. Executar Lista de Reprodução");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // Consumir quebra de linha

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome da música: ");
                        String nome = sc.nextLine();
                        System.out.print("Artista: ");
                        String artista = sc.nextLine();
                        System.out.print("Caminho do arquivo .wav: ");
                        String caminho = sc.nextLine();

// Copiar o arquivo para a pasta "repositorio"
                        Path origem = Paths.get(caminho);
                        Path destino = Paths.get("repositorio", origem.getFileName().toString());
                        try {
                            Files.createDirectories(destino.getParent());
                            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            System.out.println("Erro ao copiar o arquivo: " + e.getMessage());
                            break;
                        }

// Ler duração da música (em segundos)
                        System.out.print("Duração (em segundos): ");
                        int duracao = 0;
                        try {
                            duracao = Integer.parseInt(sc.nextLine());  // Usa nextLine e converte para int
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Duração inválida! Digite apenas números.");
                            break;
                        }

// Adiciona a música à lista
                        lista.adicionarMusica(new Musica(nome, artista, destino.toAbsolutePath().toString(), duracao));
                        System.out.println("✅ Música adicionada com sucesso!");

                        break;
                    case 2:
                        // Criação de nova lista de reprodução
                        System.out.print("Nome da nova lista de reprodução: ");
                        String nomeLista = sc.nextLine();
                        // Substitui a lista atual
                        lista = new ListaReproducao(nomeLista);
                        player = new Player(lista);
                        System.out.println("✅ Lista de Reprodução criada com sucesso!");
                        break;
                    case 3:
                        // Editar lista de reprodução: reposiciona uma música
                        if (lista.getMusicas().isEmpty()) {
                            System.out.println("A lista está vazia, nada para editar.");
                        } else {
                            System.out.println("Lista de músicas:");
                            for (int i = 0; i < lista.getMusicas().size(); i++) {
                                System.out.println((i + 1) + ". " + lista.getMusicas().get(i));
                            }
                            System.out.print("Informe o número da música que deseja mover: ");
                            int posOrigem = sc.nextInt() - 1;
                            System.out.print("Informe a nova posição para a música: ");
                            int posDestino = sc.nextInt() - 1;
                            sc.nextLine(); // limpar buffer


                            Musica removida = lista.getMusicas().remove(posOrigem);
                            if (removida != null) {
                                lista.getMusicas().add(posDestino, removida);
                                System.out.println("✅ Música movida com sucesso!");
                            } else {
                                System.out.println("Erro ao mover a música.");
                            }


                        }
                        break;
                    case 4:
                        // Executar lista de reprodução
                        System.out.println("Iniciando reprodução...");
                        player.tocar();
                        boolean executando = true;
                        while (executando) {
                            System.out.println("\nControles:");
                            System.out.println("P - Pausar");
                            System.out.println("C - Continuar");
                            System.out.println("N - Próxima Música");
                            System.out.println("B - Música Anterior");
                            System.out.println("R - Reiniciar Lista");
                            System.out.println("E - Encerrar Reprodução");
                            System.out.print("Escolha: ");
                            String controle = sc.nextLine().trim().toUpperCase();
                            switch(controle) {
                                case "P":
                                    player.pausar();
                                    break;
                                case "C":
                                    player.continuar();
                                    break;
                                case "N":
                                    player.proxima();
                                    break;
                                case "B":
                                    // Caso a música esteja tocando há mais de 10 segundos, poderia reiniciar,
                                    // senão volta para a anterior (essa lógica pode ser refinada conforme o requisito)
                                    player.anterior();
                                    break;
                                case "R":
                                    player.reiniciar();
                                    break;
                                case "E":
                                    player.parar();
                                    executando = false;
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }
                        }
                        break;
                    case 5:
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
