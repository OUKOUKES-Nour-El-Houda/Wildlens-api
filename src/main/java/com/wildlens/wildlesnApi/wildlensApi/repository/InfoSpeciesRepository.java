package com.wildlens.wildlesnApi.wildlensApi.repository;
import com.wildlens.wildlesnApi.wildlensApi.model.InfoSpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoSpeciesRepository extends JpaRepository<InfoSpecies, Long>{

    Optional<InfoSpecies> findByEspecesIgnoreCase(String especes);

}
