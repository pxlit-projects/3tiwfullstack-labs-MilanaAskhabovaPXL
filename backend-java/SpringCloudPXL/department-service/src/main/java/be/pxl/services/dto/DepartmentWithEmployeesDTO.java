package be.pxl.services.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentWithEmployeesDTO {
    private Long id;
    private Long organizationId;
    private String name;
    private List<EmployeeDto> employees;
}
