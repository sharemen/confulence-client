package confulence.client.wiki.auth;

import java.util.Base64;

/**
 * This class represents the Basic HTTP authentication scheme.
 */
public class BasicAuth implements AuthMethod {

  private String username;
  private String password;

  /**
   * This constructor creates a new instance of {@link BasicAuth} using the given username and
   * password.
   *
   * @param username The username to use for authentication
   * @param password The corresponding password for the given username
   */
  public BasicAuth(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * This method generates a value to be passed in the "Authorization" header of any HTTP requests
   * using this authorization method.
   *
   * @return The value to set in the "Authorization" header of any requests using this authorization
   * method.
   */
  @Override
  public String getAuthHeaderValue() {
    return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
  }
}
