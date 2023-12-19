<script setup>

defineProps({result: Object})

const imageClicked = (image) => {
  // Handle image click event
  console.log(`Image clicked: ${image}`);
};

</script>


<template>
  <div class="container">

    <div class="row" v-for="(row, index) in Math.ceil(result.length / 3)" :key="index">
      <div class="col-md-4" v-for="(file, i) in result.slice(index * 3, (index + 1) * 3)" :key="i">
        <div class = "sub-top">
        <img :src="`http://localhost:80/upload/${file.contentId}/${file.saveFile}`" class="img-fluid" alt="Gallery Image" @click="imageClicked(file)" />
        </div>
        <div class="user-profile">
          <img src="@/assets/anonymous.jpeg" alt="Profile Image" class="rounded-image"> {{ file.userId }}
        </div>
      </div>
    </div>
  </div>
</template>


<style scoped>
.user-profile{
  margin: 5px 0px 5px 0px;
  font-family: 'Gamja Flower', sans-serif;
  font-weight: bold;
  font-size: 18px;
}
.rounded-image {
  border-radius: 50%;
  width: 30px;
  height: 30px;
  margin-right: 10px;
  margin-left: 10px;
}
.col-md-4{
  width: 330px;
  border: 1px #B5B0B0 solid;
  margin: 10px;
  border-radius: 8px;
}
.sub-top{
  text-align: center;
  padding: 5px 0px 5px 0px;
}
.sub-top>img {
  border-radius: 6px;
  width: 300px;
  height: 200px;
}
</style>