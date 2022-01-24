package com.tyt.rest.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tyt.dto.Trail;
import com.tyt.repositories.TrailRepository;
import com.tyt.rest.controllers.exceptions.DublicateTrailException;
import com.tyt.rest.controllers.exceptions.TrailNotFoundException;

//Main Controller
@CrossOrigin(originPatterns = "http://localhost:8081", allowCredentials = "false")
@RolesAllowed("USER")
@RestController
@RequestMapping(value = "/api/trails")
public class TytController {

	Logger logger = LoggerFactory.getLogger(TytController.class);
	
	@Autowired
	private TrailRepository trailRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String login() {
		logger.info("Handling logging");
		return "login";
	}	
	
	@RequestMapping(value = "/{title}", method = RequestMethod.GET)
	public Trail getTrailByTitle(@PathVariable String title) {
		logger.info("Getting a trail with title {}", title);
		return trailRepository.findByTitle(title);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Trail> getAllTrails() {
		logger.info("Getting all trails");
		return trailRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Trail addNewTrail(@RequestBody Trail trail) throws DublicateTrailException {
		Trail existingTrail = trailRepository.findByTitle(trail.getTitle());
		if (existingTrail != null) {
			throw new DublicateTrailException("Trail with given title already exists.");
		} else {
			logger.info("Saving trail with title {}", trail.getTitle());
			return trailRepository.save(trail);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Trail updateTrail(@RequestBody Trail trail) throws TrailNotFoundException {
		Optional<Trail> existingTrailOptional = trailRepository.findById(trail.getId());
		Trail existingTrail = existingTrailOptional.get();
		if (existingTrail != null) {
			existingTrail.setTitle(trail.getTitle());
			existingTrail.setIframe(trail.getIframe());
			logger.info("Updating the trail with title {}", existingTrail.getTitle());
			return trailRepository.save(existingTrail);
		} else {
			throw new TrailNotFoundException("Trail with given title couldn't be found.");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTrail(@PathVariable String id) throws IllegalArgumentException {
		logger.info("Deleting the trail with id {}", id);
		trailRepository.deleteById(id);
	}
}
