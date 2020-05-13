package com.mds.backendmdsmarina.repositories;

import com.mds.backendmdsmarina.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByNameContaining(String name);
}
