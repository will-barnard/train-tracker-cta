<template>
    <Transition>
    <div class="container" v-if="train">
        <div  :class="urgency" class="container-small">
            <p class="num">{{ formatTime(train.arrT) }}</p>
            <p class="train-description">{{ train.destNm }} bound Blue Line run #{{ train.rn }}</p>
        </div>
        <div class="data">

            <p v-if="train.isDly == 1"> • This train is currently running late</p>
            <p> • This train run is late {{ data.latePercent }}% of the time</p>
            <p v-if="awards.mostLate == train.rn"> • This is the most late train in the Blue Line</p>
            <p v-if="awards.leastLate == train.rn"> • his is the least late train in the Blue Line</p>

            <p v-if="train.isFlt == 1"> • This train is currently faulty</p>
            <p> • This train run is faulty {{ data.faultyPercent }}% of the time</p>
            <p v-if="awards.mostFaulty == train.rn"> • This is the most faulty train in the Blue Line</p>
            <p v-if="awards.leastFaulty == train.rn"> • This is the least faulty train in the Blue Line</p>

            <p> • Predictions deviate on average {{ formatDeviance(data.avgDeviation) }} from actual arrival time</p>
            <p v-if="awards.mostDeviant == train.rn"> • This train's predictions deviate from actual arrival time more than any other train in the Blue Line</p>
            <p v-if="awards.leastDeviant == train.rn"> • This train's predictions deviate from actual arrival time less than any other train in the Blue Line</p>

        </div>

    </div>
    </Transition>
</template>

<script>
import TimeService from '@/services/TimeService.js';

export default {
    props: ['train', 'urgency', 'data', 'awards'],
    methods: {
        formatTime(timestamp) {
            return TimeService.formatDate(timestamp);
        },
        formatDeviance(dev) {
            return TimeService.secondsFormat(dev);
        }
    }
}
</script>

<style scoped>
    .container {
    border: 2px solid #f8f8f8;
    border-radius: 10px;
    margin: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.container-small {
    display: flex;
    border: 2px solid #f8f8f8;
    border-radius: 10px;
    flex-direction: row;
    align-items: center;
    margin: 3px;
    width: 95%;
    padding: 5px;
}
.urgent {
    background-color: #ad0906;
    transition: background-color 5s;
}
.urgent-two {
    background-color: rgb(185, 136, 15);
    transition: background-color 5s;
}
.blue {
    background-color: #00a1de;
}
.num {
    border: 2px solid;
    border-radius: 5px;
    padding: 5px;
    margin: 5px;
    background-color: white;
    color: #222222;
}
.data p {
    width: 100%;
    text-align: left;
    margin-left: 5px;
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