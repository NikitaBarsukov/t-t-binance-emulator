package org.dev.barsukov.service.impl;

import org.dev.barsukov.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	private static String DEFAULT_HISTORY_FILE_DIR = "user-history-files";

	@Override
	public Resource loadFileAsResource(String fileName) throws MalformedURLException {
		String path = System.getenv("EMULATOR_HISTORY_DIR_ABS") != null
					  ? System.getenv("EMULATOR_HISTORY_DIR_ABS")
					  : DEFAULT_HISTORY_FILE_DIR;
		Resource resource = new UrlResource(Paths.get(path).toUri() + fileName + ".json");
		if(resource.exists()) {
			return resource;
		} else {
			throw new RuntimeException("File not found path" +  path);
		}
	}
}
