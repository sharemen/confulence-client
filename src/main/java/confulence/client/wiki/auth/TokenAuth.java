package confulence.client.wiki.auth;

/**
 * This class represents a token-based HTTP authentication scheme, such as JWT.
 */
public class TokenAuth implements AuthMethod {

  private String token;

  /**
   * This constructor creates a new instance of {@link TokenAuth} using the given token.
   *
   * @param token The token to use for authentication
   */
  public TokenAuth(String token) {
    this.token = token;
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
    return "Bearer " + token;
  }
}
