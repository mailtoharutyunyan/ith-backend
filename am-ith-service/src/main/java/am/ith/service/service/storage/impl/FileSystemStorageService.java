package am.ith.service.service.storage.impl;

import am.ith.service.config.StorageProperties;
import am.ith.service.exception.FileNotFoundException;
import am.ith.service.exception.StorageException;
import am.ith.service.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

  private final Path rootLocation;

  @Autowired
  public FileSystemStorageService(final StorageProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  @Override
  @PostConstruct
  public void init() {
    try {
      Files.createDirectories(this.rootLocation);
    } catch (final IOException e) {
      throw new StorageException("Could not initialize storage location", e);
    }
  }

  @Override
  public String store(final MultipartFile file) {
    final String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + filename);
      }
      if (filename.contains("..")) {
        // This is a security check
        throw new StorageException(
            "Cannot store file with relative path outside current directory " + filename);
      }
      try (final InputStream inputStream = file.getInputStream()) {
        Files.copy(
            inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (final IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }

    return filename;
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    } catch (final IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }
  }

  @Override
  public Path load(final String filename) {
    return this.rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(final String filename) {
    try {
      final Path file = this.load(filename);
      final Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new FileNotFoundException("Could not read file: " + filename);
      }
    } catch (final MalformedURLException e) {
      throw new FileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
  }
}
