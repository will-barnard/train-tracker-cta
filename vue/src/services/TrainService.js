import axios from 'axios';

axios.defaults.headers = {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
}

export default {
    getTest() {
        return axios.get('http://localhost:8080/test');
    }
    // getLoganSquareStation() {
    //     return axios.get('http://localhost:8080/test');
    // },
    // getLoganNorthbound() {
    //     return axios.get();
    // },
    // getLoganSouthbound() {
    //     return axios.get();
    // }
}