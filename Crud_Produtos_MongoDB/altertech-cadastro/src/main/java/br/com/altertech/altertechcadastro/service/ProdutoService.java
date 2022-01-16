package br.com.altertech.altertechcadastro.service;

import java.util.List;
import java.util.Optional;

import br.com.altertech.altertechcadastro.shared.ProdutoDto;

public interface ProdutoService {
 
        List<ProdutoDto> ListarProdutos();
        List<ProdutoDto> ObterProdutoPorNome(String nome);
        List<ProdutoDto> ObterProdutoPorCodigo(double codigo);
        ProdutoDto cadastrarProduto(ProdutoDto produto);  
        ProdutoDto AtualizarProduto(String id, ProdutoDto produto);
        ProdutoDto AtualizarEstoque(String id, double quantidadeNova) ;
        Optional<ProdutoDto> obterPorId(String id);
        void deletarProdutoPorId(String id);

}
