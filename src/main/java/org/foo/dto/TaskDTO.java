package org.foo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foo.utils.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private ProjectDTO projectDTO;
    private UserDTO assignedEmployee;
    private String taskSubject;
    private Status taskStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate assignedDate;

}
