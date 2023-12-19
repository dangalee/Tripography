<script setup>
import { ref, onMounted } from 'vue';
import { listSido, listGugun, listContent } from '@/api/map';
import { listAttraction, getAttraction } from '@/api/attraction';
import VKakaoMap from '@/components/common/VKakaoMap.vue';
import VSelect from '@/components/common/VSelect.vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const sidoList = ref([]);
const gugunList = ref([{ text: "시/구/군", value: "" }]);
const contentList = ref([]);
const attractions = ref([]);
const selectAttraction = ref({});
const param = ref({
    sido_code: 0,
    gugun_code: 0,
    content_type_id: 0,
    title: ""
})

onMounted(() => {
    getSidoList();
    getContentList();
});

const getSidoList = () => {
    listSido(
        ({ data }) => {
            let options = [];
            options.push({ text: "시/도", value: "" });
            data.forEach((sido) => {
                options.push({ text: sido.sido_name, value: sido.sido_code });
            });
            sidoList.value = options;
        },
        (error) => {
            console.log(error);
        }
    )
}

const onChangeSido = (val) => {
    param.value.sido_code = val;
    listGugun(
        val,
        ({ data }) => {
            let options = [];
            options.push({ text: "시/구/군", value: "" });
            data.forEach((gugun) => {
                options.push({ text: gugun.gugun_name, value: gugun.gugun_code });
            });
            gugunList.value = options;
        },
        (error) => {
            console.log(error);
        }
    )
}

const getContentList = () => {
    listContent(
        ({ data }) => {
            let options = [];
            options.push({ text: "관광지 타입", value: "" });
            data.forEach((content) => {
                options.push({ text: content.content_type_name, value: content.content_type_id });
            });
            contentList.value = options;
        },
        (error) => {
            console.log(error);
        }
    )
}

const onChangeGugun = (val) => {
    param.value.gugun_code = val;
}

const onChangeContent = (val) => {
    param.value.content_type_id = val;
}

const getAttractions = () => {
    listAttraction(
        param.value,
        ({ data }) => {
            attractions.value = data;
        },
        (error) => {
            console.log(error)
        }
    )
}

const viewAttraction = (attraction) => {
    selectAttraction.value = attraction;
}

</script>

<template>
    <div class="map-div">
        <div class="search-collection">
            <VSelect class="selects" :selectOption="sidoList" @onKeySelect="onChangeSido" />
            <VSelect class="selects" :selectOption="gugunList" @onKeySelect="onChangeGugun" />
            <VSelect class="selects" :selectOption="contentList" @onKeySelect="onChangeContent" />
            <input class="custom-input" type="text" placeholder="관광지명" v-model="param.title">
            <button class="custom-button" @click="getAttractions">검색</button>
        </div>
        <div class="kakao-map">
            <VKakaoMap :attractions="attractions" :selectAttraction="selectAttraction" />
            <div class="attraction-list" v-if="attractions.length > 0">
                <div class="attraction-area" v-for="attraction in attractions" :key="attraction.content_id">
                    <div class="attraction">
                        <span @click="viewAttraction(attraction)">{{ attraction.title }}</span>
                    </div>
                    <div class="button-area">
                        <router-link :to="{ name: 'detail', params: { contentId: attraction.content_id } }" class="goDetail-button">></router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.map-div {
    width: 86%;
    margin: 0 auto;
    font-family: 'Gamja Flower', sans-serif;
    font-weight: bold;
}

.search-collection {
    text-align: right;
    margin: 10px 20px 10px 20px;
}

.selects {
    height: 30px;
    text-align: center;
    width: 150px;
    margin: 0px 5px 0px 5px;
    font-weight: bold;
}

.custom-input {
    height: 30px;
    text-align: center;
    width: 180px;
    margin: 0px 5px 0px 5px;
    font-weight: bold;
}

.custom-button {
    height: 30px;
    width: 70px;
    margin: 0px 5px 0px 5px;
    border: none;
    /* 테두리 제거 */
    background-color: #B5B0B0;
    /* 배경색 설정 */
    font-weight: bold;
}

.custom-button:hover {
    background-color: #4A4242;
    /* 호버 시 배경색 변경 */
    color: white;
}

.kakao-map {
    position: relative;
    margin: 10px 20px 10px 20px;
}

.attraction-list {
    width: 20%;
    background-color: white;
    position: absolute;
    height: 100%;
    top: 0;
    left: 0;
    z-index: 1;
    overflow: auto;
    border: 2px solid #B5B0B0;
}
.attraction-area{
    text-align: center;
    border-bottom: 2px solid #B5B0B0;
    padding: 7px;
    display: flex;
}
.attraction-area > .attraction{
    width:92%;
}
.button-area {
    width: 20px;
}
.goDetail-button {
    color: black;
    font-weight: bold;
    text-decoration: none;
}
</style>