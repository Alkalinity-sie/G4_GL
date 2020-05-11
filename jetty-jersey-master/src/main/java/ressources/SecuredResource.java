package ressources;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;

@Path("secured")
public class SecuredResource {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	
	@GET
	@Path("message")
	@Produces(MediaType.TEXT_PLAIN)
	public int securedmethod(ContainerRequestContext requestContext) throws IOException {
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		String authToken = authHeader.get(0);
		System.out.println(authToken);
		authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		System.out.println(authToken);
		String decodedString = new String(Base64.decode(authToken.getBytes()));;
		System.out.println(decodedString);
		StringTokenizer tokenizer = new StringTokenizer(decodedString,":");
		String username = tokenizer.nextToken();
		System.out.println(username);
		String password = tokenizer.nextToken();
		return UserRessource.getUserId(username);
	}

}
