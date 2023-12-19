<script setup>
defineProps({ post: Object });
</script>

<template>


  <div class = "box">
    <div class = "title-box">
  <tr class="text-center">
    <td class="text-start">
      <a :href="post.url" v-html="post.title"></a>
    </td>
  </tr>
  </div>
<div class = "content-box">
  <tr class="text-center">
    <td class="text-start" style = "color: #999999" v-html="post.description"></td>
  </tr>
</div>
</div>



</template>

<style scoped>
a {
  margin: 5px;
  color:black;
  text-align:center;
}

.box {
  border:1px solid #ccc;
  margin: 2px;
}

</style>