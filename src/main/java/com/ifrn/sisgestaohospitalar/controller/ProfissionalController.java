package com.ifrn.sisgestaohospitalar.controller;

import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Profissional> findByName(@PathVariable("name") String name){
        Profissional profissional =  profissionalService.findByNome(name);
        if (profissional != null){
            return ResponseEntity.ok(profissional);
        }
        return ResponseEntity.notFound().build();
    }
}
