package org.example.recappodexservice;


import org.example.recappodexservice.Repositiry.PokemonRepository;
import org.example.recappodexservice.exception.CollectionNotFoundExpetion;
import org.example.recappodexservice.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;
    @InjectMocks
    private PokemonService pokemonService;
    @Test
    void getFavoriteById_shouldThrowException_whenPokemonNotFound() {
        String id = "123";

        when(pokemonRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(CollectionNotFoundExpetion.class,
                () -> pokemonService.getFavoriteById(id)
        );
    }

    @Test
    void getFavoriteById_shouldThrowException_whenPokemonNotFoundById() {
        String id = "123";
        Pokemon pokemon = new Pokemon();
        pokemon.setId(id);
        pokemon.setNickname("Nickname");
        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemon));

        Pokemon result = pokemonService.getFavoriteById(id);

        assertEquals(id, result.getId());
        assertEquals("Nickname", result.getNickname());

    }


}
