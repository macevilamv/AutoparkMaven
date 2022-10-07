package by.incubator.autopark.test;

import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import by.incubator.autopark.infrastructure.orm.annotations.Column;
import by.incubator.autopark.infrastructure.orm.annotations.ID;
import by.incubator.autopark.infrastructure.orm.annotations.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "dog")
public class Dog {
    @ID
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    Integer age;

    public Dog() {

    }

    @InitMethod
    public void init() {
        this.name = getName();
        this.age = getAge();
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
