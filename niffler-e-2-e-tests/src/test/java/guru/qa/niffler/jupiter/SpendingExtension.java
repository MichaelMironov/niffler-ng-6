package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.jupiter.anno.Spending;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.Date;

public class SpendingExtension implements BeforeEachCallback {

  public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(SpendingExtension.class);

  private final SpendApiClient spendApiClient = new SpendApiClient();

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Spending.class)
        .ifPresent(anno -> {
          SpendJson spend = new SpendJson(
              null,
              new Date(),
              new CategoryJson(
                  null,
                  anno.category(),
                  anno.username(),
                  false
              ),
              CurrencyValues.RUB,
              anno.amount(),
              anno.description(),
              anno.username()
          );

            SpendJson createdSpend = spendApiClient.createSpend(spend);

            context.getStore(NAMESPACE).put(context.getUniqueId(), createdSpend);
        });
  }
}
