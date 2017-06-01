package com.example.demo;

import static javax.persistence.CascadeType.ALL;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import org.springframework.hateoas.Identifiable;

@Getter
@Entity
class MccGroup implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;
    private final String name;

    @OneToMany(mappedBy = "mcc", cascade = ALL, orphanRemoval = true)
    private List<MccGroupMcc> codes = new LinkedList<>();

    MccGroup() {
        this.name = null;
    }

    MccGroup(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccGroup that = (MccGroup) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    void addCodes(Mcc... codes) {
        for (Mcc code : codes) {
            MccGroupMcc mccGroupMcc = new MccGroupMcc(this, code);
            this.codes.add(mccGroupMcc);
            if (!code.getGroups().contains(mccGroupMcc)) {
                code.getGroups().add(mccGroupMcc);
            }
        }
    }
}