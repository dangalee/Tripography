<script setup>
import { listArticleTopTen } from "@/api/board";
import { searchPosts } from "@/api/naver.js";
import { getBestAttractions } from "@/api/attraction";
import { ref, onMounted } from "vue";
import FeedListItem from "@/components/board/item/FeedListItem.vue";
import MainPageNavigation from "@/components/common/MainPageNavigation.vue";
import HotBoardListItem from "@/components/board/item/HotBoardListItem.vue";
import NaverPostItem from "@/components/board/item/NaverPostItem.vue";

const feeds = ref([])

const keyword = ref("");
const posts = ref([]);
const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_TOP_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_TOP_ARTICLE_LIST_SIZE,
})

onMounted(() => {
  getBestAttractions(
    ({ data }) => {
      feeds.value = data;
      console.log(feeds.value)
    },
    (error) => {
      console.log(error)
    }
  )
  getHotArticleList();
});

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getHotArticleList();
};


const getHotArticleList = () => {
  console.log("서버에서 댓글 상위 10개 글목록 얻어오자!!!", param.value);
  // API 호출
  listArticleTopTen(
    param.value,
    ({ data }) => {
      console.log(data);
      articles.value = data.articles;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};

const performSearch = () => {
  console.log("네이버 포스트 검색하기");
  //API 호출
  searchPosts(
    keyword.value,
    ({ data }) => {
      posts.value = data;

    }, (error) => {
      console.log(error);
    }
  );
};
</script>


<template>
  <div class="body">
    <div class="section-left"></div>
    <div class="section-mid-left">
      <div class="BestAttraction">
        Best Attraction..
      </div>
      <FeedListItem v-for="feed in feeds" :feed="feed"></FeedListItem>   
    </div>
    <div class="section-mid-right">
      <div class="sub-section">
        <p>Hot Article</p>
        <table class="full-width-table">
          <tbody>
            <HotBoardListItem v-for="article in articles" :key="article.articleNo" :article="article"></HotBoardListItem>
          </tbody>
        </table>
        <MainPageNavigation :current-page="currentPage" :total-page="totalPage" @pageChange="onPageChange"
          class="navigation-margin"></MainPageNavigation>
      </div>

      <div class="sub-section sub-section-below">
        <p>Search Naver Posts</p>
        <div class="search-container">
          <i class="fas fa-search search-icon" @click="performSearch"></i>
          <input type="text" class="search-input" v-model="keyword" placeholder="검색어를 입력하세요 (ex. 군산 여행)">
        </div>
        <NaverPostItem v-for="post in posts" :post="post"></NaverPostItem>
      </div>
    </div>
    <div class="section-right"></div>
  </div>
</template>

<style scoped>
.BestAttraction{
  font-family: 'Kurale', serif;
  font-size: 30px;
  font-weight: bold;
  padding: 10px 20px 10px 20px;
}
div {
  width: 100%
}

.body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  display: flex;
  height: 600px;
}

p {
  text-align: center;
  margin-bottom: 0;
  padding: 0;
}

.search-container {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-input {
  border: none;
  outline: none;
  flex: 1;

}

.search-icon {
  margin-right: 8px;
  color: #555;
  margin-left: 5px;
}

.search {
  color: #808080;
}

.section-left {
  width: 7%;
  border-right: 1px solid #ECECEC;
}

.section-mid-left {
  width: 65%;
  border-right: 1px solid #ECECEC;
  overflow-y: scroll;
}

.section-mid-right {
  width: 21%;
}

.section-right {
  width: 7%;
  border-left: 1px solid #ECECEC;
}


.sub-section {
  flex: 1;
  height: 50%;
  border-bottom: 1px solid #ECECEC;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.sub-section-below {
  overflow-y: auto;
}

.full-width-table {
  width: 100%;
  border-collapse: collapse;
}

.navigation-margin {
  margin-top: 5px;
}

.scrollable-container {
  width: 300px;
  height: 200px;
  border: 1px solid #ccc;
  overflow: hidden;
}

.scrollable-content {
  width: 100%;
  height: 100%;
  overflow-y: auto;
  padding: 10px;
}
</style>
