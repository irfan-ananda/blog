package org.acme.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.acme.model.Post;
import org.acme.model.Tag;


//@Transactional
@ApplicationScoped
public class BlogService {
	
	@Inject
    EntityManager entityManager;
	
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
		if (postToUpdate != null) {			
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
	
	public Tag getTag(Long id) {
		return entityManager.find(Tag.class, id);
	}
	
	@Transactional
	public void addTag(Tag tag){
		entityManager.persist(tag);
	}
	
	@Transactional
	public void updateTag(Long id, Tag tag){
		Tag tagToUpdate = getTag(id);
		if (tagToUpdate != null) 
			tagToUpdate.setLabel(tag.getLabel());
	}
	
	@Transactional
	public void deleteTag(Long id) {
		Tag tag = getTag(id);
		entityManager.remove(tag);
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
