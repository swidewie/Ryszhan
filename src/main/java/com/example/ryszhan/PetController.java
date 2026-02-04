package com.example.ryszhan;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")  // base URL
public class PetController {

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // 1️⃣ GET all pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // 2️⃣ GET single pet by ID
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id) {
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new RuntimeException("Pet not found with ID: " + id);
        }
        return pet;
    }
}


