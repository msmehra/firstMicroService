package io.education.mediaapp.database;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.education.mediaapp.model.Playlist;
import io.education.mediaapp.model.Song;

public interface PlaylistRepository extends JpaRepository<Playlist, BigInteger> {

	public Optional<Playlist> findByName(String name);

	@Query("select s from Song s where s.playlistId = ?1")
	public Collection<Song> getSongs(BigInteger playlistId);

	@Query(value = "select name from song where playlist_id = ?", nativeQuery = true)
	public List<String> getSongsUsingNativeQuery(BigInteger playlistId);

}