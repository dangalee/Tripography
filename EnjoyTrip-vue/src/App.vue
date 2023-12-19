<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
import TheFooterNaverbar from "./components/layout/TheFooterNaverbar.vue";
</script>

<template>
  <div>
    <TheHeadingNavbar class="header"></TheHeadingNavbar>
    <router-view id="wrapper"></router-view>
    <TheFooterNaverbar class="footer"></TheFooterNaverbar>
  </div>
</template>

<style scoped>
#wrapper{
  height: 740px;
}

.footer {
    height: 50px;
    text-align: center;
    padding: 10px;
    background-color: #f0f0f0;
    width: 100%;
    position: relative;
    /* transform: translateY(-100%); */
}
</style>
