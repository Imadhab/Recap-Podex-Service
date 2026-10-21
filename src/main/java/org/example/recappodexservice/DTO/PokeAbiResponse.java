package org.example.recappodexservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public record PokeAbiResponse(
        int id,
        String name,
        int height,
        int weight,
        Sprites sprites,
        List<Sprites.TypeSlot> types
) {

    public record Sprites(Other other){
        public record Other(
                @JsonProperty("official-artwork")
                OfficialArtwork officialArtwork
        ){}
            public record OfficialArtwork(
                    String front_default
            ){}
            public record TypeSlot(TypeInfo type){}
            public record TypeInfo(String name) {}

    }
}
