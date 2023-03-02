package eu.chargetime.ocpp.utilities.test;

/*
 ubitricity.com - Java-OCA-OCPP

 MIT License

 Copyright (C) 2018 Evgeny Pakhomov <eugene.pakhomov@ubitricity.com>

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.utilities.Stopwatch;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Unit test for {@link Stopwatch}.
 *
 * @author <a href=mailto:eugene.pakhomov@ubitricity.com>Eugene Pakhomov</a>
 */
public class StopwatchTest {

  @Test
  public void createUnstarted() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();

    assertThat(stopwatch.isRunning(), is(false));
    assertThat(stopwatch.elapsed(), is(Duration.ofNanos(0L)));
  }

  @Test
  public void createStarted() {
    Stopwatch stopwatch = Stopwatch.createStarted();

    assertThat(stopwatch.isRunning(), is(true));
    assertThat(stopwatch.elapsed(), greaterThan(Duration.ofNanos(0L)));
  }

  @Test
  public void isRunningPositive() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();

    assertThat(stopwatch.isRunning(), is(true));
  }

  @Test
  public void isRunningNegative() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();
    stopwatch.stop();

    assertThat(stopwatch.isRunning(), is(false));
  }

  @Test(expected = IllegalStateException.class)
  public void duplicateRun() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();
    stopwatch.start();
  }

  @Test(expected = IllegalStateException.class)
  public void duplicateStop() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();
    stopwatch.stop();
    stopwatch.stop();
  }

  @Test
  public void start() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();

    assertThat(stopwatch.isRunning(), is(true));
    assertThat(stopwatch.elapsed(), greaterThan(Duration.ofNanos(0L)));
  }

  @Test
  public void stop() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();
    stopwatch.stop();

    assertThat(stopwatch.isRunning(), is(false));
    assertThat(stopwatch.elapsed(), greaterThan(Duration.ofNanos(0L)));
  }

  @Test
  public void reset() {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();
    stopwatch.reset();

    assertThat(stopwatch.isRunning(), is(false));
    assertThat(stopwatch.elapsed(), is(Duration.ofNanos(0L)));
  }

  @Test
  public void elapsed() {
    Stopwatch.Ticker ticker = mock(Stopwatch.Ticker.class);
    when(ticker.read()).thenReturn(1523889872515205L).thenReturn(1523889951575345L);

    Stopwatch stopwatch = new Stopwatch(ticker);
    stopwatch.start();
    stopwatch.stop();

    assertThat(stopwatch.elapsed(), is(Duration.ofNanos(79060140L)));
  }

  @Test
  public void elapsedWithUnitMs() {
    Stopwatch.Ticker ticker = mock(Stopwatch.Ticker.class);
    when(ticker.read()).thenReturn(1523889872515205L).thenReturn(1523889951575345L);

    Stopwatch stopwatch = new Stopwatch(ticker);
    stopwatch.start();
    stopwatch.stop();

    assertThat(stopwatch.elapsed(TimeUnit.MILLISECONDS), is(79L));
  }
}
