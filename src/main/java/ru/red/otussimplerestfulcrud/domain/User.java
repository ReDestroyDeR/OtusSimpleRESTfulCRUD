package ru.red.otussimplerestfulcrud.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Setter
@Getter
@Table("users")
public class User {
    @Id
    private Long id;

    @Column("username")
    private String username;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    public static User merge(User src, User dest) {
        if (dest.getUsername() == null) {
            dest.setUsername(src.getUsername());
        }
        if (dest.getFirstName() == null) {
            dest.setFirstName(src.getFirstName());
        }
        if (dest.getLastName() == null) {
            dest.setLastName(src.getLastName());
        }
        if (dest.getEmail() == null) {
            dest.setEmail(src.getEmail());
        }
        if (dest.getPhone() == null) {
            dest.setPhone(src.getPhone());
        }
        return dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(username, user.username)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email)
                && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, email, phone);
    }
}
