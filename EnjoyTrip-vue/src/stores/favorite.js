import { defineStore } from "pinia";
import { ref } from "vue";
import { getIsLike, deleteLike, insertLike} from "@/api/favorite";

export const useFavoriteStore = defineStore("favoriteStore", () => {

    const isFavorite = ref(false);
    const favoriteInfo = ref({});

    const getLikeInfo = (contentId,userId) => {
        getIsLike(
            {
                userId: userId,
                contentId: contentId
            },
            ({ data }) => {
                console.log(data)
                isFavorite.value = data.isLike;
            },
            (error) => {
                console.log(error)
            }
        )
    }

    const checkHeart =  (contentId,userId) => {
        console.log(contentId, userId);
        insertLike(
            {
                userId: userId,
                contentId: contentId
            },
            ({ data }) => {
                console.log(data.message)
                isFavorite.value = true;
            },
            (error) => {
                console.log(error)
            }
        )
    }

    const uncheckHeart =  (contentId,userId) => {
        deleteLike(
            {
                userId: userId,
                contentId: contentId
            },
            ({ data }) => {
                console.log(data.message)
                isFavorite.value = false;
            },
            (error) => {
                console.log(error)
            }
        )
    }

    return {
        isFavorite,
        getLikeInfo,
        checkHeart,
        uncheckHeart
    }
})