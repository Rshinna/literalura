package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(String autor);

    Optional<Livro> findByTituloAndAutor(String titulo, String autor);

    List<Livro> findByIdioma(String idioma);

    List<Livro> findTop10ByOrderByNumeroDeDownloadsDesc();
}
