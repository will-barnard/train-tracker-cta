<template>
    <div>
        <div v-if="invalid">
            Invalid stop
        </div>
        <div>
            <h1 >Time till next trains @ {{ stop.station_name }}</h1>
            <div v-if="show"> 
                <div class="container" v-for="train in ctatt" :key="train.rn" 
                    :class="getUrgencyClass(train.arrT)">
                    <p class="num">{{ formatTime(train.arrT) }}</p>
                    <p class="train-description">{{ train.destNm }} bound {{ getName(train.rt)}} Line</p>
                </div>
            </div>
            <div class="refresh">
                <h1 v-on:click="refresh()">Refresh</h1>
            </div>
        </div>
    </div>
</template>
<script>
import TrainService from '@/services/TrainService';
import TimeService from '@/services/TimeService';

export default {
    data() {
        return {
            arrivalsParams: {
                mapid: null, 
                stpid: null, 
                max: null,
                rt: null
            },
            mapId: "",
            stop: {},
            ctatt: [],
            invalid: false,
            show: false
        }
    },
    created() {
        this.mapId = this.$route.params.mapId;
        this.stop = this.$store.state.systemInfo.find( (stop) => {
            return stop.map_id == this.mapId;
        });
        this.arrivalsParams.mapid = this.mapId;
        this.getTrains(this.arrivalsParams);
        if (this.stop) {
            this.tick();
        } else (
            this.invalid = true
        )
    },
    methods: {
        getTrains() {
            TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.show = true;
                    this.ctatt = response.data.ctatt.eta;
                    this.show = true;
                }
            );
        },
        formatTime(timestamp) {
            return TimeService.formatDate(timestamp);
        },
        getMinutes(arrival) {
            return TimeService.getArrivalToMinute(arrival)
        },
        tick() {
            if (this.show) {
                TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.show = true;
                    this.ctatt = response.data.ctatt.eta;
                }
            ); 
            }
            setTimeout(() => {
                this.tick()
            }, 5000);
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

                return "";
            }
        },
        getName(rt) {
            if(rt == "G") {
                return "Green";
            } if (rt == "Org") {
                return "Orange";
            } if (rt == "Brn") {
                return "Brown";
            } if (rt == "Y") {
                return "Yellow";
            } if (rt == "P") {
                return "Purple";
            }
            return rt;
        }
    }
}
</script>
<style scoped>
    .urgent {
        background-color: #ad0906;
        transition: background-color .5s;
    }
    .urgent-two {
        background-color: rgb(185, 136, 15);
        transition: background-color .5s;
    }
    .container {
        border: 2px solid #f8f8f8;
        height: 5vh;
        border-radius: 10px;
        margin: 10px;
        display: flex;
        align-items: center;
    }
    .num {
        border: 2px solid;
        border-radius: 5px;
        padding: 5px;
        margin: 10px;
        background-color: white;
        color: #222222;
    }
    .refresh {
        display: inline-block;
        border: 2px solid;
        border-radius: 30px;
        padding: 8px;
        margin: 15px;
        cursor: pointer;
        transition: .6s;
    }
    .refresh:hover {
        background-color: #009b3a;
        transition: .6s;
    }
</style>