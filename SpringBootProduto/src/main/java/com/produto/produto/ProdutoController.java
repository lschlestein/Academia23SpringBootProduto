package com.produto.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {
	//Injeta uma porção na classe ProdutoController para manipulação do banco de dados
	@Autowired
	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	private ProdutoRepository produtoRepository;
	
	//Configura parâmetros na página listarProdutos e exibe a mesma
	@GetMapping("/listar")
	public String listarProduto(Model model) {
		List<Produto> lista = produtoRepository.findAll();
		model.addAttribute("listaProdutos",lista);
		return "listarProdutos";
	}
	
	/*Outra maneira de manipular páginas html
	 * @GetMapping("/listar")
	public ModelAndView listarProduto() {
		var mav = new ModelAndView();
		List<Produto> lista = produtoRepository.findAll();
		mav.addObject("listaProdutos",lista);
		mav.setViewName("listarProdutos");
		return mav;
	}*/
	//Configura o objeto que receberá os atributos do front end
	@GetMapping("/cadastrar")
	public String cadastrarProduto(Model model) {
		model.addAttribute("produto", new Produto());
		return "cadastrarProduto";
	}
	//Carrega os atributos do front no objeto produto, e o salvo no banco
	@PostMapping("/cadastrar")
	public String addProduto(@ModelAttribute Produto produto, Model model) {
		produtoRepository.save(produto);
		listarProduto(model);
		return "listarProdutos";
	}
	//Deleta o registro passado como parâmetro do banco de dados
	@GetMapping("/excluir/{id}")
	public String excluirProduto(@PathVariable Long id, Model model) {
		produtoRepository.deleteById(id);
		listarProduto(model);
		return "listarProdutos";
	}
	//Altera um registro no banco de dados
	@GetMapping("/alterar/{id}")
	public String alterarProduto(@PathVariable Long id, Model model) {
		Produto p = produtoRepository.findById(id).get();
		model.addAttribute("produto",p);
		return"alterarProduto";
	}
	
	@PostMapping("/alterar")
	public String alteraProduto(@ModelAttribute Produto p,Model model) {
		//verificar se não há algum campo nulo ou que não foi alterado
		produtoRepository.save(p);
		listarProduto(model);
		return "listarProdutos";
	}

}
