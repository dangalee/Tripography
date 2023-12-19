<script setup>
import { ref, onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useMemberStore } from '@/stores/member';
import { updateMember } from '@/api/member'
import { useRouter } from 'vue-router';

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const { getUserInfo } = memberStore;
const updateInfo = ref({
    userId: "",
    userName: "",
    userEmail: "",
})

onMounted(() => {
    loadUserInfo();
})

const loadUserInfo = ()=>{
    updateInfo.value.userName = userInfo.value.userName;
    updateInfo.value.userEmail = userInfo.value.userEmail;
    updateInfo.value.userId = userInfo.value.userId;
}

const update = (updateInfo) => {
    updateMember(
        updateInfo,
        ({data})=>{
            let token = sessionStorage.getItem("accessToken")
            getUserInfo(token);
            alert(data.message);
            router.push({name: "mypage-profile"})
        },
        (error)=>{
            console.log(error)
        }
    )
};
</script>

<template>
    <div>
        <div class="title">
            Update Profile..
        </div>
        <div class="back">

            <div class="image">
                <img src="@/assets/siba_profile.jpeg">
            </div>
            <div class="form">
                <form v-on:submit.prevent="submitForm">
                    <div class="input-container">
                        <input type="text" placeholder="Name.." v-model="updateInfo.userName">
                    </div>
                    <div class="input-container">
                        <input type="email" placeholder="Email.." v-model="updateInfo.userEmail">
                    </div>
                    <div class="button">
                        <button class="custom-button" @click="update(updateInfo)">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped>
.back {
    margin: 0 auto;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
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

.form {
    height: 100%;
    width: 40%;
}

.input-container {
    width: auto;
    border-bottom: 2px solid #B5B0B0;
    /* 밑에 테두리만 적용 */
    margin: 30px;
}

.input-container input {
    border: none;
    /* 전체 테두리 제거 */
    outline: none;
    /* 포커스 시 테두리 제거 */
    width: 100%;
    /* 부모의 너비에 맞게 확장 */
    font-size: 20px;
    margin-top: 15px;
}

.input-container input::placeholder {
    color: black;
    /* 원하는 색깔 */
    font-weight: bold;
    /* 진하게 만들기 */
}

.form div {
    margin: 22px;
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

.image {
    width: 50%;
    text-align: center;
}

.image>img {
    height: 300px;
    width: 300px;
    border-radius: 70%;
}

.button {
    text-align: right;
}</style>