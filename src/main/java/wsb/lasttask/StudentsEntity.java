package wsb.lasttask;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students", schema = "public", catalog = "oop2020")
@NamedQuery(name = "StudentsEntity.byLastName", query = "select s from StudentsEntity s where lastName like ?1")
@Data
public class StudentsEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "age")
    private Integer age;
}
