import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = '/attraction';

function listAttraction(param, success, fail) {
  local.post(`${url}/search`, param).then(success).catch(fail);
}

async function getAttraction(param, success, fail){
  await local.get(`${url}/detail/${param}`).then(success).catch(fail);
}

function getBestAttractions(success, fail){
  local.get(`${url}/best`).then(success).catch(fail);
}
export { listAttraction, getAttraction, getBestAttractions };
