package io.education.mediaapp.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.education.mediaapp.database.PlaylistRepository;
import io.education.mediaapp.database.SongsRepository;
import io.education.mediaapp.exception.PlaylistNotFoundException;
import io.education.mediaapp.exception.SongNotFoundException;
import io.education.mediaapp.model.Playlist;
import io.education.mediaapp.model.Song;

@Service("playlistService")
public class PlaylistService {

	@Autowired
	private PlaylistRepository playlistRepo;

	@Autowired
	private SongsRepository songRepo;

	public Iterable<Playlist> getAllPlaylists() {
		//System.out.println("I AM HERE *******");
		//Object results = playlistRepo.findAll();
		//System.out.println("RESULTS ARE HERE: " + playlistRepo.findAll());
		return playlistRepo.findAll();
	}

	public Playlist getPlaylistById(BigInteger playlistId) {
		return getPlaylist(playlistId);
	}

	public Optional<Playlist> createPlaylist(String name) {
		Playlist playlist = new Playlist();
		playlist.setName(name);
		playlist.setCreatedOn(new Date());
		return Optional.of(playlistRepo.save(playlist));
	}

	public void deletePlaylist(BigInteger playlistId) {
		Playlist playlist = getPlaylist(playlistId);
		playlist.setId(playlistId);
		playlistRepo.delete(playlist);
	}

	public Iterable<Song> getSongs(BigInteger playlistId) {
		if (playlistId == null) {
			return songRepo.findAll();
		}
		playlistRepo.getSongs(playlistId);
		Playlist playlist = getPlaylist(playlistId);
		return playlistRepo.getSongs(playlist.getId());
	}

	public void deleteSongs(BigInteger playlistId) {
		Playlist playlist = getPlaylist(playlistId);
		songRepo.deleteByPlaylistId(playlist.getId());
	}

	public Song addSong(BigInteger playlistId, Song song) {
		Playlist playlist = getPlaylist(playlistId);
		song.setPlaylistId(playlist.getId());
		song.setCreatedOn(new Date());
		return songRepo.save(song);
	}

	public boolean moveSong(BigInteger songId, BigInteger toPlaylistId) {
		Song song = getSong(songId);
		Playlist playlist = getPlaylist(toPlaylistId);
		return 1 == songRepo.updatePlaylist(song.getId(), playlist.getId());
	}

	public void deleteSong(BigInteger playlistId, BigInteger songId) {
		Song song = getSong(songId);
		songRepo.delete(playlistId, song.getId());
	}

	private Playlist getPlaylist(final BigInteger playlistId) {
		return playlistRepo.findById(playlistId)
				.orElseThrow(() -> new PlaylistNotFoundException(playlistId));
	}

	private Song getSong(final BigInteger songId) {
		return songRepo.findById(songId).orElseThrow(() -> new SongNotFoundException(songId));
	}

}
