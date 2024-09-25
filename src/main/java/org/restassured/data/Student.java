package org.restassured.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;

    @Override
    public String toString() {
        return "Student{" +
                "first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name=" + last_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
