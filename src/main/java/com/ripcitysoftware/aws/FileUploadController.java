package com.ripcitysoftware.aws;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

  private S3StorageService storageService;

  @Autowired
  public FileUploadController(S3StorageService storageService) {
    this.storageService = storageService;
  }

  @GetMapping("/")
  @ResponseBody
  public ResponseEntity<Model> listUploadedFiles(Model model) throws IOException {

    model.addAttribute("files", storageService.listObjects());

    return ResponseEntity.ok(model);
  }

  @ExceptionHandler(StorageException.class)
  public ResponseEntity<?> handleStoreageFileNotFound(StorageException e) {
    return ResponseEntity.badRequest().build();
  }
}
