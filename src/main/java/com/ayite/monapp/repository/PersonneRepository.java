package com.ayite.monapp.repository;

import com.ayite.monapp.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonneRepository  extends JpaRepository<Personne, Long> {
}
