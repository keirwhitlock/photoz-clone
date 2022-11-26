package com.jetbrains.keir.photoz.clone;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotozService photozservice;

    public DownloadController(PhotozService photozservice) {
        this.photozservice = photozservice;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Photo photo = photozservice.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getData();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
