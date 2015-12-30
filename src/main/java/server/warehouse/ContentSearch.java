package server.warehouse;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.entities.IdentName;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public final class ContentSearch {
	private final String content;

	public ContentSearch(String content) {
		this.content = content;
	}

	public Set<String> findIdents() throws IOException {
		WebResource searchResource = Client.create().resource(
				WarehouseConfig.get().apiSearchPath(content));
		List<IdentName> result = searchResource
				.get(new GenericType<List<IdentName>>() {
				});
		Set<String> fileteredIdents = new HashSet<>();
		for (IdentName doc : result)
			fileteredIdents.add(doc.getIdent());
		return fileteredIdents;
	}
}
