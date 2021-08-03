package com.bd.chia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bd.chia.jpa.Partial;
import com.bd.chia.repository.PartialRepository;

@RestController
@RequestMapping(path = "/partial")
public class PartialController {
	@Autowired
	PartialRepository partialRepository;
	
	@RequestMapping(value="/{launcherId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Partial> getPartials(@PathVariable String launcherId) {
		return partialRepository.findByLauncherIdOrderByTimestampDesc(launcherId);
	}
}
