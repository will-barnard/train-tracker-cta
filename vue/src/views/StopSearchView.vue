<template>
    <div>
        <h3>Search a stop</h3>
        <input class="input-field" type="text" v-model="input" @keyup="change()" placeholder="Search stops..." />
    
        <StopComponent v-for="stop in stopList" :key="stop.mapId" :stop='stop' @click="goDetail(stop.map_id)" />
    </div>
</template>

<script>
import StopComponent from '../components/StopComponent.vue';

export default {
    components: {StopComponent},
    data() {
        return {
            input: "",
            stopList: []
        }
    },
    methods: {
        change() {
            this.stopList = this.$store.state.systemInfo.filter((stop) => {
                return stop.stop_name.toLowerCase().includes(this.input.toLowerCase())
            });
        },
        goDetail(map_id) {
            this.$router.push({name: 'stop-detail', params: {mapId: map_id}})
        }
    }
}
</script>
<style scoped>
    .container {
        width: 100%;
        height: 5vh;
        border: 2px solid;
        border-radius: 5px;
        margin-bottom: 3px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .container:hover {
        cursor: pointer;
    }
    .input-field {
        width: 60vw;
        margin-bottom: 15px;
        margin-top: 15px;
    }
</style>