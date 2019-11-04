package ru.otus.annotations;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class HumanClass {
    int age = 1;
    String name = "NoNameYet";
    boolean alive = true;

    void setNameForKid(String name) throws Exception {
        if (this.getName().equals("NoNameYet")) {
            this.setName(name);
        }
        System.out.println("Human being got a name: " + this.getName());
    }

    void gettingOlder(int age) throws Exception {
        if (this.alive) {
            this.setAge(age + 1);
            System.out.println("Human being became 1 year older, he is now: " + this.getAge());
        }
        else {
            System.out.println("dead men age won't change!");
        }
    }

    void dying() {
        this.alive = false;
    }
}