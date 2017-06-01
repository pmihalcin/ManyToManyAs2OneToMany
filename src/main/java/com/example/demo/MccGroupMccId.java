package com.example.demo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;

/**
 * Created by patrik.mihalcin on 26.5.2017.
 */
@Getter
@Embeddable
class MccGroupMccId implements Serializable {

    private Long mccGroupId;
    private Long mccId;

    MccGroupMccId() {
    }

    MccGroupMccId(Long mccGroupId, Long mccId) {
        this.mccGroupId = mccGroupId;
        this.mccId = mccId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccGroupMccId that = (MccGroupMccId) o;
        return Objects.equals(mccGroupId, that.mccGroupId) &&
                Objects.equals(mccId, that.mccId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mccGroupId, mccId);
    }
}
