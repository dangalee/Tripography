<script setup></script>

<template>
  <div class="container text-center mt-3">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <div class="boardTitle">Q & A..</div>
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<style scoped>
.boardTitle{
  text-align: left;
  font-family: 'Kurale', serif;
  font-size: 40px;
  font-weight: bold;
  padding: 20px;
}
</style>
