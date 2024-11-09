import makeFetch from "../makeFetch";

const BASE_SPACE_MARINE_SERVICE_URL = "https://localhost:22001/space-marines-service"

export const fetchGetMarines = async (setMarines, setOffset, sortBy, order, limit, offset, filters, alertWithMessage) => {
    const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines");
    let params = {
        limit: limit,
        offset: offset,
        sortDirection: order,
        minId: filters.minId,
        maxId: filters.maxId,
        name: filters.name,
        minX: filters.minX,
        maxX: filters.maxX,
        minY: filters.minY,
        maxY: filters.maxY,
        minHealth: filters.minHealth,
        maxHealth: filters.maxHealth,
        loyal: filters.loyal,
        minHeight: filters.minHeight,
        maxHeight: filters.maxHeight,
        category: filters.category,
        minCreationDate: filters.minCreationDate,
        maxCreationDate: filters.maxCreationDate,
        chapterName: filters.chapterName,
        chapterWorld: filters.chapterWorld,
    }

    try {
        let searchParams = new URLSearchParams(params)
        sortBy.forEach(value => searchParams.append('sortBy', value))
        let keysForDel = [];
        searchParams.forEach((value, key) => {
            if (value === "null" || value === "") {
                keysForDel.push(key);
            }
        });
        keysForDel.forEach(key => {
            searchParams.delete(key);
        });
        url.search = searchParams.toString()
        console.log(searchParams.toString())
        await makeFetch(
            url,
            () => {},
            json => {setMarines(json["data"])},
            alertWithMessage,
        )
    } catch (e) {
        console.log("error", e);
    }
}

export const fetchMarineById = async (value, setMarines, alertWithMessage) => {
    if (value !== "") {
        const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines/" + value);
        await makeFetch(url, {}, flat => setMarines([flat]), alertWithMessage)
    }
}

export const fetchDeleteById = async (id, alertWithMessage) => {
    if (id !== "") {
        const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines/" + id);
        await makeFetch(url, {method: 'DELETE'}, () => {
        }, alertWithMessage)
    }
}

export const fetchAdd = async (data, alertWithMessage) => {
    if (data !== null) {
        const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines");
        await makeFetch(
            url,
            {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data)
            },
            _ => {
            },
            alertWithMessage
        )
    }
}

export const fetchUpdateById = async (id, data, alertWithMessage) => {
    if (id !== null && data !== null) {
        const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines/" + id);
        await makeFetch(
            url,
            {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data)
            },
            _ => {
            },
            alertWithMessage
        )
    }
}

export const fetchDeleteOneByCategory = async (category, alertWithMessage) => {
    if (category !== null) {
        const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines/categories/" + category);
        await makeFetch(url, {method: 'DELETE'}, _ => {}, alertWithMessage)
    }
}

export const fetchGetLoyalist = async (alertWithMessage) => {
    const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines/loyalists");
    await makeFetch(url, {method: "GET"}, resp => alertWithMessage("Loyalist id: " + resp["id"]), alertWithMessage)
}

export const fetchGetCountOfHealthyMarines = async (minHealth, alertWithMessage) => {
    const url = new URL(BASE_SPACE_MARINE_SERVICE_URL + "/space-marines/amount");
    let params = {minHealth: minHealth}
    url.search = new URLSearchParams(params).toString()
    await makeFetch(url, {method: "GET"}, resp => alertWithMessage("Amount: " + resp["amount"]), alertWithMessage)
}
