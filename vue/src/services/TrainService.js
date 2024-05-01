import axios from 'axios';

axios.defaults.headers = {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
}

const url = 'http://will-barnard.com:49161/'

export default {
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