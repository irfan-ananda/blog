package org.acme.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t ORDER BY t.id")
public class Tag {
	
    @Id
    @SequenceGenerator(name = "tagSeq", sequenceName = "tag_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "tagSeq")
	private long id;
	
    @Column(unique = true)
	private String label;
	
	@ManyToMany(mappedBy = "tags", cascade = {CascadeType.MERGE})
    private List<Post> posts = new ArrayList<>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
