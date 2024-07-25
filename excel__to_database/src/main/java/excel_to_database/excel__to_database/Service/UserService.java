package excel_to_database.excel__to_database.Service;

import excel_to_database.excel__to_database.Entity.UserEntity;
import excel_to_database.excel__to_database.Helper.ExcelHelper;
import excel_to_database.excel__to_database.Repository.UserRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
//this is for excel data file
  public void save(MultipartFile file) throws IOException {
    
      List<UserEntity> userData = ExcelHelper.convertExcelToListOfUser(
        file.getInputStream()
      );
      this.userRepository.saveAll(userData);
    
  }

  //this is for the text data file
  public void saveTextStudents(Path filePath) throws IOException {
    
      List<UserEntity> studentData = ExcelHelper.convertTextToListOfUser(
        filePath
      );
      this.userRepository.saveAll(studentData);
    
  }
//to fetch all the students
  public List<UserEntity> getAllUser() {
    return this.userRepository.findAll();
  }
}
