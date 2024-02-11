package org.foo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foo.utils.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private ProjectDTO projectDTO;
    private UserDTO assignedEmployee;
    private String taskSubject;
    private String taskDetail;
    private Status taskStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate assignedDate;

    public TaskDTO(ProjectDTO projectDTO, UserDTO assignedEmployee, String taskSubject, String taskDetail, Status taskStatus, LocalDate assignedDate) {
        this.projectDTO = projectDTO;
        this.assignedEmployee = assignedEmployee;
        this.taskSubject = taskSubject;
        this.taskDetail = taskDetail;
        this.taskStatus = taskStatus;
        this.assignedDate = assignedDate;
        this.id= UUID.randomUUID().getMostSignificantBits();
    }
}
