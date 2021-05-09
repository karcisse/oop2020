package wsb.lasttask;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentClassTableEntityPK implements Serializable {
    private String classId;
    private Integer studentId;

    @Column(name = "class_id")
    @Id
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Column(name = "student_id")
    @Id
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
        StudentClassTableEntityPK that = (StudentClassTableEntityPK) o;
        return Objects.equals(classId, that.classId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, studentId);
    }
}
