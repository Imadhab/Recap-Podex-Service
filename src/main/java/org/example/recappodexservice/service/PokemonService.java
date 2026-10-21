package org.example.recappodexservice.service;


import org.example.recappodexservice.DTO.FavoriteDTO;
import org.example.recappodexservice.DTO.PokeAbiResponse;
import org.example.recappodexservice.DTO.PokemonDTO;
import org.example.recappodexservice.DTO.UpdateNicknameDTO;
import org.example.recappodexservice.Pokemon;
import org.example.recappodexservice.Repositiry.PokemonRepository;
import org.example.recappodexservice.exception.CollectionNotFoundExpetion;
import org.example.recappodexservice.exception.PokemonNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PokemonService {

    public final RestClient restClient;

    private final PokemonRepository pokemonRepository;
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
            this.restClient = RestClient.builder()
                    .baseUrl("https://pokeapi.co/api/v2")
                    .build();
    }




    public PokemonDTO getPokemon(String name) {
        PokeAbiResponse pokemon;
        try {
            pokemon = restClient.get()
                    .uri("/pokemon/{name}", name)
                    .retrieve()
                    .body(PokeAbiResponse.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException("Pokemon not found");
        }

        if (pokemon == null) {
            throw new PokemonNotFoundException("Pokemon not found");
        }

        return new PokemonDTO(
                "1",
                String.valueOf(pokemon.id()),
                null,
                pokemon.name(),
                pokemon.sprites().other().officialArtwork().front_default(),
                pokemon.height(),
                pokemon.weight(),
                pokemon.types().stream()
                        .map(type -> type.type().name())
                        .toList()
        );

    }
    public Pokemon saveFavorite(FavoriteDTO favoriteDTO) {

        PokemonDTO pokemonDTO = getPokemon(favoriteDTO.pokemonName());

        Pokemon pokemon = new Pokemon();

        pokemon.setId(UUID.randomUUID().toString());
        pokemon.setPokemonId(pokemonDTO.pokemonId());
        pokemon.setNickname(favoriteDTO.nickname());
        pokemon.setPokemonName(pokemonDTO.pokemonName());
        pokemon.setPictureUrl(pokemonDTO.pictureUrl());
        pokemon.setHeight(pokemonDTO.height());
        pokemon.setWeight(pokemonDTO.weight());
        pokemon.setTypes(pokemonDTO.types());

        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> getAllFavorites(){
        return pokemonRepository.findAll();
    }

    public Pokemon getFavoriteById(String id){
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new CollectionNotFoundExpetion("Pokemon not found"));
    }

    public void deleteFavorite(String id){
        if (!pokemonRepository.existsById(id)){
            throw new CollectionNotFoundExpetion("Pokemon not found");
        }
        pokemonRepository.deleteById(id);
    }

    public Pokemon updateFavorite(String id, UpdateNicknameDTO dto) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new CollectionNotFoundExpetion("Pokemon not found"));
        pokemon.setNickname(dto.nickname());
        return pokemonRepository.save(pokemon);
    }
}
