package org.demo.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.demo.domain.Url;

@ApplicationScoped
public class UrlRepository implements PanacheRepository<Url> {
    public Optional<Url> findByLongForm(String longForm) {
        return Optional.ofNullable(find("long_form", longForm).firstResult());
    }
}
