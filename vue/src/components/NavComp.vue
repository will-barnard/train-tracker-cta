<template>
  <nav>
    <h2 v-on:click="toHome()" :class="currentRoute == 'home'?'selected':''">Home</h2>
    <h2>Logan Square</h2>
    <h2 v-on:click="toAbout()" :class="currentRoute == 'about'?'selected':''">About</h2>
  </nav>
</template>


<script>
export default {
  data() {
    return {
      currentRoute: null
    }
  },
  methods: {
    toHome() {
      this.currentRoute = 'home';
      this.$router.push({name: 'home'})

    },
    toAbout() {
      this.currentRoute = 'about';
      this.$router.push({name: 'about'})
    },
    calcRoute() {
      this.currentRoute = this.$route.name;
    }
  },
  created() {
    this.currentRoute = this.$route.name;
  }
  
}
</script>


<style>
h2 {
    display: inline;
    border: 2px solid rgba(235, 235, 235, 0.64);;
    border-radius: 10px;
    color: rgba(235, 235, 235, 0.64);;
    padding: 5px;
    cursor: pointer;
    margin: 10px;
}
h2:hover {
    color: black;
    background-color: white;
}
.selected {
  color: black;
  background-color: white;
}
</style>