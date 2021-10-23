import axios from 'axios';

const url = 'http://localhost:8080/phonenumbers';

const phoneAPi = {
    url,
    getPhoneNumbers: () => axios.get(url),
}

export default phoneAPi;