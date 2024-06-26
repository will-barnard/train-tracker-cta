<template>
    <div>

        <div class="container">
            <h3>Most late train: #{{ data.mostLate }}</h3>
        <p>Late {{ data.runList.find((obj) => {
                return obj.trainRun == data.mostLate;
            }).latePercent}}% of the time
        </p>
        </div>
        <div class="container">
            <h3>Least late train: #{{ data.leastLate }}</h3>
        <p>Late {{ data.runList.find((obj) => {
                return obj.trainRun == data.leastLate;
            }).latePercent}}% of the time
        </p>
        </div>
        <br>
        <div class="container">
            <h3>Most faulty train: #{{ data.mostFaulty }}</h3>
        <p>Faulty {{ data.runList.find((obj) => {
                return obj.trainRun == data.mostFaulty;
            }).faultyPercent}}% of the time
        </p>
        </div>
        <div class="container">
            <h3>Least faulty train: #{{ data.leastFaulty }}</h3>
        <p>Faulty {{ data.runList.find((obj) => {
                return obj.trainRun == data.leastFaulty;
            }).faultyPercent}}% of the time
        </p>
        </div>
        <br>
        <div class="container">
            <h3>Greatest prediction deviance: #{{ data.mostDeviant }}</h3>
        <p>Prediction deviate on average by {{ formatDeviance(data.runList.find((obj) => {
                return obj.trainRun == data.mostDeviant;
            }).avgDeviation)}}
        </p>
        </div>
        <div class="container">
            <h3>Lowest prediction deviance: #{{ data.leastDeviant }}</h3>
        <p>Prediction deviate on average by {{ formatDeviance(data.runList.find((obj) => {
                return obj.trainRun == data.leastDeviant;
            }).avgDeviation)}}
        </p>
        </div>

        <div class="stats">
            <h3>Data generated from {{ stats.runCount }} train approaches and {{ stats.arrivalCount }} predictions</h3>
        </div>
        
    </div>
</template>

<script>
import TimeService from '@/services/TimeService';

export default {
    props: ['data', 'stats'],
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
    h3 {
        text-align: left;
    }
    p {
        text-align: left;
    }
    .container {
        background-color: var(--vt-c-black-mute);
        border: 2px solid;
        border-radius: 10px;
        margin: 5px;
        padding: 5px;
    }
    .stats {
        background-color: var(--vt-c-black-mute);
        margin: 10px;
        padding: 10px;
        margin-top: 20px;
        border: 2px solid;
        border-radius: 10px;
    }
</style>