package tn.com.shinra.session.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_TBL")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long cin;
    private String adress;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="library_id")
    private Library library;
//
//    @Override
//    public String toString() {
//        return "Employee [id=" + id + ", name=" + name +", cin=" + cin + ", adress=" + adress + "]";
//    }
}
