package com.produto.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProdutoController {

	@Autowired
	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	private ProdutoRepository produtoRepository;

}
