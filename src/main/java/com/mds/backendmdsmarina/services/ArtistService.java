package com.mds.backendmdsmarina.services;

import com.mds.backendmdsmarina.models.Album;
import com.mds.backendmdsmarina.models.Artist;
import com.mds.backendmdsmarina.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public ResponseEntity<Object> getAllArtists() {
        Map<String, String> logMap = new HashMap<>();
        try {
            List<Artist> artists = artistRepository.findAll();
            return new ResponseEntity<>(artists, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addArtist(Artist artist) {
        Map<String, String> logMap = new HashMap<>();
        try {
            Optional<Artist> optionalArtist = this.artistRepository.findByNameContaining(artist.getName());

            if (optionalArtist.isPresent()) {
                logMap.put("message", "Artists already exists!");
                return new ResponseEntity<>(logMap, HttpStatus.CONFLICT);
            }

            this.artistRepository.save(artist);
            return new ResponseEntity<>(artist, HttpStatus.OK);

        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateAlbum(Artist artist) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Artist> optionalArtist = this.artistRepository.findByNameContaining(artist.getName());

            if (optionalArtist.isPresent()) {
                Artist existingArtist = optionalArtist.get();
                existingArtist.setAge(artist.getAge());
                existingArtist.setBiography(artist.getBiography());
                existingArtist.setName(existingArtist.getName());
                this.artistRepository.save(existingArtist);
                return new ResponseEntity<>(existingArtist, HttpStatus.OK);
            }
            logMap.put("message", "Artist not found!");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteArtist(String name) {
        Map<String, String> logMap = new HashMap<>();
        try {
            Optional<Artist> optionalArtist = this.artistRepository.findByNameContaining(name);
            if (optionalArtist.isPresent()) {
                this.artistRepository.delete(optionalArtist.get());
                return new ResponseEntity<>(optionalArtist.get(), HttpStatus.OK);
            }
            logMap.put("message", "Artist not found!");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> getArtistByName(String name) {
        Map<String, String> logMap = new HashMap<>();

        try {
            Optional<Artist> optionalArtist = this.artistRepository.findByNameContaining(name);
            if (optionalArtist.isPresent()) {
                return new ResponseEntity<>(optionalArtist.get(), HttpStatus.OK);
            }
            logMap.put("message", "Artist not found");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
