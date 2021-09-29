import axios from 'axios';
/**
 * URL under which authentication and registration actions are handled.
 */
const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
  /**
   * Checks user credentials, in case of success, an access token is set.
   * @param {object} user 
   * @returns Access token
   */
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }
  /**
   * Function to end session by removing access token.
   */
  logout() {
    localStorage.removeItem('user');
  }
  /**
   * User credentials are posted in order to create a new user
   * @param {object} user 
   * @returns S
   */
  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      firstName: user.firstName,
      lastName: user.lastName,
      postCode: user.postCode,
      password: user.password
    });
  }
}

export default new AuthService();
