import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "/comment"

function registComment(comment, success, fail) {
    console.log("boardjs comment", comment);
    local.post(`${url}`, JSON.stringify(comment)).then(success).catch(fail);
}

function modifyComment(param, success, fail) {
    local.get(`${url}`, { params: param }).then(success).catch(fail);
}

function deleteComment(commentNo, success, fail) {
    local.delete(`${url}/${commentNo}`).then(success).catch(fail);
}

function listComments(param, success, fail) {
    local.get(`${url}`, {params: param}).then(success).catch(fail);
}

function totalComments(articleNo, success, fail) {
    local.get(`${url}/${articleNo}`).then(success).catch(fail);
}


export {
    listComments,
    registComment,
    modifyComment,
    deleteComment,
    totalComments
};
  