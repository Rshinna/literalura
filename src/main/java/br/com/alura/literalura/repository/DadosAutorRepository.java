package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.DadosAutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosAutorRepository extends JpaRepository<DadosAutor, Long> {
    Optional<DadosAutor> findByNome(String nome);

}
