package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet, Long ownerId){
        //System.out.println("Aqui recupera el id: " + ownerId);
        Customer customer = customerRepository.getOne(ownerId);
        customer.addPet(pet);
        pet.setOwner(customer);
        //System.out.println("La mascota ha guardar es: " + pet);
        Pet petSaved = petRepository.save(pet);
        //System.out.println("Pet FGuardada:" + petSaved );
        return petSaved;
    }

    public Pet getPetById(Long petId){
        return petRepository.getOne(petId);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerId){
        return petRepository.findPetByOwnerId(ownerId);
    }

}
