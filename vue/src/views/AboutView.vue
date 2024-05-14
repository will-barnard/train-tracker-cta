<template>
  <div class="about">
    <h3>Thanks for checking out my train tracker!</h3>
    <div class="container">
      <div class="bodyContainer">
        <p class="divBody">
          Since March 2024 I have been collecting arriving train predictions from the official CTA API
          for the Logan Square Blue Line station.</p><br>
          <p class="divBody">Data-based content can be found under Logan Square.</p><br>
          <p class="divBody"> 
            Live predictions for the entire CTA system can be accessed using Stop Search and Lines.
          </p>
      </div>
    </div>
    <h4>Built by Will Barnard 2024</h4>
  </div>
</template>

<script>

export default {
  
}
</script>

<style scoped>
.bodyContainer {
  border: 2px solid;
  border-radius: 20px;
  padding: 15px;
  margin: 15px;
}
.divBody {
  margin-left: 10vw;
  margin-right: 10vw;
}
.about {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.headshot {
    border-radius: 500px;
    height: 20vh;
  }
  .container {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
  }
  .spacer {
    flex-grow: 1;
  }
  
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    margin-top: 30px;
  }
  
}
</style>
