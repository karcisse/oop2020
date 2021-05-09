package wsb.lasttask;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class_subject_table", schema = "public", catalog = "oop2020")
@IdClass(ClassSubjectTableEntityPK.class)
public class ClassSubjectTableEntity {
    private Integer subjectId;
    private String classId;

    @Id
    @Column(name = "subject_id")
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Id
    @Column(name = "class_id")
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
        ClassSubjectTableEntity that = (ClassSubjectTableEntity) o;
        return Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(classId, that.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, classId);
    }
}
