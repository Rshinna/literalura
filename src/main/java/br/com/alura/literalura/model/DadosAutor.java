package br.com.alura.literalura.model;

import br.com.alura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class DadosAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer anoNascimento;

    @JsonAlias("death_year")
    private Integer anoMorte;

    @Transient
    private LivroRepository livroRepository;

    public DadosAutor(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        List<Livro> livros = livroRepository.findByAutor(nome);
        return
                "Autor: '" + nome + '\'' +
                ", Ano de nascimento: " + anoNascimento +
                ", Ano de falecimento: " + anoMorte +
                " Livros: " + livros;
    }
}
