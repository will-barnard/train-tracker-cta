import { createStore as _createStore } from 'vuex';

export function createStore() {
  let store = _createStore({
    state: {
      arrivalsParams: {
        mapid: null, 
        stpid: null, 
        max: null,
        rt: null
      },
      followThisTrainParams: {
        runnumber: null
      },
      locationParams: {
        rt: []
      }
    },
    mutations: {
    },
  });
  return store;
}
