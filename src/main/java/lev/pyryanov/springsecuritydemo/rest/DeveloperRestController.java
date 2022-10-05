package lev.pyryanov.springsecuritydemo.rest;

import lev.pyryanov.springsecuritydemo.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(("api/v1/developers"))
public class DeveloperRestController {

    private List<Developer> DEVELOPERS = Stream.of (
            new Developer (1L, "Ivan", "Ivanov"),
            new Developer (2L, "Petr", "Petrov"),
            new Developer (3L, "Sergey", "Sergeev")
    ).collect(Collectors.toList());

    @GetMapping
    @PreAuthorize ("hasAuthority('developers:read')")
    public List<Developer> getAll() {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize ("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream ()
                .filter (developer -> developer.getId ().equals (id))
                .findFirst ()
                .orElse (null);
    }

    @PostMapping
    @PreAuthorize ("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize ("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id) {
        this.DEVELOPERS.removeIf (developer -> developer.getId ().equals (id));
    }
}
