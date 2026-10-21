package org.example.recappodexservice.Repositiry;

import org.example.recappodexservice.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository  extends MongoRepository<Pokemon, String> {
}
