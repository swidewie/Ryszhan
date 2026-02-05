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

    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id) {
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new RuntimeException("Pet with ID " + id + " not found");
        }
        return pet;
    }

    @PostMapping
    public String createPet(@RequestBody Pet pet) {
        petRepository.save(pet);
        return "Pet created successfully!";
    }

    @PutMapping("/{id}/color")
    public String updateColor(@PathVariable int id, @RequestParam String color) {
        boolean updated = petRepository.updateColor(id, color);
        if (updated) {
            return "Pet color updated to: " + color;
        } else {
            return "Pet not found or update failed";
        }
    }

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

