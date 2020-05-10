package com.mds.backendmdsmarina.controllers;

import com.mds.backendmdsmarina.models.Album;
import com.mds.backendmdsmarina.services.AlbumService;
import com.mds.backendmdsmarina.utils.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("")
    public ResponseEntity<Object> getAllAlbums() {
        return this.albumService.getAllAlbums();
    }

    @GetMapping("/artist")
    public ResponseEntity<Object> getAllAlbumsByArtist(@RequestParam String artist) {
        return this.albumService.getAllAlbumsByArtist(artist);
    }

    @GetMapping("/genre")
    public ResponseEntity<Object> getAllAlbumsByGenre(@RequestParam Genre genre) {
        return this.albumService.getAllAlbumsByGenre(genre);
    }

    @PostMapping("")
    public ResponseEntity<Object> addAlbum(@RequestBody Album album) {
        return this.albumService.addAlbum(album);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAlbum(@RequestBody Album album) {
        return this.albumService.updateAlbum(album);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAlbum(@RequestParam String title) {
        return this.albumService.deleteAlbum(title);
    }
}
