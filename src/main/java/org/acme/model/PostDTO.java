package org.acme.model;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
	
	private String title;
	private String content;
	private List<String> tags =  new ArrayList<>();
	
	public PostDTO() {		
	}
	
	public PostDTO(Post post) {
		this.title = post.getTitle();
		this.content = post.getContent();
		if (post.getTags() != null) {
			for (Tag t : post.getTags()) {			
				this.tags.add(t.getLabel());
			}
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public void addTag(String label) {
		this.tags.add(label);		
	}

}
