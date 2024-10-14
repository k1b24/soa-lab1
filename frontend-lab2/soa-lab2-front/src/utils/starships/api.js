import makeFetch from "../makeFetch";

const BASE_STARSHIP_SERVICE_URL = "https://localhost:8081/starships-service/starship"

export const fetchAdd = async (data, alertWithMessage) => {
    if (data !== null) {
        const url = new URL(BASE_STARSHIP_SERVICE_URL + "/create/" + data.id + "/" + data.name);
        await makeFetch(url, { method: 'POST'}, _ => {}, alertWithMessage)
    }
}

export const fetchLoadOnStarship = async (starshipId, spaceMarineId, alertWithMessage) => {
    if (starshipId !== "" && spaceMarineId !== "") {
        const url = new URL(BASE_STARSHIP_SERVICE_URL + "/" + starshipId + "/load/" + spaceMarineId);
        await makeFetch(url, {method: "POST"}, _ => {}, alertWithMessage)
    }
}