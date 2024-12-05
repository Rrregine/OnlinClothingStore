package com.onlineclothingstore.employees.dataaccesslayer.department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByDepartmentIdentifier_DepartmentId(String departmentId);
}