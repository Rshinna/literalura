package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadosLivros {
    @JsonAlias("results")
    private List<DadosLivro> livros;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DadosLivro> getLivros() {
        return livros;
    }

    public void setLivros(List<DadosLivro> livros) {
        this.livros = livros;
    }
}
