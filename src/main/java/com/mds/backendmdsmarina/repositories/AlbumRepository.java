package com.mds.backendmdsmarina.repositories;

import com.mds.backendmdsmarina.models.Album;
import com.mds.backendmdsmarina.utils.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    List<Album> findAllByArtistName(String artistName);

    List<Album> findAllByGenre(Genre genre);

    Optional<Album> findByTitle(String title);
}
