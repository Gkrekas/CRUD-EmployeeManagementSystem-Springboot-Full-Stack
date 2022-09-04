package com.example.employee.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepo;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}


	@Override
	public List<Employee> gtAllEmployees() {
		return employeeRepo.findAll();
	}


	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepo.save(employee);
	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		Employee employee=null;
		if(emp.isPresent())
			employee=emp.get();
		else
			throw new RuntimeException("Can not find empoye with user id: "+id);
		
		return employee;
	}


	@Override
	public void deleteEmployeeById(long id) {
		employeeRepo.deleteById(id);
	}


	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo -1 , pageSize, sort);
		return this.employeeRepo.findAll(pageable);
	}
	

}
