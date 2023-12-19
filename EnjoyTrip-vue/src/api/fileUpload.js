import {localAxios} from "@/util/http-commons";
const local = localAxios();
const url = "/file";

function uploadImage(formData, success, fail) {
    local.post(`${url}/uploadPhoto`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        }
    }).then(success).catch(fail);
}

async function findPictureInfo(contentId, success, fail){
    await local.post(`${url}/${contentId}`).then(success).catch(fail);
}

function getPhotoList(userId, success, fail){
    local.get(`${url}/${userId}`).then(success).catch(fail);
}

function deletePhoto(param,success,fail){
    local.delete(`${url}/delete`, {data: {fileId: param}}).then(success).catch(fail);
}
export {uploadImage, findPictureInfo, getPhotoList, deletePhoto};
