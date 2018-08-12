package io.rezoome.manager.networkv2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.core.entity.Entity;

public class ResponsePacket extends AbstractEntity implements Entity {
	private final String				data;
	private final List<File>			fileList;
	private final Map<String, String>	header;

	public ResponsePacket(Map<String, String> header, String data) {
		super();
		this.header = header;
		this.data = data;
		this.fileList = null;
	}

	public ResponsePacket(Map<String, String> header, String data, List<File> fileList) {
		super();
		this.header = header;
		this.data = data;
		this.fileList = fileList;
	}
}