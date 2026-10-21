package org.example.recappodexservice.controller;


import jakarta.validation.Valid;
import org.example.recappodexservice.DTO.FavoriteDTO;
import org.example.recappodexservice.DTO.PokemonDTO;
import org.example.recappodexservice.DTO.UpdateNicknameDTO;
import org.example.recappodexservice.Pokemon;
import org.example.recappodexservice.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon/{name}")
    public PokemonDTO getPokemon(@PathVariable String name){
        return pokemonService.getPokemon(name);
    }

    @PostMapping("/collection")
    public Pokemon saveFavorite(@Valid @RequestBody FavoriteDTO favoriteDTO){
        return pokemonService.saveFavorite(favoriteDTO);
    }

    @GetMapping("/collection")
    public List<Pokemon> getAllFavorites(){
        return pokemonService.getAllFavorites();
    }

    @GetMapping("collection/{id}")
    public Pokemon getFavoriteById(@PathVariable String id){
        return pokemonService.getFavoriteById(id);
    }

    @DeleteMapping("collection/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavoriteById(@PathVariable String id){
        pokemonService.deleteFavorite(id);
    }

    @PutMapping("/collection/{id}")
    public Pokemon updateNickname(
            @PathVariable String id, @
                    Valid @RequestBody UpdateNicknameDTO dto){
        return pokemonService.updateFavorite(id, dto);
    }

}
