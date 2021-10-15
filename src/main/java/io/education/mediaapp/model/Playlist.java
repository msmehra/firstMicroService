package io.education.mediaapp.model;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "playlist")
public class Playlist {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private BigInteger id;

	private String name;

	@Column(name = "created_on")
	@JsonProperty("created_on")
	private Date createdOn;

	@ElementCollection(targetClass = java.util.HashSet.class)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Collection<Song> songs;

}