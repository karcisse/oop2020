package wsb.lasttask;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_class_table", schema = "public", catalog = "oop2020")
@IdClass(StudentClassTableEntityPK.class)
public class StudentClassTableEntity {
    private String classId;
    private Integer studentId;

    @Id
    @Column(name = "class_id")
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Id
    @Column(name = "student_id")
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentClassTableEntity that = (StudentClassTableEntity) o;
        return Objects.equals(classId, that.classId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, studentId);
    }
}
