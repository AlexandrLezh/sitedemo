package lv.digitalbear.sitedemo.rest;

import lv.digitalbear.sitedemo.domain.DetailEntity;
import lv.digitalbear.sitedemo.repo.DetailRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("details")
public class DetailController {
    private final DetailRepo detailRepo;

    @Autowired
    public DetailController(DetailRepo detailRepo) {
        this.detailRepo = detailRepo;
    }

    @GetMapping
    public List<DetailEntity> list() {
        return detailRepo.findAll();
    }

    @GetMapping("{id}")
    public DetailEntity getOne(@PathVariable("id") DetailEntity detail) {
        return detail;
    }


    @PostMapping
    public DetailEntity create(@RequestBody DetailEntity detail) {
        return detailRepo.save(detail);
    }

    @PutMapping("{id}")
    public DetailEntity update(
            @PathVariable("id") DetailEntity detailFromDB,
            @RequestBody DetailEntity detail) {
        BeanUtils.copyProperties(detail, detailFromDB, "id");
        return detailRepo.save(detail);
    }

    @DeleteMapping("{id}")
    public void deleteDetail(@PathVariable("id") DetailEntity detail) {
        detailRepo.delete(detail);
    }
}
