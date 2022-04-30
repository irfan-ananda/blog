package org.acme.model;

import java.util.List;

import javax.persistence.*;


@Entity
@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p ORDER BY p.id")
public class Post {
	
    @Id
    @SequenceGenerator(name = "postSeq", sequenceName = "post_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "postSeq")
	private Long id;
	private String title;
	private String content;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Post_Tag", 
        joinColumns = { @JoinColumn(name = "post_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
	private List<Tag> tags;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
