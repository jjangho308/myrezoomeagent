package io.rezoome.manager.networkv2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HttpsURLConnection;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;

/**
 * Implementation of {@link HttpRequestManager}. <br />
 * 
 * @since 180309
 * @author TACKSU
 *
 */
public class HttpRequestManagerImpl extends AbstractManager implements HttpRequestManager {

	private static final int DEFAULT_BUFFER_CAP = 8192;
	private URL agentChannenUrl;

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public RequestPacketEntity obtainRequestPacket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePacketEntity sendRequest(RequestPacket packet) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) this.agentChannenUrl.openConnection();
			if(connection instanceof HttpsURLConnection){
				this.setSSLSocketFactory((HttpsURLConnection) connection);
			}
			for(Entry<String, String> entry : packet.getHeader().entrySet()){
				connection.setRequestProperty(entry.getKey(), entry.getValue());
			}
			
			connection.connect();
			GZIPOutputStream gos = new GZIPOutputStream(connection.getOutputStream());
			gos.write(packet.getData().getBytes(Charset.defaultCharset()));
			gos.flush();
			gos.close();
			GZIPInputStream gis = new GZIPInputStream(connection.getInputStream());
			byte[] buffer = new byte[DEFAULT_BUFFER_CAP];
//			return new ResponsePacket(connection.getHeaderFields(), data)
		} catch (IOException e) {
			
		}

		// TODO Auto-generated method stub
		return null;
	}

	private void setSSLSocketFactory(HttpsURLConnection connection) {
	}

}
