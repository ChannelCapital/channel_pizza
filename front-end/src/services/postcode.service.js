import axios from 'axios';
const API= 'api.postcodes.io/postcodes/';
class PostcodeService {
  getPostcode(postcode) {
    return axios.get(API + postcode + '/autocomplete');
  }
}
export default new PostcodeService();