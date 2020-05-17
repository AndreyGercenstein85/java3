package lesson7;

import javafx.scene.layout.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    Priority value();

    enum Priority{
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        NORMAL(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10);
        private int priorittValue;

        Priority(int priorittValue) {
            this.priorittValue = priorittValue;
        }

    }
}
