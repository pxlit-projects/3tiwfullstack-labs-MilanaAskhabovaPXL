package be.pxl.services.services;

import be.pxl.services.client.EmployeeFeignClient;
import be.pxl.services.domain.Department;
import be.pxl.services.dto.DepartmentWithEmployeesDTO;
import be.pxl.services.dto.EmployeeDto;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeFeignClient employeeFeignClient;

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> getDepartmentsByOrganization(Long organizationId) {
        return departmentRepository.findByOrganizationId(organizationId);
    }

    @Override
    public List<DepartmentWithEmployeesDTO> getDepartmentsWithEmployeesByOrganization(Long organizationId) {
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId);

        return departments.stream()
                .map(department -> {
                    List<EmployeeDto> employees = employeeFeignClient.getEmployeesByDepartment(department.getId());

                    return DepartmentWithEmployeesDTO.builder()
                            .id(department.getId())
                            .organizationId(department.getOrganizationId())
                            .name(department.getName())
                            .employees(employees)
                            .build();
                })
                .collect(Collectors.toList());
    }
}