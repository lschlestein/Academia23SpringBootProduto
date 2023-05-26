package com.produto.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

	@Autowired
	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	private ProdutoRepository produtoRepository;
	
	@GetMapping("/listar")
	public String listarProduto(Model model) {
		List<Produto> lista = produtoRepository.findAll();
		model.addAttribute("listaProdutos",lista);
		return "listarProdutos";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrarProduto(Model model) {
		model.addAttribute("produto", new Produto());
		return "cadastrarProduto";
	}
	
	@PostMapping("/cadastrar")
	public String addProduto(@ModelAttribute Produto produto, Model model) {
		produtoRepository.save(produto);
		listarProduto(model);
		return "listarProdutos";
	}

}
