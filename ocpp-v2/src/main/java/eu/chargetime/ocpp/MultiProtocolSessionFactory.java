/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
   Copyright (C) 2023 Robert Schlabbach <robert.schlabbach@ubitricity.com>

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

public class MultiProtocolSessionFactory implements ISessionFactory {

  private final MultiProtocolFeatureRepository multiProtocolFeatureRepository;

  public MultiProtocolSessionFactory(
      MultiProtocolFeatureRepository multiProtocolFeatureRepository) {
    this.multiProtocolFeatureRepository = multiProtocolFeatureRepository;
  }

  /**
   * Creates a client session
   *
   * <p>The session will use the feature repository from the {@link MultiProtocolFeatureRepository}
   * selected using {@link MultiProtocolFeatureRepository#selectProtocolVersion(ProtocolVersion)}
   * when the negotiated {@link ProtocolVersion} is known (after the WebSocket handshake).
   *
   * @param communicator the {@link Communicator} to use for the client session
   * @return the client {@link Session}
   */
  @Override
  public ISession createSession(Communicator communicator) {
    AsyncPromiseFulfillerDecorator promiseFulfiller =
        new AsyncPromiseFulfillerDecorator(new SimplePromiseFulfiller());
    return new Session(communicator, new Queue(), promiseFulfiller, multiProtocolFeatureRepository);
  }

  /**
   * Creates a server session
   *
   * <p>The session will use the feature repository for the given {@link ProtocolVersion}.
   *
   * @param communicator the {@link Communicator} to use for the server session
   * @param protocolVersion the {@link ProtocolVersion} to use for the server session
   * @return the server {@link Session}
   */
  public ISession createSession(Communicator communicator, ProtocolVersion protocolVersion) {
    IFeatureRepository featureRepository =
        multiProtocolFeatureRepository.getFeatureRepository(protocolVersion);
    AsyncPromiseFulfillerDecorator promiseFulfiller =
        new AsyncPromiseFulfillerDecorator(new SimplePromiseFulfiller());
    return new Session(communicator, new Queue(), promiseFulfiller, featureRepository);
  }
}
