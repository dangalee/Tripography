import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "로그인", show: true, routeName: "member-login" },
    { name: "프로필이미지", show: false, routeName: "member-image"}
  ]);

  const userMenu = ref([
    { name: "Profile", routeName: "mypage" },
    { name: "Logout", routeName: "member-logout" },
  ]);

  const changeMenuState = () => {
    menuList.value = menuList.value.map((item) => ({ ...item, show: !item.show }));
  };
  return {
    menuList,
    userMenu,
    changeMenuState,
  };
});
