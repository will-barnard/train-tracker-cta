<template>
    <Transition>
    <div class="container" v-if="train">
        <div  :class="urgency" class="container-small">
            <p class="train-description">{{ train.destNm }} bound Blue Line run #{{ train.rn }}</p>
            <p class="num">{{ formatTime(train.arrT) }}</p>
        </div>
        <div class="data">
            <div class="stats">
                <div class="stat">
                    <p>Late</p>
                    <p>{{ data === null ? 'N/A' : data.latePercent + '%' }}</p>
                </div>
                <div class="stat">
                    <p>Faulty</p>
                    <p>{{ data === null ? 'N/A' : data.faultyPercent + '%' }}</p>
                </div>
                <div class="stat">
                    <p>Deviation</p>
                    <p>{{ data === null ? 'N/A' : formatDeviance(data.avgDeviation) }}</p>
                </div>
            </div>

            <div class="currently" v-if="train.isFlt == 1 || train.isDly == 1">
                <p v-if="train.isFlt == 1">This train is currently faulty</p>
                <p v-if="train.isDly == 1">This train is currently running late</p>
            </div>

            <div class="extra" v-if="awards.mostLate == train.rn || awards.leastLate == train.rn || awards.mostFaulty == train.rn || awards.leastFaulty == train.rn || awards.mostDeviant == train.rn || awards.leastDeviant == train.rn">
                <p v-if="awards.mostLate == train.rn">This is the most late train in the Blue Line</p>
                <p v-if="awards.leastLate == train.rn">This is the least late train in the Blue Line</p>
                <p v-if="awards.mostFaulty == train.rn">This is the most faulty train in the Blue Line</p>
                <p v-if="awards.leastFaulty == train.rn">This is the least faulty train in the Blue Line</p>
                <p v-if="awards.mostDeviant == train.rn">This train's predictions deviate from actual arrival time more than any other train in the Blue Line</p>
                <p v-if="awards.leastDeviant == train.rn">This train's predictions deviate from actual arrival time less than any other train in the Blue Line</p>
            </div>
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
    border-radius: 7px;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    margin: 0px;
    width: 100%;
    padding: 0px;
}
.train-description {
    margin-left: 10px;
    font-weight: bold;
}
.num {
    border-radius: 5px;
    padding: 5px;
    margin: 0px;
    background-color: white;
    color: #222222;
    font-weight: bold;
}
.stats {
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: space-between;
    flex-grow: 1;
}
.stat {
    flex-grow: 1;
    border: 2px solid;
    border-radius: 7px;
    margin: 4px;
    background-color: var(--vt-c-black-mute);

}
.data {
    width: 100%;

}
.currently {
    flex-grow: 1;
    border: 2px solid;
    border-radius: 7px;
    margin: 4px;
    background-color: var(--vt-c-black-mute);
    text-align: left;
    padding-left: 10px;
}
.extra {
    flex-grow: 1;
    border: 2px solid;
    border-radius: 7px;
    margin: 4px;
    background-color: var(--vt-c-black-mute);
    text-align: left;
    padding-left: 10px;
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