package manytomany.manytomany.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import manytomany.manytomany.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByEmpId(Long empId);
}
