package org.dev.barsukov.service;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface FileStorageService {

	Resource loadFileAsResource(String fileName) throws MalformedURLException;

}
