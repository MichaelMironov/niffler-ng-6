package guru.qa.niffler.jupiter.anno;

import guru.qa.niffler.jupiter.SpendingExtension;
import guru.qa.niffler.jupiter.SpendingResolverExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith({SpendingExtension.class, SpendingResolverExtension.class})
public @interface Spending {
  String username();
  String category();
  String description();
  double amount();
}
