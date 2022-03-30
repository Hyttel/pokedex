package com.example.pokemon.repository;

import com.example.pokemon.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/pokedex";
    private final static String UID = "pokemon_master";
    //<editor-fold desc="Description">
    private final static String PWD = "pokemon";
    //</editor-fold>
    private static Connection connection;

    public static void getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, UID, PWD);
            System.out.println("Connected succesfully");
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public List<Pokemon> readAll() {
        final String READ_SQL = "SELECT * FROM pokemon";
        List<Pokemon> pokemonList = new ArrayList<>();
        getConnection();
        try {
            Statement statement = connection.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery(READ_SQL);
            System.out.println("\nPokedex:\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("pokedex_number");
                String name = resultSet.getString("name");
                int speed = resultSet.getInt("speed");
                int special_defence = resultSet.getInt("special_defence");
                int special_attack = resultSet.getInt("special_attack");
                int defence = resultSet.getInt("defence");
                int attack = resultSet.getInt("attack");
                int hp = resultSet.getInt("hp");
                String primary_type = resultSet.getString("primary_type");
                String secondary_type = resultSet.getString("secondary_type");

                System.out.println("Pokedex number: " + resultSet.getInt("pokedex_number") + "\n" + "Name: "
                        + resultSet.getString("name") + "\n");
                pokemonList.add(new Pokemon(id, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type));
            }
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return pokemonList;
    }

    public void addPokemon(Pokemon pokemon) {
        //connect
        getConnection();
        try {
            //prep Statement
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ;pokemon(name, speed) VALUES (?, ?)");

            //set Attributer
            preparedStatement.setString(1, pokemon.name);
            preparedStatement.setInt(2, pokemon.speed);

            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("ERROR");
            sqlException.printStackTrace();
        }
    }

    public void deletePokemonByID(int id) {
        //Connect
        getConnection();

        try{
            //Create preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pokemon WHERE pokedex_number = ?");

            //set parameter
            preparedStatement.setInt(1, id);

            //execute statement
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println("ERROR");
            sqlException.printStackTrace();
        }

    }

}
