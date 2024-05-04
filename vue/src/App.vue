<template>
  <div>
    
    <header>
      <h1>Train Tracker</h1>
    </header>
    
    <div class="space"></div>

    <NavComp />
    <div class="space"></div>
    <main id="app-body">
      <RouterView />
    </main>

    <footer>

    </footer>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router'
</script>

<script>
import NavComp from './components/NavComp.vue';

export default {
  components: {NavComp}
}
</script>

<style scoped>
body {
  display: flex;
  justify-content: center;
}
header {
  font-size: 1.4em;
  border: 2px solid;
  border-radius: 10px;
  
  margin-left: 10vw;
  margin-right: 10vw;
  margin-top: 10px;
  margin-bottom: 10px;
}
header h1 {
  margin: 0px;
  padding: 0px;
}
.space {
  height: 10px;
}

</style>
