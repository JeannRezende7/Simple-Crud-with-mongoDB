package br.com.altertech.altertechcadastro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.altertech.altertechcadastro.model.Produto;
import br.com.altertech.altertechcadastro.repository.ProdutoRepository;
import br.com.altertech.altertechcadastro.shared.ProdutoDto;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    ProdutoRepository repo;

    @Override
    public List<ProdutoDto> ListarProdutos() {
        List<Produto> produto = repo.findAll();

        return produto.stream().map(f -> new ModelMapper().map(f, ProdutoDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProdutoDto cadastrarProduto(ProdutoDto produto) {
        ModelMapper mapper = new ModelMapper();
        Produto proParaSalvar = mapper.map(produto, Produto.class);
        proParaSalvar = repo.save(proParaSalvar);
        return mapper.map(proParaSalvar, ProdutoDto.class);
    }

    @Override
    public void deletarProdutoPorId(String id) {
        repo.deleteById(id);

    }

    @Override
    public ProdutoDto AtualizarProduto(String id, ProdutoDto produto) {
        produto.setId(id);
        return salvarProduto(produto);
    }

    private ProdutoDto salvarProduto(ProdutoDto produto) {
        ModelMapper mapper = new ModelMapper();
        Produto ProdutoSalvar = mapper.map(produto, Produto.class);
        ProdutoSalvar = repo.save(ProdutoSalvar);
        return mapper.map(ProdutoSalvar, ProdutoDto.class);

    }

    @Override
    public List<ProdutoDto> ObterProdutoPorNome(String nome) {
        List<Produto> produtos = repo.findByNome(nome);

        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDto> ObterProdutoPorCodigo(double codigo) {
        List<Produto> p = repo.findByCodigo(codigo);

        return p.stream().map(codigos -> new ModelMapper().map(codigos, ProdutoDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoDto> obterPorId(String id) {
        Optional<Produto> p = repo.findById(id);

        if (p.isPresent()) {
            ProdutoDto dto = new ModelMapper().map(p.get(), ProdutoDto.class);
            return Optional.of(dto);
        }

        return Optional.empty();
    }

    @Override
    public ProdutoDto AtualizarEstoque(String id, double quantidadeNova) {
        Produto produto = repo.findById(id).get();
        produto.setQuantidadeEstoque(quantidadeNova);
        produto = repo.save(produto);
        return new ModelMapper().map(produto, ProdutoDto.class);
    }

}
