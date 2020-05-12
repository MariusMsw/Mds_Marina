package com.mds.backendmdsmarina.repositories;

import com.mds.backendmdsmarina.models.Album;
import com.mds.backendmdsmarina.models.Manea;
import com.mds.backendmdsmarina.utils.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManeaRepository extends JpaRepository<Manea, Integer> {

    List<Manea> findByArtistContainingIgnoreCase(String artistName);

    List<Manea> findAllByGenre(Genre genre);

    Optional<Manea> findByTitle(String title);

    List<Manea> findByTitleContainingIgnoreCase(String title);

}
