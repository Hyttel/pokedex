package com.example.pokemon.model;

public class Pokemon {

    public int id;
    public String name;
    public int speed;
    public int special_defence;
    public int special_attack;
    public int defence;
    public int attack;
    public int hp;
    public String primary_type;
    public String secondary_type;

    public Pokemon() {

    }
    public Pokemon(int id, String name, int speed, int special_defence, int special_attack, int defence, int attack, int hp, String primary_type, String secondary_type) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.special_defence = special_defence;
        this.special_attack = special_attack;
        this.defence = defence;
        this.attack = attack;
        this.hp = hp;
        this.primary_type = primary_type;
        this.secondary_type = secondary_type;
    }


}
