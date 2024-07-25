package excel_to_database.excel__to_database.Helper;

import excel_to_database.excel__to_database.Entity.UserEntity;
import excel_to_database.excel__to_database.Exception.ProcessingException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {

  //static Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

  //for checking the File type is it in proper excel type or not
  public static boolean checkExcelFormat(MultipartFile file) {
    //from this line we got the content type of the file
    String contentType = file.getContentType();
    if (
      contentType != null &&
      contentType.equals(
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      )
    ) {
      return true;
    } else {
      return false;
    }
  }

  public static List<UserEntity> convertExcelToListOfUser(
    InputStream inputStream
  ) {
    List<UserEntity> list = new ArrayList<>();
    try {
      try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
        XSSFSheet sheet = workbook.getSheet("Data");
        if (sheet == null) {
          throw new ProcessingException(
            "Sheet 'Data' is Not Found in the WorkBook"
          );
        }
        int rowNumber = 0;
        //this will return the rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
          Row row = iterator.next();
          //if row number is zero we need to leave that row
          if (rowNumber == 0) {
            rowNumber++;
            continue;
          }
          //this iterator is help to fetch cells from the row
          Iterator<Cell> cell = row.iterator();
          int cID = 0;
          UserEntity user = new UserEntity();
          while (cell.hasNext()) {
            Cell cells = cell.next();
            switch (cID) {
              case 0:
                user.setId((int) cells.getNumericCellValue());
                break;
              case 1:
                user.setMobile((double) cells.getNumericCellValue());
                break;
              case 2:
                user.setGender(cells.getStringCellValue());
                break;
              case 3:
                user.setAge((int) cells.getNumericCellValue());

                break;
              case 4:
                user.setVip(cells.getStringCellValue());
                break;
              case 5:
                user.setArea(cells.getStringCellValue());
                break;
              case 6:
                user.setStandard(cells.getStringCellValue());
                break;
              case 7:
                user.setProfession(cells.getStringCellValue());
                break;
              default:
                break;
            }
            cID++;
          }
          list.add(user);
        }
      }
    } catch (Exception e) {
     // e.printStackTrace();
      throw new ProcessingException(
        "Error While Processing Excel File" + e.getMessage()
      );
    }
    return list;
  }
  public static List<UserEntity> convertTextToListOfUser(Path filePath)
    throws IOException {
    List<String> lines = Files.readAllLines(filePath);
    List<UserEntity> lists = new ArrayList<>();

    for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
      String line = lines.get(lineNumber);
      String[] values = line.split(",");
      UserEntity user = new UserEntity(); 
      try {
        user.setId(Integer.parseInt(values[0].trim()));
      } catch (NumberFormatException e) {
        String errorMessage = String.format(
          "Error processing 'id' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }
      try {
        user.setMobile(Double.parseDouble(values[1].trim()));
      } catch (NumberFormatException e) {
        String errorMessage = String.format(
          "Error processing 'mobile' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }
      try {
        user.setGender(values[2].trim());
      } catch (Exception e) {
        String errorMessage = String.format(
          "Error processing 'gender' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }

      try {
        user.setAge(Integer.parseInt(values[3].trim()));
      } catch (NumberFormatException e) {
        String errorMessage = String.format(
          "Error processing 'age' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }

      try {
        user.setVip(values[4].trim());
      } catch (Exception e) {
        String errorMessage = String.format(
          "Error processing 'vip' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }

      try {
        user.setArea(values[5].trim());
      } catch (Exception e) {
        String errorMessage = String.format(
          "Error processing 'area' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }

      try {
        user.setStandard(values[6].trim());
      } catch (Exception e) {
        String errorMessage = String.format(
          "Error processing 'standard' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }

      try {
        user.setProfession(values[7].trim());
      } catch (Exception e) {
        String errorMessage = String.format(
          "Error processing 'profession' field in LineNumber %d: Missing or invalid value",
          lineNumber + 1
        );
        throw new ProcessingException(errorMessage, e);
      }

      lists.add(user);
    }

    return lists;
  }
}
