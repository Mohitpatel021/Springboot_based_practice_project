package excel_to_database.excel__to_database.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
public class UserEntity {

  @Id
  private Integer Id;

  private Double mobile;

  private String gender;

  private Integer age;

  private String vip;

  private String area;

  private String standard;

  private String profession;

  public UserEntity() {}

  public UserEntity(
    Integer id,
    Double mobile,
    String gender,
    Integer age,
    String vip,
    String area,
    String standard,
    String profession
  ) {
    Id = id;
    this.mobile = mobile;
    this.gender = gender;
    this.age = age;
    this.vip = vip;
    this.area = area;
    this.standard = standard;
    this.profession = profession;
  }

  public Integer getId() {
    return Id;
  }

  public void setId(Integer Id) {
    this.Id = Id;
  }

  public Double getMobile() {
    return mobile;
  }

  public void setMobile(Double mobile) {
    this.mobile = mobile;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getStandard() {
    return standard;
  }

  public void setStandard(String standard) {
    this.standard = standard;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getVip() {
    return vip;
  }

  public void setVip(String vip) {
    this.vip = vip;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }
}
