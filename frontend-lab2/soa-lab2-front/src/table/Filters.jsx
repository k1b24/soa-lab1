import {useState} from "react";
import {fetchMarineById} from "../utils/space-marines/api";

const Filters = ({setFilters, updateContent}) => {

    const [minId, setMinId] = useState(null)
    const [maxId, setMaxId] = useState(null)
    const [minX, setMinX] = useState(null)
    const [maxX, setMaxX] = useState(null)
    const [minY, setMinY] = useState(null)
    const [maxY, setMaxY] = useState(null)
    const [minHealth, setMinHealth] = useState(null)
    const [maxHealth, setMaxHealth] = useState(null)
    const [minHeight, setMinHeight] = useState(null)
    const [maxHeight, setMaxHeight] = useState(null)
    const [minCreationDate, setMinCreationDate] = useState(null)
    const [maxCreationDate, setMaxCreationDate] = useState(null)

    const onFilterClick = () => {
        setFilters({
            minId: minId,
            maxId: maxId,
            minX: minX,
            maxX: maxX,
            minY: minY,
            maxY: maxY,
            minHealth: minHealth,
            maxHealth: maxHealth,
            minHeight: minHeight,
            maxHeight: maxHeight,
            minCreationDate: minCreationDate,
            maxCreationDate: maxCreationDate
        })
        updateContent()
    }

    const onResetClick = () => {
        setMinId(null)
        setMaxId(null)
        setMinX(null)
        setMaxX(null)
        setMinY(null)
        setMaxY(null)
        setMinHealth(null)
        setMaxHealth(null)
        setMinHeight(null)
        setMaxHeight(null)
        setMinCreationDate(null)
        setMaxCreationDate(null)
        setFilters({
            minId: minId,
            maxId: maxId,
            minX: minX,
            maxX: maxX,
            minY: minY,
            maxY: maxY,
            minHealth: minHealth,
            maxHealth: maxHealth,
            minHeight: minHeight,
            maxHeight: maxHeight,
            minCreationDate: minCreationDate,
            maxCreationDate: maxCreationDate
        })
        updateContent()
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Filters</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <div>
                        <label htmlFor="minIdArea">minId</label>
                        <input type="number" id={"minIdArea"} value={minId} onChange={e => setMinId(e.target.value)} placeholder={"minId"}/>
                        <label htmlFor="maxIdArea">maxId</label>
                        <input type="number" id={"maxIdArea"} value={maxId} onChange={e => setMaxId(e.target.value)} placeholder={"maxId"}/>
                        <label htmlFor="minXArea">minX</label>
                        <input type="number" id={"minXArea"} value={minX} onChange={e => setMinX(e.target.value)} placeholder={"minX"}/>
                        <label htmlFor="maxXArea">maxX</label>
                        <input type="number" id={"maxXArea"} value={maxX} onChange={e => setMaxX(e.target.value)} placeholder={"maxX"}/>
                        <label htmlFor="minIdArea">minY</label>
                        <input type="number" id={"minYArea"} value={minY} onChange={e => setMinY(e.target.value)} placeholder={"minY"}/>
                        <label htmlFor="maxYArea">maxY</label>
                        <input type="number" id={"maxYArea"} value={maxY} onChange={e => setMaxY(e.target.value)} placeholder={"maxY"}/>
                        <label htmlFor="minHealthArea">minHealth</label>
                        <input type="number" id={"minHealthArea"} value={minHealth} onChange={e => setMinHealth(e.target.value)} placeholder={"minHealth"}/>
                        <label htmlFor="maxHealthArea">maxHealth</label>
                        <input type="number" id={"maxHealthArea"} value={maxHealth} onChange={e => setMaxHealth(e.target.value)} placeholder={"maxHealth"}/>
                        <label htmlFor="minHeightArea">minHeight</label>
                        <input type="number" id={"minHeightArea"} value={minHeight} onChange={e => setMinHeight(e.target.value)} placeholder={"minHeight"}/>
                        <label htmlFor="maxHeightArea">maxHeight</label>
                        <input type="number" id={"maxHeightArea"} value={maxHeight} onChange={e => setMaxHeight(e.target.value)} placeholder={"maxHeight"}/>
                        <label htmlFor="minCreationDateArea">minCreationDate</label>
                        <input type="date" id={"minCreationDateArea"} value={minCreationDate} onChange={e => setMinCreationDate(e.target.value)} placeholder={"minCreationDate"}/>
                        <label htmlFor="maxCreationDateArea">maxCreationDate</label>
                        <input type="date" id={"maxCreationDateArea"} value={maxCreationDate} onChange={e => setMaxCreationDate(e.target.value)} placeholder={"maxCreationDate"}/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onFilterClick}>Filter</button>
                    <button className="btn btn-outline btn-warning" onClick={onResetClick}>Reset</button>
                </div>
            </li>
        </ul>
    </details>
}

export default Filters