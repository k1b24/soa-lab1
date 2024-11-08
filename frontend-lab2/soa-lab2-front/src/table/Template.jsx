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

const Template = () => {

    const [limit, setLimit] = useState(10)
    const [offset, setOffset] = useState(0)
    const [sortBy, setSortBy] = useState({fields: ["id"], order: "ASC"})
    const [filters, setFilters] = useState({
        minId: null,
        maxId: null,
        minX: null,
        maxX: null,
        minY: null,
        maxY: null,
        minHealth: null,
        maxHealth: null,
        minHeight: null,
        maxHeight: null,
        minCreationDate: null,
        maxCreationDate: null
    })
    const [marines, setMarines] = useState([])

    const updateContent = () => {
        console.log("Filters: " + filters)
        fetchGetMarines(setMarines, setOffset, sortBy, limit, offset, filters, alertWithMessage);
        console.log("Marines" + marines)
    }

    const [hiddenAlert, setHiddenAlert] = useState(true)
    const [message, setAlertMessage] = useState("")

    const alertWithMessage = (text) => {
        setAlertMessage(text)
        setHiddenAlert(false)
    }

    useEffect(() => {
        updateContent()
    }, [limit, offset, sortBy]);

    const onSortClick = (field) => {
        if (sortBy.fields.includes(field)) {
            setSortBy({fields: sortBy.fields.filter(function (f) { return f === field}), order: sortBy.order})
        } else {
            setSortBy({fields: [field], order: "ASC"})
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