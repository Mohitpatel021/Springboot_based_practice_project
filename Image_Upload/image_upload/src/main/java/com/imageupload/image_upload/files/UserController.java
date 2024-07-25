package com.imageupload.image_upload.files;

import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imageupload.image_upload.UserResponse;

@RestController
public class UserController {

  @Autowired
  private UserRepo userRepository;

  
  @PutMapping(value="/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> saveData(
    @RequestParam("username") String username,
    @RequestParam("email") String email,
    @RequestParam(value="image",required=false) MultipartFile file
  ) {
    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setEmail(email);
    if(file!=null) {
    try {
      byte[] data = file.getBytes();
      //String fileName = file.getOriginalFilename();
     // String filedata = Base64.getEncoder().encodeToString(data);
      user.setImage(data);
    } catch (IOException e) {
      e.printStackTrace();
    }
    }else {
    	System.out.println("Filee Empty");
    }
    userRepository.save(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/user")
  public ModelAndView getUser() {
	  String username="mohit patel";
    UserEntity user = userRepository.findByUsername(username);
    UserResponse response = new UserResponse();
    response.setEmail(user.getEmail());
    response.setUsername(user.getUsername());
    response.setImage(user.getImage());
    ModelAndView modelAndView = new ModelAndView("user"); // Assuming "user.html" is your Thymeleaf template
    modelAndView.addObject("userResponse", response);
    return modelAndView;
   
  }
  
}
