<script setup>
import { useRouter } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { useMenuStore } from '@/stores/menu';
import { storeToRefs } from 'pinia';
import IconNologin from '@/icons/IconNologin.vue';
import IconArticleList from '@/icons/IconArticlelist.vue';
import IconMap from '@/icons/IconMap.vue';

const router = useRouter();
const menuStore = useMenuStore();
const memberStore = useMemberStore();
const { menuList, userMenu } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;
const { userLogout } = memberStore;
const { userInfo } = storeToRefs(memberStore);

const logout = (userInfo) => {
  console.log(userInfo.userId);
  userLogout(userInfo.userId);
  changeMenuState();
  router.push("/");
} 
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-white border-bottom sticky-top">
    <div class="container-fluid">
      <router-link :to="{ name: 'main' }" class="navbar-brand">
        Tripography
      </router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
        aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!--   
              <div class="collapse navbar-collapse" id="navbarScroll"> -->
      <!-- <ul class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px">
                  <li class="nav-item">
                    <router-link :to="{ name: 'board' }" class="nav-link">지도</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link :to="{ name: 'board' }" class="nav-link">게시판</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link :to="{ name: 'mypage' }" class="nav-link">마이페이지</router-link>
            </li>
          </ul> -->

    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px">
        <li class="nav-item">
          <router-link :to="{ name: 'map' }" class="nav-link">
            <IconMap />
          </router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'board' }" class="nav-link">
              <IconArticleList />
            </router-link>
          </li>
          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'member-login'">
                <li class="nav-item">
                  <router-link :to="{ name: menu.routeName }" class="nav-link">
                    <icon-nologin />
                  </router-link>
                </li>
              </template>
              <!-- <template v-if="menu.routeName === 'member-logout'">
                      <li class="nav-item">
                        <router-link to="/" @click.prevent="logout(userInfo)" class="nav-link">{{ menu.name }}</router-link>
                      </li>
                    </template>
                    <template v-if="menu.routeName === 'member-mypage'">
                      <li class="nav-item">
                        <router-link :to="{ name: menu.routeName }" class="nav-link">{{ menu.name }}</router-link>
                      </li>
                    </template> -->
              <template v-else>
                <li class="nav-item dropdown">
                  <a class="nav-link" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <img class="profile-image" src="/src/assets/logo.png">
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end">
                    <template v-for="uMenu in userMenu" :key="uMenu.routeName">
                      <template v-if="uMenu.routeName === 'mypage'">
                        <li>
                          <router-link :to="{ name: uMenu.routeName }" class="dropdown-item">{{ uMenu.name }}</router-link>
                        </li>
                        <li>
                          <li><hr class="dropdown-divider"></li>
                        </li>
                      </template>
                      <template v-if="uMenu.routeName === 'member-logout'">
                        <li>
                          <router-link to="/" @click.prevent="logout(userInfo)" class="dropdown-item">{{ uMenu.name }}</router-link>
                        </li>
                      </template>
                    </template>
                  </ul>
                </li>
              </template>
            </template>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar-brand {
  font-family: 'Kurale', serif;
  font-size: 30px;
  font-weight: bold;
  margin-left: 30px;
}
.profile-image{
  width: 44.8px;
  height: 44.8px;
  border-radius: 70%;
  border: 0.5px #B5B0B0 solid; /* 밑에 테두리만 적용 */
}
.nav-item {
  margin-left: 12px;
  margin-right: 12px;
}</style>
