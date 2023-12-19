<script setup>
import { ref, watch, onMounted } from "vue";
import { useRouter } from "vue-router";

var map;
const positions = ref([]);
const markers = ref([]);
const customOverlays = ref([]);
const props = defineProps({ attractions: Array, selectAttraction: Object });

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
      }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

watch(
  () => props.attractions.value,
  () => {
    positions.value = [];
    props.attractions.forEach((attraction) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
      obj.movemap = new kakao.maps.LatLng(attraction.latitude+0.001, attraction.longitude-0.002);
      obj.title = attraction.title;
      obj.content = `<div class="custom-overlay">
                        <div class="top">
                            <div class="title">
                              <span>${attraction.title}</span>
                            </div>
                            <div class="favorite">
                              <img src="/src/assets/icon-hearts.png"/>
                              ${attraction.favor_cnt}
                            </div>
                        </div>
                        <div class="address">
                            <span>${attraction.addr1} ${attraction.addr2}</span>
                        </div>
                        <div class="image">   
                            <img src="${attraction.first_image}"/>
                        </div>
                     </div>`;
      positions.value.push(obj);
    });

    loadMarkers();
  },
  { deep: true }
);



watch(
  () => props.selectAttraction.value,
  () => {

    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(props.selectAttraction.latitude, props.selectAttraction.longitude);
    var moveMap = new kakao.maps.LatLng(props.selectAttraction.latitude+0.001, props.selectAttraction.longitude-0.002);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    
    map.setLevel(3);
    map.panTo(moveMap);
  
    var content = `<div class="custom-overlay">
                        <div class="top">
                            <div class="title">
                              <span>${props.selectAttraction.title}</span>
                            </div>
                            <div class="favorite">
                              <img src="/src/assets/icon-hearts.png"/>
                              ${props.selectAttraction.favor_cnt}
                            </div>
                        </div>
                        <div class="address">
                          <span>${props.selectAttraction.addr1} ${props.selectAttraction.addr2}</span>
                        </div>
                        <div class="image">   
                            <img src="${props.selectAttraction.first_image}"/>
                        </div>
                     </div>` // 커스텀오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

    // 켜져있는 커스텀오버레이를 삭제합니다.
    deleteCustomOverlays();

    // 커스텀오버레이를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
      map: map,
      position: moveLatLon,
      content: content,
      yAnchor: 1.2
    });

    customOverlays.value.push(customOverlay);
  },
  { deep: true }
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  // loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();
  deleteCustomOverlays();
  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  customOverlays.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true,
      // image: markerImage, // 마커의 이미지
    });
    var customOverlay = new kakao.maps.CustomOverlay({
      position: position.latlng,
      content: position.content,
      yAnchor: 1.2
    });

    kakao.maps.event.addListener(marker, 'click', function () {
      deleteCustomOverlays();
      map.setLevel(3);
      map.panTo(position.movemap);
      customOverlay.setMap(map);
    });

    markers.value.push(marker);
    customOverlays.value.push(customOverlay);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};

const deleteCustomOverlays = () => {
  if (customOverlays.value.length > 0) {
    customOverlays.value.forEach((customOverlay) => customOverlay.setMap(null));
  }
}
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 600px;
}
.custom-overlay {
  margin: 0 auto;
  border-radius: 6px;
  width: 320px;
  background-color: white;
  border: 2px solid #B5B0B0;
}

.top {
  padding-top: 5px;
  display: flex;
}
.title{
  width: 80%;
  padding-left: 20px;
  text-align: left;
  white-space: normal;
}
.title > a{
  text-decoration: none;
  color: black;
}
.favorite{
  width: 20%;
  text-align: center;
}
.address{
  padding-top: 5px;
  padding-left: 20px;
  padding-right: 20px;
  text-align: left;
  white-space: normal;
  font-weight: normal;
}
.image{
  padding-top: 5px;
  padding-bottom: 5px;
  text-align: center;
}
.image>img {
  border-radius: 6px;
  width: 300px;
  height: 200px;
}
.favorite > img{
  width: 15px;
}
</style>
