<script setup>
import IconHeart from '@/icons/IconHeart.vue';

defineProps({ feed: Object });
</script>

<template>
  <div class="bestItem">
    <div class="sub-section-top">
      <div class="sub-section-left">
        <router-link :to="{ name: 'detail', params: { contentId: feed.content_id } }" class="nav-link">
          <img :src="feed.first_image" class="rounded-image">
        </router-link>
      </div>
      <div class="sub-section-mid">
        <div class="feed-title">{{ feed.title }}</div>
        <div>{{ feed.addr1 }} {{ feed.addr2 }}</div>
      </div>
      <div class="sub-section-right">
      <IconHeart />
      <p>{{ feed.favor_cnt }}</p>
    </div>
  </div>
</div>
<!-- <div class="sub-section-bottom">
    <div class="box">
      <div :id="feed.id" class="carousel slide" data-bs-ride="carousel">

        <div class="carousel-box">
          <div class="carousel-inner" style="max-height: 500px; overflow: hidden;">
            <div class="carousel-item active">
              <img :src="feed.first_image" class="d-block w-100" alt="...">
            </div>
          </div>

        </div>
        <button class="carousel-control-prev" type="button" :data-bs-target="'#' + feed.id" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" :data-bs-target="'#' + feed.id" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>
            </div>
          </div> -->
</template>
<style scoped>
.bestItem {
  font-family: 'Gamja Flower', sans-serif;
}
.sub-section-mid div{
  padding-left: 30px;
  font-weight: bold;
}
.favorite-button {
  border: none;
  background-color: white;
}
.feed-title{
  font-size: 23px;
}
.sub-section-left {
  width: 15%;
  flex: 1;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.sub-section-mid {
  width: 70%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.sub-section-right {
  width: 15%;
  flex: 1;
  display: flex;
  align-items: center;

}

.sub-section-right button {
  display: inline-block;
  margin: 0;
  margin-left: 10px;
}

.sub-section-right p {
  display: inline-block;
  margin: 0;
  margin-left: 10px;
  font-size: 23px;
}

.sub-section-top {
  display: flex;
  height: 100px;
  width: auto;
}

.box {
  margin: 50px;
  margin-left: 0px;
  overflow: hidden;
}

.carousel-box {
  padding-bottom: 0px;
  padding-top: 0px;
  padding-left: 50px;
  padding-right: 50px;
  border-radius: 2%;
}

.sub-section-bottom {
  flex: 1;
  height: 70%;
}

.rounded-image {
  border-radius: 50%;
  width: 85px;
  height: 85px;

}

.sub-section-mid div {
  margin: 0;
}
</style>