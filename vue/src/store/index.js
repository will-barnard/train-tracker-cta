import { createStore as _createStore } from 'vuex';
import SystemData from '../assets/SystemData.json';

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
      },
      systemInfo: SystemData
    },
    mutations: {
    },
  });
  return store;
}
