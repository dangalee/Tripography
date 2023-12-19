import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = '/favorite';

function getIsLike(param, success, fail) {
    local.get(`${url}/${param.userId}/${param.contentId}`).then(success).catch(fail);
}

function deleteLike(param, success, fail) {
    local.delete(`${url}/uncheck`, {data: {userId: param.userId, contentId: param.contentId}}).then(success).catch(fail);
}

function insertLike(param, success, fail) {
    local.post(`${url}/check`, param).then(success).catch(fail);
}

function getFavoriteList(param, success, fail){
    local.get(`${url}/${param}`).then(success).catch(fail);
}

export { getIsLike, deleteLike, insertLike, getFavoriteList };
