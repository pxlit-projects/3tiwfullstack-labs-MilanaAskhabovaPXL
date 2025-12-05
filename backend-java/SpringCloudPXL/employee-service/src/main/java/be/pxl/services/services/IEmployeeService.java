package be.pxl.services.services;

import be.pxl.services.domain.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByDepartment(Long departmentId);
    List<Employee> getEmployeesByOrganization(Long organizationId);
}