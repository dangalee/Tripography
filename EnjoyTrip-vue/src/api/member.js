import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "/member"

async function loginMember(loginInfo, success, fail) {
    await local.post(`${url}/login`, loginInfo).then(success).catch(fail);
}

async function findById(userid, success, fail) {
    local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
    await local.get(`${url}/search/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
    local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
    await local.post(`${url}/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
    await local.get(`${url}/logout/${userid}`).then(success).catch(fail);
}

function registMember(registInfo, success, fail){
    local.post(`${url}/regist`, registInfo).then(success).catch(fail);
}

function updateMember(updateInfo, success, fail){
    local.post(`${url}/update`, updateInfo).then(success).catch(fail);
}

export { loginMember, findById, tokenRegeneration, logout, registMember, updateMember };