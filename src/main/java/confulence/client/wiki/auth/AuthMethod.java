package confulence.client.wiki.auth;

/**
 * This interface defines the methods required for a HTTP authentication method.
 */
public interface AuthMethod {

  /**
   * This method generates a value to be passed in the "Authorization" header of any HTTP requests
   * using this authorization method.
   *
   * @return The value to set in the "Authorization" header of any requests using this authorization
   * method.
   */
  public String getAuthHeaderValue();
}
