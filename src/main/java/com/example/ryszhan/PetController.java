package com.example.ryszhan;

import com.example.ryszhan.PetRepository;
import com.example.ryszhan.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    // 1. GET ALL PETS
    // URL: GET http://localhost:8080/pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // 2. GET ONE PET
    // URL: GET http://localhost:8080/pets/1
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id) {
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new RuntimeException("Pet with ID " + id + " not found");
        }
        return pet;
    }

    // 3. CREATE PET
    // URL: POST http://localhost:8080/pets
    @PostMapping
    public String createPet(@RequestBody Pet pet) {
        petRepository.save(pet);
        return "Pet created successfully!";
    }

    // 4. UPDATE PET COLOR
    // URL: PUT http://localhost:8080/pets/1/color?color=Red
    @PutMapping("/{id}/color")
    public String updateColor(@PathVariable int id, @RequestParam String color) {
        boolean updated = petRepository.updateColor(id, color);
        if (updated) {
            return "Pet color updated to: " + color;
        } else {
            return "Pet not found or update failed";
        }
    }

    // 5. DELETE PET
    // URL: DELETE http://localhost:8080/pets/1
    @DeleteMapping("/{id}")
    public String deletePet(@PathVariable int id) {
        boolean deleted = petRepository.deleteById(id);
        if (deleted) {
            return "Pet deleted successfully!";
        } else {
            return "Pet not found or delete failed";
        }
    }
}

