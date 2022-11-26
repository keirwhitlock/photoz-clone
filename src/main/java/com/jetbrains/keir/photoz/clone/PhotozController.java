package com.jetbrains.keir.photoz.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    private final PhotozService photozservice;

    public PhotozController(PhotozService photozservice) {
        this.photozservice = photozservice;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return photozservice.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photozservice.get(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = photozservice.remove(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photozservice.put(file.getOriginalFilename(), file.getBytes());
        return photo;
    }
}
