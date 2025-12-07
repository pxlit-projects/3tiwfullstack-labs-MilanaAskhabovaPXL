package be.pxl.services.services;

import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    OrganizationResponse getOrganizationById(Long id);

    OrganizationResponse getOrganizationByIdWithDepartments(Long id);

    OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id);
    OrganizationResponse getByIdWithEmployees(Long id);
}
