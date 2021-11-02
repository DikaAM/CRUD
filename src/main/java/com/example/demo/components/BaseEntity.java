package com.example.demo.components;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass  // 1
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder  // 2
public abstract class BaseEntity {

    @CreationTimestamp
    @Column (name = "created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;


}
