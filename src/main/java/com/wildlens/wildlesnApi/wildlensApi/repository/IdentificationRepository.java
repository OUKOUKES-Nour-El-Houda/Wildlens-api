package com.wildlens.wildlesnApi.wildlensApi.repository;

import com.wildlens.wildlesnApi.wildlensApi.model.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Long> {
}
