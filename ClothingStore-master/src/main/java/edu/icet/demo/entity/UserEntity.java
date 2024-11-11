package edu.icet.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

        @Id
        private String id;
        private String name;
        private String email;
        private String password;
        private String role;
        private String address;
}
