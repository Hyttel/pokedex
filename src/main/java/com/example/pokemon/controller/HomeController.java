package com.example.pokemon.controller;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    PokemonRepository pokemonRepository;

    public HomeController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pokemon", pokemonRepository.readAll());
        return "index";
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Frederik Klarskov");
        model.addAttribute("class", "Dat21b");
        return "thymeleaf";
    }

    @GetMapping("/formtest")
    public String showFormTest() {
        return "formtest";
    }

    @PostMapping("/formtest")
    public String formtest(@RequestParam("name") String navn, @RequestParam("class") String kodeord, Model model) {
        System.out.println(navn + ", " + kodeord);
        model.addAttribute("mitNavn", navn);
        model.addAttribute("hemmeligtKodeord", kodeord);
        return "formtest";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public String createPokemon(@RequestParam("name") String name, @RequestParam("speed") int speed, @RequestParam("special_defence") int special_defence, @RequestParam("special_attack") int special_attack,) {
        Pokemon newPokemon = new Pokemon();

        pokemonRepository.addPokemon(newPokemon);

        //Vis liste over Pokemoner
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePokemon(@PathVariable("id") int slettetID) {
        pokemonRepository.deletePokemonByID(slettetID);
        return "redirect:/";
    }
}
