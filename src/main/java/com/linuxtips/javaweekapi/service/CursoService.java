package com.linuxtips.javaweekapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.linuxtips.javaweekapi.model.Curso;
import com.linuxtips.javaweekapi.repository.CursoRepository;

import java.util.List;

@Service //onde colocamos aas regras de negocio, aqui fazemos as injeções de dependencias
public class CursoService {

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
    
    public CursoRepository cursoRepository;//instanciando e injetando a dependencia do cursorepository no cursoservice
    
    
    public Curso criarCurso(Curso curso){
        return cursoRepository.save(curso);
    } //da classe controller 
    
    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    public ResponseEntity<Curso> buscarCursoPeloId(Long id){
        return cursoRepository.findById(id)
            .map(curso -> ResponseEntity.ok().body(curso))
            .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Curso> atualizarCursoPeloId(Curso curso, Long id){ //recebe o objeto curso e o id que quero atualizar
        return cursoRepository.findById(id)
            .map(cursoToUpdate -> {//lambda expression??
                cursoToUpdate.setNome(curso.getNome());
                cursoToUpdate.setPessoaInstrutora(curso.getPessoaInstrutora());
                cursoToUpdate.setPreco(curso.getPreco());
                Curso updated = cursoRepository.save(cursoToUpdate);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> excluirCursoPeloId(Long id){
        return cursoRepository.findById(id)
            .map(cursoToDelete -> {
                cursoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());

    }

}
