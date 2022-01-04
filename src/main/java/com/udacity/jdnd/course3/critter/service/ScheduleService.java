package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds){
        List<Employee> employees = new ArrayList<>();
        if (employeeIds != null && !employeeIds.isEmpty()) {
            employees = employeeIds.stream().map((employeeId) -> employeeRepository.getOne(employeeId)).collect(Collectors.toList());
        }
        schedule.setEmployees(employees);

        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map((petId) -> petRepository.getOne(petId)).collect(Collectors.toList());
        }
        schedule.setPets(pets);

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByPetId(Long petId){
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.findByPets(pet);
    }

    public List<Schedule> getScheduleByEmployeeId(Long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.findByEmployees(employee);
    }

    public List<Schedule> getScheduleByCustomerId(Long customerId){
        Customer customer = customerRepository.getOne(customerId);
        return scheduleRepository.findByPetsIn(customer.getPets());
    }
}
