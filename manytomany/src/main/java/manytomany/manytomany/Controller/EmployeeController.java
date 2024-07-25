package manytomany.manytomany.Controller;

import java.util.List;
import manytomany.manytomany.Entity.Employee;
import manytomany.manytomany.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  //By using request body it makes the program much efficient when it push into the production
  //By using path variable we always need to send a data in the url that makes the program less efficient and we must have to send the data to the variable. Without variable data we are not able to fetch or do other operation on that particular endpoint
  @PostMapping("/save")
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee empObj) {
    employeeService.saveEmployee(empObj);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(value = { "/getEmployees", "/{empId}" })
  public List<Employee> getEmployee(
    @PathVariable(required = false) Long empId
  ) {
    return employeeService.getEmployeeDetails(empId);
  }

  @DeleteMapping("delete/{empId}")
  public ResponseEntity<String> removeEmployee(@PathVariable Long empId) {
    employeeService.deleteEmployee(empId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{empId}/project/{projectId}")
  public Employee assignProjectToEmployee(
    @PathVariable Long empId,
    @PathVariable Long projectId
  ) {
    return employeeService.assignProjectToEmployee(empId, projectId);
  }
}
