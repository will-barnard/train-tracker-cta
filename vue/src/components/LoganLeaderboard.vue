<template>
    <div>

        <h3>Most late train: #{{ data.mostLate }}</h3>
        <h3>Least late train: #{{ data.leastLate }}</h3>
        <h3>Most faulty train: #{{ data.mostFaulty }}</h3>
        <h3>Least faulty train: #{{ data.leastFaulty }}</h3>
        <h3>Greatest prediction deviance: #{{ data.mostDeviant }}</h3>
        <h3>Lowest prediction deviance: #{{ data.leastDeviant }}</h3>
        
    </div>
</template>

<script>
export default {
    props: ['data']
}
</script>

<style scoped>
    h3 {
        margin: 20px;
        text-align: left;
    }
</style>