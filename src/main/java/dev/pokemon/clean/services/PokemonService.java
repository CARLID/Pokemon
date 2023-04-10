package dev.pokemon.clean.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import dev.pokemon.clean.domain.model.PokemonDTOEntity;
import dev.pokemon.clean.repository.IPokemonRepository;

@Service
public class PokemonService {

	@Autowired
	IPokemonRepository pokeminRepository;
	
	
	public ArrayList<PokemonDTOEntity> getPokemon(){
		return (ArrayList<PokemonDTOEntity>) pokeminRepository.findAll();
	}
	
	public PokemonDTOEntity savePokemon(PokemonDTOEntity pokemon) {
		return pokeminRepository.save(pokemon);
	}
	
	public Optional<PokemonDTOEntity> getByIdPokemon(Long idPokemon){
		return pokeminRepository.findById(idPokemon);
	}
	
	public PokemonDTOEntity updateByIdPokemon(PokemonDTOEntity request, Long idPokemon){
		PokemonDTOEntity pokemon = pokeminRepository.findById(idPokemon).get();
		
		pokemon.setDescription(request.getDescription());
		pokemon.setType(request.getType());
		
		return pokemon;
	}
	
	public Boolean deletePokemon (Long idPokemon) {
		try {
			pokeminRepository.deleteById(idPokemon);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
