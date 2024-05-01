<template>
  <nav>
    <h2 @click="toSearch()" :class="currentRoute == 'stop'?'selected':''">Stop Search</h2>
    <h2 @click="toHome()" :class="currentRoute == 'home'?'selected':''">Lines</h2>
    <h2 @click="toLogan()" :class="currentRoute == 'logan'?'selected':''">Logan Square</h2>
    <h2 @click="toAbout()" :class="currentRoute == 'about'?'selected':''">About</h2>
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
    toSearch() {
      this.currentRoute = 'stop';
      this.$router.push({name: 'stop'})

    },
    toAbout() {
      this.currentRoute = 'about';
      this.$router.push({name: 'about'})
    },
    toLogan() {
      this.currentRoute = 'logan';
      this.$router.push({name: "logan"})
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
    border: 2px #f8f8f8 solid;
    border-radius: 10px;
    color: #f8f8f8;
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