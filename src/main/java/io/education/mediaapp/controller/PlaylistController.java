package io.education.mediaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.education.mediaapp.service.PlaylistService;

@Controller
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;

	@GetMapping("/")
	public String listPlaylistAndSongs(Model model) {
		model.addAttribute("playlists", playlistService.getAllPlaylists());
		return "index";
	}

}
