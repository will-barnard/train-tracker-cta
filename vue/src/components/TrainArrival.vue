<template>
    <div class="container">
        <div class="toggles">
            <h2 v-for="train in trainLines" :key="train.name" 
                :class="getTrainCss(train.id)"
                :id="currentTrain == train.id ? getTrainCss(train.id): ''"
                v-on:click="selectTrain(train.id)"
                >
                {{ train.name }}
            </h2>
        </div>

        <Transition>
            <div class="stops" v-if="currentTrain">
                <p>Select a stop:</p>
                <form>
                    <select id="stopList" name="stops" class="stopForm" @change="stopChange($event)" v-model="selectedStop">
                        <option disabled>-----</option>
                        <option v-for="stop of this.stopLines" :key="stop.map_id" :value="stop.map_id">{{ stop.station_name }}</option>
                    </select>
                </form>

                <!-- <p v-for="stop in $store.state.systemInfo" :key="stop.stop_id">{{ stop.stop_name }}</p> -->
            </div>
        </Transition>


        
    </div>

    

    <Transition>
        <div v-if="show">
            <h1 >Time till next trains @ {{ selectedStop.station_name }}</h1>
            
            <div> 
                <div class="container" v-for="train in ctatt.ctatt.eta" :key="train.rn" 
                    :class="getUrgencyClass(train.arrT)">
                    <p>{{ train.rt }} Line train with {{ train.stpDe }} will arrive in {{ formatTime(train.arrT) }}</p>
                </div>
            </div>
            <div class="refresh">
                <h1 v-on:click="refresh()">Refresh</h1>
            </div>
        </div>
    </Transition>
    
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
            stops: {},
            currentTrain: null,
            stopLines: {},
            arrivalResult: {},
            selectedStop: {},
            trainLines: {
                redLine: {
                    id: 1,
                    name: "Red Line"

                },
                blueLine: {
                    id: 2,
                    name: "Blue Line"
                },
                brownLine: {
                    id: 3,
                    name: "Brown Line"
                },
                greenLine: {
                    id: 4,
                    name: "Green Line"
                },
                orangeLine: {
                    id: 5,
                    name: "Orange Line"
                },
                purpleLine: {
                    id: 6,
                    name: "Purple Line"
                },
                pinkLine: {
                    id: 7,
                    name: "Pink Line"
                },
                yellowLine: {
                    id: 8,
                    name: "Yellow Line"
                }
            }
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
        formatTime(timestamp) {
            return TimeService.formatDate(timestamp);
        },
        getMinutes(arrival) {
            return TimeService.getArrivalToMinute(arrival)
        },
        selectTrain(id) {
            this.show = false;
            this.ctatt = {};
            if (this.currentTrain == id) {
                this.show = false;
                this.currentTrain = null;
                this.stopLines = null;
            } else {
                this.currentTrain = id;
                this.setStopLines(this.getStopLines(id));
            }
        },
        stopChange(event) {
            this.ctatt = {};
            this.show = false;
            let mapId = event.target.value;
            this.selectedStop = this.$store.state.systemInfo.find(
                (stop) => {
                    return stop.map_id == mapId;
                }
            );
            this.arrivalsParams.mapid = this.selectedStop.map_id;
            TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.show = true;
                    this.ctatt = response.data;
                }
            );
        },
        refresh() {
            this.show = false;
            TrainService.getArrivals(this.arrivalsParams).then(
                (response) => {
                    this.show = true;
                    this.ctatt = response.data;
                }
            );
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
        setStopLines(obj) {
            this.stopLines = obj;
        },
        getStopLines(id) {
            switch (id) {
                case 1:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.red == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 2:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.blue == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 4:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.g == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 3:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.brn == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 5:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.o == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 6:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.p == true || stop.pexp == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 7:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.pnk == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                case 8:
                    return this.$store.state.systemInfo.filter(
                        (stop) => {
                            return stop.y == true;
                        }
                    ).filter(
                        (obj, index, data) => {
                            return data.findIndex((value) => value.map_id === obj.map_id) === index;
                        }
                    )
                default: return null;
        
            }
        },
        getTrainCss(id) {
            switch (id) {
                case 1:
                    return 'red';
                case 2:
                    return 'blue';
                case 3:
                    return 'brown';
                case 4:
                    return 'green';
                case 5:
                    return 'orange';
                case 6:
                    return 'purple';
                case 7:
                    return 'pink';
                case 8:
                    return 'yellow';
            }
        }
    },
    computed: {
        etaSize() {
            return this.ctatt.eta.length;
        }
    }
}


</script>


<style scoped>
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
.urgent {
    background-color: darkred;
}
.urgent-two {
    background-color: darkgoldenrod;
}
.stops {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items:center;
}
.stops form {
    flex-grow: 0;
}
.stops select {
    font-size: 1em;
}
.stops p {
    text-align: center;
    font-size: 1.5em;
    flex-grow: 0;
}
.stopForm {
    width: 40%;
}
.toggles {
    font-size: small;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    margin-bottom: 10px;
}
.toggles h2 {
    transition: .5s;
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
.red:hover {
    background-color: #C60C30;
    color: white;
}
.blue:hover {
    background-color: #00a1de;
    color: white;
}
.brown:hover {
    background-color: #62361b;
    color: white;
}
.green:hover {
    background-color: #009b3a;
    color: white;
}
.orange:hover {
    background-color: #f9461c;
    color: white;
}
.purple:hover {
    background-color: #522398;
    color: white;
}
.pink:hover {
    background-color: #e27ea6;
    color: white;
}
.yellow:hover {
    background-color: #f9e300;
    color: black;
}
#red {
    background-color: #C60C30;
}
#blue {
    background-color: #00a1de;
}
#brown {
    background-color: #62361b;
}
#green {
    background-color: #009b3a;
}
#orange {
    background-color: #f9461c;
}
#purple {
    background-color: #522398;
}
#pink {
    background-color: #e27ea6;
}
#yellow {
    background-color: #f9e300;
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