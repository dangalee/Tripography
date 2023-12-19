<script setup>
import { ref, onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useMemberStore } from '@/stores/member';
import FavoriteCard from './item/FavoriteCard.vue';
import { getFavoriteList } from '@/api/favorite'

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const favoriteList = ref([])
const userid = ref("")
onMounted(() => {
    let userId = userInfo.value.userId;
    userid.value = userId;
    getFavoriteList(
        userId,
        ({data})=>{
            favoriteList.value = data.favoritelist;
            console.log(favoriteList.value)
        },
        (error)=>{
            console.log(error);
        }
    )
})

</script>

<template>
    <div class="back">
        <div class="title">
            My Favorite list..
        </div>
        <div >
            <div class="card-section" v-for="favoriteInfo in favoriteList" :key="favoriteInfo.content_id">
                <FavoriteCard :card-info="favoriteInfo" :user-id="userid"/>
            </div>
        </div>
    </div>
</template>

<style scoped>
.back {
    margin: 0 auto;
    justify-content: center;
    align-items: center;
    font-family: 'Kurale', serif;
}
.title {
    text-align: left;
    color: black;
    font-size: 40px;
    font-weight: bold;
    font-family: 'Kurale', serif;
    padding: 20px;
    padding-left: 30px;
}
.card-section{
    display: inline-block;
    width: 333px;
}
.custom-button {
    font-size: 17px;
    /* 원하는 폰트 크기 */
    font-weight: bold;
    /* 굵게 만들기 */
    border: none;
    /* 테두리 제거 */
    background-color: #B5B0B0;
    /* 배경색 설정 */
    color: black;
    /* 글자 색 설정 */
    padding: 10px 20px;
    /* 패딩 설정 */
    cursor: pointer;
    width: 100px;
    margin: 10px 35px 10px 35px;
}

.custom-button:hover {
    background-color: #4A4242;
    /* 호버 시 배경색 변경 */
    color: white;
}
</style>