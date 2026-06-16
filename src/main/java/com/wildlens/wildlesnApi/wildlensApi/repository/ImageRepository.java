package com.wildlens.wildlesnApi.wildlensApi.repository;
import com.wildlens.wildlesnApi.wildlensApi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Integer> {
}
