package com.produto.produto;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfiguration {
	@Bean
	CommandLineRunner commandLineRunner(ProdutoRepository produtoRepository) {
		return args->{
			Produto p1 = new Produto(123,"PC","Eletrônico",2500.0F,10);
			Produto p2 = new Produto(321,"Tablet","Eletrônico",200.0F,5);
			Produto p3 = new Produto(3221,"Smartphone","Eletrônico",3500.0F,5);
			List<Produto> l = List.of(p1,p2,p3);
			produtoRepository.saveAll(l);
		};
	}
	
}
