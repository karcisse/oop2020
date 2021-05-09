package wsb.lasttask;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "public", catalog = "oop2020")
public class SubjectEntity {
    private Integer subjectId;
    private String name;

    @Id
    @Column(name = "subject_id")
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, name);
    }
}
