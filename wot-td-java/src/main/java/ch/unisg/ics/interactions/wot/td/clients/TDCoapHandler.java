package ch.unisg.ics.interactions.wot.td.clients;

import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

/**
 * An abstract class for reacting to notifications from asynchronous
 * CoAP requests and established CoAP observe relations. A concrete
 * <code>TDCoAPHandler</code> instance is needed when using the methods
 * {@link TDCoapRequest#execute(TDCoapHandler)},
 * {@link TDCoapRequest#establishRelation(TDCoapHandler)}, and
 * {@link TDCoapRequest#establishRelationAndWait(TDCoapHandler)}.
 */
public abstract class TDCoapHandler {

  private final CoapHandler coapHandler = new CoapHandler() {
    @Override
    public void onLoad(CoapResponse response) {
      handleLoad(new TDCoapResponse(response.advanced()));
    }

    @Override
    public void onError() {
      handleError();
    }
  };

  /**
   * Invoked when a CoAP response or notification has arrived.
   *
   * @param response the response
   */
  public abstract void handleLoad(TDCoapResponse response);

  /**
   * Invoked when a request timeouts or has been rejected by the server.
   */
  public abstract void handleError();

  CoapHandler getCoapHandler() {
    return this.coapHandler;
  }

}
