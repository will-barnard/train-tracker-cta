import { createStore as _createStore } from 'vuex';
// import axios from 'axios';

export function createStore() {
  let store = _createStore({
    state: {
      northbound: {},
      southbound: {}
    },
    mutations: {
      LOAD_NORTHBOUND_TRAINS(state, payload) {
        state.northbound = payload;
      }
    },
  });
  return store;
}
