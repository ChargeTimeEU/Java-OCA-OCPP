package eu.chargetime.ocpp.wss.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.wss.BaseWssFactoryBuilder;
import eu.chargetime.ocpp.wss.BaseWssSocketBuilder;
import java.io.IOException;
import java.net.*;
import javax.net.ssl.SSLSocketFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

/** Test for {@link BaseWssFactoryBuilder} */
public class BaseWssSocketBuilderTest {

  @Test
  public void builder_returnsBuilder() {
    BaseWssSocketBuilder builder = BaseWssSocketBuilder.builder();
    assertThat(builder, is(CoreMatchers.any(BaseWssSocketBuilder.class)));
  }

  @Test
  public void builder_withUnboundedSocket_connectsAndReturnsProperlyInitializedSocket()
      throws IOException, URISyntaxException {
    SSLSocketFactory sslSocketFactory = mock(SSLSocketFactory.class);
    Socket expectedSocket = mock(Socket.class);
    Socket socket = mock(Socket.class);
    when(socket.isBound()).thenReturn(false);
    when(sslSocketFactory.createSocket(socket, "fake", 101, false)).thenReturn(expectedSocket);
    Proxy proxy = mock(Proxy.class);
    BaseWssSocketBuilder.SocketFactory socketFactory =
        mock(BaseWssSocketBuilder.SocketFactory.class);
    when(socketFactory.getSocket(any(Proxy.class))).thenReturn(socket);
    BaseWssSocketBuilder.InetSocketAddressFactory inetSocketAddressFactory =
        mock(BaseWssSocketBuilder.InetSocketAddressFactory.class);
    InetSocketAddress inetSocketAddress = mock(InetSocketAddress.class);
    when(inetSocketAddressFactory.getInetSocketAddress(any(String.class), any(int.class)))
        .thenReturn(inetSocketAddress);

    Socket builtSocket =
        BaseWssSocketBuilder.builder()
            .socketFactory(socketFactory)
            .sslSocketFactory(sslSocketFactory)
            .inetSocketAddressFactory(inetSocketAddressFactory)
            .proxy(proxy)
            .tcpNoDelay(true)
            .reuseAddr(true)
            .autoClose(false)
            .connectionTimeout(5000)
            .uri(new URI("http://fake:101"))
            .build();

    assertThat(builtSocket, is(expectedSocket));

    verify(socketFactory).getSocket(proxy);
    verify(socket).setTcpNoDelay(true);
    verify(socket).setReuseAddress(true);
    verify(inetSocketAddressFactory).getInetSocketAddress("fake", 101);
    verify(socket).connect(inetSocketAddress, 5000);
    verify(sslSocketFactory).createSocket(socket, "fake", 101, false);
  }

  @Test
  public void builder_withBoundedSocket_returnsProperlyInitializedSocket()
      throws IOException, URISyntaxException {
    SSLSocketFactory sslSocketFactory = mock(SSLSocketFactory.class);
    Socket expectedSocket = mock(Socket.class);
    Socket socket = mock(Socket.class);
    when(socket.isBound()).thenReturn(true);
    when(sslSocketFactory.createSocket(socket, "fake", 101, false)).thenReturn(expectedSocket);
    Proxy proxy = mock(Proxy.class);
    BaseWssSocketBuilder.SocketFactory socketFactory =
        mock(BaseWssSocketBuilder.SocketFactory.class);
    when(socketFactory.getSocket(any(Proxy.class))).thenReturn(socket);
    BaseWssSocketBuilder.InetSocketAddressFactory inetSocketAddressFactory =
        mock(BaseWssSocketBuilder.InetSocketAddressFactory.class);
    InetSocketAddress inetSocketAddress = mock(InetSocketAddress.class);
    when(inetSocketAddressFactory.getInetSocketAddress(any(String.class), any(int.class)))
        .thenReturn(inetSocketAddress);

    Socket builtSocket =
        BaseWssSocketBuilder.builder()
            .socketFactory(socketFactory)
            .sslSocketFactory(sslSocketFactory)
            .inetSocketAddressFactory(inetSocketAddressFactory)
            .proxy(proxy)
            .tcpNoDelay(true)
            .reuseAddr(true)
            .autoClose(false)
            .connectionTimeout(5000)
            .uri(new URI("http://fake:101"))
            .build();

    assertThat(builtSocket, is(expectedSocket));

    verify(socketFactory).getSocket(proxy);
    verify(socket).setTcpNoDelay(true);
    verify(socket).setReuseAddress(true);
    verify(inetSocketAddressFactory, never())
        .getInetSocketAddress(any(String.class), any(int.class));
    verify(socket, never()).connect(any(InetSocketAddress.class), any(int.class));
    verify(sslSocketFactory).createSocket(socket, "fake", 101, false);
  }

  @Test(expected = IllegalStateException.class)
  public void builder_withNoFactorySet_failsBuildWithException() throws IOException {
    BaseWssSocketBuilder.builder().build();
  }

  @Test(expected = IllegalStateException.class)
  public void builder_withNoURISet_failsBuildWithException() throws IOException {
    SSLSocketFactory sslSocketFactory = mock(SSLSocketFactory.class);
    BaseWssSocketBuilder.builder().sslSocketFactory(sslSocketFactory).build();
  }

  @Test(expected = IllegalStateException.class)
  public void builder_withNoFactorySet_failsVerificationWithException() {
    BaseWssSocketBuilder.builder().verify();
  }
}
