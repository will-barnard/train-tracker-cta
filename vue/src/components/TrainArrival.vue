<template>
    <div class="container2">
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
                    <p class="num">{{ formatTime(train.arrT) }}</p>
                    <p class="train-description">{{ train.destNm }} bound {{ getName(train.rt)}} Line</p>
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
    },
    created() {
        this.tick();
    }
}


</script>


<style scoped>
h2 {
    display: inline;
    border: 2px solid #f8f8f8;
    border-radius: 10px;
    color: #f8f8f8;
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
    border: 2px solid #f8f8f8;
    border-radius: 10px;
    margin: 10px;
    display: flex;
    align-items: center;
}
.container2 {
    border: 2px solid #f8f8f8;
    border-radius: 10px;
    margin: 10px;
}
.urgent {
    background-color: #ad0906;
    transition: background-color .5s;
}
.urgent-two {
    background-color: rgb(185, 136, 15);
    transition: background-color .5s;
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