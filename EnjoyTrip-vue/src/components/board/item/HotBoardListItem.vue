<script setup>
defineProps({ article: Object });
</script>

<template>


  <tr class="text-center">
    <td class="text-start">
      <router-link
        :to="{ name: 'article-view', params: { articleno: article.articleNo } }"
        class="article-title link-dark"
      >
        {{ article.subject }}
      </router-link>
    </td>
    <td>{{ article.userName }}</td>
    <td>{{ article.comments }}</td>
  </tr>

</template>

<style scoped>
a {
  text-decoration: none;
}

td {
  border: 1px solid #ECECEC;
  padding: 8px;
  text-align: left;
}



</style>
