package org.acme.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.acme.model.Post;
import org.acme.model.PostDTO;
import org.acme.model.Tag;
import org.acme.model.TagDTO;
import org.acme.service.BlogService;

@Path("/api")
public class BlogController {
	
	@Inject
	BlogService blogService;
	
	private static Map<String, Long> tagMap = new HashMap<>();
	
	@GET
	@Path("/posts")
	public List<PostDTO> getPosts(){		
		List <Post> posts = blogService.getPosts();		
		List <PostDTO> postsDTO = new ArrayList<>();
		
		for (Post p : posts) {
			PostDTO postDTO = new PostDTO(p);
			postsDTO.add(postDTO);
		}
		
		return postsDTO;
	}
	
	@GET
	@Path("/posts/{id}")
	public PostDTO getPost(@PathParam("id") Long id){
		Post post = blogService.getPost(id);
		
		if (post != null) {
			PostDTO postDTO = new PostDTO(post);
			return postDTO;
		}
		return null;
	}
	
	@POST
	@Path("/posts")
	public List<PostDTO>  addPost(PostDTO postDTO) {
		blogService.fetchTag(tagMap);
		Post post = new Post(postDTO, tagMap);
		blogService.addPost(post);
		
		return getPosts();
	}
	
	@PUT
	@Path("/posts/{id}")
	public PostDTO updatePost(@PathParam("id") Long id, PostDTO postDTO){
		blogService.fetchTag(tagMap);
		Post post = new Post(postDTO, tagMap);
		blogService.updatePost(id, post);		
		
		return getPost(id);
	}
	
	@DELETE
	@Path("/posts/{id}")
	public List<PostDTO>  deletePost(@PathParam("id") Long id){
		blogService.deletePost(id);
		
		return getPosts(); 
	}
	
	@GET
	@Path("/tags")
	public List<TagDTO> getTags(){
		List <Tag> tags = blogService.getTags();
		List <TagDTO> tagsDTO = new ArrayList<>();
		
		for (Tag t : tags) {
			TagDTO tagDTO = new TagDTO(t);
			tagsDTO.add(tagDTO);
		}
		
		return tagsDTO;
	}
	
	@GET
	@Path("/tags/{id}")
	public TagDTO getTag(@PathParam("id") Long id){
		Tag tag = blogService.getTag(id);
		
		if (tag != null) {
			TagDTO tagDTO = new TagDTO(tag);
			return tagDTO;
		} else
			return null;
	}
	
	@POST
	@Path("/tags")
	public List<TagDTO>  addTag(TagDTO tagDTO) {
		Tag tag = new Tag(tagDTO);
		blogService.addTag(tag);
		
		return getTags();
	}
	
	@PUT
	@Path("/tags/{id}")
	public TagDTO updateTag(@PathParam("id") Long id, TagDTO tagDTO){
		Tag tag = new Tag(tagDTO);
		blogService.updateTag(id, tag);		
		
		return getTag(id);
	}
	
	@DELETE
	@Path("/tags/{id}")
	public List<TagDTO>  deleteTag(@PathParam("id") Long id){
		blogService.deleteTag(id);
		
		return getTags(); 
	}

}

