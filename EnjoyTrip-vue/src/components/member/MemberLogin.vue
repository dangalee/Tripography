<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginInfo = ref({
    userId: "",
    userPw: "",
});

const login = async () => {
    console.log(loginInfo.value)
    await userLogin(loginInfo.value);
    let token = sessionStorage.getItem("accessToken");
    if (isLogin) {
        getUserInfo(token);
        changeMenuState();
    }
    router.push("/");
}

const goRegist = () => {
    router.push('/member/regist');
}
</script>

<template>
    <div class="back">
        <div class="form">
            <div class="title">
                Login
            </div>
            <form v-on:submit.prevent="submitForm">
                <div class="input-container">
                    <input type="text" placeholder="ID.." v-model="loginInfo.userId">
                </div>
                <div class="input-container">
                    <input type="password" placeholder="Password.." v-model="loginInfo.userPw">
                </div>
                <div>
                    <button class="custom-button" @click="login">Login</button>
                    <button class="custom-button" @click="goRegist">Sign in</button>
                </div>
            </form>
        </div>
        <div class="image">
            <img src="@/assets/image 17.png">
        </div>
    </div>
</template>

<style scoped>
.back{
    width: 80%;
    margin: 0 auto;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
    font-family: 'Kurale', serif;
}
.title{
    text-align: left;
    color: black;
    font-size: 40px;
    font-weight: bold;
}
.form{
    height: 100%;
    width: 450px;
}
.input-container {
    border-bottom: 2px solid #B5B0B0; /* 밑에 테두리만 적용 */
    margin: 30px;
}
.input-container > input{
    margin-top: 15px;
}
.input-container input {
    border: none; /* 전체 테두리 제거 */
    outline: none; /* 포커스 시 테두리 제거 */
    width: 100%; /* 부모의 너비에 맞게 확장 */
    font-size: 20px;
}
.input-container input::placeholder {
    color: black; /* 원하는 색깔 */
    font-weight: bold; /* 진하게 만들기 */
}
.form div{
    margin: 30px;
}
.custom-button {
    font-size: 17px; /* 원하는 폰트 크기 */
    font-weight: bold; /* 굵게 만들기 */
    border: none; /* 테두리 제거 */
    background-color: #B5B0B0; /* 배경색 설정 */
    color: black; /* 글자 색 설정 */
    padding: 10px 20px; /* 패딩 설정 */
    cursor: pointer;
    width: 100px;
    margin: 10px 35px 10px 35px;
}
.custom-button:hover {
    background-color: #4A4242; /* 호버 시 배경색 변경 */
    color:white;
}
.image{
    height: 100%;
    width: 500px;
}
.image > img{
    height: 500px;
    width: 500px;
    border-radius: 70%;
    filter: opacity(65%);  
}
</style>