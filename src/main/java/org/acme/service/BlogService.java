package org.acme.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.acme.model.Post;
import org.acme.model.PostDTO;
import org.acme.model.Tag;

//@Transactional
@ApplicationScoped
public class BlogService {
	
	@Inject
    EntityManager entityManager;
	
	
	public List<PostDTO> getPosts(){
		System.out.println("====getPost===");

		List <Post> posts = entityManager.createQuery("select p from Post p", Post.class).getResultList();
		List <PostDTO> postsDTO = new ArrayList<>();
		for (Post p : posts) {
			PostDTO postDTO = new PostDTO(p);
			postsDTO.add(postDTO);
		}
		return postsDTO;
	}
	
	@Transactional
	public List<PostDTO> addPost(PostDTO postDTO){
		System.out.println("====addPost===");
		Post post = new Post(postDTO);
		entityManager.merge(post);
		return getPosts();
	}
	
	public List<Tag> getTags(){
		return entityManager.createNamedQuery("Tag.findAll", Tag.class).getResultList();
	}
	
//	@Transactional
	public void addTag(String label){
		System.out.println("====addTag===");
		Tag tag = new Tag();
		tag.setLabel(label);
		entityManager.persist(tag);
	}

}
