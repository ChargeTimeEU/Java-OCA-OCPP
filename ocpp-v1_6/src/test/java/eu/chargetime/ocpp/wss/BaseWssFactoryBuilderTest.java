package eu.chargetime.ocpp.wss;

import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.junit.Test;

import javax.net.ssl.SSLContext;

import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Test for {@link BaseWssFactoryBuilder}
 */
public class BaseWssFactoryBuilderTest {

    @Test
    public void builder_returnsBuilder() {
        BaseWssFactoryBuilder builder = BaseWssFactoryBuilder.builder();
        assertThat(builder, is(any(BaseWssFactoryBuilder.class)));
    }

    @Test
    public void builder_withSSLContextSet_throwsNoException() {
        SSLContext sslContext = mock(SSLContext.class);
        BaseWssFactoryBuilder.builder().sslContext(sslContext).verify();
    }

    @Test
    public void builder_builtWithCiphers_returnsCustomSSLWebSocketServerFactory() {
        SSLContext sslContext = mock(SSLContext.class);
        List<String> cihpers = mock(List.class);
        WebSocketServerFactory factory = BaseWssFactoryBuilder.builder()
                .sslContext(sslContext)
                .ciphers(cihpers)
                .build();

        assertThat(factory, is(instanceOf(CustomSSLWebSocketServerFactory.class)));
    }

    @Test
    public void builder_builtWithoutCiphers_returnsDefaultSSLWebSocketServerFactory() {
        SSLContext sslContext = mock(SSLContext.class);
        WebSocketServerFactory factory = BaseWssFactoryBuilder.builder()
                .sslContext(sslContext)
                .build();

        assertThat(factory, is(instanceOf(DefaultSSLWebSocketServerFactory.class)));
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
