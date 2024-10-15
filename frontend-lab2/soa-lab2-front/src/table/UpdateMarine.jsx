import {useState} from "react";
import {fetchUpdateById} from "../utils/space-marines/api";

const UpdateMarine = ({updateContent, alertWithMessage}) => {

    const [id, setId] = useState("")
    const [name, setName] = useState("")
    const [coordinates, setCoordinates] = useState({id: 0, x: "", y: ""})
    const [health, setHealth] = useState(1)
    const [loyal, setLoyal] = useState(false)
    const [height, setHeight] = useState("")
    const [category, setCategory] = useState("")
    const [chapter, setChapter] = useState({name: "", world: ""})

    const onUpdateButtonClick = () => {
        console.log(
            name,
            coordinates,
            health,
            loyal,
            height,
            category,
            chapter
        )
        // fetchUpdateById(
        //     id,
        //     {
        //         name: name,
        //         coordinates: coordinates,
        //         health: health,
        //         loyal: loyal,
        //         height: height,
        //         category: category,
        //         chapter: chapter,
        //     }, alertWithMessage).then(() => updateContent())
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Update marine</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <div>
                        <label htmlFor="idArea">id</label>
                        <textarea id={"idArea"} value={id} onChange={e => setId(e.target.value)} placeholder={"id"}/>
                        <label htmlFor="nameArea">name</label>
                        <textarea id={"nameArea"} value={name} onChange={e => setName(e.target.value)} placeholder={"name"}/>
                        <label htmlFor="xArea">coordinates.x</label>
                        <textarea id={"xArea"} value={coordinates.x} onChange={e => setCoordinates({x: e.target.value, y: coordinates.y})}
                                  placeholder={"coordinates.x"}/>
                        <label htmlFor="yArea">coordinates.y</label>
                        <textarea id={"yArea"} value={coordinates.y} onChange={e => setCoordinates({x: coordinates.x, y: e.target.value})}
                                  placeholder={"coordinates.y"}/>
                        <label htmlFor="healthArea">health</label>
                        <textarea id={"healthArea"} value={health} onChange={e => setHealth(e.target.value)} placeholder={"area"}/>
                        <label htmlFor="loyalArea">loyal </label>
                        <input type="checkbox" id={"loyalArea"} value={loyal} onChange={e => setLoyal(e.target.checked)} />
                        <label htmlFor="heightArea">height</label>
                        <textarea id={"heightArea"} value={height} onChange={e => setHeight(e.target.value)} placeholder={"height"}/>
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
                    <button className={"btn btn-outline btn-success"} onClick={onUpdateButtonClick}>Update</button>
                </div>
            </li>
        </ul>
    </details>
}

export default UpdateMarine