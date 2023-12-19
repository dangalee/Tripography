import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "/api"


function searchPosts(keyword, success, fail) {
    local.get(`${url}/${keyword}`).then(success).catch(fail);
  }

export{
    searchPosts
};
