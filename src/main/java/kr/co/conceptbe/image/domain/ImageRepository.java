package kr.co.conceptbe.image.domain;

import kr.co.conceptbe.image.exception.ImageNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    default Image getById(Long id) {
        return this.findById(id)
            .orElseThrow(ImageNotFoundException::new);
    }

}
