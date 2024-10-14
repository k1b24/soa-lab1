const alertBadRequest = (response, alertWithMessage) => {
    response.json()
        .then(resp => {
                let msg = response.status + ": " + resp["description"]
                alertWithMessage(msg)
                console.log("error", msg);
            }
        )
}

const makeFetch = async (url, requestInit, ifSuccess, alertWithMessage) => {
    await fetch(url, requestInit)
        .then(response => {
                if (200 <= response.status && response.status < 300) {
                    if (response.body !== null) {
                        response
                            .json()
                            .then(ifSuccess)
                            .catch(resp => console.log(resp))
                    }
                } else {
                    alertBadRequest(response, alertWithMessage)
                }
            }
        )
        .catch(error =>
            console.log("error", error)
        )
}

export default makeFetch