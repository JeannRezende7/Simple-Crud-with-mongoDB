package br.com.altertech.altertechcadastro.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.altertech.altertechcadastro.service.ProdutoService;
import br.com.altertech.altertechcadastro.shared.ProdutoDto;
import br.com.altertech.altertechcadastro.view.model.ProdutoModeloAlteracao;
import br.com.altertech.altertechcadastro.view.model.ProdutoModeloAlterarEstoque;
import br.com.altertech.altertechcadastro.view.model.ProdutoModeloResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @GetMapping()
    public ResponseEntity<List<ProdutoModeloResponse>> obterTodos() {
        List<ProdutoDto> dtos = service.ListarProdutos();

        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProdutoModeloResponse> resp = dtos.stream().map(dto -> mapper.map(dto, ProdutoModeloResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<ProdutoModeloResponse>> ObterProdutoPorNome(@PathVariable String nome) {
        List<ProdutoDto> dtos = service.ObterProdutoPorNome(nome);

        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProdutoModeloResponse> resp = dtos.stream().map(dto -> mapper.map(dto, ProdutoModeloResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/codigo/{codigo}")
    public ResponseEntity<List<ProdutoModeloResponse>> ObterporCodigo(@PathVariable String codigo) {
        List<ProdutoDto> dtos = service.ObterProdutoPorNome(codigo);

        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProdutoModeloResponse> resp = dtos.stream().map(dto -> mapper.map(dto, ProdutoModeloResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> CadastrarProduto(@RequestBody @Valid ProdutoDto produto) {
        return new ResponseEntity<>(service.cadastrarProduto(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarProdutoPorId(@PathVariable String id) {
        service.deletarProdutoPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoModeloResponse> AtualizarProduto(@PathVariable String id,
            @Valid @RequestBody ProdutoModeloAlteracao Produto) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDto dto = mapper.map(Produto, ProdutoDto.class);
        dto = service.AtualizarProduto(id, dto);

        return new ResponseEntity<>(mapper.map(dto, ProdutoModeloResponse.class), HttpStatus.OK);
    }

    @PostMapping(value = "/atualizarestoque/{id}")
    public ResponseEntity<ProdutoModeloResponse> atualizarestoqueProduto(
            @RequestBody ProdutoModeloAlterarEstoque atualizar, @PathVariable String id) {
        ProdutoDto produtoDto = service.AtualizarEstoque(id, atualizar.getQuantidadeEstoque());
        return new ResponseEntity<>(new ModelMapper().map(produtoDto, ProdutoModeloResponse.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/produto")
    public ResponseEntity<ProdutoModeloResponse> obterPorId(@PathVariable String id) {
        Optional<ProdutoDto> pessoa = service.obterPorId(id);

        if (pessoa.isPresent()) {
            return new ResponseEntity<>(new ModelMapper().map(pessoa.get(), ProdutoModeloResponse.class),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
