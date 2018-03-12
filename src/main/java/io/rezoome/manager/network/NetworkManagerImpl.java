package io.rezoome.manager.network;

import java.util.HashMap;
import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.network.http.HttpConnector;
import io.rezoome.manager.network.http.HttpManager;
import io.rezoome.manager.network.http.HttpsConnector;

@ManagerType(value = "Network", initPriority = 30)
public class NetworkManagerImpl extends AbstractManager implements NetworkManager {

	HttpConnector	httpConnector;
	HttpsConnector	httpsConnector;

	private static class Singleton {
		private static final NetworkManager instance = new NetworkManagerImpl();
	}

	public static NetworkManager getInstance() {
		return Singleton.instance;
	}

	protected HttpManager httpManager;

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub
		// httpConnecter = HttpConnecter.get
		// HttpConnecter.getInstance();
		// HttpsConnecter.getInstance();
		httpConnector = new HttpConnector();
		httpsConnector = new HttpsConnector();
		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPrepared() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResponsePacketEntity request(RequestPacketEntity entity) {
		// TODO Auto-generated method stub

		System.out.println("searchResult : " + JSON.toJson(entity));

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Content-type", "application/json");

		String result = httpConnector.sendPost("http://localhost:3000/agent", headers, JSON.toJson(entity));
		System.out.println(result);

		return new ResponsePacketEntity();
	}

	@Override
	public RequestPacketEntity convert(RzmRsltEntity entity, String protocol, String method) {
		RequestPacketEntity rsltEntity = new RequestPacketEntity();

		return rsltEntity;
	}

	@Override
	public HttpConnector getHttpConnecter() {
		// TODO Auto-generated method stub
		return this.httpConnector;
	}

	@Override
	public HttpsConnector getHttpsConnecter() {
		// TODO Auto-generated method stub
		return this.httpsConnector;
	}
}
