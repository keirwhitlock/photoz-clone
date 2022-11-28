package com.jetbrains.keir.photoz.clone.repository;

import com.jetbrains.keir.photoz.clone.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotozRepository extends CrudRepository<Photo, Integer> {

}
