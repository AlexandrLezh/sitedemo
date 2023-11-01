package lv.digitalbear.sitedemo.controllers.rest;

import lv.digitalbear.sitedemo.domain.DetailEntity;
import lv.digitalbear.sitedemo.repo.DetailRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/details")
public class DetailController {
    @Autowired
    private DetailRepo detailRepo;


    @GetMapping
    public List<DetailEntity> getAllDetails() {
        return detailRepo.findAll();
    }

    @GetMapping("{id}")
    public Optional<DetailEntity> getDetailById(@PathVariable Long id) {
        return detailRepo.findById(id);
    }


    @PostMapping
    public DetailEntity createDetail(@RequestBody DetailEntity detail) {

        return detailRepo.save(detail);
    }

    @PutMapping("{id}")
    public DetailEntity updateDetail(
            @PathVariable("id") DetailEntity detailFromDB,
            @RequestBody DetailEntity detail) {
        BeanUtils.copyProperties(detail, detailFromDB, "id");
        return detailRepo.save(detailFromDB);
    }

    @DeleteMapping("{id}")
    public void deleteDetail(@PathVariable("id") DetailEntity detail) {
        detailRepo.delete(detail);
    }
}
