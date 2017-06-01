package com.example.demo;

import static javax.persistence.CascadeType.ALL;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.homecredit.mer.web.core.AbstractEntity;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by Patrik.Mihalcin on 24.5.2017.
 */
@ToString(callSuper = true, exclude = {"codes"})
@Getter
@Table(name = "MER_MCC_GROUP")
@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SQ_MER_MCC_GROUP", allocationSize = 1)
class MccGroup extends AbstractEntity {

    private final String name;

    @OneToMany(mappedBy = "mcc", cascade = ALL, orphanRemoval = true)
    private List<MccGroupMcc> codes = new LinkedList<>();

    MccGroup() {
        this.name = null;
        this.codes = new LinkedList<>();

    }

    MccGroup(String name) {
        this.name = name;
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
