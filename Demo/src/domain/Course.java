package domain;

import java.io.Serializable;

public class Course implements Comparable<Course>, Serializable {

    private String id;

    private String cname;

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Course c) {
        return this.id.compareTo(c.id);
    }
}
