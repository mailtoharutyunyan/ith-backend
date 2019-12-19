package am.ith.service.repository;

import am.ith.service.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {

  @Override
  List<ImageModel> findAll();

  ImageModel getById(Long id);
}
