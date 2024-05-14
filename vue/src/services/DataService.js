import axios from 'axios';

axios.defaults.headers = {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
}

const url = 'http://will-barnard.com:49161/'

export default {
    getData() {
        return axios.get(url + 'lsdata');
    }
}