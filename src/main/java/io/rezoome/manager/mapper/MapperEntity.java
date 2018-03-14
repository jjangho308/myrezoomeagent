package io.rezoome.manager.mapper;

public abstract class MapperEntity {

  protected String name;
  protected String grade;
  protected String date;
 
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getGrade() {
    return grade;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  
  @Override
  public String toString() {
    return "MapperEntity [name=" + name + ", grade=" + grade + ", date=" + date + "]";
  }
}
