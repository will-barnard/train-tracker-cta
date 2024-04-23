import axios from 'axios';

axios.defaults.headers = {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
}

const url = 'http://192.168.1.123:49157/'

export default {
    getTest() {
        return axios.get(url + 'test');
    },
    getArrivals(params) {
        return axios.post(url + 'arrivals', params);
    },
    getFollowThisTrain(params) {
        return axios.post(url + 'follow', params);
    },
    getLocations(params) {
        return axios.post(url + 'locations', params)
    }
}