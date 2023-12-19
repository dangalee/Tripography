<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();
const showComponent = (componentName) => {
  router.push({name: componentName})
}

</script>

<template>
  <div class="body">
    <div class="section-left"></div>
    <div class="section-mid-left">
        <div @click.prevent="showComponent('mypage-profile')"> 프로필 수정 </div>
        <div @click.prevent="showComponent('mypage-favorite')"> 나의 좋아요 관리 </div>
        <div @click.prevent="showComponent('mypage-photo')"> 나의 사진 관리 </div>
    </div>
    <div class="section-mid-right">
      <router-view></router-view>
    </div>
    <div class="section-right"></div>
  </div>
</template>


<style>
div {
  width: 100%
}

.body {
  margin: 0 auto;
  padding: 0;
  display: flex;
  height: 600px;
}

.section-left {
  width: 7%;
}

.section-mid-left {
  width: 18%;
  border: 1px solid #ececec;
  border-top: none;
  text-align: center;
}
.section-mid-left div {
  border-bottom: 1px solid #ececec;
  font-family: 'Gamja Flower', sans-serif;
  font-weight: bold;
  font-size: large;
  padding: 20px 5px 20px 5px;
}
.section-mid-left div:hover {
    background-color: #B5B0B0; /* 호버 시 배경색 변경 */
    color:white;
}
.section-mid-right {
  width: 68%;
  border: 1px solid #ececec;
  border-top: none;
  border-left: none;
}

.section-right {
  width: 7%;
}
</style>