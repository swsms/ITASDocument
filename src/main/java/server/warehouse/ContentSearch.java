package server.warehouse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import server.entities.IdentName;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public final class ContentSearch {
	private final String content;

	public ContentSearch(String content) {
		this.content = content;
	}

	public Set<String> findIdents() throws IOException {
		WebResource searchResource = Client.create().resource(
				WarehouseConfig.get().apiSearchPath(content));
		IdentName[] result = new ObjectMapper().readValue(
				searchResource.get(String.class), IdentName[].class);
		Set<String> fileteredIdents = new HashSet<>();
		for (IdentName doc : result)
			fileteredIdents.add(doc.getIdent());
		return fileteredIdents;
	}
}
