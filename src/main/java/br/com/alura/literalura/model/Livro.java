package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Integer numeroDeDownloads;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getAutor() {

        return autor;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }

    public String getIdioma() {

        return idioma;
    }

    public void setIdioma(String idioma) {

        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {

        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {

        this.numeroDeDownloads = numeroDeDownloads;
    }
}
