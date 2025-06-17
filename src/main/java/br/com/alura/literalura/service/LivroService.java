package br.com.alura.literalura.service;

import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.DadosAutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    private DadosAutorRepository dadosAutorRepository;

    public LivroService(LivroRepository livroRepository) {

        this.livroRepository = livroRepository;
    }

    public List<Livro> listarLivros() {

        return livroRepository.findAll();
    }

    public void salvarLivro(Livro livro) {
        Optional<Livro> livroExistente = livroRepository.findByTituloAndAutor(livro.getTitulo(), livro.getAutor());
        if (livroExistente.isEmpty()) {
            livroRepository.save(livro);
        } else {
            System.out.println("Este livro já está registrado: " + livro.getTitulo());
        }
    }

    public List<Livro> listarLivrosPorAutor(String nomeAutor) {
        return livroRepository.findByAutor(nomeAutor);
    }

    public DadosAutor salvarAutor(DadosAutor autor) {
        Optional<DadosAutor> autorExistente = dadosAutorRepository.findByNome(autor.getNome());
        return autorExistente.orElseGet(() -> dadosAutorRepository.save(autor));
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}
