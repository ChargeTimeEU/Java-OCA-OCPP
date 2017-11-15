package eu.chargetime.ocpp;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

import java.nio.ByteBuffer;
import java.util.List;

public class OcppDraft extends Draft {

    private final Draft_6455 draft_6455;

    public OcppDraft() {
        draft_6455 = new Draft_6455();
    }

    public OcppDraft(Draft_6455 draft_6455) {
        this.draft_6455 = draft_6455;
    }

    @Override
    public HandshakeState acceptHandshakeAsClient(ClientHandshake request, ServerHandshake response) throws InvalidHandshakeException {
        return draft_6455.acceptHandshakeAsClient(request, response);
    }

    @Override
    public HandshakeState acceptHandshakeAsServer(ClientHandshake handshakedata) throws InvalidHandshakeException {
        return draft_6455.acceptHandshakeAsServer(handshakedata);
    }

    @Override
    public ByteBuffer createBinaryFrame(Framedata framedata) {
        return draft_6455.createBinaryFrame(framedata);
    }

    @Override
    public List<Framedata> createFrames(ByteBuffer binary, boolean mask) {
        return draft_6455.createFrames(binary, mask);
    }

    @Override
    public List<Framedata> createFrames(String text, boolean mask) {
        return draft_6455.createFrames(text, mask);
    }

    @Override
    public void processFrame(WebSocketImpl webSocketImpl, Framedata frame) throws InvalidDataException {
        draft_6455.processFrame(webSocketImpl, frame);
    }

    @Override
    public void reset() {
        draft_6455.reset();
    }

    @Override
    public List<Framedata> translateFrame(ByteBuffer buffer) throws InvalidDataException {
        return draft_6455.translateFrame(buffer);
    }

    @Override
    public CloseHandshakeType getCloseHandshakeType() {
        return draft_6455.getCloseHandshakeType();
    }

    @Override
    public Draft copyInstance() {
        Draft_6455 copy = (Draft_6455) draft_6455.copyInstance();
        return new OcppDraft(copy);
    }

    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder request) {
        ClientHandshakeBuilder clientHandshakeBuilder = draft_6455.postProcessHandshakeRequestAsClient(request);
        clientHandshakeBuilder.put("Sec-WebSocket-Protocol", "ocpp1.6");
        return clientHandshakeBuilder;
    }

    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake request, ServerHandshakeBuilder response) throws InvalidHandshakeException {
        HandshakeBuilder handshakeBuilder = draft_6455.postProcessHandshakeResponseAsServer(request, response);
        String subProtocol = request.getFieldValue("Sec-WebSocket-Protocol");
        if (subProtocol.length() > 0) {
            handshakeBuilder.put("Sec-WebSocket-Protocol", subProtocol);
        }
        return handshakeBuilder;
    }
}