package org.acme.model;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t ORDER BY t.id")
public class Tag {
	
    @Id
    @SequenceGenerator(name = "postSeq", sequenceName = "post_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "postSeq")
	private long id;
	
    @Column(unique = true)
	private String label;
	
	@ManyToMany(mappedBy = "tags")
	private List<Post> posts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamge() {
		return label;
	}
	public void setNamge(String namge) {
		this.label = namge;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
