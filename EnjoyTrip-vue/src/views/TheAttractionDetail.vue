<script setup>
import {uploadImage, findPictureInfo} from "@/api/fileUpload";
import UploadedItem from "@/components/board/item/UploadedItem.vue";
import { ref, onMounted, watch } from "vue";
import IconHeart from "@/icons/IconHeart.vue";
import IconEmptyHeart from "@/icons/IconEmptyHeart.vue";
import { useRoute } from "vue-router";
import { getAttraction } from '@/api/attraction'
import { useMemberStore } from "@/stores/member"
import { storeToRefs } from "pinia";
import { useFavoriteStore } from "@/stores/favorite"

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const favoriteStore = useFavoriteStore();
const { getLikeInfo, uncheckHeart, checkHeart } = favoriteStore;
const { isFavorite } = storeToRefs(favoriteStore);

const route = useRoute();
const attraction = ref({});
const {contentId} = route.params;

const result = ref([]);
const imageUpload = ref({
  selectedFile: null,
  userId: "",
  contentId: ""
})

onMounted(() => {
  getDetail();
  getImages();
})

const getDetail = async () => {
  await getAttraction(
    route.params.contentId,
    ({ data }) => {
      attraction.value = data;
      console.log(attraction.value)
      if(userInfo._value != null){
        getLikeInfo(attraction.value.content_id, userInfo._value.userId);
      }
    },
    (error) => {
      console.log(error);
    }
  )
}

const uncheck = () => {
  uncheckHeart(attraction.value.content_id, userInfo._value.userId);
}

const check = () => {
  checkHeart(attraction.value.content_id, userInfo._value.userId);
}

watch(
  () => isFavorite.value,
  () => {
    getDetail();
  }
);

const uploadImg = () => {
  console.log("calling uploadImg...");
  if (userInfo._value != null){
    imageUpload.value.userId = userInfo._value.userId;
    const formData = new FormData();
    formData.append("file", imageUpload.value.selectedFile);
    formData.append("userId", imageUpload.value.userId);
    formData.append("contentId", imageUpload.value.contentId);

    uploadImage(formData, ({data}) => {
      console.log("이미지 업로드 성공");
      getImages();
    }), (error) => {
      console.error("이미지 업로드 오류:", error);}
  }
  else {
    console.log("error");
    console.error("로그인 해주세요.");
    alert("로그인 해주세요.");
  }
}

const handleFileUpload = (event) => {
  console.log("Event: ", event);
  imageUpload.value.selectedFile = event.target.files[0];
  imageUpload.value.contentId = contentId;
  console.log(event.target.files[0]);
}

const getImages = async () => {
  console.log("관광지 content id를 토대로 화면 하단에 필요한 정보 가지고 오기", contentId)
  //API 호출
  await findPictureInfo(
    contentId,
    ({data}) => {
      result.value = data;
      console.log(result);
    },
    (error) => {
      console.log(error);
    }
  )
}
</script>

<template>
  <div class="body">
    <div class="section-left"></div>
    <div class="section-mid">
      <div class="mid-top">
        <div class="top-left">
          <img :src="attraction.first_image">
        </div>
        <div class="top-mid">
          <div class="title">{{ attraction.title }}</div>
          <div class="address">{{ attraction.addr1 }} {{ attraction.addr2 }}</div>
        </div>
        <div class="top-right">
          <div v-if="isFavorite">
            <button @click="uncheck">
              <IconHeart />
            </button>
          </div>
          <div v-else>
            <button @click="check">
              <IconEmptyHeart />
            </button>
          </div>
          <div class="favorite-count">{{attraction.favor_cnt}}</div>
        </div>
      </div>
      <div class="mid-bottom">
        <div class="bottom-left">
          <UploadedItem :result="result"></UploadedItem>
        </div>
        <div class="bottom-right">
          <button data-bs-toggle="modal" data-bs-target="#imageUploadModal"> ✚</button>
        </div>
      </div>
    </div>
    <div class="section-right"></div>
    <div class="modal fade" id="imageUploadModal" tabindex="-1" aria-labelledby="imageUploadModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="imageUploadModalLabel" style="font-family: 'Kurale', serif;">Image Upload</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Image upload form -->
                <form @submit.prevent="uploadImg" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="imageFile" class="form-label" style="font-family: 'Kurale', serif;">Choose Image File</label>
                        <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*" style="font-family: 'Kurale', serif;" @change="handleFileUpload" required>
                    </div>
                    <button type="submit" class="btn btn-dark"  style="font-family: 'Kurale', serif;"> Upload</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

</template>

<style scoped>
div {
  width: 100%
}
.title{
  font-size: 33px;
  font-weight: bold;
}
.address{
  font-size: 20px;
}
.favorite-count{
  text-align: left;
  font-size: 25px;
  font-weight: bold;
}
.body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  display: flex;
  height: 100vh;
}

.section-mid {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.section-left {
  width: 7%;
  border-right: 1px solid #ECECEC;
}

.mid-top {
  border-bottom: 1px solid #ECECEC;
  display: flex;
  height: 400px;
  font-family: 'Gamja Flower', sans-serif;
}

.mid-bottom {
  display: flex;
}

.section-right {
  width: 7%;
  border-left: 1px solid #ECECEC;
}

.top-left {
  flex: 2;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 50px;
  margin-top: 50px;
  border: 1px solid #B5B0B0;
  border-radius: 10px;
}

.top-mid {
  flex: 3;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: left;
  margin-left: 50px;
}

.top-right {
  width: 20%;
  flex: 1;
  display: flex;
  align-items: center;
}

.top-right button {
  display: inline-block;
  margin: 0;
  margin-left: 10px;
  border: none;
  background-color: white;
}

.top-right p {
  display: inline-block;
  margin: 0;
  margin-left: 10px;
}

.bottom-left {
  width: 95%;

}

.bottom-right {
  width: 5%;
}

.bottom-right button {
  margin: 10px;
}

.rounded-image {
  border-radius: 50%;
  width: 30px;
  height: 30px;
  margin-right: 10px;
  margin-left: 10px;
}
</style>