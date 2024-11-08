import {useState} from "react";
import {fetchMarineById} from "../utils/space-marines/api";

const Filters = ({setFilters, updateContent}) => {

    const [minId, setMinId] = useState(null)
    const [maxId, setMaxId] = useState(null)
    const [name, setName] = useState(null)
    const [minX, setMinX] = useState(null)
    const [maxX, setMaxX] = useState(null)
    const [minY, setMinY] = useState(null)
    const [maxY, setMaxY] = useState(null)
    const [minHealth, setMinHealth] = useState(null)
    const [maxHealth, setMaxHealth] = useState(null)
    const [loyal, setLoyal] = useState(null)
    const [minHeight, setMinHeight] = useState(null)
    const [maxHeight, setMaxHeight] = useState(null)
    const [category, setCategory] = useState(null)
    const [minCreationDate, setMinCreationDate] = useState(null)
    const [maxCreationDate, setMaxCreationDate] = useState(null)
    const [chapterName, setChapterName] = useState(null)
    const [chapterWorld, setChapterWorld] = useState(null)

    const onFilterClick = () => {
        console.log(name)
        setFilters({
            minId: minId,
            maxId: maxId,
            name: name,
            minX: minX,
            maxX: maxX,
            minY: minY,
            maxY: maxY,
            minHealth: minHealth,
            maxHealth: maxHealth,
            minHeight: minHeight,
            maxHeight: maxHeight,
            loyal: loyal,
            category: category,
            minCreationDate: minCreationDate,
            maxCreationDate: maxCreationDate,
            chapterName: chapterName,
            chapterWorld: chapterWorld
        })
    }

    const onResetClick = () => {
        setMinId("")
        setMaxId("")
        setName("")
        setMinX("")
        setMaxX("")
        setMinY("")
        setMaxY("")
        setMinHealth("")
        setMaxHealth("")
        setMinHeight("")
        setMaxHeight("")
        setCategory("")
        setLoyal("")
        setMinCreationDate("")
        setMaxCreationDate("")
        setChapterName("")
        setChapterWorld("")
        setFilters({
            minId: null,
            maxId: null,
            name: null,
            minX: null,
            maxX: null,
            minY: null,
            maxY: null,
            minHealth: null,
            maxHealth: null,
            minHeight: null,
            maxHeight: null,
            loyal: null,
            category: null,
            minCreationDate: null,
            maxCreationDate: null,
            chapterName: null,
            chapterWorld: null,
        })
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Filters</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
                <div>
                    <div>
                        <label htmlFor="minIdArea">minId</label>
                        <input type="number" id={"minIdArea"} value={minId} onChange={e => setMinId(e.target.value)} placeholder={"minId"}/>
                        <label htmlFor="maxIdArea">maxId</label>
                        <input type="number" id={"maxIdArea"} value={maxId} onChange={e => setMaxId(e.target.value)} placeholder={"maxId"}/>
                        <label htmlFor="name">name pattern</label>
                        <input type="text" id={"name"} value={name} onChange={e => setName(e.target.value)} placeholder={"name"}/>
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
                        <label htmlFor="loyal">loyal</label>
                        <input type="checkbox" id={"loyal"} value={loyal} onChange={e => setLoyal(e.target.checked)} placeholder={"loyal"}/>
                        <label htmlFor="category">category</label>
                        <select className='select' onChange={(e) => setCategory(e.target.value)} value={category} required>
                            {['DREADNOUGHT', 'INCEPTOR', 'SUPPRESSOR', 'TERMINATOR', 'LIBRARIAN'].map((enumCategory) => {
                                return (
                                    <option value={enumCategory} key={enumCategory}>{enumCategory}</option>
                                )
                            })}
                        </select>
                        <label htmlFor="minCreationDateArea">minCreationDate</label>
                        <input type="date" id={"minCreationDateArea"} value={minCreationDate} onChange={e => setMinCreationDate(e.target.value)} placeholder={"minCreationDate"}/>
                        <label htmlFor="maxCreationDateArea">maxCreationDate</label>
                        <input type="date" id={"maxCreationDateArea"} value={maxCreationDate} onChange={e => setMaxCreationDate(e.target.value)} placeholder={"maxCreationDate"}/>
                        <label htmlFor="chapterName">chapter name</label>
                        <input type="text" id={"chapterName"} value={chapterName} onChange={e => setChapterName(e.target.value)} placeholder={"chapterName"}/>
                        <label htmlFor="chapterWorld">chapter world</label>
                        <input type="text" id={"chapterWorld"} value={chapterWorld} onChange={e => setChapterWorld(e.target.value)} placeholder={"chapterWorld"}/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onFilterClick}>Filter</button>
                    <button className="btn btn-outline btn-warning" onClick={onResetClick}>Reset</button>
                </div>
        </ul>
    </details>
}

export default Filters