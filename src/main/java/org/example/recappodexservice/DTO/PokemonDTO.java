package org.example.recappodexservice.DTO;

import java.util.List;

public record PokemonDTO(
        String id,
        String pokemonId,
        String nickname,
        String pokemonName,
        String pictureUrl,
        int height,
        int weight,
        List<String> types

) {
}
