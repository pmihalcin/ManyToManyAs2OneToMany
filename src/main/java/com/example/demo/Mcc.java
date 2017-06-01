package com.example.demo;

import static javax.persistence.CascadeType.ALL;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.homecredit.mer.web.core.AbstractEntity;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by patrik.mihalcin on 3.4.2017.
 */
@ToString(callSuper = true, exclude = {"groups"})
@Getter
@Table(name = "MER_MCC")
@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SQ_MER_MCC", allocationSize = 1)
public class Mcc extends AbstractEntity {

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
        if (!super.equals(o)) return false;
        Mcc mcc = (Mcc) o;
        return Objects.equals(code, mcc.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code);
    }
}
