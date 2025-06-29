package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.ResultadosLivros;
import br.com.alura.literalura.repository.DadosAutorRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private final Scanner sc = new Scanner(System.in);

    @Autowired
    private LivroService livroService;
    @Autowired
    private DadosAutorRepository dadosAutorRepository;


    public void executar() {
        boolean executando = true;
        while (executando) {
            exibirMenu();
            var opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> buscarLivrosPeloTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> buscarTop10MaisBaixados();
                case 0 -> {
                    System.out.println("Encerrando a Literalura!");
                    executando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }




    private void exibirMenu() {
        System.out.println("""
                
                ###########################################################
                                    LITERALURA
                       Escolha um número no menu abaixo:
                ###########################################################
                                     (Menu)
                           1- Buscar livros pelo título
                           2- Listar livros registrados
                           3- Listar autores registrados
                           4- Listar autores vivos em um determinado ano
                           5- Listar livros em um determinado idioma
                           6- Buscar Top10 mais baixados
                           0- Sair
                """);
    }


    private void buscarLivrosPeloTitulo() {
        System.out.println("Digite o título do livro que deseja buscar: ");
        var tituloLivro = sc.nextLine();
        var tituloFormatado = tituloLivro.replace(" ", "+");

        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("https://gutendex.com/books/?search=" + tituloFormatado);

        ResultadosLivros resultado = new ConverteDados().obterDados(json, ResultadosLivros.class);
        List<DadosLivro> livros = resultado.getLivros();

        if (!livros.isEmpty()) {
            DadosLivro dadosLivro = livros.get(0);

            if (dadosLivro.getAutores().isEmpty()){
                System.out.println("Este livro não possui dados de autor disponíveis e não será salvo. ");
                return;
            }
            DadosAutor autor = new DadosAutor();
            autor.setNome(dadosLivro.getAutores().get(0).getNome());
            autor.setAnoNascimento(dadosLivro.getAutores().get(0).getAnoNascimento());
            autor.setAnoMorte(dadosLivro.getAutores().get(0).getAnoMorte());

            autor = livroService.salvarAutor(autor);

            Livro livro = new Livro();
            livro.setTitulo(dadosLivro.getTitulo());
            livro.setAutor(autor.getNome());
            livro.setIdioma(dadosLivro.getIdiomas().get(0));
            livro.setNumeroDeDownloads(dadosLivro.getNumeroDownloads());

            livroService.salvarLivro(livro);

            System.out.println("-----LIVRO-----");
            System.out.println("Titulo: " + dadosLivro.getTitulo());
            System.out.println("Autor: " + dadosLivro.getAutores().get(0).getNome());
            System.out.println("Idioma: " + dadosLivro.getIdiomas().get(0));
            System.out.println("Numero de downloads: " + dadosLivro.getNumeroDownloads());
            System.out.println("---------------");
        } else {
            System.out.println("Nenhum livro encontrado para o título informado!");
        }
    }


    private void listarLivrosRegistrados() {
        List<Livro> livros = livroService.listarLivros();

        if (!livros.isEmpty()) {
            livros.forEach(livro -> {
                System.out.println("-----LIVRO-----");
                System.out.println("Titulo: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Numero de downloads: " + livro.getNumeroDeDownloads());
                System.out.println("-----------------------------");
            });
        } else {
            System.out.println("Nenhum livro encontrado no banco de dados!");
        }
    }

    private void listarAutoresRegistrados() {

        List<DadosAutor> autores = dadosAutorRepository.findAll();
        if (!autores.isEmpty()) {
            for (DadosAutor autor : autores) {
                System.out.println("-----AUTOR-----");
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Ano de nascimento: " + (autor.getAnoNascimento() != null ? autor.getAnoNascimento() : "Ano não informado!"));
                System.out.println("Ano de falecimento: " + (autor.getAnoMorte() != null ? autor.getAnoMorte() : "Ano não informado!"));

                List<Livro> livros = livroService.listarLivrosPorAutor(autor.getNome());

                if (!livros.isEmpty()) {
                    System.out.println("Livros:");
                    livros.forEach(livro -> System.out.println(" - " + livro.getTitulo()));
                } else {
                    System.out.println("Nenhum livro registrado para este autor.");
                }

                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("Nenhum autor encontrado no banco de dados!");
        }
    }


    private void listarAutoresVivos() {
        System.out.println("Digite o ano para verificar autores que estejam vivos: ");
        int ano = sc.nextInt();
        sc.nextLine();

        List<DadosAutor> autores = dadosAutorRepository
                .findByAnoNascimentoLessThanEqualAndAnoMorteGreaterThanEqualOrAnoMorteIsNull(ano, ano);

        if (!autores.isEmpty()) {
            System.out.println("----AUTORES VIVOS EM " + ano + "-----");
            autores.forEach(autor ->
                    System.out.println("Nome: " + autor.getNome() + " (Nascido em " + autor.getAnoNascimento() + ")")
            );
            System.out.println("--------------------------------------");
        } else {
            System.out.println("Nenhum autor encontrado vivo no ano informado.");
        }
    }


    private void listarLivrosPorIdioma() {

        System.out.println("Digite o idioma para buscar livros (ex: 'en' para inglês, 'pt' para português): ");
        String idioma = sc.nextLine();

        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);

        if (!livros.isEmpty()) {
            System.out.println("-----LIVROS NO IDIOMA '" + idioma + "' -----");
            livros.forEach(livro -> {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Número de downloads: " + livro.getNumeroDeDownloads());
                System.out.println("------------------------------");
            });
        } else {
            System.out.println("Nenhum livro encontrado no idioma informado!");
        }
    }

    private void buscarTop10MaisBaixados() {

        List<Livro> topLivros = livroService.buscarTop10MaisBaixados();

        if (!topLivros.isEmpty()) {
            System.out.println("----- TOP 10 LIVROS MAIS BAIXADOS -----");
            int posicao = 1;
            for(Livro livro : topLivros) {
                System.out.println(posicao++ + "º lugar:");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Downloads: " + livro.getNumeroDeDownloads());
                System.out.println("------------------------------------");
            }
        }else {
            System.out.println("Nenhum livro registrado ainda!");
        }
    }
}