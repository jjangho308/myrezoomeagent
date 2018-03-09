package io.rezoome.constants;

public class Constants {

	public static final String	SAMPLE					= "sample";
	public static final String	CRLF					= System
			.getProperty("line.separator");

	public static final String	PARAM_UTF_8				= "UTF-8";

	public static final String	PARAM_METHOD_POST		= "POST";
	public static final String	PARAM_METHOD_GET		= "GET";

	public static final int		HTTP_STATUS_CODE_200	= 200;
	public static final int		HTTP_STATUS_CODE_400	= 400;

	public static final String	RESULT_MESSAGE_SUCCESS	= "success";
	public static final String	RESULT_MESSAGE_ERROR	= "error";

	public static final String	PARAM_MESSAGE_TOKEN		= "token";
	public static final String	PARAM_MESSAGE_CODE		= "code";
	public static final String	PARAM_MESSAGE_ERR		= "err";
	public static final String	PARAM_MESSAGE_RESULT	= "result";
	public static final String	PARAM_MESSAGE_MID		= "mid";
	public static final String	PARAM_MESSAGE_CMD		= "cmd";

	// Command list
	public static final String	COMMAND_ISSUE_TOKEN		= "IssueToken";
	public static final String	COMMAND_KEY_PROVISION	= "KeyProvision";
	public static final String	COMMAND_KEEP_ALIVE		= "KeepAlive";
	public static final String	COMMAND_SEARCH			= "Search";
	public static final String	COMMAND_SEARCH_RESULT	= "SearchResult";

}
