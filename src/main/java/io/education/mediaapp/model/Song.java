package io.education.mediaapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "song")
@NamedNativeQuery(name = "songsByPlaylistId", query = "select id, name, playlist_id, cover_url, created_on from song s where s.playlist_id = ?", resultClass = Song.class)
public class Song {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private BigInteger id;

	@Column(name = "playlist_id")
	@JsonProperty("playlist_id")
	private BigInteger playlistId;

	private String name;

	@Column(name = "cover_url")
	@JsonProperty("cover_url")
	private String coverUrl;

	@Column(name = "created_on")
	@JsonProperty("created_on")
	private Date createdOn;

}