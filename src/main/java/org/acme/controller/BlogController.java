package org.acme.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;


import org.acme.model.PostDTO;
import org.acme.model.Tag;
import org.acme.service.BlogService;

@Path("/api")
public class BlogController {
	
	@Inject
	BlogService blogService;
	
	
	@GET
	@Path("/posts")
	public List<PostDTO> posts(){
		return blogService.getPosts();
	}
	
	@POST
	@Path("/posts")
	public List<PostDTO>  addPost(PostDTO postDTO) {
		return blogService.addPost(postDTO);
	}
	
	@GET
	@Path("/tags")
	public List<Tag> tags(){
		return blogService.getTags();
	}
	

}

