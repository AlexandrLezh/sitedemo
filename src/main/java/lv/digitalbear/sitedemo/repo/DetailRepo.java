package lv.digitalbear.sitedemo.repo;

import lv.digitalbear.sitedemo.domain.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepo extends JpaRepository<DetailEntity, Long> {
}
