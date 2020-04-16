package com.example.spring.boot.web.rest.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/file/download")
@Slf4j
public class FileDownloadController {

	private static final int STREAM_BUFFER_SIZE = 100;

	/**
	 * Downloads file synchronously, it's not recommended for large files, because
	 * it blocks the servlet thread till entire file is written to servlet response
	 * output stream.
	 * 
	 * If file size is very large this will fail with java.lang.OutOfMemoryError:
	 * Required array size too large
	 * 
	 * @param filePath filePath in request
	 * @return
	 * @throws IOException
	 */
	@GetMapping()
	public ResponseEntity<ByteArrayResource> downloadResource(@RequestParam(name = "file-path") String filePath)
			throws IOException {
		final Path path = Paths.get(filePath);
		ByteArrayResource fileSystemResource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString())
				.body(fileSystemResource);
	}

	/**
	 * Downloads the file asynchronously. It do not block the main thread. It
	 * streams the data to output servlet output stream.
	 * <p>
	 * This will not fail for large file size as it streams data and loads only
	 * STREAM_BUFFER_SIZE at a time into main memory.
	 * <p>
	 * 
	 * <br>
	 * <br>
	 * <b>Refer:</b>
	 * <li>https://medium.com/swlh/streaming-data-with-spring-boot-restful-web-service-87522511c071
	 * </li>
	 * <li>https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
	 * </li>
	 * 
	 * @param filePath filePath in request
	 * @param response streams the data
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/stream")
	public StreamingResponseBody streamResource(@RequestParam(name = "file-path") String filePath,
			final HttpServletResponse response) throws IOException {

		Path path = Paths.get(filePath);
		response.setContentType(Files.probeContentType(path));
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inlike; filename=" + path.getFileName().toString());

		log.info("Request Handling thread :: {}, This is Servlet container thread for Http Request Handling",
				Thread.currentThread().getName());

		return outputStream -> {
			log.info("Stream Handling thread, This will be from AsyncTaskExecutor :: {}",
					Thread.currentThread().getName());
			int bytesRead;
			byte[] bytes = new byte[STREAM_BUFFER_SIZE];
			try (final InputStream inputStream = new FileInputStream(filePath)) {
				while ((bytesRead = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, bytesRead);
				}
			}
		};
	}
}
