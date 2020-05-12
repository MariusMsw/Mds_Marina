package com.mds.backendmdsmarina.controllers;

import com.mds.backendmdsmarina.models.Album;
import com.mds.backendmdsmarina.models.Manea;
import com.mds.backendmdsmarina.services.ManeaService;
import com.mds.backendmdsmarina.utils.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manea")
public class ManeaController {

    @Autowired
    private ManeaService maneaService;

    @GetMapping("")
    public ResponseEntity<Object> getAllManele() {
        return this.maneaService.getAllManele();
    }

    @GetMapping("/artist")
    public ResponseEntity<Object> getAllManeleByArtist(@RequestParam String artist) {
        return this.maneaService.getAllManeleByArtist(artist);
    }

    @GetMapping("/title")
    public ResponseEntity<Object> getManeleByTitle(@RequestParam String title) {
        return this.maneaService.getManeleByTitle(title);
    }

    @GetMapping("/genre")
    public ResponseEntity<Object> getAllManeleByGenre(@RequestParam Genre genre) {
        return this.maneaService.getAllManeleByGenre(genre);
    }

    @PostMapping("")
    public ResponseEntity<Object> addManea(@RequestBody Manea manea) {
        return this.maneaService.addManea(manea);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateManea(@RequestBody Manea manea) {
        return this.maneaService.updateManea(manea);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteManea(@RequestParam String title) {
        return this.maneaService.deleteManea(title);
    }

}
