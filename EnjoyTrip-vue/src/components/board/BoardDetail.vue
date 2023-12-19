
<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";
import { registComment, deleteComment, totalComments, listComments } from "@/api/comment";
import PageNavigation from "@/components/common/PageNavigation.vue";
const route = useRoute();
const router = useRouter();

// const articleno = ref(route.params.articleno);
const { articleno } = route.params;

const article = ref({});
const comments = ref([]);
const totalCom = ref(0);

const commentInfo = ref({
  articleNo: "",
  content: "",
  writer: "",
  time: ""
});

const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_COMMENT_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_COMMENT_LIST_SIZE,
  articleNo: ""

});

onMounted(() => {
  getArticle();
  getCommentList();
  getTotalCommentsCount();
});

const getArticle = () => {
  console.log(articleno + "번글 얻으러 가자!!!");
  commentInfo.value.articleNo = articleno;
  param.value.articleNo = articleno;
  // API 호출
  detailArticle(`${articleno}`,
    ({ data }) => {
      article.value = data;
    },
    (error) => {
      console.log(error)
    })

}
const getCommentList = () => {
  console.log(articleno + "번의 댓글들을 얻으러 가자!!!", commentInfo.value);
  //API 호출
  console.log(param);
  listComments(
    param.value,
    ({ data }) => {
      console.log(data);
      comments.value = data.comments;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  )
}

function onDeleteComment(commentNo) {
  console.log(commentNo + "번 댓글 삭제하러 가자!!!");
  deleteComment(commentNo, () => {
    window.location.reload();
  },
    (error) => {
      console.log(error);
    }
  )
}

function getTotalCommentsCount() {
  console.log("댓글 수 세기")
  totalComments(articleno, ({ data }) => {
    totalCom.value = data
  },
    (error) => {
      console.log(error);
    }
  )

}


function moveList() {
  router.push({ name: "article-list" });
}

function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

function onDeleteArticle() {
  // const { articleno } = route.params;
  console.log(articleno + "번글 삭제하러 가자!!!");
  // API 호출
  deleteArticle(`${articleno}`,
    () => {
      router.push({ name: "article-list" });
    },
    (error) => {
      console.log(error);
    }
  )
}
const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getCommentList();
};

const addComment = () => {
  console.log("댓글 등록하자!!!", commentInfo.value);
  if (commentInfo.value.content.trim() !== '') {
    //comments.value.push({content: commentInfo.value.content, writer: commentInfo.value.writer});
    // 주소 대신에 json 객체를 전달해야 한다.
    // API 호출
    commentInfo.value.time = new Date().toJSON().slice(0, 10).replace(/-/g, '/');
    registComment(commentInfo.value,
      (data) => {
        router.push({ name: "article-view" });
        console.log(data);
      },
      (error) => {
        console.log(error)
      })
  }
  window.location.reload();

}
</script>

<template>
  <div class="container">
    <div class="col-lg-10 text-start">
      <div class="row my-2">
        <h2 class="titleAndSubject">{{ article.articleNo }}. {{ article.subject }}</h2>
      </div>
      <div class="row">
        <div class="col-md-8">
          <div class="clearfix align-content-center">
            <img class="avatar me-2 float-md-start bg-light p-2"
              src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
            <p>
              <span class="fw-bold">{{ article.userName }}</span> <br />
              <span class="text-secondary fw-light">
                {{ article.registerTime }} | 조회 : {{ article.hit }}
              </span>
            </p>
          </div>
        </div>
        <div class="col-md-4 align-self-center text-end">댓글 : {{ totalCom }}</div>
        <div class="divider mb-3"></div>
        <div class="text-secondary" style="white-space: pre-line;">
          {{ article.content }}
        </div>
        <div class="divider mt-3 mb-3"></div>
        <div class="d-flex justify-content-end">
          <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
            글목록
          </button>
          <button type="button" class="btn btn-outline-success mb-3 ms-1" @click="moveModify">
            글수정
          </button>
          <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="onDeleteArticle">
            글삭제
          </button>
        </div>

        <h2>댓글</h2>
        <form @submit.prevent="addComment" style=" margin-bottom: 10px;">
          <input type="text" id="commentContent" v-model="commentInfo.content" placeholder="댓글을 입력하세요." required>
          <button type="submit">댓글 등록</button>
        </form>

        <div class="comment-container">
          <table v-for="comment in comments" class="comment-item">
            <tr>
              <td> {{ comment.content }}</td>
              <td>{{ comment.registerTime }}</td>
              <td>{{ comment.userName }}</td>
              <td>
                <!-- 삭제 버튼 -->
                <button @click="onDeleteComment(comment.commentNo)" class="delete-button">삭제</button>
              </td>
            </tr>
          </table>
        </div>


      </div>
      <PageNavigation :current-page="currentPage" :total-page="totalPage" @pageChange="onPageChange"></PageNavigation>
    </div>
  </div>
</template>



<style scoped>
.container {
  font-family: 'Gamja Flower', sans-serif;
}
.col-lg-10{
  margin: 0 auto;
}
.row{
  padding-left:30px;
}
input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  width: 80%;
}

#app {
  width: 400px;
  margin: 20px auto;
  font-family: Arial, sans-serif;
}

table {
  table-layout: fixed;
}

td {
  word-wrap: break-word;
}

td:nth-child(1) {
  width: 60%;
}

td:nth-child(2) {
  width: 20%;
}

.comment-container {
  width: 100%;
  margin-top: 20px;
}

.comment-item {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
}

.comment-item td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.comment-item td:last-child {
  text-align: center;
}

.delete-button {
  background-color: #dc3545;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}

button {
  background-color: #4caf50;
  color: #fff;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-left: 20px;
}
</style>
