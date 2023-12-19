<script setup>
import { ref } from 'vue';
import { registMember } from '@/api/member';
import { useRouter } from 'vue-router';

const router = useRouter();
const registInfo = ref({
    userId: "",
    userPw: "",
    userName: "",
    userEmail: "",
    userGrade: "user"
})

const regist = (registInfo) => {
    registMember(
        registInfo,
        ({data}) => {
            alert(data.message)
            router.push({name: "member-login"});
        },
        (error) => {
            console.log(error)
        }
    )
};
</script>

<template>
    <div class="back">
        <div class="form">
            <div class="title">
                Sign in
            </div>
            <form v-on:submit.prevent="submitForm">
                <div class="input-container">
                    <input type="text" placeholder="Name.." v-model="registInfo.userName">
                </div>
                <div class="input-container">
                    <input type="text" placeholder="ID.." v-model="registInfo.userId">
                </div>
                <div class="input-container">
                    <input type="password" placeholder="Password.." v-model="registInfo.userPw">
                </div>
                <div class="input-container">
                    <input type="password" placeholder="Password Check..">
                </div>
                <div class="input-container">
                    <input type="email" placeholder="Email.." v-model="registInfo.userEmail">
                </div>
                <div class="button">
                    <button class="custom-button" @click="regist(registInfo)">Sign in</button>
                    <button class="custom-button" type="reset">Reset</button>
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
    margin: 22px;
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