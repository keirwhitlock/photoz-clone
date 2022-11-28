package com.jetbrains.keir.photoz.clone.service;

import com.jetbrains.keir.photoz.clone.model.Photo;
import com.jetbrains.keir.photoz.clone.repository.PhotozRepository;
import org.springframework.stereotype.Service;


@Service
public class PhotozService {

    private final PhotozRepository photozRepository;

    public PhotozService(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }

    public Iterable<Photo> get() {
        return photozRepository.findAll();
    }

    public Photo get(Integer id) {
        return photozRepository.findById(id).orElse(null);

    }

    public void remove(Integer id) {

        photozRepository.deleteById(id);
    }

    public Photo put(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(filename);
        photo.setContentType(contentType);
        photo.setData(data);
        photozRepository.save(photo);
        return photo;
    }
}
