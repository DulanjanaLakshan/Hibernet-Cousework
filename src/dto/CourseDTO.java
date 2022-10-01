package dto;

public class CourseDTO {
      private String id;
      private String title;
      private String duration;
       private double salary;

    public CourseDTO() {
    }

    public CourseDTO(String id, String title, String duration, double salary) {
        this.setId(id);
        this.setTitle(title);
        this.setDuration(duration);
        this.setSalary(salary);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", salary=" + salary +
                '}';
    }
}
