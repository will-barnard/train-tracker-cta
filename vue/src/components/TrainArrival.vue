<template>
    <div class="container">
        <h1>Time till next trains:</h1>
        <div class="button">
            <h2 v-on:click="getTrainTime">GET</h2>
        </div>
    </div>
    
    
        <div v-if="show"> 
            <div class="container" v-for="train in ctatt.ctatt.eta" :key="train.rn">
            <p>{{ train.rt }} Line train with {{ train.stpDe }} will arrive in {{ formatTime(train.arrT) }}</p>
        </div>
    </div>

    <!-- <div class="button">
            <h2 v-on:click="getArrival">GET</h2>
    </div>

    <div>
        <p>{{ arrivalResult }}</p>
    </div> -->
    
</template>


<script>
import TrainService from '../services/TrainService.js';
import TimeService from '@/services/TimeService.js';

export default {
    data() {
        return {
            show: false,
            showTest: false,
            ctatt: {},
            arrivalsParams: {
                mapid: null, 
                stpid: null, 
                max: null,
                rt: null
            },
            arrivalResult: {}
        }
    },
    methods: {
        getTrainTime() {
            TrainService.getTest().then(
                (response) => {
                    this.ctatt = response.data;
                    this.show = true;
                }
            )
        },
        getArrival() {
            TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.showTest = true;
                    this.arrivalResult = response.data;
                }
            );
        },
        formatTime(timestamp) {
            return TimeService.formatDate(timestamp);
        }
    },
    computed: {
        etaSize() {
            return this.ctatt.eta.length;
        }
    }
}

</script>


<style>
body {
    display: flex;
    justify-content: center;
    align-items: center;
}
h2 {
    display: inline;
    border: 2px solid rgba(235, 235, 235, 0.64);;
    border-radius: 10px;
    color: rgba(235, 235, 235, 0.64);;
    padding: 5px;
    cursor: pointer;
}
h2:hover {
    color: black;
    background-color: white;
}
.button {
    margin: 15px;
}
.container {
    border: 2px solid rgba(235, 235, 235, 0.64);;
    border-radius: 10px;
    padding: 15px;
    margin: 10px;
}
</style>