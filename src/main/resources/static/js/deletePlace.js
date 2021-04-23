function deletePlace(placeId) {
    /*EXTERNAL : https://github.com/axios/axios*/
    axios.delete(window.location.origin + "/places/" + placeId)
        .then(response => {
            window.location.reload();
        })
        .catch(error => console.error(error));
    return true;
}
