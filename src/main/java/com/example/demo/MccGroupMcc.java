package com.example.demo;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Entity
public class MccGroupMcc {

    @EmbeddedId
    private MccGroupMccId id;

    @ManyToOne
    @MapsId("mccGroupId")
    private MccGroup mccGroup;

    @ManyToOne
    @MapsId("mccId")
    private Mcc mcc;

    @DateTimeFormat(iso = DATE_TIME)
    @Column(name = "CREATED_AT", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdDateTime;

    MccGroupMcc() {
    }

    MccGroupMcc(MccGroup mccGroup, Mcc mcc) {
        this.mccGroup = mccGroup;
        this.mcc = mcc;
        this.id = new MccGroupMccId(mccGroup.getId(), mcc.getId());
        this.createdDateTime = LocalDateTime.now();
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
}
