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

const Template = ({alertWithMessage}) => {

    const [limit, setLimit] = useState(10)
    const [offset, setOffset] = useState(0)
    const [sortBy, setSortBy] = useState({fields: ["id"], order: "asc"})
    const [filters, setFilters] = useState("")
    const [flats, setFlats] = useState([])

    const updateContent = () => {
        fetchGetMarines(setFlats, setOffset, sortBy, limit, filters, alertWithMessage);
    }

    useEffect(() => {
        updateContent()
    }, [limit, sortBy]);

    const onSortClick = (field) => {
        if (sortBy.fields.includes(field)) {
            setSortBy({fields: sortBy.fields.filter(function (f) { return f === field}), order: sortBy.order})
        } else {
            setSortBy({fields: [field], order: "asc"})
        }
    }

    return (
        <div>
            <div className="overflow-x-auto">
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
                    <TableContent content={flats}/>
                </table>
            </div>
            <Pagination currentPage={limit} setCurrentPage={setLimit} pagesNumber={offset}/>

            <FindById flats={flats} setFlats={setFlats} updateContent={updateContent} alertWithMessage={alertWithMessage}/>

            <details className="dropdown">
                <summary className="m-1 btn">Filters</summary>
                <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
                    <li>
                        <div>
                            <div>
                                <textarea placeholder={"filters"} value={filters}
                                          onChange={e => setFilters(e.target.value)}/>
                            </div>
                            <button className={"btn btn-outline btn-success"} onClick={updateContent}>Use filters
                            </button>
                        </div>
                    </li>
                </ul>
            </details>
            <DeleteById updateContent={updateContent} alertWithMessage={alertWithMessage}/>
            <AddMarine updateContent={updateContent} alertWithMessage={alertWithMessage}/>
            <UpdateMarine updateContent={updateContent} alertWithMessage={alertWithMessage}/>
            <DeleteOneByCategory updateContent={updateContent} alertWithMessage={alertWithMessage}/>
            <GetLoyalist alertWithMessage={alertWithMessage}/>
            <GetCountOfHealthyMarines alertWithMessage={alertWithMessage}/>
            <AddStarship alertWithMessage={alertWithMessage}/>
            <LoadOnStarship alertWithMessage={alertWithMessage}/>
        </div>
    )
}

export default Template;