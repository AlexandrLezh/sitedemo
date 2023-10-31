package lv.digitalbear.sitedemo.rest;

import lv.digitalbear.sitedemo.model.Developer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {
    private List<Developer> developersList = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Peter", "Petrov"),
            new Developer(3L, "Maks", "Fadeev")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll() {
        return developersList;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id) {
        return developersList.stream().filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @PostMapping
    public Developer create(@RequestBody Developer developer) {
        this.developersList.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        this.developersList.removeIf(developer -> developer.getId().equals(id));
    }
}
