package com.mds.backendmdsmarina.services;

import com.mds.backendmdsmarina.models.Manea;
import com.mds.backendmdsmarina.repositories.ManeaRepository;
import com.mds.backendmdsmarina.utils.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ManeaService {

    @Autowired
    private ManeaRepository maneaRepository;

    public ResponseEntity<Object> getAllManele() {
        Map<String, Object> logMap = new HashMap<>();
        try {
            List<Manea> manele = this.maneaRepository.findAll();
            return new ResponseEntity<>(manele, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllManeleByArtist(String artist) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            List<Manea> manele = this.maneaRepository.findByArtistContainingIgnoreCase(artist);
            return new ResponseEntity<>(manele, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllManeleByGenre(Genre genre) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            List<Manea> manele = this.maneaRepository.findAllByGenre(genre);
            return new ResponseEntity<>(manele, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addManea(Manea manea) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Manea> existingManea = this.maneaRepository.findByTitle(manea.getTitle());

            if (existingManea.isPresent()) {
                logMap.put("message", "This manea already exists!");
                return new ResponseEntity<>(logMap, HttpStatus.CONFLICT);
            }
            maneaRepository.save(manea);
            return new ResponseEntity<>(manea, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateManea(Manea manea) {

        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Manea> optionalManea = this.maneaRepository.findByTitle(manea.getTitle());

            if (optionalManea.isPresent()) {
                Manea existingManea = optionalManea.get();
                existingManea.setArtist(manea.getArtist());
                existingManea.setGenre(manea.getGenre());
                existingManea.setTitle(manea.getTitle());
                existingManea.setLink(manea.getLink());
                this.maneaRepository.save(existingManea);
                return new ResponseEntity<>(existingManea, HttpStatus.OK);
            }
            logMap.put("message", "Manea not found!");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteManea(String title) {

        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Manea> existingManea = this.maneaRepository.findByTitle(title);

            if (existingManea.isPresent()) {
                this.maneaRepository.delete(existingManea.get());
                return new ResponseEntity<>(existingManea.get(), HttpStatus.OK);
            }
            logMap.put("message", "Manea does not exists!");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> getManeleByTitle(String title) {
        Map<String, String> logMap = new HashMap<>();
        try {
            List<Manea> existingManea = this.maneaRepository.findByTitleContainingIgnoreCase(title);
            return new ResponseEntity<>(existingManea, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
