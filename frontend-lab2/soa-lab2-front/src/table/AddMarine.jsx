import {useState} from "react";
import {fetchAdd} from "../utils/space-marines/api";

const AddMarine = ({updateContent, alertWithMessage}) => {

    // const [id, setId] = useState("")
    const [name, setName] = useState("myaso")
    const [coordinates, setCoordinates] = useState({x: 1, y: 1})
    const [health, setHealth] = useState(1)
    const [loyal, setLoyal] = useState(false)
    const [height, setHeight] = useState(52)
    const [category, setCategory] = useState("DREADNOUGHT")
    const [chapter, setChapter] = useState({name: "chipi", world: "chapa"})

    const onCreateButtonClick = () => {
        fetchAdd({
            name: name,
            coordinates: coordinates,
            health: health,
            loyal: loyal,
            height: height,
            category: category,
            chapter: chapter,
        }, alertWithMessage).then(() => updateContent())
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Add marine</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <div>
                        <label htmlFor="nameArea">name</label>
                        <textarea id={"nameArea"} value={name} onChange={e => setName(e.target.value)} placeholder={"name"}/>
                        <label htmlFor="xArea">coordinates.x</label>
                        <input type="number" id={"xArea"} value={coordinates.x} onChange={e => setCoordinates({x: e.target.value, y: coordinates.y})}
                               placeholder={"coordinates.x"}/>
                        <label htmlFor="yArea">coordinates.y</label>
                        <input type="number" id={"yArea"} value={coordinates.y} onChange={e => setCoordinates({x: coordinates.x, y: e.target.value})}
                               placeholder={"coordinates.y"}/>
                        <label htmlFor="healthArea">health</label>
                        <input type="number" id={"healthArea"} value={health} onChange={e => setHealth(e.target.value)} placeholder={"area"}/>
                        <label htmlFor="loyalArea">loyal </label><br/>
                        <input type="checkbox" id={"loyalArea"} value={loyal} onChange={e => setLoyal(e.target.checked)} /><br/>
                        <label htmlFor="heightArea">height</label>
                        <input type="number" id={"heightArea"} value={height} onChange={e => setHeight(e.target.value)} placeholder={"height"}/>
                        <label htmlFor="categoryArea">category</label>
                        <select className='select' onChange={(e) => setCategory(e.target.value)} value={category} required>
                            {['DREADNOUGHT', 'INCEPTOR', 'SUPPRESSOR', 'TERMINATOR', 'LIBRARIAN'].map((enumCategory) => {
                                return (
                                    <option value={enumCategory} key={enumCategory}>{enumCategory}</option>
                                )
                            })}
                        </select><br/>
                        <label htmlFor="chapternameArea">chapter.name</label>
                        <textarea id={"chapternameArea"} value={chapter.name} onChange={e => setChapter({
                            name: e.target.value,
                            world: chapter.world,
                        })} placeholder={"chapter.name"}/>
                        <label htmlFor="chapterworldArea">chapter.world</label>
                        <textarea id={"chapterworldArea"} value={chapter.world} onChange={e => setChapter({
                            name: chapter.name,
                            world: e.target.value,
                        })} placeholder={"chapter.world"}/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onCreateButtonClick}>Create</button>
                </div>
            </li>
        </ul>
    </details>
}

export default AddMarine