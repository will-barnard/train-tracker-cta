
import axios from 'axios';

axios.defaults.headers = {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
}

const url = import.meta.env.VITE_API_URL;

export default {
    getData() {
        return axios.get(url + 'lsdata');
    },
    getStats() {
        return axios.get(url + 'stats')
    }
}