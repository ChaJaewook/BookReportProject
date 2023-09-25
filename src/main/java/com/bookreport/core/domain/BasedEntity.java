package com.bookreport.core.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@MappedSuperclass
@Getter
public class BasedEntity {
    private LocalDate insert;
    private LocalDate update;

    @PrePersist
    public void prePersist(){
        insert=LocalDate.now();
    }

    @PreUpdate
    public void preUpdate(){
        update=LocalDate.now();
    }

}
