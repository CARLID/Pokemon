package dev.pokemon.clean.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dev.pokemon.clean.domain.model.PokemonDTOEntity;
import dev.pokemon.clean.services.PokemonService;

@Controller
@RequestMapping("/api")
public class pokemonControllersRosters {
	
	@Autowired
	private PokemonService pokemonService;
	
	
	@GetMapping
	public ModelAndView lookPageOfHome(Model modelo){
		
		List<PokemonDTOEntity> listaPokemon = pokemonService.getPokemon();
		//modelo.addAttribute("listaPokemon", listaPokemon);
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("listaPokemon", listaPokemon);
		modelView.setViewName("index");
		return modelView;
	}
	
	@GetMapping("/pokemon")
	public ArrayList<PokemonDTOEntity> getPokemon(){
		return this.pokemonService.getPokemon();
	}
	
	@PostMapping(
			value = {"/pokemon"},
	        consumes = {MediaType.APPLICATION_JSON_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public PokemonDTOEntity savePokemon(@RequestBody PokemonDTOEntity pokemon) {
		return this.pokemonService.savePokemon(pokemon);
	}
	
	@GetMapping(path = "/pokemon/{id}")
	public Optional<PokemonDTOEntity> getByIdPokemon(@PathVariable("id") Long idPokemon) {
		return this.pokemonService.getByIdPokemon(idPokemon);
	}
	
	@PutMapping
	public PokemonDTOEntity updateByIdPokemon(@RequestBody PokemonDTOEntity request,@PathVariable("id") Long idPokemon) {
		return this.updateByIdPokemon(request, idPokemon);
	}
	
	@DeleteMapping(path = "/pokemon/{id}")
	public String deletePokemon(@PathVariable("id") Long idPokemon) {
		boolean ok = this.pokemonService.deletePokemon(idPokemon);
		
		if(ok) {
			return "Pokemon with id" + idPokemon + "deleted";
		}else {
			return "Error deleted Pokemon id" + idPokemon;
		}
		
	}
	 
}
