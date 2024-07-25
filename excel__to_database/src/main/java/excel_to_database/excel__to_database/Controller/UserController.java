package excel_to_database.excel__to_database.Controller;

import excel_to_database.excel__to_database.Entity.UserEntity;
import excel_to_database.excel__to_database.Exception.ProcessingException;
import excel_to_database.excel__to_database.Helper.ExcelHelper;
import excel_to_database.excel__to_database.Service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/Upload")
  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
    throws IOException {
    if (ExcelHelper.checkExcelFormat(file)) {
      this.userService.save(file);
      return ResponseEntity
        .status(HttpStatus.OK)
        .body("Data Successfully saved !!");
    }
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body("Please Upload Excel File");
  }

  @PostMapping("uploadText")
  public ResponseEntity<?> uploadTextFile(
    @RequestParam("file") MultipartFile file
  ) throws IOException {
    if (
      file == null ||
      file.getOriginalFilename() == null ||
      file.getOriginalFilename().isEmpty()
    ) {
      throw new ProcessingException("Please Upload the file First!!");
    }
    Path tempFile = Files.createTempFile("temp", null);
    file.transferTo(tempFile);
    this.userService.saveTextStudents(tempFile);
    Files.delete(tempFile);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body("Data Is saved Successfully");
  }

  @GetMapping("/fetchAll")
  public ResponseEntity<List<UserEntity>> getAllUser() {
    List<UserEntity> users = userService.getAllUser();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
