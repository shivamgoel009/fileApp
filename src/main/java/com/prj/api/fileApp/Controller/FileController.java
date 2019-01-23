package com.prj.api.fileApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prj.api.fileApp.Exception.FileProcessingException;
import com.prj.api.fileApp.Service.FileService;

@RestController
public class FileController {
	private Logger log = LoggerFactory.getLogger(FileController.class);

	@Autowired
	FileService fileService;

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String getCommonWordsFromFiles() {
		return "Server running";
	}

	@RequestMapping(value = "/commonWords", method = RequestMethod.POST)
	public ResponseEntity<List<String>> getCommonWordsFromFiles(@RequestBody String path)
			throws FileProcessingException {
		try {
			List<String> commonWords = new ArrayList<>();
			commonWords = fileService.getCommonWordsFromFiles(path);

			return new ResponseEntity<List<String>>(commonWords, HttpStatus.OK);

		} catch (Exception e) {
			throw new FileProcessingException(e.getMessage(), e);
		}
	}

}
