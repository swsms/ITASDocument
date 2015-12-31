package server.warehouse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import server.entities.IdentName;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.impl.MultiPartWriter;

public final class ContentSearch {
	private final String content;

	public ContentSearch(String content) {
		this.content = content;
	}

	public Set<String> findIdents() throws IOException {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		WebResource searchResource = Client.create(cc).resource(
				WarehouseConfig.get().apiSearchPath(content));
		IdentName[] result = new ObjectMapper().readValue(
				searchResource.get(String.class), IdentName[].class);
		Set<String> fileteredIdents = new HashSet<>();
		for (IdentName doc : result)
			fileteredIdents.add(doc.getIdent());
		return fileteredIdents;
	}
}
