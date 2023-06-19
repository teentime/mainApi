package kr.teentime.mainApi.domain.basic;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BasicTime {

    @CreatedDate
    @DateTimeFormat(pattern = "yy년 MM월 dd일 HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yy년 MM월 dd일 HH:mm:ss")
    private LocalDateTime lastModifiedDate;
}
