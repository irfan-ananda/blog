package org.acme.model;

import java.util.ArrayList;
import java.util.List;

public class TagDTO {
	
	String label;	
	List <String> posts = new ArrayList<>();
	
	public TagDTO() {
	}
	
	public TagDTO(Tag tag) {
		this.label = tag.getLabel();
		this.posts = new ArrayList<>();
		if(tag.getPosts() != null) {
			for (Post p : tag.getPosts()) {
				this.posts.add(p.getTitle());
			}
		}
			
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<String> getPosts() {
		return posts;
	}
	public void setPosts(List<String> posts) {
		this.posts = posts;
	}

}
