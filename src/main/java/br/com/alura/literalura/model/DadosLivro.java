package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivro {
    @JsonAlias({"title"}) private String titulo;
    @JsonAlias({"authors"}) private List<DadosAutor> autores;
    @JsonAlias({"languages"}) private List<String> idiomas;
    @JsonAlias({"download_count"}) private Integer numeroDownloads;

    public DadosLivro(){}

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public List<DadosAutor> getAutores() {

        return autores;
    }

    public void setAutores(List<DadosAutor> autores) {

        this.autores = autores;
    }

    public List<String> getIdiomas() {

        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {

        this.idiomas = idiomas;
    }

    public Integer getNumeroDownloads() {

        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {

        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return
                "titulo = '" + titulo + '\'' +
                ", autores = " + autores.get(0) +
                ", idiomas = " + idiomas.get(0) +
                ", numeroDownloads = " + numeroDownloads;
    }
}


