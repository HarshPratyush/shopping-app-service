package com.shopping.webservice.entity.audit;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
    @CreationTimestamp
    @Column(name="created_on",updatable = false,nullable = false)
    private LocalDateTime createdOn;


    @UpdateTimestamp
    @Column(name="updated_on")
    private LocalDateTime updatedOn;

    @CreatedBy
    @Column(name="created_by",updatable = false,nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;


}
