<template>
    <div class="container">
        <h1>Time till next trains:</h1>
        <div class="button">
            <h2 v-on:click="getTrainTime">GET</h2>
        </div>
    </div>
    
    
        <div v-if="show"> 
            <div class="container" v-for="train in ctatt.ctatt.eta" :key="train.rn">
            <p>{{ train.rt }} Line train with {{ train.stpDe }} will arrive at {{ train.arrT }}</p>
        </div>
    </div>
    
</template>


<script>
import TrainService from '../services/TrainService.js';

export default {
    data() {
        return {
            show: false,
            ctatt: {}
        }
    },
    methods: {
        getTrainTime() {
            TrainService.getTest().then(
                (response) => {
                    console.log(response.data)
                    this.ctatt = response.data;
                    this.show = true;
                }
            )
        },
        formatDate(dateString) {
            const date = new Date(dateString);
            const hours = (Date.now() - date) / 3600000;
            let difference = Math.ceil(hours);
            let display;
            if (difference > 24) {
                display = date.toLocaleDateString();
            } else {
                if (hours < 1) {
                    display = 'Less than an hour';
                } else {
                    display = `${difference} hour${difference == 1 ? '': 's'}`;
                }
            }
            return display;
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