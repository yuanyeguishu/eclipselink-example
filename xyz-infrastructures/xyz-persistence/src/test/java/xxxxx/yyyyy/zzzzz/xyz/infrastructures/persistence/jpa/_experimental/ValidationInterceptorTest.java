package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;

import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.interceptor.InvocationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@lombok.extern.slf4j.Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidationInterceptorTest {

    @Rule
    public TestName name = new TestName();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private InvocationContext invocationContext;

    @Before
    public void before() {
        log.info(String.format("[START] %s", name.getMethodName()));
    }

    @After
    public void after() {
        log.info("[END]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void UnitOfWork_StateUnderTest_ExpectedBehavior0() throws Throwable { // TODO should change method name
        Dummy dummy = new Dummy();
        when(invocationContext.getTarget()).thenReturn(dummy);
        when(invocationContext.getMethod()).thenReturn(declaredMethod(dummy, "dummy", Object.class, String.class, List.class, Map.class));
        when(invocationContext.getParameters()).thenReturn(
                //new Object[]{new Object(), "string", new ArrayList<>(), new HashMap<>()}
                new Object[]{null, null, null, null}
        );
        ValidationInterceptor validationInterceptor = new ValidationInterceptor();
        validationInterceptor.aroundInvoke(invocationContext);
    }

    private Method declaredMethod(Object target, String name, Class<?>... parameterTypes) {
        try {
            return target.getClass().getDeclaredMethod(name, parameterTypes);
        } catch (ReflectiveOperationException cause) {
            throw new RuntimeException(cause);
        }
    }

    @Valid
    private static class Dummy {

        public Dummy dummy(Object object, String string, List<Object> list, Map<Object, Object> map) {
            return null;
        }
    }
}
