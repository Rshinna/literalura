package br.com.alura.literalura;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.ResultadosLivros;
import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;

	public LiteraluraApplication(Principal principal){
		this.principal = principal;
	}

	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.executar();

	}
}
