/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2025 Robert Schlabbach <robert.schlabbach@ubitricity.com>

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

package eu.chargetime.ocpp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import javax.annotation.Nonnull;

/**
 * A variation of {@link CompletableFuture} which allows holders of the original class instance to
 * {@link #join()} the completion of the last action added using whenComplete[async]().
 */
public class Promise<T> extends CompletableFuture<T> {
  private volatile CompletableFuture<T> completion = this;

  @Override
  public T join() {
    return completion != this ? completion.join() : super.join();
  }

  @Override
  @Nonnull
  public CompletableFuture<T> whenComplete(
      @Nonnull BiConsumer<? super T, ? super Throwable> action) {
    completion = super.whenComplete(action);
    return this;
  }

  @Override
  @Nonnull
  public CompletableFuture<T> whenCompleteAsync(
      @Nonnull BiConsumer<? super T, ? super Throwable> action) {
    completion = super.whenCompleteAsync(action);
    return this;
  }

  @Override
  @Nonnull
  public CompletableFuture<T> whenCompleteAsync(
      @Nonnull BiConsumer<? super T, ? super Throwable> action, Executor executor) {
    completion = super.whenCompleteAsync(action, executor);
    return this;
  }
}
