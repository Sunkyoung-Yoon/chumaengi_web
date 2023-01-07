package com.project.springboot.domain;

/*
BaseTimeEntity 클래스는 모든 Entity 의 상위 클래스가 되어 Entity 들의
createdDate, modifiedDate 를 자동으로 관리하는 역할
 */

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//JPA Entity 클래스들이 BaseTimeEntity 을 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하게함
@MappedSuperclass
//BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    //Entity 가 생성되어 저장될때 시간이 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    //조회한 Entity 의 값을 변경할 때 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

