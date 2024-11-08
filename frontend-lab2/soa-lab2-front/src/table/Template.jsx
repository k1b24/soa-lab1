import './css/Template.css'
import {useEffect, useState} from "react";
import TableContent from "./Content";
import Pagination from "./Pagination";
import FindById from "./FindById";
import DeleteById from "./DeleteById";
import {fetchGetMarines} from "../utils/space-marines/api"
import AddMarine from "./AddMarine";
import UpdateMarine from "./UpdateMarine";
import DeleteOneByCategory from "./DeleteOneByCategory";
import GetLoyalist from "./GetLoyalist";
import GetCountOfHealthyMarines from "./GetCountOfHealthyMarines";
import AddStarship from "./AddStarship";
import LoadOnStarship from "./LoadOnStarship";
import Filters from "./Filters";
import spacemarineImg from "./spacemarine.png";
import Order from "./Order.jsx"

const Template = () => {

    const [limit, setLimit] = useState(10)
    const [offset, setOffset] = useState(0)
    const [order, setOrder] = useState("ASC")
    const [sortBy, setSortBy] = useState("ID")
    const [filters, setFilters] = useState({
        minId: null,
        maxId: null,
        name: null,
        minX: null,
        maxX: null,
        minY: null,
        maxY: null,
        minHealth: null,
        maxHealth: null,
        loyal: null,
        minHeight: null,
        maxHeight: null,
        category: null,
        minCreationDate: null,
        maxCreationDate: null,
        chapterName: null,
        chapterWorld: null,
    })
    const [marines, setMarines] = useState([])

    const updateContent = () => {
        console.log("Filters: " + filters)
        fetchGetMarines(setMarines, setOffset, sortBy, order, limit, offset, filters, alertWithMessage);
        console.log("Marines" + marines)
    }

    const [hiddenAlert, setHiddenAlert] = useState(true)
    const [message, setAlertMessage] = useState("")

    const changeOrder = () => {
        if (order === "ASC") {
            setOrder("DESC")
        } else {
            setOrder("ASC")
        }
        updateContent()
    }

    const alertWithMessage = (text) => {
        setAlertMessage(text)
        setHiddenAlert(false)
    }

    useEffect(() => {
        updateContent()
    }, [limit, offset, sortBy, filters]);

    const sortBys = ["id", "name", "creationDate", "health", "height", "category"]

    const onSortClick = (field) => {
        if (sortBys.includes(field)) {
            setSortBy(field.replace(/([a-z])([A-Z])/, '$1_$2').toUpperCase())
            updateContent()
            console.log("Sort by", sortBy)
        }
    }

    return (
        <div>
            <div className="buttons-container">t
                <FindById marines={marines} setMarines={setMarines} updateContent={updateContent} alertWithMessage={alertWithMessage}/>
                <Filters setFilters={setFilters} updateContent={updateContent} />
                <DeleteById updateContent={updateContent} alertWithMessage={alertWithMessage}/>
                <AddMarine updateContent={updateContent} alertWithMessage={alertWithMessage}/>
                <UpdateMarine updateContent={updateContent} alertWithMessage={alertWithMessage}/>
                <DeleteOneByCategory updateContent={updateContent} alertWithMessage={alertWithMessage}/>
                <GetLoyalist alertWithMessage={alertWithMessage}/>
                <GetCountOfHealthyMarines alertWithMessage={alertWithMessage}/>
                <AddStarship alertWithMessage={alertWithMessage}/>
                <LoadOnStarship alertWithMessage={alertWithMessage}/>
                <Order setUpperOrder={changeOrder} updateContent={updateContent} />
                <Pagination limit={limit} offset={offset} setOffset={setOffset}/>
            </div>
            <div className="table-container">
                <table className="table">
                    <thead>
                    <tr>
                        {
                            [
                                "id",
                                "name",
                                "coordinates.x",
                                "coordinates.y",
                                "creationDate",
                                "health",
                                "loyal",
                                "height",
                                "category",
                                "chapter.name",
                                "chapter.world",
                                "starship.name"
                            ].map(it => (
                                <th onClick={() => onSortClick(it)}>{it}</th>
                            ))
                        }
                    </tr>
                    </thead>
                    <TableContent content={marines}/>
                </table>
            </div>
            <div className="underTableContainer">
                <div className="alertContainer" hidden={hiddenAlert}>
                    <div className="alert">
                        <div>
                            <div>
                                {message}
                            </div>
                        </div>
                        <button className="okButton" onClick={ _ => setHiddenAlert(true)}>OK</button>
                    </div>
                </div>
                <img className="spacemarineImg" src={spacemarineImg}/>
            </div>
        </div>
    )
}

export default Template;