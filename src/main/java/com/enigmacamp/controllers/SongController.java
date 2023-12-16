package com.enigmacamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.dao.SongDao;
import com.enigmacamp.dto.CommonResponse;
import com.enigmacamp.dto.SongFormDto;
import com.enigmacamp.entities.Song;
import com.enigmacamp.utils.ObjectMapperUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping("songs")
@Api(tags = "Songs")
public class SongController {

	@Autowired
	SongDao songDao;

	@GetMapping("")
	@ApiOperation(value = "Return list of song.", code = 200)
	public ResponseEntity<CommonResponse<List<Song>>> findAll(@RequestParam(required = false) String title) {

		if (title != null) {
			List<Song> songs = songDao.findByTitle(title);
			return new ResponseEntity<>(new CommonResponse<List<Song>>(songs), HttpStatus.OK);
		} else {
			List<Song> songs = songDao.findAll();
			return new ResponseEntity<>(new CommonResponse<List<Song>>(songs), HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Return a song by their identifier. 404 if does not exist.", code = 200)
	public ResponseEntity<CommonResponse<Song>> findById(@PathVariable String id) throws NotFoundException {

		Song song = songDao.findById(id);
		return new ResponseEntity<>(new CommonResponse<Song>(song), HttpStatus.OK);

	}

	@PostMapping("")
	@ApiOperation("Create new songs.")
	public ResponseEntity<CommonResponse<Song>> create(@RequestBody SongFormDto form) throws NotFoundException {

		Song song = songDao.create(form);
		return new ResponseEntity<>(new CommonResponse<Song>("201", "OK", song),
				HttpStatus.CREATED);

	}

	@PutMapping("")
	@ApiOperation("Update a song.")
	public ResponseEntity<CommonResponse<Song>> update(@RequestBody SongFormDto song) throws NotFoundException {

		Song updatedSong = songDao.update(ObjectMapperUtils.map(song, Song.class));
		return new ResponseEntity<>(new CommonResponse<Song>(updatedSong), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete song by their identifier.")
	public ResponseEntity<CommonResponse<Song>> delete(@PathVariable String id) throws NotFoundException {

		songDao.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
