package com.foo.entity;

import com.foo.dto.UserDTO;
import com.foo.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Data
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity{

    private String projectName;

    private String projectCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User assignedManager;


    @Column(columnDefinition = "DATE")
    private LocalDate startDate;
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    private String projectDetail;
    @Enumerated(value = EnumType.STRING)
    private Status projectStatus;

}
