package kr.teentime.mainApi.domain.basic;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BasicEntity {

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long lastModifiedBy;
}
