package wsb.lasttask;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "public", catalog = "oop2020")
public class ClassEntity {
    private String classId;
    private String className;

    @Id
    @Column(name = "class_id")
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return Objects.equals(classId, that.classId) &&
                Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, className);
    }
}
