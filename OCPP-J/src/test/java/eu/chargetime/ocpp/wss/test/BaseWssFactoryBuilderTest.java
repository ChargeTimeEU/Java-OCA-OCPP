package eu.chargetime.ocpp.wss.test;

import eu.chargetime.ocpp.wss.BaseWssFactoryBuilder;
import eu.chargetime.ocpp.wss.CustomSSLWebSocketServerFactory;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.hamcrest.CoreMatchers;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/** Test for {@link BaseWssFactoryBuilder} */
public class BaseWssFactoryBuilderTest {

  @Test
  public void builder_returnsBuilder() {
    BaseWssFactoryBuilder builder = BaseWssFactoryBuilder.builder();
    Assert.assertThat(builder, CoreMatchers.is(CoreMatchers.any(BaseWssFactoryBuilder.class)));
  }

  @Test
  public void builder_withSSLContextSet_throwsNoException() {
    SSLContext sslContext = Mockito.mock(SSLContext.class);
    BaseWssFactoryBuilder.builder().sslContext(sslContext).verify();
  }

  @Test
  public void builder_builtWithCiphers_returnsCustomSSLWebSocketServerFactory() {
    SSLContext sslContext = Mockito.mock(SSLContext.class);
    List<String> cihpers = Mockito.mock(List.class);
    WebSocketServerFactory factory =
        BaseWssFactoryBuilder.builder().sslContext(sslContext).ciphers(cihpers).build();

    Assert.assertThat(
        factory, CoreMatchers.is(CoreMatchers.instanceOf(CustomSSLWebSocketServerFactory.class)));
  }

  @Test
  public void builder_builtWithoutCiphers_returnsDefaultSSLWebSocketServerFactory() {
    SSLContext sslContext = Mockito.mock(SSLContext.class);
    WebSocketServerFactory factory = BaseWssFactoryBuilder.builder().sslContext(sslContext).build();

    Assert.assertThat(
        factory, CoreMatchers.is(CoreMatchers.instanceOf(DefaultSSLWebSocketServerFactory.class)));
  }

  @Test(expected = IllegalStateException.class)
  public void builder_withoutSSLContextSet_failsBuildWithException() {
    BaseWssFactoryBuilder.builder().build();
  }

  @Test(expected = IllegalStateException.class)
  public void builder_withoutSSLContextSet_failsVerificationWithException() {
    BaseWssFactoryBuilder.builder().verify();
  }
}
