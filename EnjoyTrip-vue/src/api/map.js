import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = '/attraction';

function listSido(success, fail) {
  local.get(`${url}/sido`).then(success).catch(fail);
}

function listGugun(param, success, fail) {
  local.get(`${url}/gugun/${param}`).then(success).catch(fail);
}

function listContent(success, fail) {
  local.get(`${url}/content`).then(success).catch(fail);
}

export { listSido, listGugun, listContent };
