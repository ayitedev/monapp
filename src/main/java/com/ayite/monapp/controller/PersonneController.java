package com.ayite.monapp.controller;


import com.ayite.monapp.models.Personne;
import com.ayite.monapp.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnes")
@CrossOrigin(origins = "http://localhost:8081")  // Pour autoriser Vue.js à appeler cette API
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        Optional<Personne> personne = personneService.findById(id);
        return personne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        System.out.println("personne"+ personne.getNom());

        Personne savedPersonne = personneService.save(personne);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personne) {
        if (!personneService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personne.setId(id);
        Personne updatedPersonne = personneService.save(personne);
        return ResponseEntity.ok(updatedPersonne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        if (!personneService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
