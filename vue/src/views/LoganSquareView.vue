<template>
    <div>
        <div class="controls">
            <h3 :class="!toggle ? 'selected' : ''" @click="arriving">Arriving Trains</h3>
            <h3 :class="toggle ? 'selected' : ''" @click="leaderboard">Leaderboard</h3>
        </div>
        <div v-show="!toggle">
            <Transition>
            <div v-if="show">
                <LoganArrival v-for="train in ctatt.ctatt.eta" :key="train.rn" :train="train" 
                :urgency="getUrgencyClass(train.arrT)" 
                :data="getData(train.rn)" 
                :awards="data"/>
            </div>
        </Transition>
        </div>

        <div v-if="toggle && show">
            <LoganLeaderboard :data="data" :stats="stats"/>
        </div>

    </div>
</template>

<script>
import TrainService from '../services/TrainService.js';
import TimeService from '@/services/TimeService.js';
import DataService from '../services/DataService.js';
import LoganArrival from '../components/LoganArrival.vue';
import LoganLeaderboard from '@/components/LoganLeaderboard.vue';

export default {
    components: {LoganArrival, LoganLeaderboard},
    data() {
        return {
            data: {},
            stats: {},
            show: false,
            ctatt: {},
            arrivalsParams: {
                mapid: 41020, 
                stpid: null, 
                max: null,
                rt: null
            },
            toggle: false
        }
    },
    created() {
        TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.show = true;
                    this.ctatt = response.data;
                });
        DataService.getData().then(
            (response) => {
                this.data = response.data;
                this.tick();
        });
        DataService.getStats().then(
            (response) => {
                this.stats = response.data;
            }
        );
    },
    methods: {
        tick() {
            if (this.show) {
                TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.show = true;
                    this.ctatt = response.data;
                }
            ); 
            }
            setTimeout(() => {
                this.tick()
            }, 5000);
        },
        getServiceName(str) {
            if (str.startsWith("Subway service toward")) {
                return str.substring(22) + " bound";
            } if (str.startsWith("Service toward")) {
                return str.substring(15) + " bound";
            } if (str == "Service at Outer Loop platform") {
                return "Outer Loop platform"
            }
            
        },
        getUrgencyClass(arrival) {
            let int = this.getMinutes(arrival);
            if (int < 3) {
                return 'urgent';
            }
            if (int < 7) {
                return 'urgent-two'
            }
            else {

                return "blue";
            }
        },
        getData(rn) {
            return this.data.runList.find((obj) => {
                return obj.trainRun == rn;
            });
        },
        getMinutes(arrival) {
            return TimeService.getArrivalToMinute(arrival)
        },
        arriving() {
            this.toggle = false;
        },
        leaderboard() {
            this.toggle = true;
        }
    }
}
</script>

<style>
.controls {
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.controls:hover {
    cursor: pointer;
}
.controls h3 {
    margin: 5px;
    border: 2px solid #f8f8f8;
    border-radius: 10px;
    margin: 5px;
    padding: 5px;
}
.selected {
    color: black;
  background-color: white;
}
.v-enter-active,
.v-leave-active {
  transition: opacity 0.5s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
  transition: opacity 0.5s ease;
}

</style>