package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
        return mapToOrganizationResponse(organization);
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartments(Long id) {
        Organization organization = (Organization) organizationRepository.findByIdWithDepartment(id);
        return mapToOrganizationResponse(organization);
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
        Organization organization = organizationRepository.findByIdWithDepartmentsAndEmployees(id);
        return mapToOrganizationResponse(organization);
    }

    @Override
    public OrganizationResponse getByIdWithEmployees(Long id) {
        Organization organization = organizationRepository.findByIdWithEmployees(id);
        return mapToOrganizationResponse(organization);
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .employees(organization.getEmployees())
                .departments(organization.getDepartments())
                .address(organization.getAddress())
                .build();
    }

}
