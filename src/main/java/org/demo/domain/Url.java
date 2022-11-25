package org.demo.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Optional;

@Entity(name="urls")
@Getter
@Setter
@NoArgsConstructor
public class Url extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "short_form")
    private String shortForm;

    @Column(name = "long_form")
    private String longForm;

    public static Optional<Url> findByShortForm(String shortForm) {
        return Optional.ofNullable(find("short_form", shortForm).firstResult());
    }
}
