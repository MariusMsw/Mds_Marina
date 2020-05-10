package com.mds.backendmdsmarina.controllers;

import com.mds.backendmdsmarina.models.Artist;
import com.mds.backendmdsmarina.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("")
    public ResponseEntity<Object> getAllArtists() {
        return this.artistService.getAllArtists();
    }

    @PostMapping("")
    public ResponseEntity<Object> addArtist(@RequestBody Artist artist) {
        return this.artistService.addArtist(artist);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateArtist(@RequestBody Artist artist) {
        return this.artistService.updateAlbum(artist);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteArtist(@RequestParam String name) {
        return this.artistService.deleteArtist(name);
    }


}
