package com.example.ryszhan;

public class Pet {

    private int id;
    private String name;
    private String species;
    private int age;
    private String color;

    public Pet() {}

    public Pet(String name, String species, int age, String color) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.color = color;
    }

    public Pet(int id, String name, String species, int age, String color) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.color = color;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}

