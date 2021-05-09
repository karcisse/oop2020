package wsb.lasttask;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ClassSubjectTableEntityPK implements Serializable {
    private Integer subjectId;
    private String classId;

    @Column(name = "subject_id")
    @Id
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "class_id")
    @Id
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSubjectTableEntityPK that = (ClassSubjectTableEntityPK) o;
        return Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(classId, that.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, classId);
    }
}
