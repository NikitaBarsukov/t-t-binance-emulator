package org.dev.barsukov.controller;

import lombok.AllArgsConstructor;
import org.dev.barsukov.service.FileStorageService;
import org.dev.barsukov.service.TransactionService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@AllArgsConstructor
@RestController
@RequestMapping("history/files")
public class FilesController {
	public final FileStorageService fileStorageService;

	@GetMapping("/{id}")
	ResponseEntity<Resource> downloadFile(@PathVariable String id) throws MalformedURLException {
		return ResponseEntity.ok()
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".json" + "\"")
				.body(fileStorageService.loadFileAsResource(id));
	}
}



