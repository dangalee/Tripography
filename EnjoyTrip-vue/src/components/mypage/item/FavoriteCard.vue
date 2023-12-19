<script setup>
import IconHeart from '@/icons/IconHeart.vue';
import { deleteLike } from '@/api/favorite';
import { useRouter } from 'vue-router';

const props = defineProps({
    cardInfo: Object,
    userId: String
})
const router = useRouter(); 
const deleteCard = (contentId, userId) => {
    deleteLike(
        {
            userId: userId,
            contentId: contentId
        },
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
            <img :src="props.cardInfo.first_image"/>
        </div>
        <div class="card-desc">
            <div class="card-title-section">
                <router-link class="card-title" :to="{name: 'detail', params: { contentId: props.cardInfo.content_id }}">{{ props.cardInfo.title }}</router-link>
            </div>
            <div class="favorite" @click="deleteCard(props.cardInfo.content_id,props.userId)">
                <IconHeart/>
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