package org.railway.ticketbooking;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.railway.ticketbooking.models.User;
import org.railway.ticketbooking.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 * This class provides the logic to Authenticate Admin requests.
 */
@Provider
public class AuthenticationFilter
    implements ContainerRequestFilter {

  public static final String ADMIN_USER = "admin";
  public static final String ADMIN_PASSWORD = "pa$$word@123";
  public static final String REGISTER_URI = "user/register/";

  @Override
  public void filter(ContainerRequestContext requestContext) {

    String requestPath = requestContext.getUriInfo().getPath();
    if (requestPath.length() >= REGISTER_URI.length()
        && REGISTER_URI.equals(requestPath.substring(0, REGISTER_URI.length()))) {
      return;
    }

    if (!isAuthenticated(requestContext)) {
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }

  private boolean isAuthenticated(ContainerRequestContext requestContext) {
    boolean isAuthenticated = false;

    String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Basic ")) {
      return false;
    }

    // Extract username and password from the Authorization header
    String encodedCredentials = authHeader.substring("Basic ".length()).trim();
    String decodedCredentials =
        new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
    String[] credentials = decodedCredentials.split(":");

    UserRepository userRepo = new UserRepository();
    User user = userRepo.getUserByEmail(credentials[0]);
    if (user != null) {
      // Verify the credentials
      if (credentials.length == 2 && user.getEmail().equals(credentials[0])
          && user.getPassword().equals(credentials[1])) {
        isAuthenticated = true;
      }
    }
    return isAuthenticated;
  }
}
