package com.mds.backendmdsmarina.services;

import com.mds.backendmdsmarina.models.Album;
import com.mds.backendmdsmarina.repositories.AlbumRepository;
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
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public ResponseEntity<Object> getAllAlbums() {
        Map<String, Object> logMap = new HashMap<>();
        try {
            List<Album> albums = this.albumRepository.findAll();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllAlbumsByArtist(String artist) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            List<Album> albums = this.albumRepository.findAllByArtistName(artist);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllAlbumsByGenre(Genre genre) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            List<Album> albums = this.albumRepository.findAllByGenre(genre);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addAlbum(Album album) {
        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Album> existingAlbum = this.albumRepository.findByTitle(album.getTitle());

            if (existingAlbum.isPresent()) {
                logMap.put("message", "This album already exists!");
                return new ResponseEntity<>(logMap, HttpStatus.CONFLICT);
            }
            albumRepository.save(album);
            return new ResponseEntity<>(album, HttpStatus.OK);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateAlbum(Album album) {

        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Album> optionalAlbum = this.albumRepository.findByTitle(album.getTitle());

            if (optionalAlbum.isPresent()) {
                Album existingAlbum = optionalAlbum.get();
                existingAlbum.setArtistName(album.getArtistName());
                existingAlbum.setGenre(album.getGenre());
                existingAlbum.setTitle(album.getTitle());
                this.albumRepository.save(existingAlbum);
                return new ResponseEntity<>(existingAlbum, HttpStatus.OK);
            }
            logMap.put("message", "Album not found!");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteAlbum(String title) {

        Map<String, Object> logMap = new HashMap<>();
        try {
            Optional<Album> existingAlbum = this.albumRepository.findByTitle(title);

            if (existingAlbum.isPresent()) {
                this.albumRepository.delete(existingAlbum.get());
                return new ResponseEntity<>(existingAlbum.get(), HttpStatus.OK);
            }
            logMap.put("message", "Album does not exists!");
            return new ResponseEntity<>(logMap, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logMap.put("message", "An error occurred. Try again later!");
            return new ResponseEntity<>(logMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
