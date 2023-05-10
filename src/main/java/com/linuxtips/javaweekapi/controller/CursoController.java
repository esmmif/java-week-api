package com.linuxtips.javaweekapi.controller;

import javax.annotation.PostConstruct;
import com.linuxtips.javaweekapi.model.Curso;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;
import com.linuxtips.javaweekapi.service.CursoService;

@RestController
@RequestMapping ("/api/")
public class CursoController {
    
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    CursoService cursoService;

    @PostMapping("/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    //endpoints
    public Curso criarCurso(@RequestBody Curso curso){
        return cursoService.criarCurso(curso);
    }

    @GetMapping("/cursos")
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listarCursos(){
        return cursoService.listarCursos();
    }

    @GetMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> buscarCursoPeloId(@PathVariable (value = "id") Long id){
        return cursoService.buscarCursoPeloId(id);
    }

    //patch parcial e put é completo
    @PutMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Curso> atualizarCursoPeloId(@PathVariable(value = "id") Long id, @RequestBody Curso curso){
        return cursoService.atualizarCursoPeloId(curso, id);
    }

    @DeleteMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> excluirCursoPeloId(@PathVariable (value = "id") Long id){
        return cursoService.excluirCursoPeloId(id);
    }


}
