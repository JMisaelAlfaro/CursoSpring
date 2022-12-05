package com.bytecode.core.controllers;


import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bytecode.core.configuration.Paginas;
import com.bytecode.core.model.Post;

@Controller 
@RequestMapping("/home")// endpoint o subrutas

public class ControllerBasic {
	
	//metodo para generar una lista
	public List<Post> getPosts(){
		ArrayList<Post> post = new ArrayList<>(); //el Array tiene la lista de los post
		
		//se crea el post y se inserta un nuevo post
		post.add(new Post(1, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.", "http://localhost/img/post.jpeg", new Date(), "Desarrollo Web")); 
		post.add(new Post(2, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.", "http://localhost/img/post.jpeg", new Date(), "Desarrollo Web Front-end"));
		post.add(new Post(3, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.", "http://localhost/img/post.jpeg", new Date(), "Desarrollo Web Back-end"));
		post.add(new Post(4, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.", "http://localhost/img/post.jpeg", new Date(), "Desarrollo Web UX/UI"));
		
		return post; //regresa un arraylist
	}
	
	@GetMapping(path = {"/posts","/"}) //poder obtener recursos
	public String saludar(Model model) { //model es un interfaz
		model.addAttribute("posts", this.getPosts());
		return "index";
		
	}
	
	//Model and View
	/*@GetMapping(path="/public")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(Paginas.HOME);  //Model and View el nombre de la vista es index
		modelAndView.addObject("posts", this.getPosts());   // Agregar Atributos
		return modelAndView; // para retornar los post
	}
	*/
	
	@GetMapping(path = {"/post","/post/p/{post}"})
	public ModelAndView getPostIndividual(
			//@RequestParam(defaultValue = "1", name="id", required = true) 
			@PathVariable(required = true, name = "post") int id  //tipo de dto int
			 
			) {
		ModelAndView modelAndView = new ModelAndView(Paginas.POST); 
		
		//filtrar para la plantilla post
		List<Post> postFiltrado = this.getPosts().stream()
									  .filter((p) ->{   //p es el post
										return p.getId() == id;  
									  }).collect(Collectors.toList()); //recolectar con collector y se convierte a array list
		
		modelAndView.addObject("post",postFiltrado.get(0));
		
		return modelAndView;
		
	}

}
