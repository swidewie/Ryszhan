package com.example.ryszhan;

import com.example.ryszhan.db;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository {

    public List<Pet> findAll() {
        List<Pet> list = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pet p = new Pet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("color")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Pet findById(int id) {
        String sql = "SELECT * FROM pet WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("color")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Pet pet) {
        String sql = "INSERT INTO pet(name, species, age, color) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getColor());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateColor(int id, String color) {
        String sql = "UPDATE pet SET color=? WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, color);
            stmt.setInt(2, id);
            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM pet WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();
            return deleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
