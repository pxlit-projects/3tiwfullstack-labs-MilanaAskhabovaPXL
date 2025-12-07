package be.pxl.services.controller;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService departmentService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        departmentService.addDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse findById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/organization/{organizationId}")
    public List<DepartmentResponse> findByOrganization(@PathVariable Long organizationId) {
        return departmentService.getDepartmentsByOrganization(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<DepartmentResponse> findByOrganizationWithEmployees(@PathVariable Long organizationId) {
        return departmentService.getDepartmentsWithEmployeesByOrganization(organizationId);
    }
}