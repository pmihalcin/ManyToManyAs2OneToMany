package com.example.demo;

import static javax.persistence.CascadeType.ALL;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import org.springframework.hateoas.Identifiable;

@Getter
@Entity
public class Mcc implements Identifiable<String> {

    @Id
    private String code;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "mccGroup", cascade = ALL, orphanRemoval = true)
    private List<MccGroupMcc> groups = new LinkedList<>();

    protected Mcc() {
        this.code = null;
        this.name = null;
    }

    Mcc(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccGroupMcc that = (MccGroupMcc) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String getId() {
        return code;
    }
}
