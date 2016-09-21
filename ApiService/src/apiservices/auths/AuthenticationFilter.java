package apiservices.auths;

import java.io.IOException;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;

//@Provider
//@Priority(Priorities.AUTHENTICATION)
//@PreMatching
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		String headers = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (headers == null || headers.equals("")) {
			throw new NotAuthorizedException("Not authorized man");
		}
	}

}
