package tn.com.shinra.session.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LIBRARY_TBL")
public class Library implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adress;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable( name = "Library_Book",
//            joinColumns = @JoinColumn( name = "idLibrary",referencedColumnName = "id" ),
//            inverseJoinColumns = @JoinColumn( name = "idBook",referencedColumnName = "id" ) )
//    private Set<Book> books;


    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees;


}
