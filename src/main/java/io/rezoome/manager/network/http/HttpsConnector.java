package io.rezoome.manager.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;

public class HttpsConnector implements HttpManager {
	private HttpsURLConnection	httpsURLConnection;
	private URL					url;

	private final int			CONNECT_TIMEOUT	= 10000;
	private final int			READ_TIMEOUT	= 10000;
	private String				response		= "";

	@Override
	public String sendPost(String endpoint, Map<String, Object> headers, Object parameters) throws ServiceException {
		// TODO Auto-generated method stub

		try {
			url = new URL(endpoint);

			httpsURLConnection = (HttpsURLConnection) url.openConnection();

			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);

			httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
			httpsURLConnection.setReadTimeout(READ_TIMEOUT);

			httpsURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
			httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// set request header
			if (headers != null) {
				for (Map.Entry<String, Object> header : headers.entrySet()) {
					httpsURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
				}
			}

			String body = "";
			if (parameters != null) {
				if (httpsURLConnection.getRequestProperty("Content-Type").contains("form")) {
					// set request parameter
					StringBuilder bodyBuilder = new StringBuilder();

					@SuppressWarnings("unchecked")
					Map<String, Object> parameterMap = (Map<String, Object>) parameters;

					if (parameterMap != null && parameterMap.size() > 0) {
						for (Map.Entry<String, Object> parameter : parameterMap.entrySet()) {
							bodyBuilder.append(parameter.getKey().toString());
							bodyBuilder.append("=");
							bodyBuilder.append(parameter.getValue().toString());
							bodyBuilder.append("&");
						}
					}
					body = bodyBuilder.toString();
					body = body.substring(0, body.length() - 1);
				} else if (httpsURLConnection.getRequestProperty("Content-Type").contains("json")) {
					body = (String) parameters;
				}
			}
			OutputStream outputStream = httpsURLConnection.getOutputStream();
			outputStream.write(body.toString().getBytes());

			outputStream.flush();
			outputStream.close();

			int responseCode = httpsURLConnection.getResponseCode();
			if (responseCode != Constants.HTTP_STATUS_CODE_200) {
				throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
			}

			response = getResponse(httpsURLConnection.getInputStream());

			if (response == "") {
				throw new ServiceException("failed to get response");
			}

			httpsURLConnection.disconnect();

		} catch (

		MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public String sendGet(String endpoint, Map<String, Object> headers) throws ServiceException {

		try {
			url = new URL(endpoint);

			httpsURLConnection = (HttpsURLConnection) url.openConnection();

			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);

			httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
			httpsURLConnection.setReadTimeout(READ_TIMEOUT);

			httpsURLConnection.setRequestMethod(Constants.PARAM_METHOD_GET);

			// set request header
			if (headers != null) {
				for (Map.Entry<String, Object> header : headers.entrySet()) {
					httpsURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
				}
			}

			int responseCode = httpsURLConnection.getResponseCode();
			if (responseCode != Constants.HTTP_STATUS_CODE_200) {
				System.out.println(responseCode);
				throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
			}

			response = getResponse(httpsURLConnection.getInputStream());

			if (response == "") {
				throw new ServiceException("failed to get response");
			}

			httpsURLConnection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	private String getResponse(InputStream inputStream) throws UnsupportedEncodingException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Constants.PARAM_UTF_8));
		StringBuffer response = new StringBuffer();
		String inputLine;

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();
		return reader.toString();
	}
}
