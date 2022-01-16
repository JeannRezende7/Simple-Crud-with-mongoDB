package br.com.altertech.altertechcadastro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.altertech.altertechcadastro.model.Produto;


@Repository
public interface ProdutoRepository  extends MongoRepository <Produto, String>{
    List<Produto> findByCodigo(double codigo);
    List<Produto> findByNome(String nome);
    
}