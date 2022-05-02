package org.acme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	@ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "Post_Tag", 
        joinColumns = { @JoinColumn(name = "post_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "tag_label") }
    )
	private List<Tag> tags = new ArrayList<>();;
    
    public Post() {    	
    }
	
	public Post(PostDTO postDTO, Map<String, Long> tagMap) {
		this.title = postDTO.getTitle();
		this.content = postDTO.getContent();
		if (postDTO.getTags() != null) {
			for (String label : postDTO.getTags()) {		
				Tag tag = new Tag();
				Long i =  tagMap.get(label);
				System.out.println("=======");
				System.out.println("label: " + i);
				tag.setLabel(label);		
				if (i != null)
					tag.setId(i);
				this.tags.add(tag);
			}
		}
	}
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
