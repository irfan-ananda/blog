package org.acme.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static Map<String, Long> tagMap = new HashMap<>();
	
	
	public List<Post> getPosts(){
		return entityManager.createQuery("select p from Post p", Post.class).getResultList();		
	}
	
	public Post getPost(Long id){
		return entityManager.find(Post.class, id);
	}
	
	@Transactional
	public void addPost(Post post){	
		entityManager.merge(post);
	}
	
	@Transactional
	public void updatePost(Long id, Post post) {
		Post postToUpdate = getPost(id);
		if (post != null) {			
			postToUpdate.setTitle(post.getTitle());
			postToUpdate.setContent(post.getContent());
			postToUpdate.setTags(post.getTags());
			for (Tag t : postToUpdate.getTags()) {
				if (t.getId() == 0) {
					entityManager.persist(t);
				}
			}
					
		} else 
			System.out.println("Post not found");
	}
	
	@Transactional
	public void deletePost(Long id) {
		Post post = getPost(id);
		entityManager.remove(post);
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
	
	public void fetchTag(Map<String, Long> tagMap) {
		System.out.println("====fetchTag===");
		List<Tag> tags = getTags();
		System.out.println("tags: " + tags.size());
		for (Tag t: tags) {
			tagMap.put(t.getLabel(), t.getId());
		}
	}


}
