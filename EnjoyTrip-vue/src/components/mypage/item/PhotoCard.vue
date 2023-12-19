<script setup>
import IcondDeleteButton from '@/icons/IconDeleteButton.vue';
import { deletePhoto } from '@/api/fileUpload';
import { useRouter } from 'vue-router';

const props = defineProps({
    photoInfo: Object,
    userId: String
})
const router = useRouter(); 
const deleteCard = (fileId) => {
    deletePhoto(
        fileId,
        ({data})=>{
            console.log(data.message);
            alert(data.message);
            router.push({name: "mypage"});
        },
        (error)=>{
            console.log(error)
        }
    )
}
</script>

<template>
    <div class="favoriteCard">
        <div class="card-image">
            <img :src="`http://localhost:80/upload/${props.photoInfo.contentId}/${props.photoInfo.saveFile}`"/>
        </div>
        <div class="card-desc">
            <div class="card-title-section">
                <router-link class="card-title" :to="{name: 'detail', params: { contentId: props.photoInfo.contentId }}">{{ props.photoInfo.title }}</router-link>
            </div>
            <div class="favorite" @click="deleteCard(props.photoInfo.fileId)">
                <IcondDeleteButton/>
            </div>
        </div>
    </div>
</template>

<style scoped>
.favoriteCard{
    width: 310px;
    border: 1px #B5B0B0 solid;
    margin: 10px;
    padding: 5px;
    border-radius: 6px;
}
.card-desc{
    display: flex;
}
.card-title-section{
  padding-left: 10px;
  text-align: left;
  white-space: normal;
  font-family: 'Gamja Flower', sans-serif;
  font-weight: bold;
}
.card-title{
    text-decoration: none;
    color: black;
}
.favorite{
    width:50px;
    text-align: center;
}
.card-image{
  padding-top: 5px;
  padding-bottom: 5px;
  text-align: center;
}
.card-image>img {
  border-radius: 6px;
  width: 300px;
  height: 200px;
}
</style>