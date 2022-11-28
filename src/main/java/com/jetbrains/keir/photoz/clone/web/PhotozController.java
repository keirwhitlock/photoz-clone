package com.jetbrains.keir.photoz.clone.web;

import com.jetbrains.keir.photoz.clone.model.Photo;
import com.jetbrains.keir.photoz.clone.service.PhotozService;
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
    public Iterable<Photo> get() {
        return photozservice.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id) {
        Photo photo = photozservice.get(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id) {
        photozservice.remove(id);
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photozservice.put(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return photo;
    }
}
